package lych.trucks.domain.service;

import lombok.RequiredArgsConstructor;
import lych.trucks.domain.model.Company;
import lych.trucks.domain.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultCompanyService implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public List<Company> fetchAll() {
        return companyRepository.findAll();
    }

    @Override
    public Company create(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company fetch(Integer id) {
        return companyRepository.findOne(id);
    }

    @Override
    public void delete(Integer id) {
        companyRepository.delete(id);
    }

    @Override
    public Company update(Company company) {
        return companyRepository.save(company);
    }
}
