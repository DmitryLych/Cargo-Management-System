package lych.trucks.domain.service;

import lombok.RequiredArgsConstructor;
import lych.trucks.domain.model.Trailer;
import lych.trucks.domain.repository.TrailerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultTrailerService implements TrailerService {

    private final TrailerRepository trailerRepository;

    @Override
    public List<Trailer> fetchAll() {
        return trailerRepository.findAll();
    }

    @Override
    public Trailer create(Trailer trailer) {
        return trailerRepository.save(trailer);
    }

    @Override
    public Trailer fetch(Integer id) {
        return trailerRepository.findOne(id);
    }

    @Override
    public void delete(Integer id) {
        trailerRepository.delete(id);
    }

    @Override
    public Trailer update(Trailer trailer) {
        return trailerRepository.save(trailer);
    }
}
