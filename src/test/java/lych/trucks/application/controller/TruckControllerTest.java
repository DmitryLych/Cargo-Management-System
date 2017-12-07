package lych.trucks.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lych.trucks.application.dto.request.TruckRequest;
import lych.trucks.domain.model.Driver;
import lych.trucks.domain.model.Truck;
import lych.trucks.domain.repository.DriverRepository;
import lych.trucks.domain.repository.TruckRepository;
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
public class TruckControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private TruckRepository truckRepository;

    private TruckRequest truckRequest;

    private static final String REGISTER_SIGN = "register";

    private static final String BODY_NUMBER = "number";

    private static final Integer COMPANY_ID = 1;

    private Integer driverId;

    private Integer truckId;

    @Before
    public void setUp() throws Exception {

        driverRepository.deleteAll();
        truckRepository.deleteAll();

        driverId = driverRepository.save(new Driver()).getId();

        final Driver saved = driverRepository.findOne(driverId);

        final Truck truck = new Truck();

        truck.setTruckFk(driverId);
        truck.setRegisterSign(REGISTER_SIGN);
        truck.setBodyNumber(BODY_NUMBER);

        saved.setTruck(truck);

        driverRepository.save(saved);

        truckId = truckRepository.save(truck).getId();

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void create() throws Exception {

        truckRequest = new TruckRequest();

        final String content = "create";

        truckRequest.setRegisterSign(content);

        mockMvc.perform(request(POST, "/companies/" + COMPANY_ID + "/drivers/"
                + driverId + "/trucks")
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(truckRequest))
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.registerSign", is(content)));
    }

    @Test
    public void update() throws Exception {

        truckRequest = new TruckRequest();

        final String content = "update";

        truckRequest.setRegisterSign(content);
        truckRequest.setId(truckId);

        mockMvc.perform(request(PUT, "/companies/" + COMPANY_ID + "/drivers/"
                + driverId + "/trucks")
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(truckRequest))
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.registerSign", is(content)));
    }

    @Test
    public void fetch() throws Exception {

        mockMvc.perform(request(GET, "/companies/" + COMPANY_ID + "/drivers/"
                + driverId + "/trucks")
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(truckId)))
                .andExpect(jsonPath("$.registerSign", is(REGISTER_SIGN)))
                .andExpect(jsonPath("$.bodyNumber", is(BODY_NUMBER)));
    }

    @Test
    public void fetchByRegisterSign() throws Exception {

        mockMvc.perform(request(GET, "/companies/" + COMPANY_ID + "/drivers/"
                + driverId + "/trucks/register/" + REGISTER_SIGN)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(truckId)))
                .andExpect(jsonPath("$.registerSign", is(REGISTER_SIGN)))
                .andExpect(jsonPath("$.bodyNumber", is(BODY_NUMBER)));
    }

    @Test
    public void fetchByBodyNumber() throws Exception {

        mockMvc.perform(request(GET, "/companies/" + COMPANY_ID + "/drivers/"
                + driverId + "/trucks/number/" + BODY_NUMBER)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(truckId)))
                .andExpect(jsonPath("$.registerSign", is(REGISTER_SIGN)))
                .andExpect(jsonPath("$.bodyNumber", is(BODY_NUMBER)));
    }

}
