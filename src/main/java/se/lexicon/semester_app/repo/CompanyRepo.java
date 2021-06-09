package se.lexicon.semester_app.repo;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.semester_app.dto.EmployeeDto;
import se.lexicon.semester_app.entity.Company;
import se.lexicon.semester_app.entity.Employee;

import java.util.List;

public interface CompanyRepo extends CrudRepository<Company, Integer> {
    Company findByName(String name);


    //INPUT 1 EMPLOYEE TO GET LIST OF EMPLOYEES??
    //List<Employee> findByEmployees(Employee employee);



    //NOT SURE ABOUT THAT
    Company findByEmployee(EmployeeDto employeeDto);
}
