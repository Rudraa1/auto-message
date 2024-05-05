package com.rudra.waservicedev.Services;

import com.rudra.waservicedev.Models.Invoice;
import com.rudra.waservicedev.Models.Order;
import com.rudra.waservicedev.Repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService implements InvoiceServiceInterface{
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public void generateInvoice(Order order) {
        Invoice invoice = new Invoice();
        invoice.setOrder(order);
        invoice.setCustomerEmail(order.getCustomerEmail());
        invoiceRepository.save(invoice);
    }

    // Other methods for invoice-related functionalities

}
