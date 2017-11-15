package lych.trucks.domain.service;

import lych.trucks.domain.model.Company;

import java.util.List;

public interface CompanyService {

    List<Company> fetchAll();

    Company create(Company company);

    Company fetch(Integer id);

    void delete(Integer id);

    Company update(Company company);
}
