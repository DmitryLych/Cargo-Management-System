package lych.trucks.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lych.trucks.application.dto.request.CompanyRequest;
import lych.trucks.domain.model.Company;
import lych.trucks.domain.repository.CompanyRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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
public class CompanyControllerIT {

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private CompanyRepository companyRepository;

    private CompanyRequest request;

    private Integer companyId;

    private static final String COMPANY_NAME = "company";

    private static final String ADDRESS = "address";

    private static final String EMAIL = "email";

    @Before
    public void setUp() {

        companyRepository.deleteAll();

        final Company company = new Company();

        company.setCompanyName(COMPANY_NAME);

        companyId = companyRepository.save(company).getId();

        request = new CompanyRequest(COMPANY_NAME, ADDRESS, EMAIL);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void create() throws Exception {

        final String content = "new";

        request.setCompanyName(content);

        mockMvc.perform(request(POST, "/cargo/v1/companies")
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(request))
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.companyName", is(request.getCompanyName())))
                .andExpect(jsonPath("$.address", is(request.getAddress())))
                .andExpect(jsonPath("$.email", is(request.getEmail())));
    }

    @Test
    public void fetch() throws Exception {

        mockMvc.perform(request(GET, "/cargo/v1/companies/" + companyId)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyName", is(COMPANY_NAME)));
    }

    @Test
    public void update() throws Exception {

        request.setCompanyName("updated");
        request.setId(companyId);

        mockMvc.perform(request(PUT, "/cargo/v1/companies")
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(request))
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyName", is(request.getCompanyName())))
                .andExpect(jsonPath("$.address", is(request.getAddress())))
                .andExpect(jsonPath("$.email", is(request.getEmail())));
    }

    @Test
    public void delete() throws Exception {

        mockMvc.perform(request(DELETE, "/cargo/v1/companies/" + companyId)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk());

        assertThat(companyRepository.exists(companyId), is(false));

    }

    @Test
    public void fetchAll() throws Exception {

        mockMvc.perform(request(GET, "/cargo/v1/companies/")
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", is(companyId)))
                .andExpect(jsonPath("$.[0].companyName", is(COMPANY_NAME)));
    }

    @Test
    public void fetchByCompanyName() throws Exception {

        mockMvc.perform(request(GET, "/cargo/v1/companies/companyName/" + COMPANY_NAME)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(companyId)))
                .andExpect(jsonPath("$.companyName", is(COMPANY_NAME)));
    }
}
