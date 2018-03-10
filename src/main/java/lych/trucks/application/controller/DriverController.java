package lych.trucks.application.controller;

import lombok.RequiredArgsConstructor;
import lych.trucks.application.dto.request.DriverRequest;
import lych.trucks.application.dto.response.DriverResponse;
import lych.trucks.domain.model.Driver;
import lych.trucks.domain.service.DriverService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

/**
 * Rest controller for {@link Driver}.
 */
@RestController
@RequestMapping("/cargo/v1/companies/{companyId}/drivers/{userId}")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DriverController {

    private final DriverService driverService;

    private final DozerBeanMapper dozerBeanMapper;

    /**
     * Method for create some driver and add his to the company.
     *
     * @param userId    a user id.
     * @param request   DriverRequest request.
     * @param companyId Company companyId.
     * @return DriverResponse response mapped from created driver.
     */
    @PostMapping
    public ResponseEntity createDriver(@PathVariable final Integer userId,
                                       @RequestBody final DriverRequest request,
                                       @PathVariable final Integer companyId) {
        final Driver driverToSave = dozerBeanMapper.map(request, Driver.class);
        final Driver driverToResponse = driverService.createDriver(userId, companyId, driverToSave);

        final DriverResponse response = dozerBeanMapper.map(driverToResponse, DriverResponse.class);
        return ResponseEntity.ok(response);
    }

    /**
     * Method for display some driver.
     *
     * @param driverId Driver driverId.
     * @return DriverResponse response mapped from found driver.
     */
    @GetMapping(path = "/{driverId}")
    public ResponseEntity fetchDriver(@PathVariable final Integer driverId) {
        final Driver driverToResponse = driverService.fetchDriver(driverId);

        final DriverResponse response = dozerBeanMapper.map(driverToResponse, DriverResponse.class);
        return ResponseEntity.ok(response);
    }

    /**
     * Method for delete some driver.
     *
     * @param driverId Driver driverId.
     * @return DriverResponse response mapped from deleted driver.
     */
    @DeleteMapping(path = "/{driverId}")
    public ResponseEntity deleteDriver(@PathVariable final Integer driverId) {
        final Driver driverToResponse = driverService.deleteDriver(driverId);

        final DriverResponse response = dozerBeanMapper.map(driverToResponse, DriverResponse.class);
        return ResponseEntity.ok(response);
    }

    /**
     * Method for update driver.
     *
     * @param request DriverRequest request.
     * @return DriverResponse response mapped from updated driver.
     */
    @PutMapping
    public ResponseEntity updateDriver(@RequestBody final DriverRequest request) {
        final Driver driverToUpdate = dozerBeanMapper.map(request, Driver.class);
        final Driver driverToResponse = driverService.updateDriver(driverToUpdate);

        final DriverResponse response = dozerBeanMapper.map(driverToResponse, DriverResponse.class);
        return ResponseEntity.ok(response);
    }

    /**
     * Method for display all drivers of the some company.
     *
     * @param companyId Company companyId.
     * @return List of DriverResponse response mapped from found drivers.
     */
    @GetMapping
    public ResponseEntity fetchAllDrivers(@PathVariable final Integer companyId) {
        final List<Driver> driversToResponse = driverService.fetchAllDrivers(companyId);

        final List<DriverResponse> response = driversToResponse.stream()
                .map(driver -> dozerBeanMapper.map(driver, DriverResponse.class))
                .collect(toList());
        return ResponseEntity.ok(response);
    }

    /**
     * Method for fetch drivers by last name and first name.
     *
     * @param lastName  {@link Driver} lastName.
     * @param firstName {@link Driver} firstName.
     * @return list of {@link DriverResponse} response mapped from list of drivers which found.
     */
    @GetMapping(path = "/lastName/{lastName}/firstName/{firstName}")
    public ResponseEntity fetchByLastNameAndFirstName(@PathVariable final String lastName,
                                                      @PathVariable final String firstName) {

        final List<Driver> driversToResponse = driverService.fetchDriversByLastNameAndFirstName(lastName, firstName);

        final List<DriverResponse> response = Optional.ofNullable(driversToResponse)
                .map(drivers -> drivers.stream()
                        .map(driver -> dozerBeanMapper.map(driver, DriverResponse.class))
                        .collect(toList()))
                .orElse(emptyList());

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for fetch drivers by status.
     *
     * @param status a status.
     * @return list of {@link DriverResponse} response mapped from list of drivers which found.
     */
    @GetMapping(path = "/status/{status}")
    public ResponseEntity fetchByStatus(@PathVariable final boolean status) {

        final List<Driver> driversToResponse = driverService.fetchDriversByStatus(status);

        final List<DriverResponse> response = Optional.ofNullable(driversToResponse)
                .map(drivers -> drivers.stream()
                        .map(driver -> dozerBeanMapper.map(driver, DriverResponse.class))
                        .collect(toList()))
                .orElse(emptyList());

        return ResponseEntity.ok().body(response);
    }
}
