package br.com.kavex.recruta.repository;

import br.com.kavex.recruta.model.InterviewQuestion;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class InterviewQuestionRepository implements PanacheRepository<InterviewQuestion> {
}
