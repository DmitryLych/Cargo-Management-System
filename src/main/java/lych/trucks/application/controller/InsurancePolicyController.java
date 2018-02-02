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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

/**
 * Rest controller for {@link InsurancePolicy}.
 */
@RestController
@RequestMapping("/cargo/v1/companies/{companyId}/drivers/{driverId}/insurance")
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
    @PostMapping
    public ResponseEntity createInsurancePolicy(@PathVariable final Integer driverId, @RequestBody final InsurancePolicyRequest request) {

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
    @GetMapping
    public ResponseEntity fetchAllInsurancePolicies(@PathVariable final Integer driverId) {

        final List<InsurancePolicy> insurancePoliciesToResponse = insurancePolicyService.fetchAll(driverId);

        final List<InsurancePolicyResponse> response = Optional.ofNullable(insurancePoliciesToResponse)
                .map(insurancePolicies -> insurancePolicies.stream()
                        .map(insurancePolicy -> dozerBeanMapper.map(insurancePolicy, InsurancePolicyResponse.class))
                        .collect(toList()))
                .orElse(emptyList());

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for find some insurance policy.
     *
     * @param insuranceId InsurancePolicy insuranceId.
     * @return InsurancePolicyResponse response mapped from found insurance policy.
     */
    @GetMapping(path = "/{insuranceId}")
    public ResponseEntity fetchInsurancePolicy(@PathVariable final Integer insuranceId) {

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
    @PutMapping
    public ResponseEntity updateInsurancePolicy(@RequestBody final InsurancePolicyRequest request) {

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
    @DeleteMapping(path = "/{insuranceId}")
    public ResponseEntity deleteInsurancePolicy(@PathVariable final Integer insuranceId) {

        final InsurancePolicy insurancePolicyToResponse = insurancePolicyService.delete(insuranceId);

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
    @GetMapping(path = "/validate/{validate}")
    public ResponseEntity fetchInsurancePoliciesByValidate(@PathVariable final long validate) {

        final List<InsurancePolicy> insurancePoliciesToResponse = insurancePolicyService
                .fetchByValidate(validate);

        final List<InsurancePolicyResponse> response = Optional.ofNullable(insurancePoliciesToResponse)
                .map(insurancePolicies -> insurancePolicies.stream()
                        .map(insurancePolicy -> dozerBeanMapper.map(insurancePolicy, InsurancePolicyResponse.class))
                        .collect(toList()))
                .orElse(emptyList());

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for fetch insurance policy by type.
     *
     * @param type {@link InsurancePolicy} type.
     * @return list of {@link InsurancePolicyResponse} response mapped from list of insurance policy
     * which found.
     */
    @GetMapping(path = "/type/{type}")
    public ResponseEntity fetchInsurancePoliciesByType(@PathVariable final String type) {

        final List<InsurancePolicy> insurancePoliciesToResponse = insurancePolicyService.fetchByType(type);

        final List<InsurancePolicyResponse> response = Optional.ofNullable(insurancePoliciesToResponse)
                .map(insurancePolicies -> insurancePolicies.stream()
                        .map(insurancePolicy -> dozerBeanMapper.map(insurancePolicy, InsurancePolicyResponse.class))
                        .collect(toList()))
                .orElse(emptyList());

        return ResponseEntity.ok().body(response);
    }
}
