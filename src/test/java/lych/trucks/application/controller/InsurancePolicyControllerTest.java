package lych.trucks.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lych.trucks.application.dto.request.InsurancePolicyRequest;
import lych.trucks.domain.model.Driver;
import lych.trucks.domain.model.InsurancePolicy;
import lych.trucks.domain.repository.DriverRepository;
import lych.trucks.domain.repository.InsurancePolicyRepository;
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
import java.util.Date;

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
public class InsurancePolicyControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private InsurancePolicyRepository insurancePolicyRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private InsurancePolicyRequest request;

    private static final Date VALIDATE = new Date();

    private static final String TYPE = "type";

    private Integer driverId;

    private Integer insurancePolicyId;

    private static final Integer COMPANY_ID = 1;

    @Before
    public void setUp() throws Exception {

        driverRepository.deleteAll();
        insurancePolicyRepository.deleteAll();

        driverId = driverRepository.save(new Driver()).getId();

        final InsurancePolicy insurancePolicy = new InsurancePolicy();

        final Driver saved = driverRepository.findOne(driverId);

        insurancePolicy.setDriver(saved);
        insurancePolicy.setType(TYPE);
        insurancePolicy.setValidate(VALIDATE);

        insurancePolicyId = insurancePolicyRepository.save(insurancePolicy).getId();

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void create() throws Exception {

        request = new InsurancePolicyRequest();

        final String content = "new";

        request.setType(content);

        mockMvc.perform(request(POST, "/companies/" + COMPANY_ID + "/drivers/"
                + driverId + "/insurance")
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(request))
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.type", is(content)));
    }

    @Test
    public void fetchAll() throws Exception {

        mockMvc.perform(request(GET, "/companies/" + COMPANY_ID + "/drivers/"
                + driverId + "/insurance")
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", is(insurancePolicyId)))
                .andExpect(jsonPath("$.[0].validate", is(VALIDATE.getTime())))
                .andExpect(jsonPath("$.[0].type", is(TYPE)));
    }

    @Test
    public void fetch() throws Exception {

        mockMvc.perform(request(GET, "/companies/" + COMPANY_ID + "/drivers/"
                + driverId + "/insurance/" + insurancePolicyId)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(insurancePolicyId)))
                .andExpect(jsonPath("$.validate", is(VALIDATE.getTime())))
                .andExpect(jsonPath("$.type", is(TYPE)));
    }

    @Test
    public void update() throws Exception {

        final String content = "update";

        request = new InsurancePolicyRequest();

        request.setId(insurancePolicyId);
        request.setType(content);

        mockMvc.perform(request(PUT, "/companies/" + COMPANY_ID + "/drivers/"
                + driverId + "/insurance")
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(request))
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(insurancePolicyId)))
                .andExpect(jsonPath("$.validate", is(VALIDATE.getTime())))
                .andExpect(jsonPath("$.type", is(content)));
    }

    @Test
    public void delete() throws Exception {

        mockMvc.perform(request(DELETE, "/companies/" + COMPANY_ID + "/drivers/"
                + driverId + "/insurance/" + insurancePolicyId)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk());

        assertThat(insurancePolicyRepository.exists(insurancePolicyId), is(false));
    }

    @Test
    public void fetchByValidate() throws Exception {

        mockMvc.perform(request(GET, "/companies/" + COMPANY_ID + "/drivers/"
                + driverId + "/insurance/validate/" + VALIDATE.getTime())
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].validate", is(VALIDATE.getTime())))
                .andExpect(jsonPath("$.[0].type", is(TYPE)));

    }

    @Test
    public void fetchByType() throws Exception {

        mockMvc.perform(request(GET, "/companies/" + COMPANY_ID + "/drivers/"
                + driverId + "/insurance/type/" + TYPE)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].validate", is(VALIDATE.getTime())))
                .andExpect(jsonPath("$.[0].type", is(TYPE)));
    }
}
