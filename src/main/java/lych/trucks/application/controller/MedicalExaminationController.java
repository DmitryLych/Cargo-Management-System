package lych.trucks.application.controller;

import lombok.RequiredArgsConstructor;
import lych.trucks.application.dto.request.MedicalExaminationRequest;
import lych.trucks.application.dto.response.MedicalExaminationResponse;
import lych.trucks.domain.model.MedicalExamination;
import lych.trucks.domain.service.MedicalExaminationService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Rest controller for {@link MedicalExamination}.
 */
@RestController
@RequestMapping("/cargo/v1/{userId}/companies/{companyId}/drivers/{driverId}/medical")
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
    @PostMapping
    @PreAuthorize("@defaultDriverService.canAccess(#userId,#driverId)")
    public ResponseEntity createMedicalExamination(@PathVariable final Integer userId,
                                                   @PathVariable final Integer driverId,
                                                   @RequestBody final MedicalExaminationRequest request) {
        final MedicalExamination medicalExaminationToCreate = dozerBeanMapper.map(request, MedicalExamination.class);
        final MedicalExamination medicalExaminationToResponse = medicalExaminationService.
                createMedicalExamination(driverId, medicalExaminationToCreate);

        final MedicalExaminationResponse response = dozerBeanMapper.map(medicalExaminationToResponse, MedicalExaminationResponse.class);
        return ResponseEntity.ok(response);
    }

    /**
     * Method for update medical examination.
     *
     * @param request MedicalExaminationRequest request.
     * @return MedicalExaminationResponse response mapped from updated medical examination.
     */
    @PutMapping
    @PreAuthorize("@defaultDriverService.canAccess(#userId,#driverId)")
    public ResponseEntity updateMedicalExamination(@PathVariable final Integer userId,
                                                   @PathVariable final Integer driverId,
                                                   @RequestBody final MedicalExaminationRequest request) {
        final MedicalExamination medicalExaminationToUpdate = dozerBeanMapper.map(request, MedicalExamination.class);
        final MedicalExamination medicalExaminationToResponse = medicalExaminationService
                .updateMedicalExamination(medicalExaminationToUpdate);

        final MedicalExaminationResponse response = dozerBeanMapper.map(medicalExaminationToResponse,
                MedicalExaminationResponse.class);
        return ResponseEntity.ok(response);
    }

    /**
     * Method for display medical examination.
     *
     * @param driverId Driver driverId.
     * @return MedicalExaminationResponse response mapped from displayed medical examination.
     */
    @GetMapping
    @PreAuthorize("@defaultDriverService.canAccess(#userId,#driverId)")
    public ResponseEntity fetchMedicalExamination(@PathVariable final Integer userId,
                                                  @PathVariable final Integer driverId) {
        final MedicalExamination medicalExaminationToResponse = medicalExaminationService
                .fetchMedicalExamination(driverId);

        final MedicalExaminationResponse response = dozerBeanMapper.map(medicalExaminationToResponse,
                MedicalExaminationResponse.class);
        return ResponseEntity.ok(response);
    }

    /**
     * Method for fetch medical examinations by validate.
     *
     * @param validate {@link MedicalExamination} validate.
     * @return list of {@link MedicalExaminationResponse} response mapped from list of medical examinations
     * which found.
     */
    @RequestMapping(path = "/validate/{validate}")
    @PreAuthorize("@defaultDriverService.canAccess(#userId,#driverId)")
    public ResponseEntity fetchMedicalExaminationsByValidate(@PathVariable final Integer userId,
                                                             @PathVariable final Integer driverId,
                                                             @PathVariable final long validate) {
        final List<MedicalExamination> medicalExaminationsToResponse = medicalExaminationService
                .fetchMedicalExaminationByValidate(validate);

        final List<MedicalExaminationResponse> response = medicalExaminationsToResponse.stream()
                .map(medicalExamination -> dozerBeanMapper.map(medicalExamination,
                        MedicalExaminationResponse.class))
                .collect(toList());
        return ResponseEntity.ok(response);
    }
}
