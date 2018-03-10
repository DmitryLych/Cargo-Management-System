package lych.trucks.application.controller;

import lombok.RequiredArgsConstructor;
import lych.trucks.application.dto.request.CompanyRequest;
import lych.trucks.application.dto.response.CompanyResponse;
import lych.trucks.domain.model.Company;
import lych.trucks.domain.service.CompanyService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Rest controller for {@link Company}.
 */
@RestController
@RequestMapping("/cargo/v1/companies/{userId}")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CompanyController {

    private final CompanyService companyService;

    private final DozerBeanMapper dozerBeanMapper;

    /**
     * Method for create company.
     *
     * @param userId  a user id.
     * @param request CompanyRequest request.
     * @return CompanyResponse response mapped from created company.
     */
    @PostMapping
    public ResponseEntity createCompany(@PathVariable final Integer userId,
                                        @RequestBody final CompanyRequest request) {
        final Company companyToSave = dozerBeanMapper.map(request, Company.class);
        final Company companyToResponse = companyService.createCompany(userId, companyToSave);

        final CompanyResponse response = dozerBeanMapper.map(companyToResponse, CompanyResponse.class);
        return ResponseEntity.ok(response);
    }

    /**
     * Method for display some company.
     *
     * @param companyId Company companyId.
     * @return CompanyResponse response mapped from found company.
     */
    @GetMapping(path = "/{companyId}")
    public ResponseEntity fetchCompany(@PathVariable final Integer companyId) {
        final Company companyToResponse = companyService.fetchCompany(companyId);

        final CompanyResponse response = dozerBeanMapper.map(companyToResponse, CompanyResponse.class);
        return ResponseEntity.ok(response);
    }

    /**
     * Method for update some company.
     *
     * @param request CompanyRequest request.
     * @return CompanyResponse response mapped from updated company.
     */
    @PutMapping
    public ResponseEntity updateCompany(@RequestBody final CompanyRequest request) {
        final Company companyToUpdate = dozerBeanMapper.map(request, Company.class);
        final Company companyToResponse = companyService.updateCompany(companyToUpdate);

        final CompanyResponse response = dozerBeanMapper.map(companyToResponse, CompanyResponse.class);
        return ResponseEntity.ok(response);
    }

    /**
     * Method for delete some company.
     *
     * @param companyId Company companyId.
     * @return CompanyResponse response mapped from deleted company.
     */
    @DeleteMapping(path = "/{companyId}")
    public ResponseEntity deleteCompany(@PathVariable final Integer companyId) {
        final Company companyToResponse = companyService.deleteCompany(companyId);

        final CompanyResponse response = dozerBeanMapper.map(companyToResponse, CompanyResponse.class);
        return ResponseEntity.ok(response);
    }

    /**
     * Method for find all companies.
     *
     * @return List of found companies.
     */
    @GetMapping
    public ResponseEntity fetchAllCompanies() {
        final List<Company> companiesToResponse = companyService.fetchAllCompanies();

        final List<CompanyResponse> response = companiesToResponse.stream()
                .map(company -> dozerBeanMapper.map(company, CompanyResponse.class))
                .collect(toList());
        return ResponseEntity.ok(response);
    }

    /**
     * Method for fetch company by company name.
     *
     * @param companyName {@link Company} companyName.
     * @return {@link CompanyResponse} response mapped from company which found.
     */
    @GetMapping(path = "/companyName/{companyName}")
    public ResponseEntity fetchCompanyByCompanyName(@PathVariable final String companyName) {
        final Company companyToResponse = companyService.fetchCompanyByCompanyName(companyName);

        final CompanyResponse response = dozerBeanMapper.map(companyToResponse, CompanyResponse.class);
        return ResponseEntity.ok(response);
    }
}
