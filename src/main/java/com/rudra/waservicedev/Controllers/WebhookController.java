package com.rudra.waservicedev.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rudra.waservicedev.Models.Order;
import com.rudra.waservicedev.Models.WebhookRequest;
import com.rudra.waservicedev.Models.WebhookResponse;
import com.rudra.waservicedev.Services.OrderService;
import com.rudra.waservicedev.Services.ResponseService;
import org.aspectj.weaver.ast.Or;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/webhook")
public class WebhookController {

    private final ResponseService responseService;

    private final OrderService orderService;

    public WebhookController(ResponseService responseService, OrderService orderService) {
        this.responseService = responseService;
        this.orderService = orderService;
    }

    @PostMapping("/webhooks")
    public WebhookResponse Response(@RequestBody WebhookRequest request) {
        return responseService.Response(request);
    }

//    @PostMapping("/getOrders")
//    public WebhookResponse getAllOrders() {
//
//        List<Order> reply_message = orderService.getAllOrders();
//
//        WebhookResponse response = new WebhookResponse();
//        response.setReply_message(reply_message.toString());
//        return response;
//
////        return orderService.getAllOrders();
//    }

    @PostMapping("/getOrders")
    public WebhookResponse getAllOrders() {
        WebhookResponse orders = orderService.getAllOrders();

        WebhookResponse response = new WebhookResponse();
        // Convert the list of orders to JSON string using Jackson ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        try {
            String ordersJson = mapper.writeValueAsString(orders);
            response.setReply_message(ordersJson);
        } catch (JsonProcessingException e) {
            // Handle JSON processing exception
            e.printStackTrace();
            response.setReply_message("Error processing orders");
        }
        return response;
    }


//    @PostMapping("/create")
//    public WebhookResponse createOrder(@RequestBody Order order) {
//        return orderService.createOrder(order);
//    }

    @PostMapping("/msg")
    public WebhookResponse msg(){
        String reply_message = "helloo";

        WebhookResponse response = new WebhookResponse();
        response.setReply_message(reply_message);
        return response;
    }

}