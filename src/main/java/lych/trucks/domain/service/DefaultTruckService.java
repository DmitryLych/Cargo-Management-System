package lych.trucks.domain.service;

import lombok.RequiredArgsConstructor;
import lych.trucks.domain.model.Driver;
import lych.trucks.domain.model.Truck;
import lych.trucks.domain.repository.TruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link TruckService}.
 */
@Service
@SuppressWarnings("PMD")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultTruckService implements TruckService {

    private final TruckRepository truckRepository;

    private final DriverService driverService;

    @Override
    public Truck createTruck(final Integer driverId, final Truck truck) {

        final Driver driver = driverService.fetchDriver(driverId);

        driver.setTruck(truck);

        truck.setTruckFk(driverId);

        driverService.updateDriver(driver);

        return truckRepository.save(truck);
    }

    @Override
    public Truck fetchTruck(final Integer driverId) {
        return truckRepository.findByTruckFk(driverId);
    }

    @Override
    public Truck updateTruck(final Truck truck) {

        final Truck saved = truckRepository.findOne(truck.getId());

        truck.setBodyNumber(saved.getBodyNumber());
        truck.setTruckFk(truck.getTruckFk() == null ? saved.getTruckFk()
                : truck.getTruckFk());
        truck.setColor(truck.getColor() == null ? saved.getColor() : truck.getColor());
        truck.setRegisterSign(truck.getRegisterSign() == null ? saved.getRegisterSign() : truck.getRegisterSign());
        truck.setTrailer(truck.getTrailer() == null ? saved.getTrailer() : truck.getTrailer());
        truck.setWeight(saved.getWeight());
        truck.setYearOfIssue(saved.getYearOfIssue());

        return truckRepository.save(truck);
    }

    @Override
    public Truck fetchTruckByRegisterSign(final String registerSign) {
        return truckRepository.findByRegisterSign(registerSign);
    }

    @Override
    public Truck fetchTruckByBodyNumber(final String bodyNumber) {
        return truckRepository.findByBodyNumber(bodyNumber);
    }
}
