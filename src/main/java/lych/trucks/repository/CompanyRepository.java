package lych.trucks.repository;

import lych.trucks.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Integer>{
}
