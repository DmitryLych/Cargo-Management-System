package lych.trucks.domain.repository;

import lych.trucks.domain.model.MedicalExamination;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MedicalExaminationRepositoryTest {

    @Autowired
    private MedicalExaminationRepository medicalExaminationRepository;

    private static final Integer DRIVER_ID_CONTENT = 1;

    private Date validateContent;

    @Before
    public void setUp() {

        final MedicalExamination medicalExamination = new MedicalExamination();

        validateContent = new Date();

        medicalExamination.setValidate(validateContent);
        medicalExamination.setMedicalExaminationFk(DRIVER_ID_CONTENT);

        medicalExaminationRepository.save(medicalExamination);
    }

    @Test
    public void findByMedicalExaminationFk() {

        final MedicalExamination foundMedicalExamination = medicalExaminationRepository
                .findByMedicalExaminationFk(DRIVER_ID_CONTENT);

        assertThat(foundMedicalExamination.getMedicalExaminationFk(), is(DRIVER_ID_CONTENT));
    }

    @Test
    public void findByValidate() {

        final List<MedicalExamination> foundMedicalExaminations = medicalExaminationRepository.
                findByValidate(validateContent);

        foundMedicalExaminations.forEach(medicalExamination -> assertThat(medicalExamination.getValidate(),
                is(validateContent)));
    }

}
