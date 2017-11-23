package lych.trucks.application.controller;

import lombok.RequiredArgsConstructor;
import lych.trucks.application.dto.request.TrailerRequest;
import lych.trucks.application.dto.response.TrailerResponse;
import lych.trucks.domain.model.Trailer;
import lych.trucks.domain.service.TrailerService;
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
 * Rest controller for {@link Trailer}.
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TrailerController {

    private final DozerBeanMapper dozerBeanMapper;

    private final TrailerService trailerService;

    /**
     * Method for create trailer.
     *
     * @param truckId Truck truckId.
     * @param request TrailerRequest request.
     * @return TrailerResponse response mapped from created trailer.
     */
    @RequestMapping(value = "/companies/{companyId}/drivers/{driverId}/trucks/{truckId}/trailers",
            method = RequestMethod.POST)
    public ResponseEntity create(@PathVariable final Integer truckId, @RequestBody final TrailerRequest request) {

        final Trailer trailerToCreate = dozerBeanMapper.map(request, Trailer.class);

        final Trailer trailerToResponse = trailerService.create(truckId, trailerToCreate);

        final TrailerResponse response = dozerBeanMapper.map(trailerToResponse, TrailerResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Method for update trailer.
     *
     * @param request TrailerRequest request.
     * @return TrailerResponse response mapped from updated trailer.
     */
    @RequestMapping(value = "/companies/{companyId}/drivers/{driverId}/trucks/{truckId}/trailers",
            method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody final TrailerRequest request) {

        final Trailer trailerToUpdate = dozerBeanMapper.map(request, Trailer.class);

        final Trailer trailerToResponse = trailerService.update(trailerToUpdate);

        final TrailerResponse response = dozerBeanMapper.map(trailerToResponse, TrailerResponse.class);

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for display trailer.
     *
     * @param truckId Truck truckId.
     * @return TrailerResponse response mapped from displayed trailer.
     */
    @RequestMapping(value = "/companies/{companyId}/drivers/{driverId}/trucks/{truckId}/trailers",
            method = RequestMethod.GET)
    public ResponseEntity fetch(@PathVariable final Integer truckId) {

        final Trailer trailerToResponse = trailerService.fetch(truckId);

        final TrailerResponse response = dozerBeanMapper.map(trailerToResponse, TrailerResponse.class);

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for delete trailer.
     *
     * @param truckId   Truck truckId.
     * @param trailerId Trailer trailerId.
     * @return TrailerResponse response mapped from deleted trailer.
     */
    @RequestMapping(value = "/companies/{companyId}/drivers/{driverId}/trucks/{truckId}/trailers/{trailerId}",
            method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable final Integer truckId, @PathVariable final Integer trailerId) {

        final Trailer trailerToResponse = trailerService.fetch(truckId);

        trailerService.delete(trailerId);

        final TrailerResponse response = dozerBeanMapper.map(trailerToResponse, TrailerResponse.class);

        return ResponseEntity.ok().body(response);
    }
}
