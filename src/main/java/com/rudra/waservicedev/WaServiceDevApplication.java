package com.rudra.waservicedev;

//import com.rudra.waservicedev.Models.WebhookRequest;
//import com.rudra.waservicedev.Models.WebhookResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//@SpringBootApplication
//public class WaServiceDevApplication {
//
//    public static void main(String[] args) {
//        SpringApplication.run(WaServiceDevApplication.class, args);
//    }
//
//}
//



import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class WaServiceDevApplication {

    public static void main(String[] args) {
        SpringApplication.run(WaServiceDevApplication.class, args);
        System.out.println("hello");

//        WebhookResponse get =

//        System.out.println(handleWebhook);
    }
}


//    @PostMapping("/webhook")
//    public WebhookResponse handleWebhook(@RequestBody WebhookRequest request) {
//
//        String message = request.getMessage_body();
//        System.out.println("Received message from Node.js: " + message);
//
//        System.out.println(request);
//        // Process the incoming message
//        String phone_number_id = request.getPhone_number_id();
//        String from = request.getFrom();
//        String message_body = request.getMessage_body();
//
//        System.out.println("Received message from phone number: " + phone_number_id);
//        System.out.println("From: " + from);
//        System.out.println("Message body: " + message_body);
//
//        // Here you can implement your business logic to generate a response
//        String reply_message;
//        if(message_body.contains("Hello javrvis")){
//            reply_message = "Hello Boss";
//        }else {
//            reply_message = "This is a response from the Spring Boot backend. Mohit is the king";
//        }
//
//        // Create a response object
//        WebhookResponse response = new WebhookResponse();
//        response.setReply_message(reply_message);
//
//        return response;
//    }
//}

//@Getter
//@Setter
//class WebhookRequest {
//    private String phone_number_id;
//    private String from;
//    private String message_body;
//
//    // Getters and setters
//}
//
//@Getter
//@Setter
//class WebhookResponse {
//    private String reply_message;
//
//    // Getters and setters
//}