package lych.trucks.domain.service;

import lych.trucks.domain.model.Trailer;

public interface TrailerService {

    Trailer create(Integer truckId, Trailer trailer);

    Trailer fetch(Integer truckId);

    void delete(Integer id);

    Trailer update(Trailer trailer);
}
