package com.rudra.waservicedev.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PriceRequest {

    private Long id;
    private String productName;
    private int quantity;
}
