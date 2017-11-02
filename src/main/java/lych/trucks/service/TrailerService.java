package lych.trucks.service;

import lych.trucks.model.Trailer;

import java.util.List;

public interface TrailerService {

    List<Trailer> trailers();

    Trailer create(Trailer trailer);

    Trailer fetch(Integer id);

    void delete(Integer id);

    Trailer update(Trailer trailer);
}
