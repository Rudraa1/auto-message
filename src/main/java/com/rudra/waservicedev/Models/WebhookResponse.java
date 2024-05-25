package com.rudra.waservicedev.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WebhookResponse {

    private String reply_message;

    public void setData(List<Order> orders) {
    }
}