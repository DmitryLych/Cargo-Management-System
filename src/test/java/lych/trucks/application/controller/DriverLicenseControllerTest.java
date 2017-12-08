package lych.trucks.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lych.trucks.application.dto.request.DriverLicenseRequest;
import lych.trucks.domain.model.Driver;
import lych.trucks.domain.model.DriverLicense;
import lych.trucks.domain.repository.DriverLicenseRepository;
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
@Transactional
public class DriverLicenseControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DriverLicenseRepository driverLicenseRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private DriverLicenseRequest request;

    private static final String CATEGORY = "a,b,c,d";

    private static final String SPECIAL_NOTES = "notes";

    private static final Long VALIDATE = 123L;

    private Integer driverLicenseId;

    private Integer driverId;

    private static final Integer COMPANY_ID = 1;

    @Before
    public void setUp() throws Exception {

        driverRepository.deleteAll();
        driverLicenseRepository.deleteAll();

        final Driver driver = new Driver();

        driverId = driverRepository.save(driver).getId();

        final DriverLicense driverLicense = new DriverLicense();

        driverLicense.setDriverLicenseFk(driverId);
        driverLicense.setCategory(CATEGORY);
        driverLicense.setSpecialNotes(SPECIAL_NOTES);

        final Driver newDriver = driverRepository.findOne(driverId);

        newDriver.setDriverLicense(driverLicense);

        driverRepository.save(newDriver);

        driverLicenseId = driverLicenseRepository.save(driverLicense).getId();

        request = new DriverLicenseRequest(CATEGORY, VALIDATE);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void create() throws Exception {

        final String content = "new";

        request.setSpecialNotes(content);

        mockMvc.perform(request(POST, "/companies/" + COMPANY_ID + "/drivers/"
                + driverId + "/licenses")
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(request))
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.specialNotes", is(content)));
    }

    @Test
    public void fetch() throws Exception {

        mockMvc.perform(request(GET, "/companies/" + COMPANY_ID + "/drivers/"
                + driverId + "/licenses")
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.category", is(CATEGORY)))
                .andExpect(jsonPath("$.id", is(driverLicenseId)))
                .andExpect(jsonPath("$.specialNotes", is(SPECIAL_NOTES)));
    }

    @Test
    public void update() throws Exception {

        final String content = "update";

        request.setId(driverLicenseId);
        request.setSpecialNotes(content);

        mockMvc.perform(request(PUT, "/companies/" + COMPANY_ID + "/drivers/"
                + driverId + "/licenses")
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(request))
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(driverLicenseId)))
                .andExpect(jsonPath("$.category", is(CATEGORY)))
                .andExpect(jsonPath("$.specialNotes", is(content)));
    }

    @Test
    public void fetchByCategory() throws Exception {

        mockMvc.perform(request(GET, "/companies/" + COMPANY_ID + "/drivers/"
                + driverId + "/licenses/category/" + CATEGORY)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", is(driverLicenseId)))
                .andExpect(jsonPath("$.[0].category", is(CATEGORY)))
                .andExpect(jsonPath("$.[0].specialNotes", is(SPECIAL_NOTES)));
    }

    @Test
    public void fetchBySpecialNotes() throws Exception {

        mockMvc.perform(request(GET, "/companies/" + COMPANY_ID + "/drivers/"
                + driverId + "/licenses/specialNotes/" + SPECIAL_NOTES)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", is(driverLicenseId)))
                .andExpect(jsonPath("$.[0].category", is(CATEGORY)))
                .andExpect(jsonPath("$.[0].specialNotes", is(SPECIAL_NOTES)));
    }

}
