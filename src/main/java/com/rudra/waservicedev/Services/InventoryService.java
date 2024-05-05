package com.rudra.waservicedev.Services;

import com.rudra.waservicedev.Models.Inventory;
import com.rudra.waservicedev.Models.Product;
import com.rudra.waservicedev.Repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService implements InventoryServiceInterface{

    @Autowired
    private InventoryRepository inventoryRepository;


    @Override
    public boolean checkAvailability(Product product, int quantity) {
        Inventory inventory = inventoryRepository.findByProduct(product);
        return inventory != null && inventory.getQuantity() >= quantity;
    }

    @Override
    public void updateInventory(Product product, int quantity) {
        Inventory inventory = inventoryRepository.findByProduct(product);
        if (inventory != null) {
            inventory.setQuantity(inventory.getQuantity() - quantity);
            inventoryRepository.save(inventory);
        }

    }
}


