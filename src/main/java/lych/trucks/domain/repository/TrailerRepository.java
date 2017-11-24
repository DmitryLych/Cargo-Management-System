package lych.trucks.domain.repository;

import lych.trucks.domain.model.Trailer;
import lych.trucks.domain.model.Truck;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface for work with database.
 */
public interface TrailerRepository extends JpaRepository<Trailer, Integer> {

    /**
     * Method for find {@link Trailer} by truck id.
     *
     * @param truckId {@link Truck} truckId.
     * @return trailer which found.
     */
    Trailer findByOwnerIdForTrailer(Integer truckId);
}
