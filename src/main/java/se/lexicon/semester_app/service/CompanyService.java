package se.lexicon.semester_app.service;

import se.lexicon.semester_app.dto.CompanyDto;
import se.lexicon.semester_app.dto.EmployeeDto;
import se.lexicon.semester_app.dto.VacationDayDto;
import se.lexicon.semester_app.exception.RecordNotFoundException;

import java.util.List;
import java.util.UUID;

public interface CompanyService {
    List<CompanyDto> findAll();
    CompanyDto findById(UUID uuid) throws RecordNotFoundException;

    CompanyDto createCompany(CompanyDto companyDto) throws RecordNotFoundException;
    CompanyDto updateCompany(CompanyDto companyDto) throws RecordNotFoundException;
    void delete(UUID uuid) throws RecordNotFoundException;

    EmployeeDto updateEmployee(EmployeeDto employeeDto) throws RecordNotFoundException;
    EmployeeDto addEmployee(EmployeeDto employeeDto) throws RecordNotFoundException;
    EmployeeDto removeEmployee(UUID employeeUuid) throws RecordNotFoundException;;

}
