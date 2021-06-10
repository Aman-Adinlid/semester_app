package se.lexicon.semester_app.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.semester_app.entity.Company;

import java.util.List;

public interface CompanyRepository extends CrudRepository<Company, Integer> {
    List<Company> findCompanyByNameContainsIgnoreCase(String name); //not sure, should it be a list??

    // List<Employee> findByEmployees();
}
