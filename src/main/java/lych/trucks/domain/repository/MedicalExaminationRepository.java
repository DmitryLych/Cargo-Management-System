package lych.trucks.domain.repository;

import lych.trucks.domain.model.MedicalExamination;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface for work with database.
 */
public interface MedicalExaminationRepository extends JpaRepository<MedicalExamination, Integer> {

    MedicalExamination findByOwnerIdForMedicalExamination(Integer driverId);
}
