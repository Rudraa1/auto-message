package com.rudra.waservicedev.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private double Price;
//    private double discountedPrice;
    private double quantity;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<PriceOption> priceOptions;
//    private boolean isDeleted;
}