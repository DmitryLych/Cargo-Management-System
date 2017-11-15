package lych.trucks.application.controller;

import lombok.RequiredArgsConstructor;
import lych.trucks.application.dto.DriverDto;
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
    public ResponseEntity create(@RequestBody final DriverDto driverDto, @PathVariable final Integer companyId) {

        final Driver driverToSave = dozerBeanMapper.map(driverDto, Driver.class);

        final Driver driverToResponse = driverService.create(companyId, driverToSave);

        final DriverDto response = dozerBeanMapper.map(driverToResponse, DriverDto.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @RequestMapping(value = "/companies/{companyId}/drivers/{driverId}", method = RequestMethod.GET)
    public ResponseEntity fetch(@PathVariable final Integer driverId) {

        final Driver driverToResponse = driverService.fetch(driverId);

        final DriverDto response = dozerBeanMapper.map(driverToResponse, DriverDto.class);

        return ResponseEntity.ok().body(response);
    }

    @RequestMapping(value = "/companies/{companyId}/drivers/{driverId}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable final Integer driverId) {

        final Driver driverToResponse = driverService.fetch(driverId);

        driverService.delete(driverId);

        final DriverDto response = dozerBeanMapper.map(driverToResponse, DriverDto.class);

        return ResponseEntity.ok().body(response);
    }

    @RequestMapping(value = "/companies/{companyId}/drivers", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody final DriverDto driverDto) {

        final Driver driverToUpdate = dozerBeanMapper.map(driverDto, Driver.class);

        final Driver driverToResponse = driverService.update(driverToUpdate);

        final DriverDto response = dozerBeanMapper.map(driverToResponse, DriverDto.class);

        return ResponseEntity.ok().body(response);
    }

    @RequestMapping(value = "/companies/{companyId}/drivers", method = RequestMethod.GET)
    public ResponseEntity fetchAll() {

        final List<DriverDto> response = new ArrayList<>();

        driverService.fetchAll().forEach(driver -> response.add(dozerBeanMapper.map(driver, DriverDto.class)));

        return ResponseEntity.ok().body(response);
    }
}
