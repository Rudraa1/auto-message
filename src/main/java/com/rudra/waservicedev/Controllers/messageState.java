//import com.rudra.waservicedev.Models.Message;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/messages")
//public class MessageController {
//
//    @Autowired
//    private ConversationService conversationService;
//
//    @PostMapping("/receive")
//    public ResponseEntity<Void> receiveMessage(@RequestBody Message message) {
//        conversationService.handleMessage(message);
//        return ResponseEntity.ok().build();
//    }
//}
//
//@Service
//public class ConversationService {
//
//    @Autowired
//    private ConversationStateRepository conversationStateRepository;
//
//    @Autowired
//    private WhatsAppService whatsAppService; // Service to interact with WhatsApp API
//
//    public void handleMessage(Message message) {
//        // Fetch the current state from the database
//        ConversationState state = conversationStateRepository.findByWhatsAppNumber(message.getFrom());
//
//        if (state == null) {
//            // Handle new conversation
//            state = new ConversationState(message.getFrom(), "INITIAL");
//            conversationStateRepository.save(state);
//        }
//
//        switch (state.getState()) {
//            case "INITIAL":
//                handleInitialMessage(message, state);
//                break;
//            case "ASKING_PRODUCT":
//                handleProductQuery(message, state);
//                break;
//            case "NEGOTIATION":
//                forwardToEmployee(message);
//                break;
//            case "POST_NEGOTIATION":
//                handlePostNegotiation(message, state);
//                break;
//            // Add more cases as needed
//        }
//
//        conversationStateRepository.save(state);
//    }
//
//    private void handleInitialMessage(Message message, ConversationState state) {
//        if (message.getBody().equalsIgnoreCase("order")) {
//            whatsAppService.sendMessage(message.getFrom(), "Okay, which product you want?");
//            state.setState("ASKING_PRODUCT");
//        } else {
//            whatsAppService.sendMessage(message.getFrom(), "Hi, how can we help you?");
//        }
//    }
//
//    private void handleProductQuery(Message message, ConversationState state) {
//        whatsAppService.sendMessage(message.getFrom(), "Okay, here is the price list for product " + message.getBody() + ": ...");
//        state.setState("NEGOTIATION");
//        // Notify employee
//        notifyEmployee(message.getFrom());
//    }
//
//    private void handlePostNegotiation(Message message, ConversationState state) {
//        if (productAvailable(message)) {
//            whatsAppService.sendMessage(message.getFrom(), "Your Order is Confirmed. Pls cross-check below details: ...");
//        } else {
//            whatsAppService.sendMessage(message.getFrom(), "Product not available in desired quantity.");
//        }
//        state.setState("COMPLETION");
//    }
//
//    private void notifyEmployee(String whatsappNumber) {
//        // Notify the employee to take over the conversation via WhatsApp
//        whatsAppService.sendMessage(whatsappNumber, "An employee will assist you shortly.");
//    }
//
//    private void forwardToEmployee(Message message) {
//        // Implement forwarding message to employee's WhatsApp
//        String employeeNumber = "+employeeNumber"; // Replace with actual employee number
//        whatsAppService.forwardMessageToEmployee(message, employeeNumber);
//    }
//
//    private boolean productAvailable(Message message) {
//        // Implement product availability check
//        return true; // Example logic
//    }
//}
