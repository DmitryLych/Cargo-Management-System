package lych.trucks.domain.repository;

import lych.trucks.domain.model.Truck;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TruckRepositoryTest {

    @Autowired
    private TruckRepository truckRepository;

    private static final Integer DRIVER_ID_CONTENT = 1;

    private static final String REGISTER_SIGN_CONTENT = "register";

    private static final String BODY_NUMBER_CONTENT = "body";

    @Before
    public void setUp() {

        final Truck truck = new Truck();

        truck.setTruckFk(DRIVER_ID_CONTENT);
        truck.setBodyNumber(BODY_NUMBER_CONTENT);
        truck.setRegisterSign(REGISTER_SIGN_CONTENT);

        truckRepository.save(truck);
    }

    @Test
    public void findByTruckFk() {

        final Truck foundTruck = truckRepository.findByTruckFk(DRIVER_ID_CONTENT);

        assertThat(foundTruck.getTruckFk(), is(DRIVER_ID_CONTENT));
    }

    @Test
    public void findByRegisterSign() {

        final Truck foundTruck = truckRepository.findByRegisterSign(REGISTER_SIGN_CONTENT);

        assertThat(foundTruck.getRegisterSign(), is(REGISTER_SIGN_CONTENT));
    }

    @Test
    public void findByBodyNumber() {

        final Truck foundTruck = truckRepository.findByBodyNumber(BODY_NUMBER_CONTENT);

        assertThat(foundTruck.getBodyNumber(), is(BODY_NUMBER_CONTENT));
    }

}
