package lych.trucks.domain.service;

import lych.trucks.domain.model.Trailer;

/**
 * Service for {@link Trailer} work with database.
 */
public interface TrailerService {

    /**
     * Method for create trailer.
     *
     * @param truckId Truck truckId.
     * @param trailer Trailer trailer.
     * @return created trailer.
     */
    Trailer create(Integer truckId, Trailer trailer);

    /**
     * Method for find trailer.
     *
     * @param truckId Truck truckId.
     * @return trailer which found.
     */
    Trailer fetch(Integer truckId);

    /**
     * Method for delete trailer.
     *
     * @param id Trailer id.
     */
    void delete(Integer id);

    /**
     * Method for update trailer.
     *
     * @param trailer Trailer trailer.
     * @return updated trailer.
     */
    Trailer update(Trailer trailer);
}
