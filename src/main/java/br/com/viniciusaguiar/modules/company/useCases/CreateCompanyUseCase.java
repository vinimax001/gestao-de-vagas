package br.com.viniciusaguiar.modules.company.useCases;

import br.com.viniciusaguiar.exceptions.UserFoundException;
import br.com.viniciusaguiar.modules.company.entities.CompanyEntity;
import br.com.viniciusaguiar.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    public  CompanyEntity execute(CompanyEntity companyEntity){

        this.companyRepository
            .findByNameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
            .ifPresent((user) -> {
                throw new UserFoundException();
            });

        return this.companyRepository.save(companyEntity);
    }
}