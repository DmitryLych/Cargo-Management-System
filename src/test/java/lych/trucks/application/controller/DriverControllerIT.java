package lych.trucks.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lych.trucks.application.dto.request.DriverRequest;
import lych.trucks.domain.model.Company;
import lych.trucks.domain.model.Driver;
import lych.trucks.domain.repository.CompanyRepository;
import lych.trucks.domain.repository.DriverRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DriverControllerIT {

    private MockMvc mockMvc;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    private DriverRequest request;

    private Integer driverId;

    @Autowired
    private CompanyRepository companyRepository;

    private Integer companyId;

    private static final String FIRST_NAME = "first";

    private static final String LAST_NAME = "last";

    private static final Long YEAR_OF_ISSUED = 123L;

    private static final String TELEPHONE_NUMBER = "number";

    private static final String EMAIL = "email";

    private static final String INCORRECT_COMPANY_ID = "150";

    private static final String INCORRECT_DRIVER_ID = "501";

    private static final String INCORRECT_FIRST_NAME = "wrong";

    private static final boolean STATUS = true;

    private static final boolean INCORRECT_STATUS = false;

    @Before
    public void setUp() {

        companyRepository.deleteAll();
        driverRepository.deleteAll();

        final Company company = new Company();

        final Driver driver = new Driver();

        driver.setFirstName(FIRST_NAME);
        driver.setLastName(LAST_NAME);
        driver.setStatus(true);
        driver.setCompany(company);

        companyId = companyRepository.save(company).getId();

        driverId = driverRepository.save(driver).getId();

        request = new DriverRequest(LAST_NAME, FIRST_NAME, YEAR_OF_ISSUED, TELEPHONE_NUMBER, EMAIL);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void create() throws Exception {

        final String content = "new";

        request.setEmail(content);

        mockMvc.perform(request(POST, "/cargo/v1/companies/" + companyId + "/drivers")
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(request))
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email", is(content)));
    }

    @Test
    public void fetch() throws Exception {

        mockMvc.perform(request(GET, "/cargo/v1/companies/" + companyId + "/drivers/" + driverId)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(driverId)))
                .andExpect(jsonPath("$.firstName", is(FIRST_NAME)))
                .andExpect(jsonPath("$.lastName", is(LAST_NAME)));
    }

    @Test
    public void fetch_call_incorrectId_expect_illegalArgument() throws Exception {

        mockMvc.perform(request(GET, "/cargo/v1/companies/" + companyId + "/drivers/" + INCORRECT_DRIVER_ID)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Driver not found."
                        + " Driver with this Id: '501' not exists.")))
                .andExpect(jsonPath("$.errorId", notNullValue()));
    }

    @Test
    public void delete() throws Exception {

        mockMvc.perform(request(DELETE, "/cargo/v1/companies/" + companyId + "/drivers/" + driverId)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk());

        assertThat(driverRepository.exists(driverId), is(false));
    }

    @Test
    public void update() throws Exception {

        final String content = "update";

        request.setLastName(content);
        request.setId(driverId);

        mockMvc.perform(request(PUT, "/cargo/v1/companies/" + companyId + "/drivers")
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(request))
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(driverId)))
                .andExpect(jsonPath("$.lastName", is(content)));
    }

    @Test
    public void fetchAll() throws Exception {

        mockMvc.perform(request(GET, "/cargo/v1/companies/" + companyId + "/drivers")
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", is(driverId)))
                .andExpect(jsonPath("$.[0].lastName", is(LAST_NAME)))
                .andExpect(jsonPath("$.[0].firstName", is(FIRST_NAME)));
    }

    @Test
    public void fetchAll_call_incorrectCompanyId_expect_IllegalArgument() throws Exception {

        mockMvc.perform(request(GET, "/cargo/v1/companies/" + INCORRECT_COMPANY_ID + "/drivers")
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Drivers not found."
                        + " Drivers in company with Id: '150' not exists.")))
                .andExpect(jsonPath("$.errorId", notNullValue()));
    }

    @Test
    public void fetchByLastNameAndFirstName() throws Exception {

        mockMvc.perform(request(GET, "/cargo/v1/companies/" + companyId + "/drivers/lastName/"
                + LAST_NAME + "/firstName/"
                + FIRST_NAME)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", is(driverId)))
                .andExpect(jsonPath("$.[0].lastName", is(LAST_NAME)))
                .andExpect(jsonPath("$.[0].firstName", is(FIRST_NAME)));
    }

    @Test
    public void fetchByLastNameAndFirstName_callIncorrectValue_expect_IllegalArgument() throws Exception {

        mockMvc.perform(request(GET, "/cargo/v1/companies/" + companyId + "/drivers/lastName/"
                + LAST_NAME + "/firstName/"
                + INCORRECT_FIRST_NAME)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Drivers not found."
                        + " Drivers with this last name: 'last' and first name: 'wrong' not exists.")))
                .andExpect(jsonPath("$.errorId", notNullValue()));
    }

    @Test
    public void fetchDriver_byStatus_expect_ok() throws Exception {

        mockMvc.perform(request(GET, "/cargo/v1/companies/" + companyId + "/drivers/status/" + STATUS)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", is(driverId)))
                .andExpect(jsonPath("$.[0].lastName", is(LAST_NAME)))
                .andExpect(jsonPath("$.[0].firstName", is(FIRST_NAME)))
                .andExpect(jsonPath("$.[0].status", is(STATUS)));
    }

    @Test
    public void fetchDriver_byStatus_call_incorrectStatus_expect_IllegalArgument() throws Exception {

        mockMvc.perform(request(GET, "/cargo/v1/companies/" + companyId + "/drivers/status/"
                + INCORRECT_STATUS)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Drivers not found."
                        + " Drivers with status: 'false' not exists.")))
                .andExpect(jsonPath("$.errorId", notNullValue()));
    }
}
