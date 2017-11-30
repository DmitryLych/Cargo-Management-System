package lych.trucks.domain.service;

import lych.trucks.domain.model.Driver;
import lych.trucks.domain.model.MedicalExamination;
import lych.trucks.domain.repository.DriverRepository;
import lych.trucks.domain.repository.MedicalExaminationRepository;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultMedicalExaminationServiceTest {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private MedicalExaminationRepository medicalExaminationRepository;

    @Autowired
    private MedicalExaminationService medicalExaminationService;

    private static final Date VALIDATE_CONTENT = new Date();

    private Integer driverIdContent;

    private Integer medicalExaminationIdContent;

    @Before
    public void setUp() {

        driverRepository.deleteAll();

        medicalExaminationRepository.deleteAll();

        driverIdContent = driverRepository.save(new Driver()).getId();

        final MedicalExamination medicalExamination = new MedicalExamination();

        medicalExamination.setOwnerIdForMedicalExamination(driverIdContent);
        medicalExamination.setValidate(VALIDATE_CONTENT);

        medicalExaminationIdContent = medicalExaminationRepository.save(medicalExamination).getId();
    }

    @Test
    public void create() {

        final Date newValidate = new Date();

        final MedicalExamination medicalExamination = new MedicalExamination();

        medicalExamination.setValidate(newValidate);

        final Integer newId;

        newId = medicalExaminationService.create(driverIdContent, medicalExamination).getId();

        assertThat(medicalExaminationRepository.findOne(newId).getValidate().getTime(),
                Is.is(newValidate.getTime()));
    }

    @Test
    public void fetch() {

        assertThat(medicalExaminationService.fetch(driverIdContent).getValidate().getTime(),
                Is.is(VALIDATE_CONTENT.getTime()));
    }

    @Test
    public void delete() {

        medicalExaminationService.delete(medicalExaminationIdContent);

        assertThat(medicalExaminationRepository.exists(medicalExaminationIdContent), Is.is(false));
    }

    @Test
    public void update() {

        final MedicalExamination medicalExamination = medicalExaminationRepository
                .findOne(medicalExaminationIdContent);

        final Date newDate = new Date();

        medicalExamination.setValidate(newDate);

        medicalExaminationService.update(medicalExamination);

        assertThat(medicalExaminationRepository.findOne(medicalExaminationIdContent)
                        .getValidate().getTime(),
                Is.is(newDate.getTime()));
    }

    @Test
    public void fetchByValidate() {

        final List<MedicalExamination> medicalExaminations = medicalExaminationService
                .fetchByValidate(VALIDATE_CONTENT);

        medicalExaminations.forEach(medicalExamination -> assertThat(medicalExamination.getValidate().getTime(),
                Is.is(VALIDATE_CONTENT.getTime())));
    }
}
