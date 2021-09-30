package se.lexicon.semester_app.service;

import se.lexicon.semester_app.dto.CompanyDto;
import se.lexicon.semester_app.dto.EmployeeDto;
import se.lexicon.semester_app.entity.Company;
import se.lexicon.semester_app.exception.RecordNotFoundException;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

    CompanyDto findById(int id) throws RecordNotFoundException;

    List<CompanyDto> findCompanyByName(String name);

    List<CompanyDto> findAll();

    CompanyDto create(CompanyDto companyDto) throws RecordNotFoundException;

    CompanyDto update(CompanyDto companyDto) throws RecordNotFoundException;

    void delete(int id) throws RecordNotFoundException;

    CompanyDto findCompanyByWorkspace(String workspace) throws RecordNotFoundException;
}
