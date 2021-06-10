package se.lexicon.semester_app.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.semester_app.entity.Employee;
import se.lexicon.semester_app.entity.VacationDay;

import java.util.List;
import java.util.UUID;

public interface EmployeeRepository extends CrudRepository<Employee, UUID> {
    // List<VacationDay>();

    List<VacationDay> findByVacationDay(UUID id);

}
