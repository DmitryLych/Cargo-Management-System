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
public class MedicalExaminationControllerTest {

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

    @Before
    public void setUp() throws Exception {

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

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void create() throws Exception {

        request = new MedicalExaminationRequest();

        final long content = new Date().getTime();

        request.setValidate(content);

        mockMvc.perform(request(POST, "/companies/" + COMPANY_ID + "/drivers/"
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

        request = new MedicalExaminationRequest();

        final long content = new Date().getTime();

        request.setId(medicalExaminationId);
        request.setValidate(content);

        mockMvc.perform(request(PUT, "/companies/" + COMPANY_ID + "/drivers/"
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

        mockMvc.perform(request(GET, "/companies/" + COMPANY_ID + "/drivers/"
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

        mockMvc.perform(request(GET, "/companies/" + COMPANY_ID + "/drivers/"
                + driverId + "/medical/validate/" + VALIDATE.getTime())
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", is(medicalExaminationId)))
                .andExpect(jsonPath("$.[0].validate", is(VALIDATE.getTime())));
    }

}