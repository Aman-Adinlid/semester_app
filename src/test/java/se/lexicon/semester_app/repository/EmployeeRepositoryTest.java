package se.lexicon.semester_app.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.semester_app.entity.Employee;

@SpringBootTest
public class EmployeeRepositoryTest {
    EmployeeRepository employeeRepository;
    Employee employee;

    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


}
