package lych.trucks.domain.service;

import lombok.RequiredArgsConstructor;
import lych.trucks.domain.model.Driver;
import lych.trucks.domain.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultDriverService implements DriverService {

    private final DriverRepository driverRepository;

    @Override
    public List<Driver> fetchAll() {
        return driverRepository.findAll();
    }

    @Override
    public Driver create(Driver driver) {
        return driverRepository.save(driver);
    }

    @Override
    public Driver fetch(Integer id) {
        return driverRepository.findOne(id);
    }

    @Override
    public void delete(Integer id) {
        driverRepository.delete(id);
    }

    @Override
    public Driver update(Driver driver) {
        return driverRepository.save(driver);
    }
}
