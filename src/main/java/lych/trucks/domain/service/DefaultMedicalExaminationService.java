package lych.trucks.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lych.trucks.domain.model.Driver;
import lych.trucks.domain.model.MedicalExamination;
import lych.trucks.domain.repository.MedicalExaminationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Implementation of {@link MedicalExaminationService}.
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultMedicalExaminationService implements MedicalExaminationService {

    private final MedicalExaminationRepository medicalExaminationRepository;

    private final DriverService driverService;

    @Override
    public MedicalExamination create(final Integer driverId, final MedicalExamination medicalExamination) {

        log.info("Medical examination created.");

        final Driver driver = driverService.fetch(driverId);

        medicalExamination.setMedicalExaminationFk(driverId);

        driver.setMedicalExamination(medicalExamination);

        driverService.update(driver);

        return medicalExaminationRepository.save(medicalExamination);
    }

    @Override
    public MedicalExamination fetch(final Integer driverId) {

        log.info("Medical examination displayed.");

        return medicalExaminationRepository.findByOwnerIdForMedicalExamination(driverId);
    }

    @Override
    public MedicalExamination update(final MedicalExamination medicalExamination) {

        log.info("Medical examination updated.");

        final MedicalExamination saved = medicalExaminationRepository.findOne(medicalExamination.getId());

        medicalExamination.setMedicalExaminationFk(saved.getMedicalExaminationFk());
        medicalExamination.setValidate(medicalExamination.getValidate() == null ? saved.getValidate() : medicalExamination.getValidate());

        return medicalExaminationRepository.save(medicalExamination);
    }

    @Override
    public List<MedicalExamination> fetchByValidate(final Date validate) {

        log.info("Medical examination fetched by validate.");

        return medicalExaminationRepository.findByValidate(validate);
    }
}
