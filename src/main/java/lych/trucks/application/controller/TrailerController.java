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
 * Rest controller for {@link Trailer}.
 */
@RestController
@RequestMapping("/cargo/v1/companies/{companyId}/drivers/{driverId}/trucks/{truckId}/trailers")
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
    @PostMapping
    public ResponseEntity createTrailer(@PathVariable final Integer driverId,
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
    @PutMapping
    public ResponseEntity updateTrailer(@RequestBody final TrailerRequest request) {

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
    @GetMapping
    public ResponseEntity fetchTrailer(@PathVariable final Integer truckId) {

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
    @GetMapping(path = "/register/{registerSign}")
    public ResponseEntity fetchTrailerByRegisterSign(@PathVariable final String registerSign) {

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
    @GetMapping(path = "/volume/{volume}")
    public ResponseEntity fetchTrailersByVolume(@PathVariable final Integer volume) {

        final List<Trailer> trailersToResponse = trailerService.fetchByVolume(volume);

        final List<TrailerResponse> response = Optional.ofNullable(trailersToResponse)
                .map(trailers -> trailers.stream()
                        .map(trailer -> dozerBeanMapper.map(trailer, TrailerResponse.class))
                        .collect(toList()))
                .orElse(emptyList());

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for fetch trailers by type.
     *
     * @param type {@link Trailer} trailerType.
     * @return list of {@link TrailerResponse} response mapped from list of trailers which found.
     */
    @GetMapping(path = "/type/{type}")
    public ResponseEntity fetchTrailersByType(@PathVariable final String type) {

        final List<Trailer> trailersToResponse = trailerService.fetchByType(type);

        final List<TrailerResponse> response = Optional.ofNullable(trailersToResponse)
                .map(trailers -> trailers.stream()
                        .map(trailer -> dozerBeanMapper.map(trailer, TrailerResponse.class))
                        .collect(toList()))
                .orElse(emptyList());

        return ResponseEntity.ok().body(response);
    }
}
