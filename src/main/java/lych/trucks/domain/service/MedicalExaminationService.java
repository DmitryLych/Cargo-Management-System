package lych.trucks.domain.service;

import lych.trucks.domain.model.MedicalExamination;

public interface MedicalExaminationService {

    MedicalExamination create(MedicalExamination medicalExamination);

    MedicalExamination fetch(Integer id);

    void delete(Integer id);

    MedicalExamination update(MedicalExamination medicalExamination);
}
