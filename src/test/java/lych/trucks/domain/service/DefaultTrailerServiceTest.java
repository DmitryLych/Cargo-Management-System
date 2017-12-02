package lych.trucks.domain.service;

import lych.trucks.domain.model.Driver;
import lych.trucks.domain.model.Trailer;
import lych.trucks.domain.model.Truck;
import lych.trucks.domain.repository.DriverRepository;
import lych.trucks.domain.repository.TrailerRepository;
import lych.trucks.domain.repository.TruckRepository;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultTrailerServiceTest {

    @Autowired
    private TrailerRepository trailerRepository;

    @Autowired
    private TrailerService trailerService;

    @Autowired
    private TruckRepository truckRepository;

    @Autowired
    private DriverRepository driverRepository;

    private Integer trailerIdContent;

    private Integer truckIdContent;

    private static final String REGISTER_SIGN_CONTENT = "register";

    private static final Integer VOLUME_CONTENT = 123;

    private static final String TYPE_CONTENT = "type";

    @Before
    public void setUp() {

        driverRepository.deleteAll();

        truckRepository.deleteAll();

        trailerRepository.deleteAll();

        final Integer driverIdContent = driverRepository.save(new Driver()).getId();

        final Truck truck = new Truck();

        truck.setOwnerIdForTruck(driverIdContent);

        truckIdContent = truckRepository.save(truck).getId();

        final Trailer trailer = new Trailer();

        trailer.setVolume(VOLUME_CONTENT);
        trailer.setRegisterSign(REGISTER_SIGN_CONTENT);
        trailer.setOwnerIdForTrailer(truckIdContent);
        trailer.setTrailerType(TYPE_CONTENT);

        trailerIdContent = trailerRepository.save(trailer).getId();
    }

    @Test
    public void create() {

        final Trailer trailer = new Trailer();

        final String content = "create";

        trailer.setTrailerType(content);

        final Integer newId;

        newId = trailerService.create(truckIdContent, trailer).getId();

        assertThat(trailerRepository.findOne(newId).getTrailerType(), Is.is(content));
    }

    @Test
    public void fetch() {

        assertThat(trailerService.fetch(truckIdContent).getVolume(), Is.is(VOLUME_CONTENT));
    }

    @Test
    public void delete() {

        trailerService.delete(trailerIdContent);

        assertThat(trailerRepository.exists(trailerIdContent), Is.is(false));
    }

    @Test
    public void update() {

        final Trailer trailer = trailerRepository.findOne(trailerIdContent);

        final String content = "321";

        trailer.setRegisterSign(content);

        trailerService.update(trailer);

        assertThat(trailerRepository.findOne(trailerIdContent).getRegisterSign(), Is.is(content));
    }

    @Test
    public void fetchByRegisterSign() {

        assertThat(trailerService.fetchByRegisterSign(REGISTER_SIGN_CONTENT).getRegisterSign(),
                Is.is(REGISTER_SIGN_CONTENT));
    }

    @Test
    public void fetchByVolume() {

        List<Trailer> trailers = trailerService.fetchByVolume(VOLUME_CONTENT);

        trailers.forEach(trailer -> assertThat(trailer.getVolume(), Is.is(VOLUME_CONTENT)));
    }

    @Test
    public void fetchByType() {

        List<Trailer> trailers = trailerService.fetchByType(TYPE_CONTENT);

        trailers.forEach(trailer -> assertThat(trailer.getTrailerType(), Is.is(TYPE_CONTENT)));
    }
}
