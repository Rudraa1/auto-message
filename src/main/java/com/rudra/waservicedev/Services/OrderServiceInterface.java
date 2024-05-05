package com.rudra.waservicedev.Services;

import com.rudra.waservicedev.Models.Order;
import com.rudra.waservicedev.Models.WebhookRequest;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

// OrderServiceInterface.java
public interface OrderServiceInterface {

    String placeOrder(String productName, int quantity, String customerName, String customerPhone);

    // Other methods for order-related functionalities
}