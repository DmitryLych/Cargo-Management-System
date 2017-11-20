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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TruckController {

    private final DozerBeanMapper dozerBeanMapper;

    private final TruckService truckService;

    @RequestMapping(value = "/companies/{companyId}/drivers/{driverId}/trucks", method = RequestMethod.POST)
    public ResponseEntity create(@PathVariable final Integer driverId, @RequestBody final TruckRequest request) {

        final Truck truckToCreate = dozerBeanMapper.map(request, Truck.class);

        final Truck truckToResponse = truckService.create(driverId, truckToCreate);

        final TruckResponse response = dozerBeanMapper.map(truckToResponse, TruckResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @RequestMapping(value = "/companies/{companyId}/drivers/{driverId}/trucks", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody final TruckRequest request) {

        final Truck truckToUpdate = dozerBeanMapper.map(request, Truck.class);

        final Truck truckToResponse = truckService.update(truckToUpdate);

        final TruckResponse response = dozerBeanMapper.map(truckToResponse, TruckResponse.class);

        return ResponseEntity.ok().body(response);
    }

    @RequestMapping(value = "/companies/{companyId}/drivers/{driverId}/trucks", method = RequestMethod.GET)
    public ResponseEntity fetch(@PathVariable final Integer driverId) {

        final Truck truckToResponse = truckService.fetch(driverId);

        final TruckResponse response = dozerBeanMapper.map(truckToResponse, TruckResponse.class);

        return ResponseEntity.ok().body(response);
    }

    @RequestMapping(value = "/companies/{companyId}/drivers/{driverId}/trucks/{truckId}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable final Integer truckId, @PathVariable final Integer driverId) {

        final Truck truckToResponse = truckService.fetch(driverId);

        truckService.delete(truckId);

        final TruckResponse response = dozerBeanMapper.map(truckToResponse, TruckResponse.class);

        return ResponseEntity.ok().body(response);
    }
}
