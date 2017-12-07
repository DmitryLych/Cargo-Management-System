package lych.trucks.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lych.trucks.application.dto.request.OrderRequest;
import lych.trucks.domain.model.Customer;
import lych.trucks.domain.model.Driver;
import lych.trucks.domain.model.Order;
import lych.trucks.domain.repository.CustomerRepository;
import lych.trucks.domain.repository.DriverRepository;
import lych.trucks.domain.repository.OrderRepository;
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
public class OrderControllerTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private OrderRepository orderRepository;

    private static final boolean ISSUED = true;

    private static final boolean COMPLETED = true;

    private static final boolean PAID = true;

    private Integer orderId;

    private Integer customerId;

    private Integer driverId;

    private OrderRequest orderRequest;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {

        customerRepository.deleteAll();
        driverRepository.deleteAll();
        orderRepository.deleteAll();

        final Driver savedDriver = driverRepository.save(new Driver());

        driverId = savedDriver.getId();

        final Customer savedCustomer = customerRepository.save(new Customer());

        customerId = savedCustomer.getCustomerId();

        final Order order = new Order();

        order.setDriver(savedDriver);
        order.setCustomer(savedCustomer);
        order.setPaid(PAID);
        order.setIssued(ISSUED);
        order.setCompleted(COMPLETED);

        orderId = orderRepository.save(order).getOrderId();

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void create() throws Exception {

        final String content = "content";

        orderRequest = new OrderRequest();

        orderRequest.setDownloadAddress(content);

        mockMvc.perform(request(POST, "/customers/" + customerId + "/orders")
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(orderRequest))
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.downloadAddress", is(content)));
    }

    @Test
    public void update() throws Exception {

        final String content = "update";

        orderRequest = new OrderRequest();

        orderRequest.setOrderId(orderId);
        orderRequest.setDownloadAddress(content);

        mockMvc.perform(request(PUT, "/customers/" + customerId + "/orders")
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(orderRequest))
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId", is(orderId)))
                .andExpect(jsonPath("$.downloadAddress", is(content)));
    }

    @Test
    public void fetch() throws Exception {

        mockMvc.perform(request(GET, "/customers/" + customerId + "/orders/" + orderId)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId", is(orderId)))
                .andExpect(jsonPath("$.issued", is(ISSUED)))
                .andExpect(jsonPath("$.paid", is(PAID)))
                .andExpect(jsonPath("$.completed", is(COMPLETED)));
    }

    @Test
    public void delete() throws Exception {

        mockMvc.perform(request(DELETE, "/customers/" + customerId + "/orders/" + orderId)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk());

        assertThat(orderRepository.exists(orderId), is(false));
    }

    @Test
    public void fetchByDriver() throws Exception {

        mockMvc.perform(request(GET, "/customers/" + customerId + "/orders/driver/" + driverId)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].orderId", is(orderId)))
                .andExpect(jsonPath("$.[0].paid", is(PAID)))
                .andExpect(jsonPath("$.[0].completed", is(COMPLETED)))
                .andExpect(jsonPath("$.[0].issued", is(ISSUED)));
    }

    @Test
    public void fetchByCustomer() throws Exception {

        mockMvc.perform(request(GET, "/customers/" + customerId + "/orders")
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].orderId", is(orderId)))
                .andExpect(jsonPath("$.[0].paid", is(PAID)))
                .andExpect(jsonPath("$.[0].completed", is(COMPLETED)))
                .andExpect(jsonPath("$.[0].issued", is(ISSUED)));
    }

    @Test
    public void fetchByIssuedAndCustomer() throws Exception {

        mockMvc.perform(request(GET, "/customers/" + customerId + "/orders/issued/" + ISSUED)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].orderId", is(orderId)))
                .andExpect(jsonPath("$.[0].paid", is(PAID)))
                .andExpect(jsonPath("$.[0].completed", is(COMPLETED)))
                .andExpect(jsonPath("$.[0].issued", is(ISSUED)));
    }

    @Test
    public void fetchByCompletedAndCustomer() throws Exception {

        mockMvc.perform(request(GET, "/customers/" + customerId + "/orders/completed/"
                + COMPLETED)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].orderId", is(orderId)))
                .andExpect(jsonPath("$.[0].paid", is(PAID)))
                .andExpect(jsonPath("$.[0].completed", is(COMPLETED)))
                .andExpect(jsonPath("$.[0].issued", is(ISSUED)));
    }

    @Test
    public void fetchByPaidAndCustomer() throws Exception {

        mockMvc.perform(request(GET, "/customers/" + customerId + "/orders/paid/" + PAID)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].orderId", is(orderId)))
                .andExpect(jsonPath("$.[0].paid", is(PAID)))
                .andExpect(jsonPath("$.[0].completed", is(COMPLETED)))
                .andExpect(jsonPath("$.[0].issued", is(ISSUED)));
    }

}