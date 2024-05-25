package com.rudra.waservicedev.Services;

import com.rudra.waservicedev.Models.PriceOption;
import com.rudra.waservicedev.Models.Product;
import com.rudra.waservicedev.Models.WebhookResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

// ProductServiceInterface.java

public interface ProductServiceInterface {

    void updatePrices(Map<String, Double> priceUpdates);

    List<Product> getAllProducts();

    Product getProductByName(String productName);

    Product addNewProduct(Product product);

    WebhookResponse getPriceOptions(String productName);

    // Other methods for product-related functionalities
}