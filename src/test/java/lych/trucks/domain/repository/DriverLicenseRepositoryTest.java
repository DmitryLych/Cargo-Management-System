package lych.trucks.domain.repository;

import lych.trucks.domain.model.DriverLicense;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DriverLicenseRepositoryTest {

    @Autowired
    private DriverLicenseRepository driverLicenseRepository;

    private static final Integer DRIVER_ID_CONTENT = 1;

    private static final String CATEGORY_CONTENT = "a,b,c,d";

    private static final String SPECIAL_NOTES_CONTENT = "content";

    @Before
    public void setUp() {

        final DriverLicense driverLicense = new DriverLicense();

        driverLicense.setSpecialNotes(SPECIAL_NOTES_CONTENT);
        driverLicense.setCategory(CATEGORY_CONTENT);
        driverLicense.setOwnerIdForDriverLicense(DRIVER_ID_CONTENT);

        driverLicenseRepository.save(driverLicense);
    }

    @Test
    public void findByOwnerIdForDriverLicense() {

        final DriverLicense foundDriverLicense = driverLicenseRepository.findByOwnerIdForDriverLicense(DRIVER_ID_CONTENT);

        assertThat(foundDriverLicense.getOwnerIdForDriverLicense(), Is.is(DRIVER_ID_CONTENT));
    }

    @Test
    public void findByCategory() {

        final List<DriverLicense> foundDriverLicenses = driverLicenseRepository.findByCategory(CATEGORY_CONTENT);

        foundDriverLicenses.forEach(driverLicense -> assertThat(driverLicense.getCategory(), Is.is(CATEGORY_CONTENT)));
    }

    @Test
    public void findBySpecialNotes() {

        final List<DriverLicense> foundDriverLicenses = driverLicenseRepository.findBySpecialNotes(SPECIAL_NOTES_CONTENT);

        foundDriverLicenses.forEach(driverLicense -> assertThat(driverLicense.getSpecialNotes(), Is.is(SPECIAL_NOTES_CONTENT)));
    }
}
