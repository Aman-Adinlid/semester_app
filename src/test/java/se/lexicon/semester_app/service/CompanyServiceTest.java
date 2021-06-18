package se.lexicon.semester_app.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.semester_app.dto.CompanyDto;
import se.lexicon.semester_app.dto.EmployeeDto;
import se.lexicon.semester_app.dto.VacationDayDto;
import se.lexicon.semester_app.exception.RecordNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CompanyServiceTest {

    CompanyService companyService;
    CompanyDto companyDto;



    @Autowired
    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }
    @BeforeEach
    public void setup() throws RecordNotFoundException {
        VacationDayDto vacationDayDto = new VacationDayDto();
        vacationDayDto.setVacationDate(LocalDate.now());
        vacationDayDto.setApproved(vacationDayDto.isApproved());

        List<VacationDayDto> vacationDayDtoList = new ArrayList<>();
        vacationDayDtoList.add(vacationDayDto);

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setSavedVacation(12);
        employeeDto.setYearlyVacationDays(2021);
        employeeDto.setDateOfEmployment(LocalDate.of(2021, 01, 12));

        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        employeeDtoList.add(employeeDto);

        companyDto = new CompanyDto();
        companyDto.setName("companyName");
        companyService.create(companyDto);


    }
    @Test
    public void findById() throws RecordNotFoundException {
      CompanyDto  companyDto2 = companyService.findById(1);
        System.out.println("CompanyDto 2 : "+companyDto2);
        System.out.println("CompanyDto 1 : "+companyDto);
        Assertions.assertEquals(companyDto2,companyService.findAll().get(0));
    }

    @Test
    public void findAll(){
        List<CompanyDto> companyDtos = companyService.findAll();
        System.out.println(companyDtos);
    }
}
