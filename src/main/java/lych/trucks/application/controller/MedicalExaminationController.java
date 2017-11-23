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
     * Method for delete medical examination.
     *
     * @param medicalId MedicalExamination medicalId.
     * @param driverId  Driver driverId.
     * @return MedicalExaminationResponse response mapped from deleted medical examination.
     */
    @RequestMapping(value = "/companies/{companyId}/drivers/{driverId}/medical/{medicalId}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable final Integer medicalId, @PathVariable final Integer driverId) {

        final MedicalExamination medicalExaminationToResponse = medicalExaminationService.fetch(driverId);

        medicalExaminationService.delete(medicalId);

        final MedicalExaminationResponse response = dozerBeanMapper.map(medicalExaminationToResponse, MedicalExaminationResponse.class);

        return ResponseEntity.ok().body(response);
    }
}
