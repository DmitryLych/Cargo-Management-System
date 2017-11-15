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
    public MedicalExamination create(MedicalExamination medicalExamination) {
        return medicalExaminationRepository.save(medicalExamination);
    }

    @Override
    public MedicalExamination fetch(Integer id) {
        return medicalExaminationRepository.findOne(id);
    }

    @Override
    public void delete(Integer id) {
        medicalExaminationRepository.delete(id);
    }

    @Override
    public MedicalExamination update(MedicalExamination medicalExamination) {
        return medicalExaminationRepository.save(medicalExamination);
    }
}
