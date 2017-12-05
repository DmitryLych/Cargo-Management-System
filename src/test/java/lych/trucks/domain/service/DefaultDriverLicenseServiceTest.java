package lych.trucks.domain.service;

import lych.trucks.domain.model.Driver;
import lych.trucks.domain.model.DriverLicense;
import lych.trucks.domain.repository.DriverLicenseRepository;
import lych.trucks.domain.repository.DriverRepository;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultDriverLicenseServiceTest {

    @Autowired
    private DriverLicenseRepository driverLicenseRepository;

    @Autowired
    private DriverLicenseService driverLicenseService;

    @Autowired
    private DriverRepository driverRepository;

    private static final String CATEGORY_CONTENT = "a,b,c,d";

    private static final String SPECIAL_NOTES_CONTENT = "content";

    private Integer driverIdContent;

    private Integer driverLicenseIdContent;

    @Before
    public void setUp() {

        final Driver driver = new Driver();

        driverIdContent = driverRepository.save(driver).getId();

        final DriverLicense driverLicense = new DriverLicense();

        driverLicense.setDriverLicenseFk(driverIdContent);
        driverLicense.setCategory(CATEGORY_CONTENT);
        driverLicense.setSpecialNotes(SPECIAL_NOTES_CONTENT);

        driverLicenseIdContent = driverLicenseRepository.save(driverLicense).getId();
    }

    @Test
    public void create() {

        final String content = "a";

        final DriverLicense driverLicense = new DriverLicense();

        driverLicense.setCategory(content);

        final Integer savedId = driverLicenseService.create(driverIdContent, driverLicense).getId();

        assertThat(driverLicenseRepository.findOne(savedId).getCategory(), Is.is(content));
    }

    @Test
    public void fetch() {

        assertThat(driverLicenseService.fetch(driverIdContent).getCategory(), Is.is(CATEGORY_CONTENT));
    }

    @Test
    public void update() {

        final String content = "e";

        final DriverLicense driverLicense = driverLicenseRepository.findOne(driverLicenseIdContent);

        driverLicense.setCategory(content);

        driverLicenseService.update(driverLicense);

        assertThat(driverLicenseRepository.findOne(driverLicenseIdContent).getCategory(), Is.is(content));
    }

    @Test
    public void fetchByCategory() {

        final List<DriverLicense> driverLicenses = driverLicenseService.fetchByCategory(CATEGORY_CONTENT);

        driverLicenses.forEach(driverLicense -> assertThat(driverLicense.getCategory(), Is.is(CATEGORY_CONTENT)));
    }

    @Test
    public void fetchBySpecialNotes() {

        final List<DriverLicense> driverLicenses = driverLicenseService.fetchBySpecialNotes(SPECIAL_NOTES_CONTENT);

        driverLicenses.forEach(driverLicense -> assertThat(driverLicense.getSpecialNotes(), Is.is(SPECIAL_NOTES_CONTENT)));
    }

}
