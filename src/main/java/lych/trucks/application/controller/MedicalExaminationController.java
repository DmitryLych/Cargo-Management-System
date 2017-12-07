package lych.trucks.application.controller;

import lombok.RequiredArgsConstructor;
import lych.trucks.application.dto.request.MedicalExaminationRequest;
import lych.trucks.application.dto.response.MedicalExaminationResponse;
import lych.trucks.domain.model.MedicalExamination;
import lych.trucks.domain.service.MedicalExaminationService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Rest controller for {@link MedicalExamination}.
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MedicalExaminationController {

    private final DozerBeanMapper dozerBeanMapper;

    private final MedicalExaminationService medicalExaminationService;

    /**
     * Method for create medical examination.
     *
     * @param driverId Driver driverId.
     * @param request  MedicalExaminationRequest request.
     * @return MedicalExaminationResponse response mapped from created medical examination.
     */
    @RequestMapping(value = "/companies/{companyId}/drivers/{driverId}/medical", method = RequestMethod.POST)
    public ResponseEntity create(@PathVariable final Integer driverId,
                                 @RequestBody final MedicalExaminationRequest request) {

        final MedicalExamination medicalExaminationToCreate = dozerBeanMapper.map(request, MedicalExamination.class);

        final MedicalExamination medicalExaminationToResponse = medicalExaminationService.create(driverId, medicalExaminationToCreate);

        final MedicalExaminationResponse response = dozerBeanMapper.map(medicalExaminationToResponse, MedicalExaminationResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Method for update medical examination.
     *
     * @param request MedicalExaminationRequest request.
     * @return MedicalExaminationResponse response mapped from updated medical examination.
     */
    @RequestMapping(value = "/companies/{companyId}/drivers/{driverId}/medical", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody final MedicalExaminationRequest request) {

        final MedicalExamination medicalExaminationToUpdate = dozerBeanMapper.map(request, MedicalExamination.class);

        final MedicalExamination medicalExaminationToResponse = medicalExaminationService.update(medicalExaminationToUpdate);

        final MedicalExaminationResponse response = dozerBeanMapper.map(medicalExaminationToResponse, MedicalExaminationResponse.class);

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for display medical examination.
     *
     * @param driverId Driver driverId.
     * @return MedicalExaminationResponse response mapped from displayed medical examination.
     */
    @RequestMapping(value = "/companies/{companyId}/drivers/{driverId}/medical", method = RequestMethod.GET)
    public ResponseEntity fetch(@PathVariable final Integer driverId) {

        final MedicalExamination medicalExaminationToResponse = medicalExaminationService.fetch(driverId);

        final MedicalExaminationResponse response = dozerBeanMapper.map(medicalExaminationToResponse, MedicalExaminationResponse.class);

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for fetch medical examinations by validate.
     *
     * @param validate {@link MedicalExamination} validate.
     * @return list of {@link MedicalExaminationResponse} response mapped from list of medical examinations
     * which found.
     */
    @RequestMapping(value = "/companies/{companyId}/drivers/{driverId}/medical/validate/{validate}",
            method = RequestMethod.GET)
    public ResponseEntity fetchByValidate(@PathVariable final long validate) {

        final List<MedicalExamination> medicalExaminationsToResponse = medicalExaminationService
                .fetchByValidate(validate);

        final List<MedicalExaminationResponse> response = new ArrayList<>();

        medicalExaminationsToResponse.forEach(medicalExamination -> response.add(dozerBeanMapper
                .map(medicalExamination, MedicalExaminationResponse.class)));

        return ResponseEntity.ok().body(response);
    }
}
