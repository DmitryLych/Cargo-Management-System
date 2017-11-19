package lych.trucks.domain.service;

import lombok.RequiredArgsConstructor;
import lych.trucks.domain.model.Company;
import lych.trucks.domain.model.Driver;
import lych.trucks.domain.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultDriverService implements DriverService {

    private final DriverRepository driverRepository;

    private final CompanyService companyService;

    @Override
    public List<Driver> fetchAll() {

        return driverRepository.findAll();
    }

    @Override
    public Driver create(Integer companyId, Driver driver) {

        final Company companyToUpdate = companyService.fetch(companyId);

        final List<Driver> driversToUpdate = companyToUpdate.getDrivers();

        driversToUpdate.add(driver);

        companyToUpdate.setDrivers(driversToUpdate);

        driver.setCompany(companyToUpdate);

        companyService.update(companyToUpdate);



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
