package lych.trucks.application.controller;

import lombok.RequiredArgsConstructor;
import lych.trucks.application.dto.request.TruckRequest;
import lych.trucks.application.dto.response.TruckResponse;
import lych.trucks.domain.model.Truck;
import lych.trucks.domain.service.TruckService;
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

/**
 * Rest controller for {@link Truck}.
 */
@RestController
@RequestMapping("/cargo/v1/companies/{companyId}/drivers/{driverId}/trucks")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TruckController {

    private final DozerBeanMapper dozerBeanMapper;

    private final TruckService truckService;

    /**
     * Method for create truck.
     *
     * @param driverId Driver driverId.
     * @param request  TruckRequest request.
     * @return TruckResponse response mapped from created truck.
     */
    @PostMapping
    public ResponseEntity createTruck(@PathVariable final Integer driverId, @RequestBody final TruckRequest request) {

        final Truck truckToCreate = dozerBeanMapper.map(request, Truck.class);

        final Truck truckToResponse = truckService.create(driverId, truckToCreate);

        final TruckResponse response = dozerBeanMapper.map(truckToResponse, TruckResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Method for update truck.
     *
     * @param request TruckRequest request.
     * @return TruckResponse response mapped from updated truck.
     */
    @PutMapping
    public ResponseEntity updateTruck(@RequestBody final TruckRequest request) {

        final Truck truckToUpdate = dozerBeanMapper.map(request, Truck.class);

        final Truck truckToResponse = truckService.update(truckToUpdate);

        final TruckResponse response = dozerBeanMapper.map(truckToResponse, TruckResponse.class);

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for display some truck.
     *
     * @param driverId Driver driverId.
     * @return TruckResponse response mapped from found truck.
     */
    @GetMapping
    public ResponseEntity fetchTruck(@PathVariable final Integer driverId) {

        final Truck truckToResponse = truckService.fetch(driverId);

        final TruckResponse response = dozerBeanMapper.map(truckToResponse, TruckResponse.class);

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for fetch truck by register sign.
     *
     * @param registerSign {@link Truck} registerSign.
     * @return {@link TruckResponse} response mapped from truck which found.
     */
    @GetMapping(path = "/register/{registerSign}")
    public ResponseEntity fetchTruckByRegisterSign(@PathVariable final String registerSign) {

        final Truck truckToResponse = truckService.fetchByRegisterSign(registerSign);

        final TruckResponse response = dozerBeanMapper.map(truckToResponse, TruckResponse.class);

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for fetch truck by body number.
     *
     * @param bodyNumber {@link Truck} bodyNumber.
     * @return {@link TruckResponse} response mapped from truck which found.
     */
    @GetMapping(path = "/number/{bodyNumber}")
    public ResponseEntity fetchTruckByBodyNumber(@PathVariable final String bodyNumber) {

        final Truck truckToResponse = truckService.fetchByBodyNumber(bodyNumber);

        final TruckResponse response = dozerBeanMapper.map(truckToResponse, TruckResponse.class);

        return ResponseEntity.ok().body(response);
    }
}
