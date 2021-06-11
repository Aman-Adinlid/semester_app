package se.lexicon.semester_app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.semester_app.dto.CompanyDto;
import se.lexicon.semester_app.dto.EmployeeDto;
import se.lexicon.semester_app.exception.RecordNotFoundException;
import se.lexicon.semester_app.repository.CompanyRepository;

import java.util.List;
import java.util.UUID;

@Service
public class CompanyServiceImpl implements CompanyService {
    CompanyRepository companyRepository;
    ModelMapper modelMapper;

    @Autowired
    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CompanyDto> findAll() {
        return null;
    }

    @Override
    public CompanyDto findById(UUID uuid) throws RecordNotFoundException {
        return null;
    }

    @Override
    public CompanyDto createCompany(CompanyDto companyDto) throws RecordNotFoundException {
        return null;
    }

    @Override
    public CompanyDto updateCompany(CompanyDto companyDto) throws RecordNotFoundException {
        return null;
    }

    @Override
    public void delete(UUID uuid) throws RecordNotFoundException {

    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) throws RecordNotFoundException {
        return null;
    }

    @Override
    public EmployeeDto addEmployee(EmployeeDto employeeDto) throws RecordNotFoundException {
        return null;
    }

    @Override
    public EmployeeDto removeEmployee(UUID employeeUuid) throws RecordNotFoundException {
        return null;
    }
}
