package com.rudra.waservicedev.Controllers;

import com.rudra.waservicedev.Models.WebhookResponse;
import com.rudra.waservicedev.Models.Message;
import com.rudra.waservicedev.Services.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private ConversationService conversationService;

    @PostMapping("/receive")
    public ResponseEntity<WebhookResponse> receiveMessage(@RequestBody Message message) {
        WebhookResponse response = conversationService.handleMessage(message);
        return ResponseEntity.ok(response);
    }
}