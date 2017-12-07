package lych.trucks.application.controller;

import lombok.RequiredArgsConstructor;
import lych.trucks.application.dto.request.DriverRequest;
import lych.trucks.application.dto.response.DriverResponse;
import lych.trucks.domain.model.Driver;
import lych.trucks.domain.service.DriverService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Rest controller for {@link Driver}.
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DriverController {

    private final DriverService driverService;

    private final DozerBeanMapper dozerBeanMapper;

    /**
     * Method for create some driver and add his to the company.
     *
     * @param request   DriverRequest request.
     * @param companyId Company companyId.
     * @return DriverResponse response mapped from created driver.
     */
    @RequestMapping(value = "/companies/{companyId}/drivers", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody final DriverRequest request, @PathVariable final Integer companyId) {

        final Driver driverToSave = dozerBeanMapper.map(request, Driver.class);

        final Driver driverToResponse = driverService.create(companyId, driverToSave);

        final DriverResponse response = dozerBeanMapper.map(driverToResponse, DriverResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Method for display some driver.
     *
     * @param driverId Driver driverId.
     * @return DriverResponse response mapped from found driver.
     */
    @RequestMapping(value = "/companies/{companyId}/drivers/{driverId}", method = RequestMethod.GET)
    public ResponseEntity fetch(@PathVariable final Integer driverId) {

        final Driver driverToResponse = driverService.fetch(driverId);

        final DriverResponse response = dozerBeanMapper.map(driverToResponse, DriverResponse.class);

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for delete some driver.
     *
     * @param driverId Driver driverId.
     * @return DriverResponse response mapped from deleted driver.
     */
    @RequestMapping(value = "/companies/{companyId}/drivers/{driverId}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable final Integer driverId) {

        final Driver driverToResponse = driverService.delete(driverId);

        final DriverResponse response = dozerBeanMapper.map(driverToResponse, DriverResponse.class);

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for update driver.
     *
     * @param request DriverRequest request.
     * @return DriverResponse response mapped from updated driver.
     */
    @RequestMapping(value = "/companies/{companyId}/drivers", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody final DriverRequest request) {

        final Driver driverToUpdate = dozerBeanMapper.map(request, Driver.class);

        final Driver driverToResponse = driverService.update(driverToUpdate);

        final DriverResponse response = dozerBeanMapper.map(driverToResponse, DriverResponse.class);

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for display all drivers of the some company.
     *
     * @param companyId Company companyId.
     * @return List of DriverResponse response mapped from found drivers.
     */
    @RequestMapping(value = "/companies/{companyId}/drivers", method = RequestMethod.GET)
    public ResponseEntity fetchAll(@PathVariable final Integer companyId) {

        final List<DriverResponse> response = new ArrayList<>();

        final List<Driver> driversToResponse = driverService.fetchAll(companyId);

        driversToResponse.forEach(driver ->
                response.add(dozerBeanMapper.map(driver, DriverResponse.class)));

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for fetch drivers by last name and first name.
     *
     * @param lastName  {@link Driver} lastName.
     * @param firstName {@link Driver} firstName.
     * @return list of {@link DriverResponse} response mapped from list of drivers which found.
     */
    @RequestMapping(value = "/companies/{companyId}/drivers/lastName/{lastName}/firstName/{firstName}",
            method = RequestMethod.GET)
    public ResponseEntity fetchByLastNameAndFirstName(@PathVariable final String lastName,
                                                      @PathVariable final String firstName) {

        final List<DriverResponse> response = new ArrayList<>();

        final List<Driver> driversToResponse = driverService.fetchByLastNameAndFirstName(lastName, firstName);

        driversToResponse.forEach(driver -> response.add(dozerBeanMapper.map(driver, DriverResponse.class)));

        return ResponseEntity.ok().body(response);
    }
}
