package se.lexicon.semester_app.service;

import se.lexicon.semester_app.dto.CompanyDto;
import se.lexicon.semester_app.dto.EmployeeDto;
import se.lexicon.semester_app.exception.RecordNotFoundException;

import java.util.List;

public interface CompanyService {

    CompanyDto findById(String id) throws RecordNotFoundException;

    List<CompanyDto> findCompanyByName(String name);

    List<CompanyDto> findAll();

    // employee method can not be in here at all
    List<EmployeeDto> getEmployees(EmployeeDto employeeDto);

    // employee method can not be in here at all
    EmployeeDto getEmployee(EmployeeDto employeeDto);

    CompanyDto create(CompanyDto companyDto) throws RecordNotFoundException;

    CompanyDto update(CompanyDto companyDto) throws RecordNotFoundException;

    void delete(String id) throws RecordNotFoundException;
}
