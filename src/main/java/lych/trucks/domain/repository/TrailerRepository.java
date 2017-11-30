package lych.trucks.domain.repository;

import lych.trucks.domain.model.Trailer;
import lych.trucks.domain.model.Truck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Interface for {@link Trailer} work with database.
 */
public interface TrailerRepository extends JpaRepository<Trailer, Integer> {

    /**
     * Method for find {@link Trailer} by truck id.
     *
     * @param truckId {@link Truck} truckId.
     * @return trailer which found.
     */
    @Query(value = "SELECT * FROM trailers WHERE owner_id_for_trailer=?1", nativeQuery = true)
    Trailer findByOwnerIdForTrailer(Integer truckId);

    /**
     * Method for find {@link Trailer} by register sign.
     *
     * @param registerSign {@link Trailer} registerSign.
     * @return trailer which found.
     */
    @Query(value = "SELECT * FROM trailers WHERE register_sign=?1", nativeQuery = true)
    Trailer findByRegisterSign(String registerSign);

    /**
     * Method for find {@link Trailer} by volume.
     *
     * @param volume {@link Trailer} volume.
     * @return trailer which found.
     */
    @Query(value = "SELECT * FROM trailers WHERE volume=?1", nativeQuery = true)
    List<Trailer> findByVolume(Integer volume);

    /**
     * Method for find {@link Trailer} by type.
     *
     * @param type {@link Trailer} trailerType.
     * @return trailer which found.
     */
    @Query(value = "SELECT * FROM trailers WHERE trailer_type=?1", nativeQuery = true)
    List<Trailer> findByType(String type);
}
