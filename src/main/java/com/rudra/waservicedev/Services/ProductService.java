package com.rudra.waservicedev.Services;

import com.rudra.waservicedev.Models.Product;
import com.rudra.waservicedev.Repository.InventoryRepository;
import com.rudra.waservicedev.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductService implements ProductServiceInterface{

    @Autowired
    private ProductRepository productRepository;

    private InventoryRepository inventoryRepository;

    @Override
    public void updatePrices(Map<String, Double> priceUpdates) {
        for (Map.Entry<String, Double> entry : priceUpdates.entrySet()) {
            String productName = entry.getKey();
            Double price = entry.getValue();
            Product product = productRepository.findByProductName(productName);
            if (product != null) {
                product.setPrice(price);
                productRepository.save(product);
            }
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductByName(String productName) {
        return productRepository.findByProductName(productName);
    }

    @Override
    public Product addNewProduct(Product product) {
        inventoryRepository.save(product);

        return productRepository.save(product);
    }

    // Other methods for product-related functionalities
}
