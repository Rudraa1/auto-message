package com.rudra.waservicedev.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_no")
    private String orderNo;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "quantity")
    private double quantity;

    @Column(name = "price_per_kg")
    private double pricePerKg;

    @Column(name = "delivery_terms")
    private String deliveryTerms;

    @Column(name = "destination")
    private String destination;

    @Column(name = "payment_terms")
    private String paymentTerms;

    @Column(name = "dispatch_date")
    private String dispatchDate;

    @Column(name = "estimated_transit_days")
    private int estimatedTransitDays;

    private String CustomerName;

    private String CustomerEmail;

    private String Status;

    // Constructors, getters, and setters

    public Order() {
    }

    public Order(String orderNo, String companyName, String product, double quantity, double pricePerKg,
                 String deliveryTerms, String destination, String paymentTerms, String dispatchDate, int estimatedTransitDays) {
        this.orderNo = orderNo;
        this.companyName = companyName;
        this.productName = product;
        this.quantity = quantity;
        this.pricePerKg = pricePerKg;
        this.deliveryTerms = deliveryTerms;
        this.destination = destination;
        this.paymentTerms = paymentTerms;
        this.dispatchDate = dispatchDate;
        this.estimatedTransitDays = estimatedTransitDays;
    }

    // Repeat the same pattern for other fields
}


//// Order.java
//@Entity
//public class Order {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    private Product product;
//
//    private int quantity;
//    private String customerName;
//    private String status;
//
//    // getters and setters
//}