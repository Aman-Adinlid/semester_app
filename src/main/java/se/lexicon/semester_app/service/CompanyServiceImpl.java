package se.lexicon.semester_app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.semester_app.dto.CompanyDto;
import se.lexicon.semester_app.dto.EmployeeDto;
import se.lexicon.semester_app.entity.Company;
import se.lexicon.semester_app.exception.ArgumentException;
import se.lexicon.semester_app.exception.RecordNotFoundException;
import se.lexicon.semester_app.repo.CompanyRepo;
import se.lexicon.semester_app.repo.EmployeeRepo;
import se.lexicon.semester_app.repo.CompanyRepo;
import se.lexicon.semester_app.repo.EmployeeRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    CompanyRepo companyRepo;
    ModelMapper modelMapper;
    EmployeeRepo employeeRepo;

    @Autowired
    public void setCompanyRepository(CompanyRepo companyRepo) {
        this.companyRepo = companyRepo;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setEmployeeRepository(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public CompanyDto findById(int id) throws RecordNotFoundException {
        return modelMapper.map(companyRepo.findById(id).orElseThrow(() ->
                new RecordNotFoundException("CompanyDto not found")), CompanyDto.class);
    }

    @Override
    public List<CompanyDto> findCompanyByName(String name) {
        List<Company> companyList = new ArrayList<>();
        companyRepo.findCompanyByNameContainsIgnoreCase(name).iterator().forEachRemaining(companyList::add);
        List<CompanyDto> companyDtoList = companyList.stream().map(company ->
                modelMapper.map(company, CompanyDto.class)).collect(Collectors.toList());
        return companyDtoList;
    }

    @Override
    public List<CompanyDto> findAll() {
        List<Company> companyList = new ArrayList<>();
        companyRepo.findAll().iterator().forEachRemaining(companyList::add);
        List<CompanyDto> companyDtoList = companyList.stream().map(company ->
                modelMapper.map(company, CompanyDto.class)).collect(Collectors.toList());
        return companyDtoList;
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

        return modelMapper.map(companyRepo.save(modelMapper.map(companyDto, Company.class)), CompanyDto.class);

    }

    @Transactional
    @Override
    public CompanyDto update(CompanyDto companyDto) throws RecordNotFoundException {
        if (companyDto == null) throw new ArgumentException("CompanyDto object should not be null");
        if (companyDto.getId() < 1) throw new IllegalArgumentException("CompanyId should not be null");
        Optional<Company> companyOptional = companyRepo.findById(companyDto.getId());
        if (companyOptional.isPresent()) {
            return modelMapper.map(companyRepo.save(modelMapper.map(companyDto, Company.class)), CompanyDto.class);
        } else {
            throw new RecordNotFoundException("CompanyDto not found");
        }
    }

    @Override
    public void delete(int id) throws RecordNotFoundException {
        if (id == 0) throw new ArgumentException("Id is not valid");
        companyRepo.delete(modelMapper.map(companyRepo.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Id ")), Company.class));
    }
}
