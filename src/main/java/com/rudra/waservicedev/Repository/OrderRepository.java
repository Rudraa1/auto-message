package com.rudra.waservicedev.Repository;

import com.rudra.waservicedev.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Order save(Order product);

}
