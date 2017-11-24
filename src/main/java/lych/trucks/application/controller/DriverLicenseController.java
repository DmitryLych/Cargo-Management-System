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

/**
 * Rest controller for {@link DriverLicense}.
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DriverLicenseController {

    private final DozerBeanMapper dozerBeanMapper;

    private final DriverLicenseService driverLicenseService;

    /**
     * Method for created driver license.
     *
     * @param driverId Driver driverId.
     * @param request  DriverLicenseRequest.
     * @return DriverLicenseResponse response mapped from created driver license.
     */
    @RequestMapping(value = "/companies/{companyId}/drivers/{driverId}/licenses", method = RequestMethod.POST)
    public ResponseEntity create(@PathVariable final Integer driverId, @RequestBody final DriverLicenseRequest request) {

        final DriverLicense driverLicenseToCreate = dozerBeanMapper.map(request, DriverLicense.class);

        final DriverLicense driverLicenseToResponse = driverLicenseService.create(driverId, driverLicenseToCreate);

        final DriverLicenseResponse response = dozerBeanMapper.map(driverLicenseToResponse, DriverLicenseResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Method for display driver license.
     *
     * @param driverId Driver driverId.
     * @return DriverLicenseResponse response mapped from found driver license.
     */
    @RequestMapping(value = "/companies/{companyId}/drivers/{driverId}/licenses", method = RequestMethod.GET)
    public ResponseEntity fetch(@PathVariable final Integer driverId) {

        final DriverLicense driverLicenseToResponse = driverLicenseService.fetch(driverId);

        final DriverLicenseResponse response = dozerBeanMapper.map(driverLicenseToResponse, DriverLicenseResponse.class);

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for update driver license.
     *
     * @param request DriverLicenseRequest request.
     * @return DriverLicenseResponse response mapped from updated driver license.
     */
    @RequestMapping(value = "/companies/{companyId}/drivers/{driverId}/licenses", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody final DriverLicenseRequest request) {

        final DriverLicense driverLicenseToUpdate = dozerBeanMapper.map(request, DriverLicense.class);

        final DriverLicense driverLicenseToResponse = driverLicenseService.update(driverLicenseToUpdate);

        final DriverLicenseResponse response = dozerBeanMapper.map(driverLicenseToResponse, DriverLicenseResponse.class);

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for delete driver license.
     *
     * @param licenseId DriverLicense licenseId.
     * @param driverId  Driver driverId.
     * @return deleted driver license.
     */
    @RequestMapping(value = "/companies/{companyId}/drivers/{driverId}/licenses/{licenseId}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable final Integer licenseId, @PathVariable final Integer driverId) {

        final DriverLicense driverLicenseToResponse = driverLicenseService.fetch(driverId);

        driverLicenseService.delete(licenseId);

        final DriverLicenseResponse response = dozerBeanMapper.map(driverLicenseToResponse, DriverLicenseResponse.class);

        return ResponseEntity.ok().body(response);
    }
}
