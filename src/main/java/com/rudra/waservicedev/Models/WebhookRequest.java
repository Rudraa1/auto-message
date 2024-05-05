package com.rudra.waservicedev.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebhookRequest {
    private String phone_number_id;
    private String from;
    private String message_body;
}
