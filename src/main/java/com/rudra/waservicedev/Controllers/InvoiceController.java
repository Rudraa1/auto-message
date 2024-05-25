package com.rudra.waservicedev.Controllers;

import com.rudra.waservicedev.Models.WebhookResponse;
import com.rudra.waservicedev.Services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// InvoiceController.java
@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping()
    public WebhookResponse getAllOrders() {
        WebhookResponse response = new WebhookResponse();
        response.setReply_message("PO sent to email");
        return response;
    }

    // Endpoints for invoice-related functionalities
}