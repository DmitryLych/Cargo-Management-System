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
@SuppressWarnings("PMD.ExcessiveImports")
public class OrderControllerIT {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private OrderRepository orderRepository;

    private static final boolean ISSUED = true;

    private static final boolean COMPLETED = true;

    private static final boolean PAID = true;

    private static final Double COAST = 123.0;

    private static final String DOWNLOAD_PLACE = "download";

    private static final String UNLOAD_PLACE = "unload";

    private Integer orderId;

    private Integer customerId;

    private Integer driverId;

    private OrderRequest orderRequest;

    private static final Integer INCORRECT_ORDER_ID = 3213;

    private static final Integer INCORRECT_DRIVER_ID = 3232;

    private static final Integer INCORRECT_CUSTOMER_ID = 6654;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @Before
    public void setUp() {

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

        orderRequest = new OrderRequest(COAST, DOWNLOAD_PLACE, UNLOAD_PLACE);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void create() throws Exception {

        final String content = "content";

        orderRequest.setDownloadAddress(content);

        mockMvc.perform(request(POST, "/cargo/v1/customers/" + customerId + "/orders")
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

        orderRequest.setOrderId(orderId);
        orderRequest.setDownloadAddress(content);

        mockMvc.perform(request(PUT, "/cargo/v1/customers/" + customerId + "/orders")
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

        mockMvc.perform(request(GET, "/cargo/v1/customers/" + customerId + "/orders/" + orderId)
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
    public void fetch_call_incorrectOrderId_expect_IllegalArgument() throws Exception {

        mockMvc.perform(request(GET, "/cargo/v1/customers/" + customerId + "/orders/"
                + INCORRECT_ORDER_ID)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Order not found."
                        + " Order with this Id: '3213' not exists.")))
                .andExpect(jsonPath("$.errorId", notNullValue()));
    }

    @Test
    public void delete() throws Exception {

        mockMvc.perform(request(DELETE, "/cargo/v1/customers/" + customerId + "/orders/" + orderId)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk());

        assertThat(orderRepository.exists(orderId), is(false));
    }

    @Test
    public void fetchByDriver() throws Exception {

        mockMvc.perform(request(GET, "/cargo/v1/customers/" + customerId + "/orders/driver/" + driverId)
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
    public void fetchByDriver_call_incorrectDriverId_expect_IllegalArgument() throws Exception {

        mockMvc.perform(request(GET, "/cargo/v1/customers/" + customerId + "/orders/driver/"
                + INCORRECT_DRIVER_ID)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Orders not found."
                        + " Driver with Id: '3232' don`t have orders.")))
                .andExpect(jsonPath("$.errorId", notNullValue()));
    }

    @Test
    public void fetchByCustomer() throws Exception {

        mockMvc.perform(request(GET, "/cargo/v1/customers/" + customerId + "/orders")
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
    public void fetchByCustomer_call_incorrectCustomerId_expect_illegalArgument() throws Exception {

        mockMvc.perform(request(GET, "/cargo/v1/customers/" + INCORRECT_CUSTOMER_ID + "/orders")
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Orders not found."
                        + " Customer with Id: '6654' don`t have orders.")))
                .andExpect(jsonPath("$.errorId", notNullValue()));
    }

    @Test
    public void fetchByIssuedAndCustomer() throws Exception {

        mockMvc.perform(request(GET, "/cargo/v1/customers/" + customerId + "/orders/issued/" + ISSUED)
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
    public void fetchByIssuedAndCustomer_expect_IllegalArgument() throws Exception {

        mockMvc.perform(request(GET, "/cargo/v1/customers/" + customerId + "/orders/issued/" + !ISSUED)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Orders not found."
                        + " Customer with Id: '" + customerId + "' don`t have orders with issued status: 'false'")))
                .andExpect(jsonPath("$.errorId", notNullValue()));
    }

    @Test
    public void fetchByCompletedAndCustomer() throws Exception {

        mockMvc.perform(request(GET, "/cargo/v1/customers/" + customerId + "/orders/completed/"
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
    public void fetchByCompletedAndCustomer_callWrong_expect_IllegalArgument() throws Exception {

        mockMvc.perform(request(GET, "/cargo/v1/customers/" + customerId + "/orders/completed/"
                + !COMPLETED)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Orders not found."
                        + " Customer with Id: '" + customerId + "' don`t have orders with completed status: 'false'")))
                .andExpect(jsonPath("$.errorId", notNullValue()));
    }

    @Test
    public void fetchByPaidAndCustomer() throws Exception {

        mockMvc.perform(request(GET, "/cargo/v1/customers/" + customerId + "/orders/paid/" + PAID)
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
    public void fetchByPaidAndCustomer_callWrong_expect_IllegalArgument() throws Exception {

        mockMvc.perform(request(GET, "/cargo/v1/customers/" + customerId + "/orders/paid/" + !PAID)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Orders not found. "
                        + "Customer with Id: '" + customerId + "' don`t have orders with paid status: 'false'")))
                .andExpect(jsonPath("$.errorId", notNullValue()));
    }
}
