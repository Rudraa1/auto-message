package com.rudra.waservicedev.Services;

import com.rudra.waservicedev.Models.Product;

// InventoryServiceInterface.java
public interface InventoryServiceInterface {

    boolean checkAvailability(Product product, int quantity);

    void updateInventory(Product product, int quantity);

    // Other methods for inventory-related functionalities
}