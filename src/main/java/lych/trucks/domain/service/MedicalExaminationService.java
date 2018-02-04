package lych.trucks.domain.service;

import lych.trucks.domain.model.MedicalExamination;

import java.util.List;

/**
 * Service for {@link MedicalExamination} work with database.
 */
public interface MedicalExaminationService {

    /**
     * Method for create medical examination.
     *
     * @param driverId           Driver driverId.
     * @param medicalExamination MedicalExamination medicalExamination.
     * @return created medical examination.
     */
    MedicalExamination createMedicalExamination(Integer driverId, MedicalExamination medicalExamination);

    /**
     * Method for find medical examination.
     *
     * @param driverId Driver driverId.
     * @return medical examination which found.
     */
    MedicalExamination fetchMedicalExamination(Integer driverId);

    /**
     * Method for update medical examination.
     *
     * @param medicalExamination MedicalExamination medicalExamination.
     * @return updated medical examination.
     */
    MedicalExamination updateMedicalExamination(MedicalExamination medicalExamination);

    /**
     * Method for fetch medical examination by validate.
     *
     * @param validate MedicalExamination validate.
     * @return list of medical examination which found.
     */
    List<MedicalExamination> fetchMedicalExaminationByValidate(Long validate);
}
