package com.rudra.waservicedev.Services;

import com.rudra.waservicedev.Models.Customers;
import com.rudra.waservicedev.Models.Order;
import com.rudra.waservicedev.Models.Product;
import com.rudra.waservicedev.Models.WebhookRequest;
import com.rudra.waservicedev.Repository.CustomerRepository;
import com.rudra.waservicedev.Repository.OrderRepository;
import com.rudra.waservicedev.Repository.ProductRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class OrderService implements OrderServiceInterface {

    private final OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private InvoiceService invoiceService;

    private CustomerService customerService;

    private ProductRepository productRepository;

    private ResponseService responseService;

    @Autowired
    public OrderService(OrderRepository orderRepository, CustomerService customerService, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.productRepository = productRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order createOrder(Order order) {
        // Add any additional logic here, such as validation or calculation
        return orderRepository.save(order);
    }



    @Override
    public String placeOrder(String productName, int quantity, String customerName, String customerPhone) {
        // Fetch the product from the product service

        Product product = productService.getProductByName(productName);

        if(customerService.findByPhone(customerPhone) == null){
            throw new RuntimeException("Customer not registered");
        }

        if (product == null) {
            throw new RuntimeException("Product not found");
        }
        if (product.getPrice() == 0) {
            throw new RuntimeException("Price not set for product");
        }

        // Check if the desired quantity is available in inventory
        boolean available = inventoryService.checkAvailability(product, quantity);
        if (available) {
            // Create a new order
            Order order = new Order();
            order.setProductName(product.getProductName());
            order.setQuantity(quantity);
            order.setCustomerName(customerName);
            order.setStatus("Pending"); // Consider setting a more descriptive status

            // Save the order
            orderRepository.save(order);

            // Update inventory
            inventoryService.updateInventory(product, quantity);

            // Generate invoice
            invoiceService.generateInvoice(order);

            // Return confirmation message
            return generateConfirmationMessage(order, productName);
//            return "yes";
        } else {
            throw new RuntimeException("Product not available in desired quantity");
        }
    }

    private String generateConfirmationMessage(Order order, String productName) {

        Product product = productService.getProductByName(productName);

        double price = product.getPrice();

        // Customize confirmation message format
        return "Dear " + order.getCustomerName() + " ji,\n" +
                "Your Order is Confirmed.\n" +
                "Pls cross-check below details:\n\n" +
                "Order No: " + order.getId() + "\n" +
                "Name: " + order.getCustomerName() + ", " + "\n" +
                "Product: " + order.getProductName() + "\n" +
                "Qty: " + order.getQuantity() + "\n" +
                "Price: " + price + " + GST - TDS" + "\n" +
                "Delivery Terms: " + order.getDeliveryTerms() + "\n" +
                "Destination: " + order.getDestination() + "\n" +
                "Payment: " + order.getPaymentTerms() + "\n" +
                "Dispatch Date: " + order.getDispatchDate() + " (TODAY)\n" +
                "Estimated Transit Days: " + order.getEstimatedTransitDays() + "\n\n" +
                "▪️ Late Payment will attract an Interest cost of 36%pa, from 1st day of delay\n\n" +
                "Note: In-case of any changes in Billing, pls WhatsApp on https://wa.me/+918821900100";
    }

    // Other methods for order-related functionalities
}


