package com.rudra.waservicedev.Repository;

import com.rudra.waservicedev.Models.Customers;
import com.rudra.waservicedev.Models.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Long> {
    Customers findCustomersByCustomerPhone(String customerPhone);

    Customers save(Customers customers);
}
