package se.lexicon.semester_app.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.semester_app.dto.CompanyDto;
import se.lexicon.semester_app.dto.EmployeeDto;
import se.lexicon.semester_app.exception.RecordNotFoundException;

import java.time.LocalDate;

@SpringBootTest
public class EmployeeServiceTest {

    EmployeeService employeeService;
    EmployeeDto employeeDto;
    CompanyDto companyDto;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @BeforeEach

    public void setUp() throws RecordNotFoundException {
        employeeDto = new EmployeeDto();
        employeeDto.setSavedVacation(12);
        employeeDto.setYearlyVacationDays(2021);
        employeeDto.setDateOfEmployment(LocalDate.of(2021, 01, 12));

        companyDto = new CompanyDto();
        companyDto.setName("Test");

        employeeDto.setCompany(companyDto);
        employeeService.create(employeeDto);

    }

    @Test
    @DisplayName("Test1 ")
    public void test1_findById() throws RecordNotFoundException {
        // EmployeeDto createdEmployeeDto = employeeService.create(employeeDto);
        // employeeService.findById(createdEmployeeDto.getId());
        //assertEquals("1", employeeService.findById("1").getId());
    }
}
