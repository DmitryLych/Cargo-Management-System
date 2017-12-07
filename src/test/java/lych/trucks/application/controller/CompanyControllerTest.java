package lych.trucks.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lych.trucks.application.dto.request.CompanyRequest;
import lych.trucks.application.dto.response.CompanyResponse;
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
public class CompanyControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private CompanyRepository companyRepository;

    private CompanyResponse response;

    private CompanyRequest request;

    private Integer companyId;

    private static final String COMPANY_NAME = "company";

    @Before
    public void setUp() throws Exception {

        companyRepository.deleteAll();

        final Company company = new Company();

        company.setCompanyName(COMPANY_NAME);

        companyId = companyRepository.save(company).getId();

        request = new CompanyRequest();

        request.setCompanyName("request");

        response = new CompanyResponse();

        response.setCompanyName("request");

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void create() throws Exception {

        mockMvc.perform(request(POST, "/companies")
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(request))
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.companyName", is(response.getCompanyName())));
    }

    @Test
    public void fetch() throws Exception {

        mockMvc.perform(request(GET, "/companies/" + companyId)
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

        response.setCompanyName("updated");

        mockMvc.perform(request(PUT, "/companies")
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(request))
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyName", is(request.getCompanyName())));
    }

    @Test
    public void delete() throws Exception {

        mockMvc.perform(request(DELETE, "/companies/" + companyId)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk());

        assertThat(companyRepository.exists(companyId), is(false));

    }

    @Test
    public void fetchAll() throws Exception {

        mockMvc.perform(request(GET, "/companies/")
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", is(companyId)))
                .andExpect(jsonPath("$.[0].companyName", is(COMPANY_NAME)));
    }

    @Test
    public void fetchByCompanyName() throws Exception {

        mockMvc.perform(request(GET, "/companies/companyName/" + COMPANY_NAME)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(companyId)))
                .andExpect(jsonPath("$.companyName", is(COMPANY_NAME)));
    }

}
