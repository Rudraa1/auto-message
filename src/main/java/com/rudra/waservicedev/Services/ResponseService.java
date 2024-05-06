package com.rudra.waservicedev.Services;

import com.rudra.waservicedev.Models.WebhookRequest;
import com.rudra.waservicedev.Models.WebhookResponse;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {

    public WebhookResponse Response (WebhookRequest request){
        String message = request.getMessage_body();
    System.out.println("Received message from Node.js: " + message);

    System.out.println(request);
        // Process the incoming message
        String phone_number_id = request.getPhone_number_id();
        String from = request.getFrom();
        String message_body = request.getMessage_body();

    System.out.println("Received message from phone number: " + phone_number_id);
    System.out.println("From: " + from);
    System.out.println("Message body: " + message_body);

        // Here you can implement your business logic to generate a response
        String reply_message;
    if(message_body.contains("Hello system")){
            reply_message = "Hello Boss";
        }else {
            reply_message = "This is a response from the Spring Boot backend.";
        }

        // Create a response object
        WebhookResponse response = new WebhookResponse();
    response.setReply_message(reply_message);

    return response;
    }
}
