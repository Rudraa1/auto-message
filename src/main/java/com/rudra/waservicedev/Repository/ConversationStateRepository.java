package com.rudra.waservicedev.Repository;

import com.rudra.waservicedev.Models.ConversationState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversationStateRepository extends JpaRepository<ConversationState, Long> {
    ConversationState findByWhatsappNumber(String whatsappNumber);
}