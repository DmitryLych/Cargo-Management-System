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
public class InsurancePolicyControllerIT {

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

    private static final Double COAST = 1.0;

    private Integer driverId;

    private Integer insurancePolicyId;

    private static final Integer COMPANY_ID = 1;

    private static final Integer INCORRECT_DRIVER_ID = 123;

    private static final Long INCORRECT_VALIDATE = 444L;

    private static final String INCORRECT_TYPE = "wrong";

    private static final Integer INCORRECT_ID = 32;

    @Before
    public void setUp() {

        driverRepository.deleteAll();
        insurancePolicyRepository.deleteAll();

        driverId = driverRepository.save(new Driver()).getId();

        final InsurancePolicy insurancePolicy = new InsurancePolicy();

        final Driver saved = driverRepository.findOne(driverId);

        insurancePolicy.setDriver(saved);
        insurancePolicy.setType(TYPE);

        insurancePolicy.setValidate(VALIDATE);

        insurancePolicyId = insurancePolicyRepository.save(insurancePolicy).getId();

        request = new InsurancePolicyRequest(VALIDATE.getTime(), TYPE, COAST);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void create() throws Exception {

        final String content = "new";

        request.setType(content);

        mockMvc.perform(request(POST, "/cargo/v1/companies/" + COMPANY_ID + "/drivers/"
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

        mockMvc.perform(request(GET, "/cargo/v1/companies/" + COMPANY_ID + "/drivers/"
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
    public void fetchAll_call_incorrectDriverId_expect_IllegalArgument() throws Exception {

        mockMvc.perform(request(GET, "/cargo/v1/companies/" + COMPANY_ID + "/drivers/"
                + INCORRECT_DRIVER_ID + "/insurance")
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Insurance policies not found."
                        + " Driver with Id: '123' don`t have insurance policies.")))
                .andExpect(jsonPath("$.errorId", notNullValue()));
    }

    @Test
    public void fetch() throws Exception {

        mockMvc.perform(request(GET, "/cargo/v1/companies/" + COMPANY_ID + "/drivers/"
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
    public void fetch_call_wrongId_expect_IllegalArgument() throws Exception {

        mockMvc.perform(request(GET, "/cargo/v1/companies/" + COMPANY_ID + "/drivers/"
                + driverId + "/insurance/" + INCORRECT_ID)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Insurance policy not found."
                        + " Insurance policy with this Id: '32' not exists.")))
                .andExpect(jsonPath("$.errorId", notNullValue()));
    }

    @Test
    public void update() throws Exception {

        final String content = "update";

        request.setId(insurancePolicyId);
        request.setType(content);

        mockMvc.perform(request(PUT, "/cargo/v1/companies/" + COMPANY_ID + "/drivers/"
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

        mockMvc.perform(request(DELETE, "/cargo/v1/companies/" + COMPANY_ID + "/drivers/"
                + driverId + "/insurance/" + insurancePolicyId)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk());

        assertThat(insurancePolicyRepository.exists(insurancePolicyId), is(false));
    }

    @Test
    public void fetchByValidate() throws Exception {

        mockMvc.perform(request(GET, "/cargo/v1/companies/" + COMPANY_ID + "/drivers/"
                + driverId + "/insurance/validate/" + VALIDATE.getTime())
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].validate", is(VALIDATE.getTime())))
                .andExpect(jsonPath("$.[0].type", is(TYPE)));

    }

    @Test
    public void fetchByValidate_call_wrongValidate_expect_IllegalArgument() throws Exception {

        mockMvc.perform(request(GET, "/cargo/v1/companies/" + COMPANY_ID + "/drivers/"
                + driverId + "/insurance/validate/" + INCORRECT_VALIDATE)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Insurance policies not found."
                        + " Insurance policies with this validate time: 'Thu Jan 01 03:00:00 MSK 1970' not exists.")))
                .andExpect(jsonPath("$.errorId", notNullValue()));
    }

    @Test
    public void fetchByType() throws Exception {

        mockMvc.perform(request(GET, "/cargo/v1/companies/" + COMPANY_ID + "/drivers/"
                + driverId + "/insurance/type/" + TYPE)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].validate", is(VALIDATE.getTime())))
                .andExpect(jsonPath("$.[0].type", is(TYPE)));
    }

    @Test
    public void fetchByType_call_wrongType_expect_IllegalArgument() throws Exception {

        mockMvc.perform(request(GET, "/cargo/v1/companies/" + COMPANY_ID + "/drivers/"
                + driverId + "/insurance/type/" + INCORRECT_TYPE)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Insurance policies not found."
                        + " Insurance policies with this type: 'wrong'not exists.")))
                .andExpect(jsonPath("$.errorId", notNullValue()));
    }
}
