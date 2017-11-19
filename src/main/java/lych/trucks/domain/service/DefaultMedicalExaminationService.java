package lych.trucks.domain.service;

import lombok.RequiredArgsConstructor;
import lych.trucks.domain.model.MedicalExamination;
import lych.trucks.domain.repository.MedicalExaminationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultMedicalExaminationService implements MedicalExaminationService {

    private final MedicalExaminationRepository medicalExaminationRepository;

    @Override
    public MedicalExamination create(final Integer driverId, final MedicalExamination medicalExamination) {

        medicalExamination.setOwnerIdForMedicalExamination(driverId);

        return medicalExaminationRepository.save(medicalExamination);
    }

    @Override
    public MedicalExamination fetch(final Integer driverId) {

        return medicalExaminationRepository.findByOwnerIdForMedicalExamination(driverId);
    }

    @Override
    public void delete(final Integer id) {
        medicalExaminationRepository.delete(id);
    }

    @Override
    public MedicalExamination update(final MedicalExamination medicalExamination) {

        final MedicalExamination saved = medicalExaminationRepository.findOne(medicalExamination.getId());

        medicalExamination.setOwnerIdForMedicalExamination(saved.getOwnerIdForMedicalExamination());
        medicalExamination.setValidate(medicalExamination.getValidate() == null ? saved.getValidate() : medicalExamination.getValidate());

        return medicalExaminationRepository.save(medicalExamination);
    }
}
