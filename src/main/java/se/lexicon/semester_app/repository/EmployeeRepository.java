package se.lexicon.semester_app.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.semester_app.entity.Employee;
import se.lexicon.semester_app.entity.VacationDay;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, String> {
    // @Query
    List<VacationDay> findAllByVacationDay(String id); //got warning cuz it should be a employee here!!!!!!!!!


}
