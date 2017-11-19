package lych.trucks.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lych.trucks.domain.model.Company;
import lych.trucks.domain.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of {@link CompanyService}
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultCompanyService implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public List<Company> fetchAll() {

        log.info("All companies displayed.");

        return companyRepository.findAll();
    }

    @Override
    public Company create(final Company company) {

        log.info("Company created.");

        return companyRepository.save(company);
    }

    @Override
    public Company fetch(final Integer id) {

        log.info("Company displayed.");

        return companyRepository.findOne(id);
    }

    @Override
    public void delete(final Integer id) {

        log.info("Company deleted.");

        companyRepository.delete(id);
    }

    @Override
    public Company update(final Company company) {

        log.info("Company updated.");

        final Company saved = companyRepository.findOne(company.getId());

        company.setAddress(company.getAddress() == null ? saved.getAddress() : company.getAddress());
        company.setCompanyName(company.getCompanyName() == null ? saved.getCompanyName() : company.getCompanyName());
        company.setDrivers(company.getDrivers() == null ? saved.getDrivers() : company.getDrivers());
        company.setEmail(company.getEmail() == null ? saved.getEmail() : company.getEmail());
        company.setTelephoneNumber(company.getTelephoneNumber() == null ? saved.getTelephoneNumber() : company.getTelephoneNumber());

        return companyRepository.save(company);
    }
}
