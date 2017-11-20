package lych.trucks.domain.service;

import lombok.RequiredArgsConstructor;
import lych.trucks.domain.model.Truck;
import lych.trucks.domain.repository.TruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultTruckService implements TruckService {

    private final TruckRepository truckRepository;

    @Override
    public Truck create(final Integer driverId, final Truck truck) {

        truck.setOwnerIdForTruck(driverId);

        return truckRepository.save(truck);
    }

    @Override
    public Truck fetch(final Integer driverId) {

        return truckRepository.findByOwnerIdForTruck(driverId);
    }

    @Override
    public void delete(final Integer id) {
        truckRepository.delete(id);
    }

    @Override
    public Truck update(final Truck truck) {

        final Truck saved = truckRepository.findOne(truck.getId());

        truck.setBodyNumber(saved.getBodyNumber());
        truck.setOwnerIdForTruck(truck.getOwnerIdForTruck() == null ? saved.getOwnerIdForTruck() : truck.getOwnerIdForTruck());
        truck.setColor(truck.getColor() == null ? saved.getColor() : truck.getColor());
        truck.setHeight(saved.getHeight());
        truck.setLength(saved.getLength());
        truck.setRegisterSign(truck.getRegisterSign() == null ? saved.getRegisterSign() : truck.getRegisterSign());
        truck.setTrailer(truck.getTrailer() == null ? saved.getTrailer() : truck.getTrailer());
        truck.setWeight(saved.getWeight());
        truck.setYearOfIssue(saved.getYearOfIssue());

        return truckRepository.save(truck);
    }
}
