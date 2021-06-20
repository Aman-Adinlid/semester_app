package se.lexicon.semester_app.repo;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.semester_app.entity.Employee;
import java.util.UUID;

public interface EmployeeRepo extends CrudRepository<Employee, String> {

}
