package lych.trucks.domain.service;

import lombok.RequiredArgsConstructor;
import lych.trucks.domain.model.Driver;
import lych.trucks.domain.model.MedicalExamination;
import lych.trucks.domain.repository.MedicalExaminationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link MedicalExaminationService}.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultMedicalExaminationService implements MedicalExaminationService {

    private final MedicalExaminationRepository medicalExaminationRepository;

    private final DriverService driverService;

    @Override
    public MedicalExamination createMedicalExamination(final Integer driverId, final MedicalExamination medicalExamination) {
        validateMedicalExamination(medicalExamination);

        final Driver driver = driverService.fetchDriver(driverId);

        medicalExamination.setMedicalExaminationFk(driverId);

        driver.setMedicalExamination(medicalExamination);

        driverService.updateDriver(driver);

        return medicalExaminationRepository.save(medicalExamination);
    }

    @Override
    public MedicalExamination fetchMedicalExamination(final Integer driverId) {
        return Optional.ofNullable(medicalExaminationRepository.findByMedicalExaminationFk(driverId))
                .orElseThrow(() -> new IllegalArgumentException("Medical examination not found. "
                        + "Driver with this Id: '" + driverId + "' don`t have medical examination."));
    }

    @Override
    public MedicalExamination updateMedicalExamination(final MedicalExamination medicalExamination) {
        validateMedicalExamination(medicalExamination);

        final MedicalExamination saved = medicalExaminationRepository.findOne(medicalExamination.getId());

        medicalExamination.setMedicalExaminationFk(saved.getMedicalExaminationFk());
        medicalExamination.setValidate(medicalExamination.getValidate().getTime() == 0 ? saved.getValidate()
                : medicalExamination.getValidate());

        return medicalExaminationRepository.save(medicalExamination);
    }

    @Override
    public List<MedicalExamination> fetchMedicalExaminationByValidate(final Long validate) {

        final Date validateTime = new Date();

        validateTime.setTime(Optional.of(validate)
                .orElseThrow(() -> new IllegalArgumentException("Incorrect validate")));

        final List<MedicalExamination> medicalExaminations = medicalExaminationRepository.findByValidate(validateTime);

        if (medicalExaminations == null || medicalExaminations.isEmpty()) {
            throw new IllegalArgumentException("Medical examinations not found. "
                    + "Medical examination with this validate time: '" + validateTime + "' not exists.");
        }

        return medicalExaminations;
    }

    private static void validateMedicalExamination(final MedicalExamination medicalExamination) {

        if (medicalExamination == null) {
            throw new IllegalArgumentException("Medical examination can`t be null.");
        }
    }
}
