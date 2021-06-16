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
import se.lexicon.semester_app.repository.CompanyRepository;
import se.lexicon.semester_app.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
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
        return modelMapper.map(companyRepository.findById(id).orElseThrow(() ->
                new RecordNotFoundException("CompanyDto not found")), CompanyDto.class);
    }

    @Override
    public List<CompanyDto> findCompanyByName(String name) {
        List<Company> companyList = new ArrayList<>();
        companyRepository.findCompanyByNameContainsIgnoreCase(name).iterator().forEachRemaining(companyList::add);
        List<CompanyDto> companyDtoList = companyList.stream().map(company ->
                modelMapper.map(company, CompanyDto.class)).collect(Collectors.toList());
        return companyDtoList;
    }

    @Override
    public List<CompanyDto> findAll() {
        List<Company> companyList = new ArrayList<>();
        companyRepository.findAll().iterator().forEachRemaining(companyList::add);
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
        // check company dto
        // chek companyDto.getEmployee()
        // save Employee to database
        // save company
        // return
        return modelMapper.map(companyRepository.save(modelMapper.map(companyDto, Company.class)), CompanyDto.class);

    }

    @Transactional
    @Override
    public CompanyDto update(CompanyDto companyDto) throws RecordNotFoundException {
        if (companyDto == null) throw new ArgumentException("CompanyDto object should not be null");
        if (companyDto.getId() < 1) throw new IllegalArgumentException("CompanyId should not be null");
        Optional<Company> companyOptional = companyRepository.findById(companyDto.getId());
        if (companyOptional.isPresent()) {
            return modelMapper.map(companyRepository.save(modelMapper.map(companyDto, Company.class)), CompanyDto.class);
        } else {
            throw new RecordNotFoundException("CompanyDto not found");
        }
    }

    @Override
    public void delete(int id) throws RecordNotFoundException {
        if (id == 0) throw new ArgumentException("Id is not valid");
        companyRepository.delete(modelMapper.map(companyRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Id ")), Company.class));
    }
}
