package lych.trucks.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lych.trucks.application.dto.request.MedicalExaminationRequest;
import lych.trucks.domain.model.Driver;
import lych.trucks.domain.model.MedicalExamination;
import lych.trucks.domain.repository.DriverRepository;
import lych.trucks.domain.repository.MedicalExaminationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
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
public class MedicalExaminationControllerIT {

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private MedicalExaminationRepository medicalExaminationRepository;

    private static final Date VALIDATE = new Date();

    private static final Integer COMPANY_ID = 1;

    private Integer medicalExaminationId;

    private MedicalExaminationRequest request;

    private Integer driverId;

    private static final Long INCORRECT_VALIDATE = 7575L;

    @Before
    public void setUp() {

        driverRepository.deleteAll();
        medicalExaminationRepository.deleteAll();

        driverId = driverRepository.save(new Driver()).getId();

        final MedicalExamination medicalExamination = new MedicalExamination();

        medicalExamination.setMedicalExaminationFk(driverId);
        medicalExamination.setValidate(VALIDATE);

        final Driver saved = driverRepository.findOne(driverId);

        saved.setMedicalExamination(medicalExamination);

        driverRepository.save(saved);

        medicalExaminationId = medicalExaminationRepository.save(medicalExamination).getId();

        request = new MedicalExaminationRequest(VALIDATE.getTime());

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void create() throws Exception {

        final Long content = new Date().getTime();

        request.setValidate(content);

        mockMvc.perform(request(POST, "/cargo/v1/companies/" + COMPANY_ID + "/drivers/"
                + driverId + "/medical")
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(request))
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.validate", is(content)));
    }

    @Test
    public void update() throws Exception {

        final long content = new Date().getTime();

        request.setId(medicalExaminationId);
        request.setValidate(content);

        mockMvc.perform(request(PUT, "/cargo/v1/companies/" + COMPANY_ID + "/drivers/"
                + driverId + "/medical")
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(request))
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(medicalExaminationId)))
                .andExpect(jsonPath("$.validate", is(content)));
    }

    @Test
    public void fetch() throws Exception {

        mockMvc.perform(request(GET, "/cargo/v1/companies/" + COMPANY_ID + "/drivers/"
                + driverId + "/medical")
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.validate", is(VALIDATE.getTime())))
                .andExpect(jsonPath("$.id", is(medicalExaminationId)));
    }

    @Test
    public void fetchByValidate() throws Exception {

        mockMvc.perform(request(GET, "/cargo/v1/companies/" + COMPANY_ID + "/drivers/"
                + driverId + "/medical/validate/" + VALIDATE.getTime())
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", is(medicalExaminationId)))
                .andExpect(jsonPath("$.[0].validate", is(VALIDATE.getTime())));
    }

    @Test
    public void fetchByValidate_call_incorrectValidate_expect_IllegalArgument() throws Exception {

        mockMvc.perform(request(GET, "/cargo/v1/companies/" + COMPANY_ID + "/drivers/"
                + driverId + "/medical/validate/" + INCORRECT_VALIDATE)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Medical examinations not found."
                        + " Medical examination with this validate time: 'Thu Jan 01 03:00:07 MSK 1970' not exists.")))
                .andExpect(jsonPath("$.errorId", notNullValue()));
    }
}
