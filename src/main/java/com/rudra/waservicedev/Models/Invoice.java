package com.rudra.waservicedev.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// Invoice.java
@Getter
@Setter
@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Order order;
    private String customerEmail;
}