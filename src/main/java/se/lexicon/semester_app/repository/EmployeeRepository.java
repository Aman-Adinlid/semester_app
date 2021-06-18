package se.lexicon.semester_app.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.semester_app.entity.Company;
import se.lexicon.semester_app.entity.Employee;

import java.util.List;


public interface EmployeeRepository extends CrudRepository<Employee, String> {

    List<Employee> findByCompany(Company company); // think about it
}
