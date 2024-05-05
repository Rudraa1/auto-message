package com.rudra.waservicedev.Services;

import com.rudra.waservicedev.Models.Product;

import java.util.List;
import java.util.Map;

// ProductServiceInterface.java
public interface ProductServiceInterface {

    void updatePrices(Map<String, Double> priceUpdates);

    List<Product> getAllProducts();

    Product getProductByName(String productName);

    Product addNewProduct(Product product);

    // Other methods for product-related functionalities
}