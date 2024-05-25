package com.rudra.waservicedev.Services;

import com.rudra.waservicedev.Models.Order;
import com.rudra.waservicedev.Models.WebhookRequest;
import com.rudra.waservicedev.Models.WebhookResponse;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

// OrderServiceInterface.java
public interface OrderServiceInterface {

    WebhookResponse placeOrder(String productName, int quantity, String customerName, String customerPhone);

    WebhookResponse priceRequest(String productName);

    // Other methods for order-related functionalities
}