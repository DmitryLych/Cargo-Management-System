package lych.trucks.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lych.trucks.domain.model.Driver;
import lych.trucks.domain.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultDriverService implements DriverService {

    private final DriverRepository driverRepository;


    @Override
    public List<Driver> fetchAll(Integer ownerId) {
        return driverRepository.findAllByOwnerId(ownerId);
    }

    @Override
    public Driver create(Integer companyId, Driver driver) {

        log.info("Driver was added.");

        driver.setOwnerId(companyId);

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
