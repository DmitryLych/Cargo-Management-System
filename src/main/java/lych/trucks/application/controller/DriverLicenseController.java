package lych.trucks.application.controller;

import lombok.RequiredArgsConstructor;
import lych.trucks.application.dto.request.DriverLicenseRequest;
import lych.trucks.application.dto.response.DriverLicenseResponse;
import lych.trucks.domain.model.DriverLicense;
import lych.trucks.domain.service.DriverLicenseService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DriverLicenseController {

    private final DozerBeanMapper dozerBeanMapper;

    private final DriverLicenseService driverLicenseService;

    @RequestMapping(value = "/companies/{companyId}/drivers/{driverId}/licenses", method = RequestMethod.POST)
    public ResponseEntity create(@PathVariable final Integer driverId, @RequestBody final DriverLicenseRequest request) {

        final DriverLicense driverLicenseToCreate = dozerBeanMapper.map(request, DriverLicense.class);

        final DriverLicense driverLicenseToResponse = driverLicenseService.create(driverId, driverLicenseToCreate);

        final DriverLicenseResponse response = dozerBeanMapper.map(driverLicenseToResponse, DriverLicenseResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @RequestMapping(value = "/companies/{companyId}/drivers/{driverId}/licenses", method = RequestMethod.GET)
    public ResponseEntity fetch(@PathVariable final Integer driverId) {

        final DriverLicense driverLicenseToResponse = driverLicenseService.fetch(driverId);

        final DriverLicenseResponse response = dozerBeanMapper.map(driverLicenseToResponse, DriverLicenseResponse.class);

        return ResponseEntity.ok().body(response);
    }

    @RequestMapping(value = "/companies/{companyId}/drivers/{driverId}/licenses", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody final DriverLicenseRequest request) {

        final DriverLicense driverLicenseToUpdate = dozerBeanMapper.map(request, DriverLicense.class);

        final DriverLicense driverLicenseToResponse = driverLicenseService.update(driverLicenseToUpdate);

        final DriverLicenseResponse response = dozerBeanMapper.map(driverLicenseToResponse, DriverLicenseResponse.class);

        return ResponseEntity.ok().body(response);
    }

    @RequestMapping(value = "/companies/{companyId}/drivers/{driverId}/licenses/{licenseId}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable final Integer licenseId, @PathVariable final Integer driverId) {

        final DriverLicense driverLicenseToResponse = driverLicenseService.fetch(driverId);

        driverLicenseService.delete(licenseId);

        final DriverLicenseResponse response = dozerBeanMapper.map(driverLicenseToResponse, DriverLicenseResponse.class);

        return ResponseEntity.ok().body(response);
    }
}
