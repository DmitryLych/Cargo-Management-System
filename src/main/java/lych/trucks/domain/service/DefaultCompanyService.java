package lych.trucks.domain.service;

import lombok.RequiredArgsConstructor;
import lych.trucks.application.security.service.UserService;
import lych.trucks.domain.model.Company;
import lych.trucks.domain.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link CompanyService}.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultCompanyService implements CompanyService {

    private final CompanyRepository companyRepository;

    private final UserService userService;

    @Override
    public List<Company> fetchAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company createCompany(final Integer userId, final Company company) {
        validateCompany(company);

        company.setUser(userService.fetchUser(userId));
        return companyRepository.save(company);
    }

    @Override
    public Company fetchCompany(final Integer id) {
        return Optional.ofNullable(companyRepository.findOne(id))
                .orElseThrow(() -> new IllegalArgumentException("Can`t find Company by Id."
                        + " Company with this Id: '" + id + "' not exist."));
    }

    @Override
    public Company deleteCompany(final Integer id) {

        final Company company = fetchCompany(id);

        companyRepository.delete(id);

        return company;
    }

    @Override
    @SuppressWarnings("PMD.NPathComplexity")
    public Company updateCompany(final Company company) {
        validateCompany(company);

        final Company saved = fetchCompany(company.getId());

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

        return Optional.ofNullable(companyRepository.findByCompanyName(companyName))
                .orElseThrow(() -> new IllegalArgumentException("Can`t find Company by Company name."
                        + "Company with this Company name: '" + companyName
                        + "' not exist."));
    }

    @Override
    public boolean canAccess(final Integer userId, final Integer companyId) {

        final Company company = Optional.ofNullable(fetchCompany(companyId))
                .orElseThrow(() -> new AccessDeniedException("Access Denied!!!"));

        if (!company.getUser().getId().equals(userId)
                && userService.fetchUser(userId).getAuthorities().stream().noneMatch(authority -> "Admin"
                .equalsIgnoreCase(authority.toString()))) {
            throw new AccessDeniedException("Access Denied!!!");
        }

        return true;
    }

    private static void validateCompany(final Company company) {
        if (company == null) {
            throw new IllegalStateException("Company can`t be null.");
        }
    }
}
