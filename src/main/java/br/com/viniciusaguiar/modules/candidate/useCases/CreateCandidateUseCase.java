package br.com.viniciusaguiar.modules.candidate.useCases;

import br.com.viniciusaguiar.exceptions.UserFoundException;
import br.com.viniciusaguiar.modules.candidate.CandidateEntity;
import br.com.viniciusaguiar.modules.candidate.CandidateRepository;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.data.util.ClassUtils.ifPresent;
@Service
public class CreateCandidateUseCase {
@Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity execute(CandidateEntity candidateEntity) {
        this.candidateRepository
        .findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
        .ifPresent((user) -> {
            throw new UserFoundException();
        });
        return this.candidateRepository.save(candidateEntity);
    }
}