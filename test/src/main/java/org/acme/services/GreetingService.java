package org.acme.services;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingService {
    
    @ConfigProperty(name = "greeting.message")
    String greetingMessage;

    @ConfigProperty(name = "quarkus.profile", defaultValue = "dev")
    String activeProfile;

    public String getGreetingMessage() {
        if ("prod".equals(activeProfile))
            return greetingMessage.toUpperCase();
        return greetingMessage;
    }
}
