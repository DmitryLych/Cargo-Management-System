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

import java.util.ArrayList;
import java.util.List;

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
     * @param driverId Driver driverId.
     * @param truckId  Truck truckId.
     * @param request  TrailerRequest request.
     * @return TrailerResponse response mapped from created trailer.
     */
    @RequestMapping(value = "/companies/{companyId}/drivers/{driverId}/trucks/{truckId}/trailers",
            method = RequestMethod.POST)
    public ResponseEntity create(@PathVariable final Integer driverId,
                                 @PathVariable final Integer truckId, @RequestBody final TrailerRequest request) {

        final Trailer trailerToCreate = dozerBeanMapper.map(request, Trailer.class);

        final Trailer trailerToResponse = trailerService.create(driverId, truckId, trailerToCreate);

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
     * Method for fetch some trailer by register sign.
     *
     * @param registerSign {@link Trailer} registerSign.
     * @return {@link TrailerResponse} response mapped from trailer which found.
     */
    @RequestMapping(value = "/companies/{companyId}/drivers/{driverId}/trucks/{truckId}/trailers/register/"
            + "{registerSign}",
            method = RequestMethod.GET)
    public ResponseEntity fetchByRegisterSign(@PathVariable final String registerSign) {

        final Trailer trailerToResponse = trailerService.fetchByRegisterSign(registerSign);

        final TrailerResponse response = dozerBeanMapper.map(trailerToResponse, TrailerResponse.class);

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for fetch trailers by volume.
     *
     * @param volume {@link Trailer} volume.
     * @return list of {@link TrailerResponse} response mapped from list of trailers which found.
     */
    @RequestMapping(value = "/companies/{companyId}/drivers/{driverId}/trucks/{truckId}/trailers/volume/"
            + "{volume}",
            method = RequestMethod.GET)
    public ResponseEntity fetchByVolume(@PathVariable final Integer volume) {

        final List<Trailer> trailersToResponse = trailerService.fetchByVolume(volume);

        final List<TrailerResponse> response = new ArrayList<>();

        trailersToResponse.forEach(trailer -> response.add(dozerBeanMapper.map(trailer, TrailerResponse.class)));

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for fetch trailers by type.
     *
     * @param type {@link Trailer} trailerType.
     * @return list of {@link TrailerResponse} response mapped from list of trailers which found.
     */
    @RequestMapping(value = "/companies/{companyId}/drivers/{driverId}/trucks/{truckId}/trailers/type/"
            + "{type}",
            method = RequestMethod.GET)
    public ResponseEntity fetchByType(@PathVariable final String type) {

        final List<Trailer> trailersToResponse = trailerService.fetchByType(type);

        final List<TrailerResponse> response = new ArrayList<>();

        trailersToResponse.forEach(trailer -> response.add(dozerBeanMapper.map(trailer, TrailerResponse.class)));

        return ResponseEntity.ok().body(response);
    }
}
