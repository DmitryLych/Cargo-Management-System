package lych.trucks.domain.service;

import lych.trucks.domain.model.Driver;
import lych.trucks.domain.model.Truck;
import lych.trucks.domain.repository.DriverRepository;
import lych.trucks.domain.repository.TruckRepository;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultTruckServiceTest {

    @Autowired
    private TruckRepository truckRepository;

    @Autowired
    private TruckService truckService;

    @Autowired
    private DriverRepository driverRepository;

    private Integer driverIdContent;

    private Integer truckIdContent;

    private static final String REGISTER_SIGN_CONTENT = "register";

    private static final String BODY_NUMBER_CONTENT = "number";

    @Before
    public void setUp() {

        driverRepository.deleteAll();

        truckRepository.deleteAll();

        driverIdContent = driverRepository.save(new Driver()).getId();

        final Truck truck = new Truck();

        truck.setOwnerIdForTruck(driverIdContent);
        truck.setRegisterSign(REGISTER_SIGN_CONTENT);
        truck.setBodyNumber(BODY_NUMBER_CONTENT);

        truckIdContent = truckRepository.save(truck).getId();
    }

    @Test
    public void create() {

        final Truck truck = new Truck();

        final String content = "new";

        truck.setRegisterSign(content);

        final Integer newTruckId = truckService.create(driverIdContent, truck).getId();

        assertThat(truckRepository.findOne(newTruckId).getBodyNumber(), Is.is(content));
    }

    @Test
    public void fetch() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void fetchByRegisterSign() throws Exception {
    }

    @Test
    public void fetchByBodyNumber() throws Exception {
    }

}