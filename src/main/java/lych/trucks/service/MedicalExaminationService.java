package lych.trucks.service;

import lych.trucks.model.MedicalExamination;

public interface MedicalExaminationService {

    MedicalExamination create(MedicalExamination medicalExamination);

    MedicalExamination fetch(Integer id);

    void delete(Integer id);

    MedicalExamination update(MedicalExamination medicalExamination);
}
