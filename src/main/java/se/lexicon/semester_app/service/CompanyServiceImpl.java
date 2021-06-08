package se.lexicon.semester_app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.semester_app.dto.CompanyDto;
import se.lexicon.semester_app.dto.EmployeeDto;
import se.lexicon.semester_app.exception.RecordNotFoundException;
import se.lexicon.semester_app.repository.CompanyRepository;
import se.lexicon.semester_app.repository.EmployeeRepository;

import java.util.List;

public class CompanyServiceImpl implements CompanyService {
    CompanyRepository companyRepository;
    ModelMapper modelMapper;
    EmployeeRepository employeeRepository;

    @Autowired
    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public CompanyDto findById(int id) throws RecordNotFoundException {
        return null;
    }

    @Override
    public CompanyDto findByName(String name) {
        return null;
    }

    @Override
    public List<CompanyDto> findAll() {
        return null;
    }

    @Override
    public List<EmployeeDto> getEmployees(EmployeeDto employeeDto) {
        return null;
    }

    @Override
    public EmployeeDto getEmployee(EmployeeDto employeeDto) {
        return null;
    }

    @Transactional
    @Override
    public CompanyDto create(CompanyDto companyDto) {
        return null;
    }

    @Transactional
    @Override
    public CompanyDto update(CompanyDto companyDto) throws RecordNotFoundException {
        return null;
    }

    @Override
    public void delete(int id) throws RecordNotFoundException {

    }
}
