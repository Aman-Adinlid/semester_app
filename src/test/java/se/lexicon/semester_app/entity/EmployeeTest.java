package se.lexicon.semester_app.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class EmployeeTest {
    Employee employee;
    List<VacationDay> vacationDayList;
    List<Employee> employeeList;
    Company company;
    User user;

    @BeforeEach
    public void setUp() {
        employee = new Employee();
        employee.setEmail("Test");
        employee.setMobile("Test1");
        employee.setSavedVacation(12);
        employee.setYearlyVacationDays(2021);
        employee.setDateOfEmployment(LocalDate.of(2021, 01, 12));

        company = new Company();
        company.setName("Test");

        user = new User();
        user.setEmail("Test");
        user.setUserType(UserType.USER);
    }

    @Test
    @DisplayName("Test1")
    public void test1_create_Employee() {
        Assertions.assertEquals("Test", employee.getEmail());
        Assertions.assertEquals("Test1", employee.getMobile());
        Assertions.assertEquals(12, employee.getSavedVacation());
        Assertions.assertEquals(2021, employee.getYearlyVacationDays());
        Assertions.assertEquals(LocalDate.of(2021, 01, 12), employee.getDateOfEmployment());
    }

    @Test
    @DisplayName("Test2")
    public void test2_equal() {
        employee = new Employee();
        employee.setEmail("Test");
        employee.setMobile("Test1");
        employee.setSavedVacation(12);
        employee.setYearlyVacationDays(2021);
        employee.setDateOfEmployment(LocalDate.of(2021, 01, 12));
        employee.setCompany(company);
        employee.setUser(user);
        Assertions.assertTrue(employee.equals(employee));
    }

    @Test
    @DisplayName("Test3")
    public void test3_hashCode() {
        employee = new Employee();
        employee.setEmail("Test");
        employee.setMobile("Test1");
        employee.setSavedVacation(12);
        employee.setYearlyVacationDays(2021);
        employee.setDateOfEmployment(LocalDate.of(2021, 01, 12));
        employee.setCompany(company);
        employee.setUser(user);
        Assertions.assertEquals(employee.hashCode(), employee.hashCode());

    }

}
