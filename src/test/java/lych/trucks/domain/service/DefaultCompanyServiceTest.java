package lych.trucks.domain.service;

import lych.trucks.domain.model.Company;
import lych.trucks.domain.repository.CompanyRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCompanyServiceTest {

    @InjectMocks
    private DefaultCompanyService companyService;

    @Mock
    private CompanyRepository companyRepository;

    private static final Integer COMPANY_ID = 1;

    private Company company;

    private static final Integer INCORRECT_COMPANY_ID = 2;

    private static final String COMPANY_NAME_CONTENT = "company name";

    @Before
    public void setUp() {
        company = new Company();
        company.setCompanyName(COMPANY_NAME_CONTENT);
        company.setId(COMPANY_ID);
    }

    @Test
    public void create() {

        when(companyRepository.save(company)).thenReturn(company);

        final Company saved = companyService.createCompany(company);

        verify(companyRepository).save(company);

        assertThat(saved.getCompanyName(), is(COMPANY_NAME_CONTENT));
    }

    @Test(expected = IllegalStateException.class)
    public void createCompany_call_nullCompany_expect_IllegalState() {
        companyService.createCompany(null);
    }

    @Test
    public void fetch() {
        when(companyRepository.findOne(COMPANY_ID)).thenReturn(company);

        final Company fetched = companyService.fetchCompany(COMPANY_ID);

        verify(companyRepository).findOne(COMPANY_ID);

        assertThat(fetched.getCompanyName(), is(COMPANY_NAME_CONTENT));
    }

    @Test(expected = IllegalArgumentException.class)
    public void fetchCompany_callIncorrectId_expect_IllegalArgument() {
        companyService.fetchCompany(INCORRECT_COMPANY_ID);
    }

    @Test
    public void delete() {

        when(companyRepository.findOne(COMPANY_ID)).thenReturn(company);

        final Company deleted = companyService.deleteCompany(COMPANY_ID);

        verify(companyRepository).delete(COMPANY_ID);
        verify(companyRepository).findOne(COMPANY_ID);

        assertThat(deleted.getCompanyName(), is(COMPANY_NAME_CONTENT));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteCompany_callIncorrectId_expect_IllegalArgument() {
        companyService.deleteCompany(INCORRECT_COMPANY_ID);
    }

    @Test
    public void update() {

        when(companyRepository.findOne(COMPANY_ID)).thenReturn(company);

        final String content = "email";
        company.setEmail(content);

        when(companyRepository.save(company)).thenReturn(company);

        final Company updated = companyService.updateCompany(company);

        verify(companyRepository).findOne(COMPANY_ID);
        verify(companyRepository).save(company);

        assertThat(updated.getEmail(), is(content));
    }

    @Test(expected = IllegalStateException.class)
    public void updateCompany_callNullCompany_expect_IllegalState() {
        companyService.updateCompany(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateCompany_callIncorrectId_expect_IllegalState() {
        companyService.updateCompany(company);
    }

    @Test
    public void fetchByCompanyName() {
        when(companyRepository.findByCompanyName(COMPANY_NAME_CONTENT)).thenReturn(company);

        final Company fetchedCompany = companyService.fetchCompanyByCompanyName(COMPANY_NAME_CONTENT);

        verify(companyRepository).findByCompanyName(COMPANY_NAME_CONTENT);

        assertThat(fetchedCompany.getCompanyName(), is(COMPANY_NAME_CONTENT));
    }

    @Test(expected = IllegalArgumentException.class)
    public void fetchByCompanyName_callIncorrectCompanyName_expect_IllegalArgument() {
        companyService.fetchCompanyByCompanyName(COMPANY_NAME_CONTENT);
    }
}
