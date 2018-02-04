package lych.trucks.domain.service;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultTrailerService implements TrailerService {

    private final TrailerRepository trailerRepository;

    private final TruckService truckService;

    @Override
    public Trailer createTrailer(final Integer driverId, final Integer truckId, final Trailer trailer) {

        final Truck truck = truckService.fetchTruck(driverId);

        trailer.setTrailerFk(truckId);

        truck.setTrailer(trailer);

        truckService.updateTruck(truck);

        return trailerRepository.save(trailer);
    }

    @Override
    public Trailer fetchTrailer(final Integer truckId) {
        return trailerRepository.findByTrailerFk(truckId);
    }

    @Override
    public Trailer updateTrailer(final Trailer trailer) {

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
        return trailerRepository.findByRegisterSign(registerSign);
    }

    @Override
    public List<Trailer> fetchTrailersByVolume(final Integer volume) {
        return trailerRepository.findByVolume(volume);
    }

    @Override
    public List<Trailer> fetchTrailersByType(final String type) {
        return trailerRepository.findByType(type);
    }
}
