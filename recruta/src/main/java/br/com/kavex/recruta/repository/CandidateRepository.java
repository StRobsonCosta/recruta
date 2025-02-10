package br.com.kavex.recruta.repository;

import br.com.kavex.recruta.model.Candidate;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CandidateRepository implements PanacheMongoRepository<Candidate> {
}
