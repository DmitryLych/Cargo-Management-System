package lych.trucks.domain.service;

import lych.trucks.domain.model.Company;

import java.util.List;

/**
 * Service for {@link Company} work with database.
 */
public interface CompanyService {

    /**
     * Method for fetch all companies.
     *
     * @return list of companies.
     */
    List<Company> fetchAllCompanies();

    /**
     * Method for create company.
     *
     * @param userId a user id.
     * @param company Company company.
     * @return created Company company.
     */
    Company createCompany(Integer userId, Company company);

    /**
     * Method for fetch company by id.
     *
     * @param id Company id.
     * @return Company company.
     */
    Company fetchCompany(Integer id);

    /**
     * Method for delete company by id.
     *
     * @param id Company id.
     * @return deleted company.
     */
    Company deleteCompany(Integer id);

    /**
     * Method for update company.
     *
     * @param company Company company.
     * @return updated Company company.
     */
    Company updateCompany(Company company);

    /**
     * Method for fetch company by company name.
     *
     * @param companyName Company companyName.
     * @return company which found.
     */
    Company fetchCompanyByCompanyName(String companyName);
}
