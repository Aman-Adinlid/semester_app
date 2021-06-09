package se.lexicon.semester_app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import se.lexicon.semester_app.dto.CompanyDto;
import se.lexicon.semester_app.dto.EmployeeDto;
import se.lexicon.semester_app.entity.Company;
import se.lexicon.semester_app.entity.Employee;
import se.lexicon.semester_app.repo.CompanyRepo;
import se.lexicon.semester_app.repo.EmployeeRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CompanyServiceImpl implements CompanyService {
    CompanyRepo companyRepo;
    EmployeeRepo employeeRepo;
    ModelMapper modelMapper;

    @Autowired
    public void setCompanyRepo(CompanyRepo companyRepo) {
        this.companyRepo = companyRepo;
    }

    @Autowired
    public void setEmployeeRepo(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CompanyDto findById(int id) {
        if (id == 0) throw new IllegalArgumentException("id cannot be 0");
        Optional<Company> optional = companyRepo.findById(id);
        if (optional.isPresent()) {
            CompanyDto dto = modelMapper.map(optional, CompanyDto.class);
            return dto;
        }
        return null;
    }

    @Override
    public List<CompanyDto> findAll() {
        List<Company> companies = new ArrayList<>();
        companyRepo.findAll().iterator().forEachRemaining(companies::add);
        List<CompanyDto> companyDtos = companies.stream().map(company -> modelMapper.map(company, CompanyDto.class)).collect(Collectors.toList());
        return companyDtos;
    }

    @Override
    public CompanyDto create(CompanyDto companyDto) {
        if (companyDto == null) throw new IllegalArgumentException("companyDto cannot be null");
        if (companyDto.getId() != 0) throw new IllegalArgumentException("id cannot exist to create");
        Company companyEntity = modelMapper.map(companyDto, Company.class);
        Company savedEntity = companyRepo.save(companyEntity);
        CompanyDto converted = modelMapper.map(savedEntity, CompanyDto.class);
        return converted;
    }

    @Override
    public CompanyDto update(CompanyDto companyDto) {
        if (companyDto == null) throw new IllegalArgumentException("companyDto cannot be null");
        if (companyDto.getId() == 0) throw new IllegalArgumentException("id cannot be 0");
        Optional<Company> optional = companyRepo.findById(companyDto.getId());
        if (optional.isPresent())
            return modelMapper.map(companyRepo.save(modelMapper.map(companyDto, Company.class)), CompanyDto.class);
        return null;
    }

    @Override
    public void deleteById(int id) {
        if (id == 0) throw new IllegalArgumentException("id must exist");
        Optional<Company> optional = companyRepo.findById(id);
        if (optional.isPresent())
            companyRepo.deleteById(id);

    }

    @Override
    public CompanyDto findByName(String name) {
        Company company = companyRepo.findByName(name);
        CompanyDto dto = modelMapper.map(company, CompanyDto.class);
        return dto;
    }

    //INPUT 1 EMPLOYEE TO GET LIST OF EMPLOYEES??
/*    @Override
    public List<EmployeeDto> getEmployees(EmployeeDto employeeDto) {
        List<Employee> employees = companyRepo.findByEmployees(employeeDto);
        List<EmployeeDto> dtos = employees.stream().map(employee -> modelMapper.map(employee, EmployeeDto.class)).collect(Collectors.toList());
        return dtos;
    }*/

    @Override
    public CompanyDto findByEmployee(EmployeeDto employeeDto) {
        Company company = companyRepo.findByEmployee(employeeDto);
        CompanyDto dto = modelMapper.map(company, CompanyDto.class);
        return dto;
    }
}
