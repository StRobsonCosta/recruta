package br.com.kavex.recruta.service;

import br.com.kavex.recruta.dto.BotResponse;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AIService {

    public String generateInterviewQuestion(String area) {
        return "Qual é a diferença entre monólito e microsserviços?"; //MOCK
    }

    public BotResponse getFeedback(String message) { //parece não estar certo ter um método desse aki.
        return null;
    }
}
