package lych.trucks.application.controller;

import lombok.RequiredArgsConstructor;
import lych.trucks.application.dto.request.InsurancePolicyRequest;
import lych.trucks.application.dto.response.InsurancePolicyResponse;
import lych.trucks.domain.model.InsurancePolicy;
import lych.trucks.domain.service.InsurancePolicyService;
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
 * Rest controller for {@link InsurancePolicy}.
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InsurancePolicyController {

    private final DozerBeanMapper dozerBeanMapper;

    private final InsurancePolicyService insurancePolicyService;

    /**
     * Method for create insurance policy.
     *
     * @param driverId Driver driverId.
     * @param request  InsurancePolicyRequest request.
     * @return InsurancePolicyResponse response mapped from created insurance policy.
     */
    @RequestMapping(value = "/companies/{companyId}/drivers/{driverId}/insurance", method = RequestMethod.POST)
    public ResponseEntity create(@PathVariable final Integer driverId, @RequestBody final InsurancePolicyRequest request) {

        final InsurancePolicy insurancePolicyToCreate = dozerBeanMapper.map(request, InsurancePolicy.class);

        final InsurancePolicy insurancePolicyToResponse = insurancePolicyService.create(driverId, insurancePolicyToCreate);

        final InsurancePolicyResponse response = dozerBeanMapper.map(insurancePolicyToResponse, InsurancePolicyResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Method for display all insurance policy.
     *
     * @param driverId Driver driverId.
     * @return List of InsurancePolicyResponse response mapped from List of InsurancePolicy.
     */
    @RequestMapping(value = "/companies/{companyId}/drivers/{driverId}/insurance", method = RequestMethod.GET)
    public ResponseEntity fetchAll(@PathVariable final Integer driverId) {

        final List<InsurancePolicyResponse> response = new ArrayList<>();

        final List<InsurancePolicy> insurancePoliciesToResponse = insurancePolicyService.fetchAll(driverId);

        insurancePoliciesToResponse.forEach(insurancePolicy -> response.add(dozerBeanMapper.map(insurancePolicy,
                InsurancePolicyResponse.class)));

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for find some insurance policy.
     *
     * @param insuranceId InsurancePolicy insuranceId.
     * @return InsurancePolicyResponse response mapped from found insurance policy.
     */
    @RequestMapping(value = "/companies/{companyId}/drivers/{driverId}/insurance/{insuranceId}", method = RequestMethod.GET)
    public ResponseEntity fetch(@PathVariable final Integer insuranceId) {

        final InsurancePolicy insurancePolicyToResponse = insurancePolicyService.fetch(insuranceId);

        final InsurancePolicyResponse response = dozerBeanMapper.map(insurancePolicyToResponse, InsurancePolicyResponse.class);

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for update insurance policy.
     *
     * @param request InsurancePolicyRequest.
     * @return InsurancePolicyResponse response mapped from updated insurance policy.
     */
    @RequestMapping(value = "/companies/{companyId}/drivers/{driverId}/insurance", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody final InsurancePolicyRequest request) {

        final InsurancePolicy insurancePolicyToUpdate = dozerBeanMapper.map(request, InsurancePolicy.class);

        final InsurancePolicy insurancePolicyToResponse = insurancePolicyService.update(insurancePolicyToUpdate);

        final InsurancePolicyResponse response = dozerBeanMapper.map(insurancePolicyToResponse, InsurancePolicyResponse.class);

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for delete InsurancePolicy.
     *
     * @param insuranceId InsurancePolicy insuranceId.
     * @return InsurancePolicyResponse response mapped from deleted insurance policy.
     */
    @RequestMapping(value = "/companies/{companyId}/drivers/{driverId}/insurance/{insuranceId}",
            method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable final Integer insuranceId) {

        final InsurancePolicy insurancePolicyToResponse = insurancePolicyService.fetch(insuranceId);

        insurancePolicyService.delete(insuranceId);

        final InsurancePolicyResponse response = dozerBeanMapper.map(insurancePolicyToResponse, InsurancePolicyResponse.class);

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for fetch insurance policy by validate.
     *
     * @param validate {@link InsurancePolicy} validate.
     * @return list of {@link InsurancePolicyResponse} response mapped from list of insurance policy
     * which found.
     */
    @RequestMapping(value = "/companies/{companyId}/drivers/{driverId}/insurance/validate/{validate}",
            method = RequestMethod.GET)
    public ResponseEntity fetchByValidate(@PathVariable final long validate) {

        final List<InsurancePolicy> insurancePoliciesToResponse = insurancePolicyService
                .fetchByValidate(validate);

        final List<InsurancePolicyResponse> response = new ArrayList<>();

        insurancePoliciesToResponse.forEach(insurancePolicy -> response.add(dozerBeanMapper
                .map(insurancePolicy, InsurancePolicyResponse.class)));

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for fetch insurance policy by type.
     *
     * @param type {@link InsurancePolicy} type.
     * @return list of {@link InsurancePolicyResponse} response mapped from list of insurance policy
     * which found.
     */
    @RequestMapping(value = "/companies/{companyId}/drivers/{driverId}/insurance/type/{type}",
            method = RequestMethod.GET)
    public ResponseEntity fetchByType(@PathVariable final String type) {

        final List<InsurancePolicy> insurancePoliciesToResponse = insurancePolicyService.fetchByType(type);

        final List<InsurancePolicyResponse> response = new ArrayList<>();

        insurancePoliciesToResponse.forEach(insurancePolicy -> response.add(dozerBeanMapper
                .map(insurancePolicy, InsurancePolicyResponse.class)));

        return ResponseEntity.ok().body(response);
    }
}
