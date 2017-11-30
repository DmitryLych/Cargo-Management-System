package lych.trucks.domain.service;

import lych.trucks.domain.model.Trailer;

import java.util.List;

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
     * Method for delete some trailer.
     *
     * @param id Trailer id.
     * @return deleted trailer.
     */
    Trailer delete(Integer id);

    /**
     * Method for update trailer.
     *
     * @param trailer Trailer trailer.
     * @return updated trailer.
     */
    Trailer update(Trailer trailer);

    /**
     * Method for fetch trailer by register sign.
     *
     * @param registerSign Trailer registerSign.
     * @return trailer which found.
     */
    Trailer fetchByRegisterSign(String registerSign);

    /**
     * Method for fetch trailer by volume.
     *
     * @param volume Trailer volume.
     * @return list of trailers which found.
     */
    List<Trailer> fetchByVolume(Integer volume);

    /**
     * Method for fetch trailers by type.
     *
     * @param type Trailer type.
     * @return list of trailers which found.
     */
    List<Trailer> fetchByType(String type);
}
