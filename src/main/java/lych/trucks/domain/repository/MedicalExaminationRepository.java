package lych.trucks.domain.repository;

import lych.trucks.domain.model.Driver;
import lych.trucks.domain.model.MedicalExamination;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface for work with database.
 */
public interface MedicalExaminationRepository extends JpaRepository<MedicalExamination, Integer> {

    /**
     * Method for find {@link MedicalExamination} by driver id.
     *
     * @param driverId {@link Driver} driverId.
     * @return medical examination which found.
     */
    MedicalExamination findByOwnerIdForMedicalExamination(Integer driverId);
}
