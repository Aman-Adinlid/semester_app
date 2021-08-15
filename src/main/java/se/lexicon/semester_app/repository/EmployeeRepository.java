package se.lexicon.semester_app.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.semester_app.entity.Company;
import se.lexicon.semester_app.entity.Employee;

import java.util.List;
import java.util.Optional;


public interface EmployeeRepository extends CrudRepository<Employee, String> {

     List<Employee> findByCompany(Company company); // think about it

     List<Employee> findByCompanyId(int id);

     Optional<Employee> findEmployeeByUserId(int id);
}

