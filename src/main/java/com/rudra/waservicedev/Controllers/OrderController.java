package com.rudra.waservicedev.Controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.rudra.waservicedev.Dtos.OrderRequest;
import com.rudra.waservicedev.Dtos.PriceRequest;
import com.rudra.waservicedev.Models.Order;
import com.rudra.waservicedev.Models.WebhookRequest;
import com.rudra.waservicedev.Models.WebhookResponse;
import com.rudra.waservicedev.Services.OrderService;
import com.rudra.waservicedev.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    private final ProductService productService;

    @Autowired
    public OrderController(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    @GetMapping("/allOrders")
    public WebhookResponse getAllOrders() {
        return orderService.getAllOrders();
    }



    @PostMapping("/placeOrder")
    public ResponseEntity<WebhookResponse> placeOrder(@RequestBody String requestBody) {

        System.out.println(requestBody);

//        String requestBody = "{\"phone_number_id\":\"1234567890\",\"from\":\"1234567890\",\"message_body\":\"Hello, I want to place order for 500kgs of product A\"}";

// Parse the JSON string into a JsonObject
        JsonObject jsonObject = new Gson().fromJson(requestBody, JsonObject.class);

        if (jsonObject.has("phone_number_id")) {
            System.out.println("yesssssss");
        }

// Extract the necessary information from the JsonObject
        String phoneNumber = jsonObject.get("phone_number_id").getAsString();
        String sender = jsonObject.get("from").getAsString();
        String messageBody = jsonObject.get("message_body").getAsString();


        // Extracting product name and quantity from the message body
        String productName = extractProductName(messageBody);
//        int quantity = extractQuantity(messageBody);

        System.out.println(productName);

        // Creating OrderRequest object
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setProductName(productName);
        orderRequest.setQuantity(100);
        orderRequest.setCustomerPhone(sender);

        WebhookResponse order = orderService.placeOrder(orderRequest.getProductName(), orderRequest.getQuantity(), orderRequest.getCustomerName(), orderRequest.getCustomerPhone());
        return ResponseEntity.ok(order);
    }

//     Function to extract productName from the message body using regular expressions
//    private String extractProductName(String messageBody) {
//        // Regular expression pattern to match phrases indicating the product name
////        Pattern pattern = Pattern.compile("place order(?: for)?\\s+(\\S.*)\\s*(?:of)?\\s*(?:quantity)?", Pattern.CASE_INSENSITIVE);
////        Pattern pattern = Pattern.compile("place order(?: for)?\\s+\\S.*\\s*of\\s*(\\S.*)\\.", Pattern.CASE_INSENSITIVE);
////        Pattern pattern = Pattern.compile("place order(?:\\s+for)?(?:\\s+(?:of|for))?\\s*(\\S.*?)\\s*(?:of|\\d+)", Pattern.CASE_INSENSITIVE);
////        Pattern pattern = Pattern.compile("(?:place order|order|ऑर्डर(?: देना| लगा दो)?)\\s*(?:for|of)?\\s*(\\S.*?)\\s*(?:of|for|\\d+|१|१\\.\\d+|\\d+\\.\\d+|टन)?", Pattern.CASE_INSENSITIVE);
////
////        Matcher matcher = pattern.matcher(messageBody);
////
////
////        // Check if a match is found
////        if (matcher.find()) {
////            // Extract the product name from the first capturing group
////            return matcher.group(1).trim();
////        } else {
////            // Handle case where no match is found
////            throw new IllegalArgumentException("Product name not found in message body");
////        }
//}

    private String extractProductName(String messageBody) {
        // Define an array of regular expressions for each known pattern
        String[] patterns = {
                "place order(?:\\s+for)?(?:\\s+(?:of|for))?\\s*(\\S.*?)\\s*(?:of|\\d+)", // Pattern 1
                "place order(?:\\s+for)?\\s+(\\S.*)",
                "order(?:\\s+for)?\\s+(\\S.*?)\\s+(?:of|for)?\\s*(\\d+)",
                "I want to place order(?:\\s+of)?\\s+(\\d+) tons?\\s+(?:for)?\\s+(\\S.*)",
                "I want to place order(?:\\s+of)?\\s+(\\S.*)\\s+(?:for)?\\s+(\\d+) ton",
                "प्रोडक्ट ए का ऑर्डर\\s+(\\d+)\\s+(\\S.*)",
                "ऑर्डर\\s+(\\S+)"
                // Add more patterns here as needed
        };

        // Iterate through compiled patterns to find a match
        for (String patternStr : patterns) {
            // Compile each pattern
            Pattern pattern = Pattern.compile(patternStr, Pattern.CASE_INSENSITIVE);
            // Match the pattern in the message body
            Matcher matcher = pattern.matcher(messageBody);
            if (matcher.find()) {
                // Match found, extract the product name and return it
                return matcher.group(1).trim();
            }
        }

//         Handle case where no match is found
        throw new IllegalArgumentException("Product name not found in message body");

    }



    // Function to extract quantity from the message body
    private int extractQuantity(String messageBody) {

        Pattern quantityPattern = Pattern.compile("\\b(\\d+)\\s*(kgs|kg|g|grams|tons|ton)\\b", Pattern.CASE_INSENSITIVE);
        Matcher quantityMatcher = quantityPattern.matcher(messageBody);

        int quantity = 0;
        if (quantityMatcher.find()) {
            quantity = Integer.parseInt(quantityMatcher.group(1));
            // You may also capture the unit (e.g., kg, grams) if needed
        }

        System.out.println("Quantity: " + quantity);
        return quantity;
    }

    @PostMapping("/priceReq")
    public ResponseEntity<WebhookResponse>  priceRequest(@RequestBody  String requestBody){

        System.out.println(requestBody);

//        productService.createProductWithPriceOptions();

        JsonObject jsonObject = new Gson().fromJson(requestBody, JsonObject.class);

        String phoneNumber = jsonObject.get("phone_number_id").getAsString();
        String sender = jsonObject.get("from").getAsString();
        String messageBody = jsonObject.get("message_body").getAsString();


        String productName = extractProductName(messageBody);

        int orderQuantity = extractQuantity(messageBody);



        PriceRequest priceRequest = new PriceRequest();

        priceRequest.setProductName(productName);
        priceRequest.setQuantity(orderQuantity);


        WebhookResponse priceReq = productService.getPriceOptions(priceRequest.getProductName());

        return ResponseEntity.ok(priceReq);
    }

    // Other methods as needed
}
