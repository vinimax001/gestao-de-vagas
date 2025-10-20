package br.com.viniciusaguiar.modules.candidate.controllers;

import br.com.viniciusaguiar.exceptions.UserFoundException;
import br.com.viniciusaguiar.modules.candidate.CandidateEntity;
import br.com.viniciusaguiar.modules.candidate.CandidateRepository;
import br.com.viniciusaguiar.modules.candidate.useCases.CreateCandidateUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Validated @RequestBody CandidateEntity candidateEntity){
      try{
          var result = this.createCandidateUseCase.execute(candidateEntity);
          return ResponseEntity.ok().body(result);
      }catch (Exception e) {
          return ResponseEntity
                  .badRequest()
                  .body(e.getMessage());
      }
    }
}