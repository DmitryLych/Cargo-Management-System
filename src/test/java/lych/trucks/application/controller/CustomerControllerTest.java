package lych.trucks.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lych.trucks.application.dto.request.CustomerRequest;
import lych.trucks.domain.model.Customer;
import lych.trucks.domain.repository.CustomerRepository;
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
public class CustomerControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private CustomerRepository customerRepository;

    private CustomerRequest request;

    private Integer customerId;

    private static final String CUSTOMER_NAME = "customer";

    private static final String CREATE_CUSTOMER_NAME = "create";

    private static final String UPDATE_CUSTOMER_NAME = "update";

    @Before
    public void setUp() throws Exception {

        customerRepository.deleteAll();

        final Customer customer = new Customer();

        customer.setCustomerName(CUSTOMER_NAME);

        customerId = customerRepository.save(customer).getCustomerId();

        request = new CustomerRequest();

        request.setCustomerName(CREATE_CUSTOMER_NAME);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void create() throws Exception {

        mockMvc.perform(request(POST, "/customers")
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(request))
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.customerName", is(CREATE_CUSTOMER_NAME)));
    }

    @Test
    public void update() throws Exception {

        request.setCustomerName(UPDATE_CUSTOMER_NAME);
        request.setCustomerId(customerId);

        mockMvc.perform(request(PUT, "/customers")
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(request))
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerName", is(UPDATE_CUSTOMER_NAME)));
    }

    @Test
    public void fetch() throws Exception {

        mockMvc.perform(request(GET, "/customers/" + customerId)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId", is(customerId)))
                .andExpect(jsonPath("$.customerName", is(CUSTOMER_NAME)));
    }

    @Test
    public void delete() throws Exception {

        mockMvc.perform(request(DELETE, "/customers/" + customerId)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk());

        assertThat(customerRepository.exists(customerId), is(false));
    }

    @Test
    public void fetchAll() throws Exception {

        mockMvc.perform(request(GET, "/customers")
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].customerId", is(customerId)))
                .andExpect(jsonPath("$.[0].customerName", is(CUSTOMER_NAME)));
    }

    @Test
    public void fetchByCustomerName() throws Exception {

        mockMvc.perform(request(GET, "/customers/customerName/" + CUSTOMER_NAME)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId", is(customerId)))
                .andExpect(jsonPath("$.customerName", is(CUSTOMER_NAME)));
    }

}
