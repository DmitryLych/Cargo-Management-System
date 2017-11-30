package lych.trucks.domain.service;

import lych.trucks.domain.model.Company;
import lych.trucks.domain.repository.CompanyRepository;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultCompanyServiceTest {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyRepository companyRepository;

    private Integer companyIdContent;

    private static final String COMPANY_NAME_CONTENT = "company name";

    @Before
    public void setUp() {

        companyRepository.deleteAll();

        final Company company = new Company();

        company.setCompanyName(COMPANY_NAME_CONTENT);

        companyIdContent = companyRepository.save(company).getId();
    }

    @Test
    public void create() {

        final String content = "newCompany";

        final Company company = new Company();

        company.setCompanyName(content);

        assertThat(companyService.create(company).getCompanyName(), Is.is(content));
    }

    @Test
    public void fetch() {

        assertThat(companyService.fetch(companyIdContent).getCompanyName(), Is.is(COMPANY_NAME_CONTENT));
    }

    @Test
    public void delete() {

        companyService.delete(companyIdContent);

        assertThat(companyRepository.exists(companyIdContent), Is.is(false));
    }

    @Test
    public void update() {

        final String content = "email";

        final Company companyToUpdate = companyRepository.findOne(companyIdContent);

        companyToUpdate.setEmail(content);

        assertThat(companyService.update(companyToUpdate).getEmail(), Is.is(content));
    }

    @Test
    public void fetchByCompanyName() {

        assertThat(companyService.fetchByCompanyName(COMPANY_NAME_CONTENT).getCompanyName(), Is.is(COMPANY_NAME_CONTENT));
    }
}
