package lych.trucks.domain.repository;

import lych.trucks.domain.model.MedicalExamination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalExaminationRepository extends JpaRepository<MedicalExamination, Integer> {
}
