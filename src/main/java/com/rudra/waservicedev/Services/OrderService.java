package com.rudra.waservicedev.Services;

import com.rudra.waservicedev.Models.*;
import com.rudra.waservicedev.Repository.CustomerRepository;
import com.rudra.waservicedev.Repository.OrderRepository;
import com.rudra.waservicedev.Repository.ProductRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class OrderService implements OrderServiceInterface {

    private final OrderRepository orderRepository;

    private ProductService productService;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private InvoiceService invoiceService;

    private CustomerService customerService;

    private ProductRepository productRepository;

    private ResponseService responseService;

    @Autowired
    public OrderService(OrderRepository orderRepository, CustomerService customerService, ProductRepository productRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.productRepository = productRepository;
        this.productService = productService;
    }

    public WebhookResponse getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        WebhookResponse response = new WebhookResponse();
        response.setData(orders);
        return response;
    }

    public WebhookResponse createOrder(Order order) {
        // Add any additional logic here, such as validation or calculation
        orderRepository.save(order);
        WebhookResponse response = new WebhookResponse();
        List<Order> orderList = new ArrayList<>();
        orderList.add(order); // Creating a list with the single order object
        response.setData(orderList); // Passing the list of orders
        return response;
    }

    @Override
    public WebhookResponse priceRequest(String productName){
        WebhookResponse response = new WebhookResponse();
        response.setReply_message("yes present");
        return response;
    }

    @Override
    public WebhookResponse placeOrder(String productName, int quantity, String customerName, String customerPhone) {
        // Fetch the product from the product service

//        Product product = productService.getProductByName(productName);
        String reply_message = "hello";
        System.out.println(customerPhone);

        Customers custName = customerService.findByPhone(customerPhone);

        String custname = custName.getCustomerName();

        System.out.println("name"  + custName);

//        if( custName== null){
//            reply_message = "Customer not registered";
//            WebhookResponse response = new WebhookResponse();
//            response.setReply_message(reply_message);
//            return response;
////            throw new RuntimeException("Customer not registered");
//        }

//        if (product == null) {
//            reply_message = "Product not found";
//            WebhookResponse response = new WebhookResponse();
//            response.setReply_message(reply_message);
//            return response;
////            throw new RuntimeException("Product not found");
//        }
//        if (product.getPrice() == 0) {
//            reply_message = "Price not set for product";
//            WebhookResponse response = new WebhookResponse();
//            response.setReply_message(reply_message);
//            return response;
////            throw new RuntimeException("Price not set for product");
//        }

        // Check if the desired quantity is available in inventory
//        boolean available = inventoryService.checkAvailability(product, quantity);
        if (true) {
            // Hard code testing
//            Order order = new Order();
//            order.setProductName("polymer");
//            order.setQuantity(10);
//            order.setCustomerName("Bansal");
//            order.setStatus("Pending"); // Consider setting a more descriptive status

            Order order = new Order();
//            order.setProductName(product.getProductName());
            order.setQuantity(quantity);
            order.setCustomerName(custname);
            order.setStatus("Pending");

            // Save the order
            orderRepository.save(order);

            // Update inventory
//            inventoryService.updateInventory(product, quantity);

            // Generate invoice
            invoiceService.generateInvoice(order);

            // Return confirmation message
            return generateConfirmationMessage(order, productName);
        } else {
            throw new RuntimeException("Product not available in desired quantity");
        }
    }

    private WebhookResponse generateConfirmationMessage(Order order, String productName) {

        Product product = productService.getProductByName(productName);

        double price = 100;

//        double price = product.getPrice();

//        String confirmationMessage = "Dear " + "Bansal" + " ji,\n" +
//                "Your Order is Confirmed.\n" +
//                "Pls cross-check below details:\n\n" +
//                "Order No: " + 1 + "\n" +
//                "Name: " + "Bansal Polymer" + ", " + "\n" +
//                "Product: " + "Polymer" + "\n" +
//                "Qty: " + order.getQuantity() + "\n" +
//                "Price: " + price + " + GST - TDS" + "\n" +
//                "Delivery Terms: " + "3 Days" + "\n" +
//                "Destination: " + "Indore" + "\n" +
//                "Payment: " + "Cash" + "\n" +
//                "Dispatch Date: " + "01.01.2024" + " (TODAY)\n" +
//                "Estimated Transit Days: " + 3 + "\n\n" +
//                "▪️ Late Payment will attract an Interest cost of 36%pa, from 1st day of delay\n\n" +
//                "Note: In-case of any changes in Billing, pls WhatsApp on https://wa.me/+918821900100";

        // Customize confirmation message format
        String confirmationMessage = "Dear " + order.getCustomerName() + " ji,\n" +
                "Your Order is Confirmed.\n" +
                "Pls cross-check below details:\n\n" +
                "Order No: " + order.getId() + "\n" +
                "Name: " + order.getCustomerName() + ", " + "\n" +
//                "Product: " + order.getProductName() + "\n" +
                "Qty: " + order.getQuantity() + "\n" +
                "Price: " + price + " + GST - TDS" + "\n" +
                "Delivery Terms: " + order.getDeliveryTerms() + "\n" +
                "Destination: " + order.getDestination() + "\n" +
                "Payment: " + order.getPaymentTerms() + "\n" +
                "Dispatch Date: " + order.getDispatchDate() + " (TODAY)\n" +
                "Estimated Transit Days: " + order.getEstimatedTransitDays() + "\n\n" +
                "▪️ Late Payment will attract an Interest cost of 36%pa, from 1st day of delay\n\n" +
                "Note: In-case of any changes in Billing, pls WhatsApp on https://wa.me/+918821900100";

        WebhookResponse response = new WebhookResponse();
        response.setReply_message(confirmationMessage);
        return response;
    }

    // Other methods for order-related functionalities
}
