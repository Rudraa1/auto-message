package com.rudra.waservicedev.Services;

import com.rudra.waservicedev.Models.ConversationState;
import com.rudra.waservicedev.Models.Message;
import com.rudra.waservicedev.Models.WebhookResponse;
import com.rudra.waservicedev.Repository.ConversationStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ConversationService implements ConversationServiceInterface {

    @Autowired
    private ConversationStateRepository conversationStateRepository;

    public WebhookResponse handleMessage(Message message) {
        // Fetch the current state from the database
        ConversationState state = conversationStateRepository.findByWhatsappNumber(message.getFrom());

        if (state == null) {
            // Handle new conversation
            state = new ConversationState(message.getFrom(), "INITIAL");
            conversationStateRepository.save(state);
        }

        String replyMessage = "";
        switch (state.getState()) {
            case "INITIAL":
                replyMessage = handleInitialMessage(message, state);
                break;
            case "ASKING_PRODUCT":
                replyMessage = handleProductQuery(message, state);
                break;
            case "NEGOTIATION":
                replyMessage = handleNegotiation(message, state);
                break;
            case "POST_NEGOTIATION":
                replyMessage = handlePostNegotiation(message, state);
                break;
            // Add more cases as needed
        }

        conversationStateRepository.save(state);

        WebhookResponse response = new WebhookResponse();
        response.setReply_message(replyMessage);
        return response;
    }

    private String handleInitialMessage(Message message, ConversationState state) {
        String reply;
        if (message.getBody().equalsIgnoreCase("order")) {
            reply = "Okay, which product you want?";
            state.setState("ASKING_PRODUCT");
        } else {
            reply = "Hi, how can we help you?";
        }
        return reply;
    }

    private String handleProductQuery(Message message, ConversationState state) {
        String reply = "Okay, here is the price list for product " + message.getBody() + ": ...";
        state.setState("NEGOTIATION");
        // Notify employee
        notifyEmployee(message.getFrom());
        return reply;
    }

    private String handleNegotiation(Message message, ConversationState state) {
        String reply;
        if (message.getBody().startsWith("price:")) {
            // Extract and store negotiated price
            String negotiatedPrice = message.getBody().split(":")[1].trim();
            Map<String, String> context = state.getContext();
            context.put("negotiatedPrice", negotiatedPrice);
            state.setContext(context);
            reply = "Price recorded: " + negotiatedPrice + ". Type 'back to bot' to continue.";
        } else if (message.getBody().equalsIgnoreCase("back to bot")) {
            state.setState("POST_NEGOTIATION");
            reply = handlePostNegotiation(message, state);
        } else {
            // Forward message to employee
            forwardToEmployee(message);
            reply = ""; // No reply from bot when employee is handling
        }
        return reply;
    }

    private String handlePostNegotiation(Message message, ConversationState state) {
        String negotiatedPrice = state.getContext().get("negotiatedPrice");
        String reply;
        if (productAvailable(message)) {
            reply = "Your Order is Confirmed. Price: " + negotiatedPrice + ". Pls cross-check below details: ...";
        } else {
            reply = "Product not available in desired quantity.";
        }
        state.setState("COMPLETION");
        return reply;
    }

    private void notifyEmployee(String whatsappNumber) {
        // Notify the employee to take over the conversation via WhatsApp
        // This could be a message sent to a group or a specific employee's WhatsApp number
    }

    private void forwardToEmployee(Message message) {
        // Implement forwarding message to employee's WhatsApp
        String employeeNumber = "+employeeNumber"; // Replace with actual employee number
        // Send the message to the employee's WhatsApp number
    }

    private boolean productAvailable(Message message) {
        // Implement product availability check
        return true; // Example logic
    }
}
