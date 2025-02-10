package br.com.kavex.recruta.service;

import br.com.kavex.recruta.dto.UserMsgRequest;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class WhatsAppService {

    public void processMessage(UserMsgRequest request) {
        System.out.println("Recebida mensagem: " + request.message);
    }
}
