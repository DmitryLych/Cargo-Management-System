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
    public Truck fetch(Integer id) {
        return truckRepository.findOne(id);
    }

    @Override
    public void delete(Integer id) {
        truckRepository.delete(id);
    }

    @Override
    public Truck update(Truck truck) {
        return truckRepository.save(truck);
    }
}
