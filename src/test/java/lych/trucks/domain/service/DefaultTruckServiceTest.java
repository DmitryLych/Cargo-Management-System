package lych.trucks.domain.service;

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

import static org.hamcrest.core.Is.is;
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

    private static final String REGISTER_SIGN_CONTENT = "register";

    private static final String BODY_NUMBER_CONTENT = "number";

    @Before
    public void setUp() {

        driverRepository.deleteAll();

        truckRepository.deleteAll();

        final Driver driver = driverRepository.save(new Driver());

        driverIdContent = driver.getId();

        final Truck truck = new Truck();

        truck.setTruckFk(driverIdContent);
        truck.setRegisterSign(REGISTER_SIGN_CONTENT);
        truck.setBodyNumber(BODY_NUMBER_CONTENT);

        truckRepository.save(truck);
    }

    @Test
    public void create() {

        final Truck truck = new Truck();

        final String content = "new";

        truck.setRegisterSign(content);

        final Integer newTruckId = truckService.create(driverIdContent, truck).getId();

        assertThat(truckRepository.findOne(newTruckId).getRegisterSign(), is(content));
    }

    @Test
    public void fetch() {

        assertThat(truckService.fetch(driverIdContent).getBodyNumber(), is(BODY_NUMBER_CONTENT));
    }

    @Test
    public void update() {

        final String content = "update";

        final Truck truck = truckRepository.findByTruckFk(driverIdContent);

        truck.setRegisterSign(content);

        truckService.update(truck);

        assertThat(truckRepository.findByTruckFk(driverIdContent).getRegisterSign(), is(content));
    }

    @Test
    public void fetchByRegisterSign() {

        assertThat(truckService.fetchByRegisterSign(REGISTER_SIGN_CONTENT).getRegisterSign(),
                is(REGISTER_SIGN_CONTENT));
    }

    @Test
    public void fetchByBodyNumber() {

        assertThat(truckService.fetchByBodyNumber(BODY_NUMBER_CONTENT).getBodyNumber(),
                is(BODY_NUMBER_CONTENT));
    }

}
