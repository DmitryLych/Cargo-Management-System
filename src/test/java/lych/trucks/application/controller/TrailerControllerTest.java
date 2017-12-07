package lych.trucks.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lych.trucks.application.dto.request.TrailerRequest;
import lych.trucks.domain.model.Driver;
import lych.trucks.domain.model.Trailer;
import lych.trucks.domain.model.Truck;
import lych.trucks.domain.repository.DriverRepository;
import lych.trucks.domain.repository.TrailerRepository;
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
public class TrailerControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private TrailerRepository trailerRepository;

    @Autowired
    private TruckRepository truckRepository;

    @Autowired
    private DriverRepository driverRepository;

    private Integer truckId;

    private Integer driverId;

    private Integer trailerId;

    private static final String REGISTER_SIGN = "register";

    private static final Integer VOLUME = 123;

    private static final String TYPE = "type";

    private static final Integer COMPANY_ID = 1;

    private TrailerRequest trailerRequest;

    @Before
    public void setUp() throws Exception {

        driverRepository.deleteAll();
        truckRepository.deleteAll();
        trailerRepository.deleteAll();

        driverId = driverRepository.save(new Driver()).getId();

        final Driver savedDriver = driverRepository.findOne(driverId);

        final Truck truck = new Truck();

        truck.setTruckFk(driverId);

        savedDriver.setTruck(truck);

        driverRepository.save(savedDriver);

        truckId = truckRepository.save(truck).getId();

        final Truck savedTruck = truckRepository.findOne(truckId);

        final Trailer trailer = new Trailer();

        trailer.setTrailerFk(truckId);
        trailer.setVolume(VOLUME);
        trailer.setRegisterSign(REGISTER_SIGN);
        trailer.setTrailerType(TYPE);

        savedTruck.setTrailer(trailer);

        truckRepository.save(savedTruck);

        trailerId = trailerRepository.save(trailer).getId();

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void create() throws Exception {

        final String content = "new";

        trailerRequest = new TrailerRequest();

        trailerRequest.setTrailerType(content);

        mockMvc.perform(request(POST, "/companies/" + COMPANY_ID + "/drivers/"
                + driverId + "/trucks/" + truckId + "/trailers")
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(trailerRequest))
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.trailerType", is(content)));
    }

    @Test
    public void update() throws Exception {

        final String content = "update";

        trailerRequest = new TrailerRequest();

        trailerRequest.setRegisterSign(content);
        trailerRequest.setId(trailerId);

        mockMvc.perform(request(PUT, "/companies/" + COMPANY_ID + "/drivers/"
                + driverId + "/trucks/" + truckId + "/trailers")
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(trailerRequest))
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(trailerId)))
                .andExpect(jsonPath("$.registerSign", is(content)));
    }

    @Test
    public void fetch() throws Exception {

        mockMvc.perform(request(GET, "/companies/" + COMPANY_ID + "/drivers/"
                + driverId + "/trucks/" + truckId + "/trailers")
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trailerType", is(TYPE)))
                .andExpect(jsonPath("$.volume", is(VOLUME)))
                .andExpect(jsonPath("$.registerSign", is(REGISTER_SIGN)));
    }

    @Test
    public void fetchByRegisterSign() throws Exception {

        mockMvc.perform(request(GET, "/companies/" + COMPANY_ID + "/drivers/"
                + driverId + "/trucks/" + truckId + "/trailers/register/" + REGISTER_SIGN)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trailerType", is(TYPE)))
                .andExpect(jsonPath("$.volume", is(VOLUME)))
                .andExpect(jsonPath("$.registerSign", is(REGISTER_SIGN)));
    }

    @Test
    public void fetchByVolume() throws Exception {

        mockMvc.perform(request(GET, "/companies/" + COMPANY_ID + "/drivers/"
                + driverId + "/trucks/" + truckId + "/trailers/volume/" + VOLUME)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].trailerType", is(TYPE)))
                .andExpect(jsonPath("$.[0].volume", is(VOLUME)))
                .andExpect(jsonPath("$.[0].registerSign", is(REGISTER_SIGN)));
    }

    @Test
    public void fetchByType() throws Exception {

        mockMvc.perform(request(GET, "/companies/" + COMPANY_ID + "/drivers/"
                + driverId + "/trucks/" + truckId + "/trailers/type/" + TYPE)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].trailerType", is(TYPE)))
                .andExpect(jsonPath("$.[0].volume", is(VOLUME)))
                .andExpect(jsonPath("$.[0].registerSign", is(REGISTER_SIGN)));
    }

}
