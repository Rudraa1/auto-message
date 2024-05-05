package com.rudra.waservicedev.Repository;

import com.rudra.waservicedev.Models.Inventory;
import com.rudra.waservicedev.Models.Invoice;
import com.rudra.waservicedev.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// InventoryRepository.java
@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    // You don't need to override the save method here
    // Spring Data JPA provides a default implementation
}