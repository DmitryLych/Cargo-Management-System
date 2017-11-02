package lych.trucks.service;

import lych.trucks.model.Company;

import java.util.List;

public interface CompanyService {

    List<Company> companies();

    Company create(Company company);

    Company fetch(Integer id);

    void delete(Integer id);

    Company update(Company company);
}
