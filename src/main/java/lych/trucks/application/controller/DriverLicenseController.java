package lych.trucks.application.controller;

import lombok.RequiredArgsConstructor;
import lych.trucks.application.dto.request.DriverLicenseRequest;
import lych.trucks.application.dto.response.DriverLicenseResponse;
import lych.trucks.domain.model.DriverLicense;
import lych.trucks.domain.service.DriverLicenseService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Rest controller for {@link DriverLicense}.
 */
@RestController
@RequestMapping("/cargo/v1/{userId}/companies/{companyId}/drivers/{driverId}/licenses")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DriverLicenseController {

    private final DozerBeanMapper dozerBeanMapper;

    private final DriverLicenseService driverLicenseService;

    /**
     * Method for created driver license.
     *
     * @param userId   a user id.
     * @param driverId Driver driverId.
     * @param request  DriverLicenseRequest.
     * @return DriverLicenseResponse response mapped from created driver license.
     */
    @PostMapping
    @PreAuthorize("@defaultDriverService.canAccess(#userId,#driverId)")
    public ResponseEntity createDriverLicense(@PathVariable final Integer userId,
                                              @PathVariable final Integer driverId,
                                              @RequestBody final DriverLicenseRequest request) {
        final DriverLicense driverLicenseToCreate = dozerBeanMapper.map(request, DriverLicense.class);
        final DriverLicense driverLicenseToResponse = driverLicenseService.createDriverLicense(driverId, driverLicenseToCreate);

        final DriverLicenseResponse response = dozerBeanMapper.map(driverLicenseToResponse,
                DriverLicenseResponse.class);
        return ResponseEntity.ok(response);
    }

    /**
     * Method for display driver license.
     *
     * @param userId   a user id.
     * @param driverId Driver driverId.
     * @return DriverLicenseResponse response mapped from found driver license.
     */
    @GetMapping
    @PreAuthorize("@defaultDriverService.canAccess(#userId,#driverId)")
    public ResponseEntity fetchDriverLicense(@PathVariable final Integer userId,
                                             @PathVariable final Integer driverId) {
        final DriverLicense driverLicenseToResponse = driverLicenseService.fetchDriverLicense(driverId);

        final DriverLicenseResponse response = dozerBeanMapper.map(driverLicenseToResponse,
                DriverLicenseResponse.class);
        return ResponseEntity.ok(response);
    }

    /**
     * Method for update driver license.
     *
     * @param userId   a user id.
     * @param driverId a driver id.
     * @param request  DriverLicenseRequest request.
     * @return DriverLicenseResponse response mapped from updated driver license.
     */
    @PutMapping
    @PreAuthorize("@defaultDriverService.canAccess(#userId,#driverId)")
    public ResponseEntity updateDriverLicense(@PathVariable final Integer userId,
                                              @PathVariable final Integer driverId,
                                              @RequestBody final DriverLicenseRequest request) {
        final DriverLicense driverLicenseToUpdate = dozerBeanMapper.map(request, DriverLicense.class);
        final DriverLicense driverLicenseToResponse = driverLicenseService.updateDriverLicense(driverLicenseToUpdate);

        final DriverLicenseResponse response = dozerBeanMapper.map(driverLicenseToResponse,
                DriverLicenseResponse.class);
        return ResponseEntity.ok(response);
    }

    /**
     * Method for fetch driver licenses by category.
     *
     * @param userId   a user id.
     * @param driverId a driver id.
     * @param category {@link DriverLicense} category.
     * @return list of {@link DriverLicenseResponse} response mapped from list of driver license which found.
     */
    @GetMapping(path = "/category/{category}")
    @PreAuthorize("@defaultDriverService.canAccess(#userId,#driverId)")
    public ResponseEntity fetchDriverLicensesByCategory(@PathVariable final Integer userId,
                                                        @PathVariable final Integer driverId,
                                                        @PathVariable final String category) {
        final List<DriverLicense> driverLicensesToResponse = driverLicenseService
                .fetchDriverLicensesByCategory(category);

        final List<DriverLicenseResponse> response = driverLicensesToResponse.stream()
                .map(driverLicense -> dozerBeanMapper.map(driverLicense, DriverLicenseResponse.class))
                .collect(toList());
        return ResponseEntity.ok(response);
    }

    /**
     * Method for fetch driver licenses by special notes.
     *
     * @param userId       a user id.
     * @param driverId     a driver id.
     * @param specialNotes {@link DriverLicense} specialNotes.
     * @return list of {@link DriverLicenseResponse} response mapped from list of driver license which found.
     */
    @GetMapping(path = "/specialNotes/{specialNotes}")
    @PreAuthorize("@defaultDriverService.canAccess(#userId,#driverId)")
    public ResponseEntity fetchDriverLicensesBySpecialNotes(@PathVariable final Integer userId,
                                                            @PathVariable final Integer driverId,
                                                            @PathVariable final String specialNotes) {
        final List<DriverLicense> driverLicensesToResponse = driverLicenseService
                .fetchDriverLicensesBySpecialNotes(specialNotes);

        final List<DriverLicenseResponse> response = driverLicensesToResponse.stream()
                .map(driverLicense -> dozerBeanMapper.map(driverLicense, DriverLicenseResponse.class))
                .collect(toList());
        return ResponseEntity.ok(response);
    }
}
