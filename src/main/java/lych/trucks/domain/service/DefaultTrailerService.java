package lych.trucks.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lych.trucks.domain.model.Trailer;
import lych.trucks.domain.model.Truck;
import lych.trucks.domain.repository.TrailerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of {@link TrailerService}.
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultTrailerService implements TrailerService {

    private final TrailerRepository trailerRepository;

    private final TruckService truckService;

    @Override
    public Trailer createTrailer(final Integer driverId, final Integer truckId, final Trailer trailer) {

        log.info("Trailer created.");

        final Truck truck = truckService.fetchTruck(driverId);

        trailer.setTrailerFk(truckId);

        truck.setTrailer(trailer);

        truckService.updateTruck(truck);

        return trailerRepository.save(trailer);
    }

    @Override
    public Trailer fetchTrailer(final Integer truckId) {

        log.info("Trailer found.");

        return trailerRepository.findByTrailerFk(truckId);
    }

    @Override
    public Trailer updateTrailer(final Trailer trailer) {

        log.info("Trailer updated.");

        final Trailer saved = trailerRepository.findOne(trailer.getId());

        trailer.setTrailerFk(trailer.getTrailerFk() == null ? saved.getTrailerFk()
                : trailer.getTrailerFk());
        trailer.setHeight(saved.getHeight());
        trailer.setRegisterSign(trailer.getRegisterSign() == null ? saved.getRegisterSign()
                : trailer.getRegisterSign());
        trailer.setColor(trailer.getColor() == null ? saved.getColor() : trailer.getColor());
        trailer.setLongest(saved.getLongest());
        trailer.setVolume(saved.getVolume());
        trailer.setWeight(saved.getWeight());
        trailer.setYearOfIssue(saved.getYearOfIssue());

        return trailerRepository.save(trailer);
    }

    @Override
    public Trailer fetchTrailerByRegisterSign(final String registerSign) {

        log.info("Trailer fetched by register sign.");

        return trailerRepository.findByRegisterSign(registerSign);
    }

    @Override
    public List<Trailer> fetchTrailersByVolume(final Integer volume) {

        log.info("Trailer fetched by volume");

        return trailerRepository.findByVolume(volume);
    }

    @Override
    public List<Trailer> fetchTrailersByType(final String type) {

        log.info("Trailer fetched by type.");

        return trailerRepository.findByType(type);
    }
}
