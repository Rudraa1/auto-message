package com.rudra.waservicedev.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {

    private Long id;
    private String productName;
    private int quantity;
    private String customerName;
    private String customerPhone;

}