package com.rudra.waservicedev.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {

    private String from;
    private String body;

    // Constructors, getters, and setters

    public Message() {}

    public Message(String from, String body) {
        this.from = from;
        this.body = body;
    }
}
