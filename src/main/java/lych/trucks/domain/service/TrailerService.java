package lych.trucks.domain.service;

import lych.trucks.domain.model.Trailer;

import java.util.List;

public interface TrailerService {

    List<Trailer> fetchAll();

    Trailer create(Trailer trailer);

    Trailer fetch(Integer id);

    void delete(Integer id);

    Trailer update(Trailer trailer);
}
