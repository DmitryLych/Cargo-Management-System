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

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DriverController {

    private final DriverService driverService;

    private final DozerBeanMapper dozerBeanMapper;

    @RequestMapping(value = "/companies/{companyId}/drivers", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody final DriverRequest driverRequest, @PathVariable final Integer companyId) {

        final Driver driverToSave = dozerBeanMapper.map(driverRequest, Driver.class);

        final Driver driverToResponse = driverService.create(companyId, driverToSave);

        final DriverResponse response = dozerBeanMapper.map(driverToResponse, DriverResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @RequestMapping(value = "/companies/{companyId}/drivers/{driverId}", method = RequestMethod.GET)
    public ResponseEntity fetch(@PathVariable final Integer driverId) {

        final Driver driverToResponse = driverService.fetch(driverId);

        final DriverResponse response = dozerBeanMapper.map(driverToResponse, DriverResponse.class);

        return ResponseEntity.ok().body(response);
    }

    @RequestMapping(value = "/companies/{companyId}/drivers/{driverId}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable final Integer driverId) {

        final Driver driverToResponse = driverService.fetch(driverId);

        driverService.delete(driverId);

        final DriverResponse response = dozerBeanMapper.map(driverToResponse, DriverResponse.class);

        return ResponseEntity.ok().body(response);
    }

    @RequestMapping(value = "/companies/{companyId}/drivers", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody final DriverRequest driverRequest) {

        final Driver driverToUpdate = dozerBeanMapper.map(driverRequest, Driver.class);

        final Driver driverToResponse = driverService.update(driverToUpdate);

        final DriverResponse response = dozerBeanMapper.map(driverToResponse, DriverResponse.class);

        return ResponseEntity.ok().body(response);
    }

    @RequestMapping(value = "/companies/{companyId}/drivers", method = RequestMethod.GET)
    public ResponseEntity fetchAll(@PathVariable final Integer companyId) {

        final List<DriverResponse> response = new ArrayList<>();

        driverService.fetchAll(companyId).forEach(driver -> response.add(dozerBeanMapper.map(driver, DriverResponse.class)));

        return ResponseEntity.ok().body(response);
    }
}
