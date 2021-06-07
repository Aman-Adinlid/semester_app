package se.lexicon.semester_app.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.semester_app.entity.Employee;

import java.util.UUID;

public interface EmployeeRepository extends CrudRepository<Employee, UUID> {
}
