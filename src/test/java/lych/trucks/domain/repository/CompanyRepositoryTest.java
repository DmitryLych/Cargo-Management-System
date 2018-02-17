package lych.trucks.domain.repository;

import lych.trucks.domain.model.Company;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CompanyRepositoryTest {

    @Autowired
    private CompanyRepository companyRepository;

    private static final String CONTENT = "company";

    @Before
    public void setUp() {

        final Company company = new Company();

        company.setCompanyName(CONTENT);

        companyRepository.save(company);
    }

    @Test
    public void findByCompanyName() {

        final Company foundCompany = companyRepository.findByCompanyName(CONTENT);

        assertThat(foundCompany.getCompanyName(), is(CONTENT));
    }
}
