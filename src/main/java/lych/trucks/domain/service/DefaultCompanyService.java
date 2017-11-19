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

        final Company saved = companyRepository.findOne(company.getId());

        company.setAddress(company.getAddress() == null ? saved.getAddress() : company.getAddress());
        company.setCompanyName(company.getCompanyName() == null ? saved.getCompanyName() : company.getCompanyName());
        company.setDrivers(company.getDrivers() == null ? saved.getDrivers() : company.getDrivers());
        company.setEmail(company.getEmail() == null ? saved.getEmail() : company.getEmail());
        company.setTelephoneNumber(company.getTelephoneNumber() == null ? saved.getTelephoneNumber() : company.getTelephoneNumber());

        return companyRepository.save(company);
    }
}
