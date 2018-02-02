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
 * Rest controller for {@link DriverLicense}.
 */
@RestController
@RequestMapping("/cargo/v1/companies/{companyId}/drivers/{driverId}/licenses")
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
    @PostMapping
    public ResponseEntity createDriverLicense(@PathVariable final Integer driverId,
                                              @RequestBody final DriverLicenseRequest request) {

        final DriverLicense driverLicenseToCreate = dozerBeanMapper.map(request, DriverLicense.class);

        final DriverLicense driverLicenseToResponse = driverLicenseService.create(driverId, driverLicenseToCreate);

        final DriverLicenseResponse response = dozerBeanMapper.map(driverLicenseToResponse,
                DriverLicenseResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Method for display driver license.
     *
     * @param driverId Driver driverId.
     * @return DriverLicenseResponse response mapped from found driver license.
     */
    @GetMapping
    public ResponseEntity fetchDriverLicense(@PathVariable final Integer driverId) {

        final DriverLicense driverLicenseToResponse = driverLicenseService.fetch(driverId);

        final DriverLicenseResponse response = dozerBeanMapper.map(driverLicenseToResponse,
                DriverLicenseResponse.class);

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for update driver license.
     *
     * @param request DriverLicenseRequest request.
     * @return DriverLicenseResponse response mapped from updated driver license.
     */
    @PutMapping
    public ResponseEntity updateDriverLicense(@RequestBody final DriverLicenseRequest request) {

        final DriverLicense driverLicenseToUpdate = dozerBeanMapper.map(request, DriverLicense.class);

        final DriverLicense driverLicenseToResponse = driverLicenseService.update(driverLicenseToUpdate);

        final DriverLicenseResponse response = dozerBeanMapper.map(driverLicenseToResponse, DriverLicenseResponse.class);

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for fetch driver licenses by category.
     *
     * @param category {@link DriverLicense} category.
     * @return list of {@link DriverLicenseResponse} response mapped from list of driver license which found.
     */
    @GetMapping(path = "/category/{category}")
    public ResponseEntity fetchDriverLicensesByCategory(@PathVariable final String category) {

        final List<DriverLicense> driverLicensesToResponse = driverLicenseService.fetchByCategory(category);

        final List<DriverLicenseResponse> response = Optional.ofNullable(driverLicensesToResponse)
                .map(driverLicenses -> driverLicenses.stream()
                        .map(driverLicense -> dozerBeanMapper.map(driverLicense, DriverLicenseResponse.class))
                        .collect(toList()))
                .orElse(emptyList());

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for fetch driver licenses by special notes.
     *
     * @param specialNotes {@link DriverLicense} specialNotes.
     * @return list of {@link DriverLicenseResponse} response mapped from list of driver license which found.
     */
    @GetMapping(path = "/specialNotes/{specialNotes}")
    public ResponseEntity fetchDriverLicensesBySpecialNotes(@PathVariable final String specialNotes) {

        final List<DriverLicense> driverLicensesToResponse = driverLicenseService
                .fetchBySpecialNotes(specialNotes);

        final List<DriverLicenseResponse> response = Optional.ofNullable(driverLicensesToResponse)
                .map(driverLicenses -> driverLicenses.stream()
                        .map(driverLicense -> dozerBeanMapper.map(driverLicense, DriverLicenseResponse.class))
                        .collect(toList()))
                .orElse(emptyList());

        return ResponseEntity.ok().body(response);
    }
}
