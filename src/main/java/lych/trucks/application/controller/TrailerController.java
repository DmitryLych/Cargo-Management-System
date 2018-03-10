package lych.trucks.application.controller;

import lombok.RequiredArgsConstructor;
import lych.trucks.application.dto.request.TrailerRequest;
import lych.trucks.application.dto.response.TrailerResponse;
import lych.trucks.domain.model.Trailer;
import lych.trucks.domain.service.TrailerService;
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
 * Rest controller for {@link Trailer}.
 */
@RestController
@RequestMapping("/cargo/v1/{userId}/companies/{companyId}/drivers/{driverId}/trucks/{truckId}/trailers")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TrailerController {

    private final DozerBeanMapper dozerBeanMapper;

    private final TrailerService trailerService;

    /**
     * Method for create trailer.
     *
     * @param userId    a user id.
     * @param companyId a company id.
     * @param driverId  Driver driverId.
     * @param truckId   Truck truckId.
     * @param request   TrailerRequest request.
     * @return TrailerResponse response mapped from created trailer.
     */
    @PostMapping
    @PreAuthorize("@defaultCompanyService.canAccess(#userId,#companyId)")
    public ResponseEntity createTrailer(@PathVariable final Integer companyId,
                                        @PathVariable final Integer userId,
                                        @PathVariable final Integer driverId,
                                        @PathVariable final Integer truckId,
                                        @RequestBody final TrailerRequest request) {
        final Trailer trailerToCreate = dozerBeanMapper.map(request, Trailer.class);
        final Trailer trailerToResponse = trailerService.createTrailer(driverId, truckId, trailerToCreate);

        final TrailerResponse response = dozerBeanMapper.map(trailerToResponse, TrailerResponse.class);
        return ResponseEntity.ok(response);
    }

    /**
     * Method for update trailer.
     *
     * @param userId    a user id.
     * @param companyId a company id.
     * @param request   TrailerRequest request.
     * @return TrailerResponse response mapped from updated trailer.
     */
    @PutMapping
    @PreAuthorize("@defaultCompanyService.canAccess(#userId,#companyId)")
    public ResponseEntity updateTrailer(@PathVariable final Integer userId,
                                        @PathVariable final Integer companyId,
                                        @RequestBody final TrailerRequest request) {
        final Trailer trailerToUpdate = dozerBeanMapper.map(request, Trailer.class);
        final Trailer trailerToResponse = trailerService.updateTrailer(trailerToUpdate);

        final TrailerResponse response = dozerBeanMapper.map(trailerToResponse, TrailerResponse.class);
        return ResponseEntity.ok(response);
    }

    /**
     * Method for display trailer.
     *
     * @param userId   a user id.
     * @param driverId a driver id.
     * @param truckId  Truck truckId.
     * @return TrailerResponse response mapped from displayed trailer.
     */
    @GetMapping
    @PreAuthorize("@defaultDriverService.canAccess(#userId,#driverId)")
    public ResponseEntity fetchTrailer(@PathVariable final Integer userId,
                                       @PathVariable final Integer driverId,
                                       @PathVariable final Integer truckId) {
        final Trailer trailerToResponse = trailerService.fetchTrailer(truckId);

        final TrailerResponse response = dozerBeanMapper.map(trailerToResponse, TrailerResponse.class);
        return ResponseEntity.ok(response);
    }

    /**
     * Method for fetch some trailer by register sign.
     *
     * @param userId       a user id.
     * @param companyId    a company id.
     * @param registerSign {@link Trailer} registerSign.
     * @return {@link TrailerResponse} response mapped from trailer which found.
     */
    @GetMapping(path = "/register/{registerSign}")
    @PreAuthorize("@defaultCompanyService.canAccess(#userId,#companyId)")
    public ResponseEntity fetchTrailerByRegisterSign(@PathVariable final Integer userId,
                                                     @PathVariable final Integer companyId,
                                                     @PathVariable final String registerSign) {
        final Trailer trailerToResponse = trailerService.fetchTrailerByRegisterSign(registerSign);

        final TrailerResponse response = dozerBeanMapper.map(trailerToResponse, TrailerResponse.class);
        return ResponseEntity.ok(response);
    }

    /**
     * Method for fetch trailers by volume.
     *
     * @param userId    a user id.
     * @param companyId a company id.
     * @param volume    {@link Trailer} volume.
     * @return list of {@link TrailerResponse} response mapped from list of trailers which found.
     */
    @GetMapping(path = "/volume/{volume}")
    @PreAuthorize("@defaultCompanyService.canAccess(#userId,#companyId)")
    public ResponseEntity fetchTrailersByVolume(@PathVariable final Integer userId,
                                                @PathVariable final Integer companyId,
                                                @PathVariable final Integer volume) {
        final List<Trailer> trailersToResponse = trailerService.fetchTrailersByVolume(volume);

        final List<TrailerResponse> response = trailersToResponse.stream()
                .map(trailer -> dozerBeanMapper.map(trailer, TrailerResponse.class))
                .collect(toList());
        return ResponseEntity.ok(response);
    }

    /**
     * Method for fetch trailers by type.
     *
     * @param userId    a user id.
     * @param companyId a company id.
     * @param type      {@link Trailer} trailerType.
     * @return list of {@link TrailerResponse} response mapped from list of trailers which found.
     */
    @GetMapping(path = "/type/{type}")
    @PreAuthorize("@defaultCompanyService.canAccess(#userId,#companyId)")
    public ResponseEntity fetchTrailersByType(@PathVariable final Integer userId,
                                              @PathVariable final Integer companyId,
                                              @PathVariable final String type) {
        final List<Trailer> trailersToResponse = trailerService.fetchTrailersByType(type);

        final List<TrailerResponse> response = trailersToResponse.stream()
                .map(trailer -> dozerBeanMapper.map(trailer, TrailerResponse.class))
                .collect(toList());
        return ResponseEntity.ok(response);
    }
}
