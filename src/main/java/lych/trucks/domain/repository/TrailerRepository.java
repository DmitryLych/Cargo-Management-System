package lych.trucks.domain.repository;

import lych.trucks.domain.model.Trailer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrailerRepository extends JpaRepository<Trailer, Integer> {
}
