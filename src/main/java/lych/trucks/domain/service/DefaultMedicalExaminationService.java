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
    public MedicalExamination createMedicalExamination(final Integer driverId, final MedicalExamination medicalExamination) {

        log.info("Medical examination created.");

        final Driver driver = driverService.fetchDriver(driverId);

        medicalExamination.setMedicalExaminationFk(driverId);

        driver.setMedicalExamination(medicalExamination);

        driverService.updateDriver(driver);

        return medicalExaminationRepository.save(medicalExamination);
    }

    @Override
    public MedicalExamination fetchMedicalExamination(final Integer driverId) {

        log.info("Medical examination displayed.");

        return medicalExaminationRepository.findByMedicalExaminationFk(driverId);
    }

    @Override
    public MedicalExamination updateMedicalExamination(final MedicalExamination medicalExamination) {

        log.info("Medical examination updated.");

        final MedicalExamination saved = medicalExaminationRepository.findOne(medicalExamination.getId());

        medicalExamination.setMedicalExaminationFk(saved.getMedicalExaminationFk());
        medicalExamination.setValidate(medicalExamination.getValidate().getTime() == 0 ? saved.getValidate()
                : medicalExamination.getValidate());

        return medicalExaminationRepository.save(medicalExamination);
    }

    @Override
    public List<MedicalExamination> fetchMedicalExaminationByValidate(final Long validate) {

        log.info("Medical examination fetched by validate.");

        final Date date = new Date();

        date.setTime(validate);

        return medicalExaminationRepository.findByValidate(date);
    }
}
