package lych.trucks.service;

import lombok.RequiredArgsConstructor;
import lych.trucks.model.Truck;
import lych.trucks.repository.TruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultTruckService implements TruckService {

    private final TruckRepository truckRepository;

    @Override
    public List<Truck> trucks() {
        return truckRepository.findAll();
    }

    @Override
    public Truck create(Truck truck) {
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
