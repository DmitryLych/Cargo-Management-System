package lych.trucks.domain.service;

import lombok.RequiredArgsConstructor;
import lych.trucks.domain.model.Driver;
import lych.trucks.domain.model.Truck;
import lych.trucks.domain.repository.TruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        validateTruck(truck);

        final Driver driver = driverService.fetchDriver(driverId);

        driver.setTruck(truck);

        truck.setTruckFk(driverId);

        driverService.updateDriver(driver);

        return truckRepository.save(truck);
    }

    @Override
    public Truck fetchTruck(final Integer driverId) {

        return Optional.ofNullable(truckRepository.findByTruckFk(driverId))
                .orElseThrow(() -> new IllegalArgumentException("Truck not found. "
                        + "Driver with Id: '" + driverId + "' don`t have truck."));
    }

    @Override
    public Truck updateTruck(final Truck truck) {
        validateTruck(truck);

        final Truck saved = Optional.ofNullable(truckRepository.findOne(truck.getId()))
                .orElseThrow(() -> new IllegalArgumentException("Truck not found. "
                        + "Truck with Id: '" + truck.getId() + "' not exists."));

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
        return Optional.ofNullable(truckRepository.findByRegisterSign(registerSign))
                .orElseThrow(() -> new IllegalArgumentException("Truck not found. "
                        + "Truck with register sign: '" + registerSign + "' not exists."));
    }

    @Override
    public Truck fetchTruckByBodyNumber(final String bodyNumber) {
        return Optional.ofNullable(truckRepository.findByBodyNumber(bodyNumber))
                .orElseThrow(() -> new IllegalArgumentException("Truck not found. "
                        + "Truck with body number: '" + bodyNumber + "' not exist."));
    }

    private static void validateTruck(final Truck truck) {

        if (truck == null) {
            throw new IllegalArgumentException("Truck can`t be null.");
        }
    }
}
