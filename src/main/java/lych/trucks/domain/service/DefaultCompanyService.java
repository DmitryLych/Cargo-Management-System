package lych.trucks.domain.service;

import lombok.RequiredArgsConstructor;
import lych.trucks.domain.model.Company;
import lych.trucks.domain.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of {@link CompanyService}.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultCompanyService implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public List<Company> fetchAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company createCompany(final Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company fetchCompany(final Integer id) {
        return companyRepository.findOne(id);
    }

    @Override
    public Company deleteCompany(final Integer id) {

        final Company company = companyRepository.findOne(id);

        companyRepository.delete(id);

        return company;
    }

    @Override
    @SuppressWarnings("PMD.NPathComplexity")
    public Company updateCompany(final Company company) {

        final Company saved = companyRepository.findOne(company.getId());

        company.setAddress(company.getAddress() == null ? saved.getAddress() : company.getAddress());
        company.setCompanyName(company.getCompanyName() == null ? saved.getCompanyName() : company.getCompanyName());
        company.setDrivers(company.getDrivers() == null ? saved.getDrivers() : company.getDrivers());
        company.setEmail(company.getEmail() == null ? saved.getEmail() : company.getEmail());
        company.setTelephoneNumber(company.getTelephoneNumber() == null ? saved.getTelephoneNumber()
                : company.getTelephoneNumber());

        return companyRepository.save(company);
    }

    @Override
    public Company fetchCompanyByCompanyName(final String companyName) {

        return companyRepository.findByCompanyName(companyName);
    }
}
