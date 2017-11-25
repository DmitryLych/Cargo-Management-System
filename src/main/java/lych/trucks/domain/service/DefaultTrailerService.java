package lych.trucks.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lych.trucks.domain.model.Trailer;
import lych.trucks.domain.repository.TrailerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link TrailerService}.
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultTrailerService implements TrailerService {

    private final TrailerRepository trailerRepository;

    @Override
    public Trailer create(final Integer truckId, final Trailer trailer) {

        log.info("Trailer created.");

        trailer.setOwnerIdForTrailer(truckId);

        return trailerRepository.save(trailer);
    }

    @Override
    public Trailer fetch(final Integer truckId) {

        log.info("Trailer found.");

        return trailerRepository.findByOwnerIdForTrailer(truckId);
    }

    @Override
    public void delete(final Integer id) {

        log.info("Trailer deleted");

        trailerRepository.delete(id);
    }

    @Override
    public Trailer update(final Trailer trailer) {

        log.info("Trailer updated.");

        final Trailer saved = trailerRepository.findOne(trailer.getId());

        trailer.setOwnerIdForTrailer(trailer.getOwnerIdForTrailer() == null ? saved.getOwnerIdForTrailer()
                : trailer.getOwnerIdForTrailer());
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
}
