package se.lexicon.semester_app.service;

import se.lexicon.semester_app.dto.CompanyDto;
import se.lexicon.semester_app.dto.EmployeeDto;
import se.lexicon.semester_app.exception.RecordNotFoundException;

import java.util.List;

public interface CompanyService {

    CompanyDto findById(int id) throws RecordNotFoundException;

    List<CompanyDto> findCompanyByName(String name);

    List<CompanyDto> findAll();

    // employee method can not be in here at all
    List<EmployeeDto> getEmployees(EmployeeDto employeeDto);

    // employee method can not be in here at all
    EmployeeDto getEmployee(EmployeeDto employeeDto);

    CompanyDto create(CompanyDto companyDto) throws RecordNotFoundException;

    CompanyDto update(CompanyDto companyDto) throws RecordNotFoundException;

    void delete(int id) throws RecordNotFoundException;

    CompanyDto findCompanyByWorkspace(String workspace) throws RecordNotFoundException;
}
