package lych.trucks.domain.service;

import lombok.RequiredArgsConstructor;
import lych.trucks.domain.model.Trailer;
import lych.trucks.domain.repository.TrailerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultTrailerService implements TrailerService {

    private final TrailerRepository trailerRepository;

    @Override
    public Trailer create(final Integer truckId, final Trailer trailer) {

        trailer.setOwnerIdForTrailer(truckId);

        return trailerRepository.save(trailer);
    }

    @Override
    public Trailer fetch(final Integer truckId) {

        return trailerRepository.findByOwnerIdForTrailer(truckId);
    }

    @Override
    public void delete(Integer id) {
        trailerRepository.delete(id);
    }

    @Override
    public Trailer update(Trailer trailer) {

        final Trailer saved = trailerRepository.findOne(trailer.getId());

        trailer.setOwnerIdForTrailer(trailer.getOwnerIdForTrailer() == null ? saved.getOwnerIdForTrailer() :
                trailer.getOwnerIdForTrailer());
        trailer.setHeight(saved.getHeight());
        trailer.setLength(saved.getLength());
        trailer.setRegisterSign(trailer.getRegisterSign() == null ? saved.getRegisterSign() :
                trailer.getRegisterSign());
        trailer.setWeight(saved.getWeight());
        trailer.setYearOfIssue(saved.getYearOfIssue());

        return trailerRepository.save(trailer);
    }
}
