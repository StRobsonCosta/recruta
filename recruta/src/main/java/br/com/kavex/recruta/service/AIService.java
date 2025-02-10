package br.com.kavex.recruta.service;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AIService {

    public String generateInterviewQuestion(String area) {
        return "Qual é a diferença entre monólito e microsserviços?"; //MOCK
    }
}
