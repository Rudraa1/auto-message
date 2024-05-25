package com.rudra.waservicedev.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class PriceOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double quantity; // Quantity in tons
    private double price;    // Price in Rs
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
//    private double discountedPrice;

    public PriceOption(double v, double v1, Product product) {
    }

    public PriceOption() {

    }
}
