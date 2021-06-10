package se.lexicon.semester_app.service;

import se.lexicon.semester_app.dto.CompanyDto;
import se.lexicon.semester_app.dto.EmployeeDto;
import se.lexicon.semester_app.exception.RecordNotFoundException;

import java.util.List;

public interface CompanyService {

    CompanyDto findById(int id) throws RecordNotFoundException;

    List<CompanyDto> findCompanyByName(String name);

    List<CompanyDto> findAll();

    List<EmployeeDto> getEmployees(EmployeeDto employeeDto);

    EmployeeDto getEmployee(EmployeeDto employeeDto);

    CompanyDto create(CompanyDto companyDto);

    CompanyDto update(CompanyDto companyDto) throws RecordNotFoundException;

    void delete(int id) throws RecordNotFoundException;
}
