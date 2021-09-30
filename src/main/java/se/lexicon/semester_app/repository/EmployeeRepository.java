package se.lexicon.semester_app.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.semester_app.entity.Company;
import se.lexicon.semester_app.entity.Employee;
import se.lexicon.semester_app.exception.RecordNotFoundException;

import java.util.List;
import java.util.Optional;


public interface EmployeeRepository extends CrudRepository<Employee, String> {

     List<Employee> findByCompany(Company company); // think about it

     Optional<Employee> findByUserId(int id) throws RecordNotFoundException;

     List<Employee> findEmployeesByCompanyId(int id);
}
