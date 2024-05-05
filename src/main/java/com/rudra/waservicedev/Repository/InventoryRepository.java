package com.rudra.waservicedev.Repository;

import com.rudra.waservicedev.Models.Inventory;
import com.rudra.waservicedev.Models.Invoice;
import com.rudra.waservicedev.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// InventoryRepository.java
@Repository
public interface InventoryRepository extends JpaRepository <Inventory, Long> {
    Inventory findByProduct(Product product);

    Inventory save(Product product);
}