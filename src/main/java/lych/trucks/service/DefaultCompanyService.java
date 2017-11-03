package lych.trucks.service;

import lombok.RequiredArgsConstructor;
import lych.trucks.model.Company;
import lych.trucks.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultCompanyService implements CompanyService{

    private final CompanyRepository companyRepository;

    @Override
    public List<Company> companies() {
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
