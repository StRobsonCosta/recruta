package br.com.kavex.recruta.service;

import br.com.kavex.recruta.dto.BotResponse;
import br.com.kavex.recruta.dto.UserMsgRequest;
import br.com.kavex.recruta.model.Candidate;
import br.com.kavex.recruta.model.InterviewQuestion;
import br.com.kavex.recruta.repository.CandidateRepository;
import br.com.kavex.recruta.repository.InterviewQuestionRepository;
import jakarta.enterprise.context.ApplicationScoped;

import jakarta.inject.Inject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

@ApplicationScoped
public class WhatsAppService {

    @Inject
    CandidateRepository candidateRepository;

    @Inject
    InterviewQuestionRepository questionRepository;

    @Inject
    AIService aiService;

    public void processMessage(UserMsgRequest request) {
        System.out.println("Recebida mensagem: " + request.message);

        // Verificar se o candidato existe pelo userId
        Candidate candidate = candidateRepository.find("userId", request.phone).firstResultOptional()
                .orElseGet(() -> createNewCandidate(request.phone));

        // Buscar a próxima pergunta com base no índice atual
        int currentIndex = candidate.progress;
        Optional<InterviewQuestion> nextQuestion = questionRepository.find("order", currentIndex).firstResultOptional();

        String responseMessage;
        if (nextQuestion.isPresent()) {
            InterviewQuestion question = nextQuestion.get();
            responseMessage = question.getText();
            candidate.progress = currentIndex + 1;
            candidateRepository.update(candidate);
        } else {
            // Se não há mais perguntas, acionar IA para feedback
            BotResponse aiResponse = aiService.getFeedback(request.message);
            responseMessage = aiResponse.responseText();
        }

        // Enviar a resposta (simulação)
        sendWhatsAppMessage(request.phone, responseMessage);
    }

    private Candidate createNewCandidate(String phone) {
        Candidate newCandidate = new Candidate();
        newCandidate.userId = phone;
        newCandidate.progress = 0;
        candidateRepository.persist(newCandidate);
        return newCandidate;
    }

    private void sendWhatsAppMessage(String phone, String message) {
        try {
            String apiUrl = "https://api.whatsapp.com/send"; // Ajuste conforme a API escolhida
            String token = "SEU_TOKEN_AQUI";

            HttpClient client = HttpClient.newHttpClient();
            String payload = String.format("{\"phone\":\"%s\", \"message\":\"%s\"}", phone, message);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .POST(HttpRequest.BodyPublishers.ofString(payload))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("WhatsApp API response: " + response.body());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro ao enviar mensagem pelo WhatsApp.");
        }
    }

}
