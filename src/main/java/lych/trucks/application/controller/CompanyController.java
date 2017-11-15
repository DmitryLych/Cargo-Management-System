package lych.trucks.application.controller;

import lombok.RequiredArgsConstructor;
import lych.trucks.application.dto.CompanyDto;
import lych.trucks.domain.model.Company;
import lych.trucks.domain.service.CompanyService;
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

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CompanyController {

    private final CompanyService companyService;

    private final DozerBeanMapper dozerBeanMapper;

    @RequestMapping(value = "/companies", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody final CompanyDto companyDto) {

        final Company companyToSave = dozerBeanMapper.map(companyDto, Company.class);

        final Company companyToResponse = companyService.create(companyToSave);

        final CompanyDto response = dozerBeanMapper.map(companyToResponse, CompanyDto.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @RequestMapping(value = "/companies/{companyId}", method = RequestMethod.GET)
    public ResponseEntity fetch(@PathVariable final Integer companyId) {

        final Company companyToResponse = companyService.fetch(companyId);

        final CompanyDto response = dozerBeanMapper.map(companyToResponse, CompanyDto.class);

        return ResponseEntity.ok().body(response);
    }

    @RequestMapping(value = "/companies", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody final CompanyDto companyDto) {

        final Company companyToUpdate = dozerBeanMapper.map(companyDto, Company.class);

        final Company companyToResponse = companyService.update(companyToUpdate);

        final CompanyDto response = dozerBeanMapper.map(companyToResponse, CompanyDto.class);

        return ResponseEntity.ok().body(response);
    }

    @RequestMapping(value = "/companies/{companyId}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable final Integer companyId) {

        final Company companyToResponse = companyService.fetch(companyId);

        companyService.delete(companyId);

        final CompanyDto response = dozerBeanMapper.map(companyToResponse, CompanyDto.class);

        return ResponseEntity.ok().body(response);
    }

    @RequestMapping(value = "/companies", method = RequestMethod.GET)
    public ResponseEntity fetchAll() {

        final List<CompanyDto> response = new ArrayList<>();

        companyService.fetchAll().forEach(company -> response.add(dozerBeanMapper.map(company, CompanyDto.class)));

        return ResponseEntity.ok().body(response);
    }
}