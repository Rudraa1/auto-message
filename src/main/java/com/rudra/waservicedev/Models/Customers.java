package com.rudra.waservicedev.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customers {

    @Id
    private Long custId;

    private String customerName;

    private String companyName;

    private String customerEmail;

    private String customerPhone;
}
