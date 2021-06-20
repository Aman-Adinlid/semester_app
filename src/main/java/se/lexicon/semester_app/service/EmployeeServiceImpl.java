package se.lexicon.semester_app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.semester_app.dto.CompanyDto;
import se.lexicon.semester_app.dto.EmployeeDto;
import se.lexicon.semester_app.dto.UserDto;
import se.lexicon.semester_app.entity.Employee;
import se.lexicon.semester_app.exception.ArgumentException;
import se.lexicon.semester_app.exception.RecordNotFoundException;
import se.lexicon.semester_app.repo.EmployeeRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeRepo employeeRepository;
    ModelMapper modelMapper;
    CompanyService companyService;
    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Autowired
    public void setEmployeeRepository(EmployeeRepo employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public EmployeeDto findById(String id) throws RecordNotFoundException {
        if (id == null) throw new ArgumentException("Id should not be null");
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            EmployeeDto convertedData = modelMapper.map(optionalEmployee.get(), EmployeeDto.class);
            return convertedData;
        } else {
            throw new RecordNotFoundException("EmployeeDto not found");
        }
    }



    @Override
    public EmployeeDto create(EmployeeDto employeeDto) throws RecordNotFoundException {
        if (employeeDto == null) throw new ArgumentException("EmployeeDto object should not be null");
        if (employeeDto.getId() != null) throw new IllegalArgumentException("Id should be null");

        Employee employeeEntity = modelMapper.map(employeeDto, Employee.class);
        Employee savedEmployeeEntity = employeeRepository.save(employeeEntity);
        EmployeeDto convertedEntityToDto = modelMapper.map(savedEmployeeEntity, EmployeeDto.class);

        UserDto userDto = userService.findById(convertedEntityToDto.getUser().getId());
        CompanyDto companyDto = companyService.findById(convertedEntityToDto.getCompany().getId());
        convertedEntityToDto.setUser(userDto);
        convertedEntityToDto.setCompany(companyDto);
        return convertedEntityToDto;
    }



    @Override
    public EmployeeDto update(EmployeeDto employeeDto) throws RecordNotFoundException {
        return null;
    }

    @Override
    public List<EmployeeDto> findByUserType(int userType) {
        return null;
    }

    @Override
    public List<EmployeeDto> findAll() {
        return null;
    }

    @Override
    public List<EmployeeDto> findByCompany(CompanyDto companyDto) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}

