package se.lexicon.semester_app.service;

import se.lexicon.semester_app.dto.CompanyDto;
import se.lexicon.semester_app.dto.EmployeeDto;

import java.util.List;

public interface CompanyService {
    CompanyDto findById(int id);

    List<CompanyDto> findAll();

    CompanyDto create(CompanyDto companyDto);

    CompanyDto update(CompanyDto companyDto);

    void deleteById(int id);

    CompanyDto findByName(String name);

    //INPUT 1 EMPLOYEE TO GET LIST OF EMPLOYEES??
    //List<EmployeeDto> getEmployees(EmployeeDto employeeDto);

    CompanyDto findByEmployee(EmployeeDto employeeDto);
}
