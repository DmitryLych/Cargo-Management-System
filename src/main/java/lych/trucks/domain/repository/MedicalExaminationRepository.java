package lych.trucks.domain.repository;

import lych.trucks.domain.model.Driver;
import lych.trucks.domain.model.MedicalExamination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * Interface for {@link MedicalExamination} work with database.
 */
public interface MedicalExaminationRepository extends JpaRepository<MedicalExamination, Integer> {

    /**
     * Method for find medical examination by driver id.
     *
     * @param driverId {@link Driver} driverId.
     * @return medical examination which found.
     */
    @Query(value = "SELECT * FROM medical_examinations WHERE medical_examination_fk=?1", nativeQuery = true)
    MedicalExamination findByOwnerIdForMedicalExamination(Integer driverId);

    /**
     * Method for find medical examination by validate.
     *
     * @param validate MedicalExamination validate.
     * @return list of medical examination which found.
     */
    @Query(value = "SELECT * FROM medical_examinations WHERE validate=?1", nativeQuery = true)
    List<MedicalExamination> findByValidate(Date validate);
}
