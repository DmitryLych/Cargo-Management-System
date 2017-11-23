package lych.trucks.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lych.trucks.domain.model.MedicalExamination;
import lych.trucks.domain.repository.MedicalExaminationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultMedicalExaminationService implements MedicalExaminationService {

    private final MedicalExaminationRepository medicalExaminationRepository;

    @Override
    public MedicalExamination create(final Integer driverId, final MedicalExamination medicalExamination) {

        log.info("Medical examination created.");

        medicalExamination.setOwnerIdForMedicalExamination(driverId);

        return medicalExaminationRepository.save(medicalExamination);
    }

    @Override
    public MedicalExamination fetch(final Integer driverId) {

        log.info("Medical examination displayed.");

        return medicalExaminationRepository.findByOwnerIdForMedicalExamination(driverId);
    }

    @Override
    public void delete(final Integer id) {

        log.info("Medical examination deleted.");

        medicalExaminationRepository.delete(id);
    }

    @Override
    public MedicalExamination update(final MedicalExamination medicalExamination) {

        log.info("Medical examination updated.");

        final MedicalExamination saved = medicalExaminationRepository.findOne(medicalExamination.getId());

        medicalExamination.setOwnerIdForMedicalExamination(saved.getOwnerIdForMedicalExamination());
        medicalExamination.setValidate(medicalExamination.getValidate() == null ? saved.getValidate() : medicalExamination.getValidate());

        return medicalExaminationRepository.save(medicalExamination);
    }
}
