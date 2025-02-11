package br.com.kavex.recruta.service;

import br.com.kavex.recruta.dto.InterviewQuestionDTO;
import br.com.kavex.recruta.model.InterviewQuestion;
import br.com.kavex.recruta.repository.InterviewQuestionRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
@Slf4j
public class InterviewQuestionService {

    @Inject
    InterviewQuestionRepository repository;

    public List<InterviewQuestionDTO> getAllQuestions() {
        log.info("Buscando todas as perguntas de entrevista");
        return repository.listAll().stream()
                .map(q -> new InterviewQuestionDTO(q.getId(), q.getCategory(), q.getQuestion()))
                .collect(Collectors.toList());
    }

    public Optional<InterviewQuestionDTO> getQuestionById(Long id) {
        log.info("Buscando pergunta por ID: {}", id);
        return repository.findByIdOptional(id)
                .map(q -> new InterviewQuestionDTO(q.getId(), q.getCategory(), q.getQuestion()));
    }

    @Transactional
    public void createQuestion(InterviewQuestionDTO questionDTO) {
        log.info("Criando nova pergunta: {}", questionDTO);
        InterviewQuestion question = new InterviewQuestion();
        question.setCategory(questionDTO.getCategory());
        question.setQuestion(questionDTO.getQuestion());
        repository.persist(question);
    }

    @Transactional
    public boolean deleteQuestion(Long id) {
        log.info("Deletando pergunta com ID: {}", id);
        return repository.deleteById(id);
    }
}
