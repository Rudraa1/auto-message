package com.rudra.waservicedev.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Entity
public class ConversationState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "whatsapp_number", unique = true, nullable = false)
    private String whatsappNumber;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "context", columnDefinition = "jsonb")
    @Convert(converter = com.example.whatsappbot.model.HashMapConverter.class)
    private Map<String, String> context = new HashMap<>();

    // Constructors, getters, and setters

    public ConversationState() {}

    public ConversationState(String whatsappNumber, String state) {
        this.whatsappNumber = whatsappNumber;
        this.state = state;
    }
}
