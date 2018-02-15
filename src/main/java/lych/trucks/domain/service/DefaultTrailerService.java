package lych.trucks.domain.service;

import lombok.RequiredArgsConstructor;
import lych.trucks.domain.model.Trailer;
import lych.trucks.domain.model.Truck;
import lych.trucks.domain.repository.TrailerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link TrailerService}.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultTrailerService implements TrailerService {

    private final TrailerRepository trailerRepository;

    private final TruckService truckService;

    @Override
    public Trailer createTrailer(final Integer driverId, final Integer truckId, final Trailer trailer) {
        validateTrailer(trailer);

        final Truck truck = truckService.fetchTruck(driverId);

        trailer.setTrailerFk(truckId);

        truck.setTrailer(trailer);

        truckService.updateTruck(truck);

        return trailerRepository.save(trailer);
    }

    @Override
    public Trailer fetchTrailer(final Integer truckId) {
        return Optional.ofNullable(trailerRepository.findByTrailerFk(truckId))
                .orElseThrow(() -> new IllegalArgumentException("Trailer not found. "
                        + "Truck with Id: '" + truckId + "' don`t have trailer."));
    }

    @Override
    public Trailer updateTrailer(final Trailer trailer) {
        validateTrailer(trailer);

        final Trailer saved = Optional.ofNullable(trailerRepository.findOne(trailer.getId()))
                .orElseThrow(() -> new IllegalArgumentException("Trailer not found. "
                        + "Trailer with Id: '" + trailer.getId() + "' not exists."));

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
        return Optional.ofNullable(trailerRepository.findByRegisterSign(registerSign))
                .orElseThrow(() -> new IllegalArgumentException("Trailer not found. "
                        + "Trailer with register sign: '" + registerSign + "' not exists."));
    }

    @Override
    public List<Trailer> fetchTrailersByVolume(final Integer volume) {

        return Optional.ofNullable(trailerRepository.findByVolume(volume))
                .orElseThrow(() -> new IllegalArgumentException("Trailers not found. "
                        + "Trailers with volume: '" + volume + "' not exists."));
    }

    @Override
    public List<Trailer> fetchTrailersByType(final String type) {

        return Optional.ofNullable(trailerRepository.findByType(type))
                .orElseThrow(() -> new IllegalArgumentException("Trailers not found. "
                        + "Trailers with type: '" + type + "' not exist."));
    }

    private static void validateTrailer(final Trailer trailer) {

        if (trailer == null) {
            throw new IllegalArgumentException("Trailer can`t be null");
        }
    }
}
