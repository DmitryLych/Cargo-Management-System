package lych.trucks.domain.service;

import lych.trucks.domain.model.MedicalExamination;

public interface MedicalExaminationService {

    MedicalExamination create(Integer driverId, MedicalExamination medicalExamination);

    MedicalExamination fetch(Integer driverId);

    void delete(Integer id);

    MedicalExamination update(MedicalExamination medicalExamination);
}
