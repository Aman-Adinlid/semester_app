package se.lexicon.semester_app.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.semester_app.dto.CompanyDto;
import se.lexicon.semester_app.dto.EmployeeDto;
import se.lexicon.semester_app.dto.VacationDayDto;
import se.lexicon.semester_app.exception.RecordNotFoundException;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CompanyServiceImplTest {
    CompanyService companyService;
    CompanyDto companyDto;
    List<EmployeeDto> employeeDtoList;
    EmployeeService employeeService;
    EmployeeDto employeeDto;
    List<VacationDayDto> vacationDayDtoList;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;


    }

    @BeforeEach
    public void setUp() throws RecordNotFoundException {
        companyDto = new CompanyDto();
        companyDto.setName("Test");
        companyDto.setEmployeeDto(employeeDtoList);
        companyService.create(companyDto);

        employeeDto = new EmployeeDto();
        employeeDto.setFirstName("Test");
        employeeDto.setLastName("Test");
        employeeDto.setEmail("Test");
        employeeDto.setMobile("Test");
        employeeDto.setVacationDayDto(vacationDayDtoList);
        employeeDto.setSavedVacation(12);
        employeeDto.setYearlyVacationDays(2021);
        employeeDto.setDateOfEmployment(LocalDate.of(2021, 01, 12));
        employeeDto.setPassword("Test12");
        employeeService.create(employeeDto);
    }

    @Test
    @DisplayName("Test1 ")
    public void test1_findById() throws RecordNotFoundException {
        CompanyDto createdCompanyDto = companyService.create(companyDto);
        companyService.findById(createdCompanyDto.getId());
        assertEquals(2, companyService.findById(2).getId());

    }

    @Test
    @DisplayName("Test2 ")
    public void test2_findAll() {
        List<CompanyDto> companyDtoList = companyService.findAll();
        System.out.println(companyDtoList);
        assertEquals(1, companyService.findAll().get(0).getId());

    }

    @Test
    @DisplayName("Test3 ")
    public void test3_findCompanyName() {
        assertEquals("Test", companyService.findCompanyByName("Test").get(0).getName());
    }

    @Test
    @DisplayName("Test4 ")
    public void test4_create() throws RecordNotFoundException {
        CompanyDto createdCompanyDto = companyService.create(companyDto);
        companyService.findById(createdCompanyDto.getId());
        assertEquals("Test", companyService.create(companyDto).getName());
    }

    @Test
    @DisplayName("Test5 ")
    public void test5_update() throws RecordNotFoundException {
        // assertEquals(1, companyService.update(companyDto).getId());
    }

    @Test
    @DisplayName("Test6 ")
    public void test6_delete() throws RecordNotFoundException {
        CompanyDto createdCompanyDto = companyService.create(companyDto);
        companyService.delete(createdCompanyDto.getId());
        assertEquals(1, companyService.findAll().size());
    }

}


