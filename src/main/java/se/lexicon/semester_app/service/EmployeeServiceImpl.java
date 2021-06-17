package se.lexicon.semester_app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.semester_app.dto.CompanyDto;
import se.lexicon.semester_app.dto.EmployeeDto;
import se.lexicon.semester_app.entity.Employee;
import se.lexicon.semester_app.exception.ArgumentException;
import se.lexicon.semester_app.exception.RecordNotFoundException;
import se.lexicon.semester_app.repository.EmployeeRepository;
import se.lexicon.semester_app.repository.UserRepository;
import se.lexicon.semester_app.repository.VacationDayRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    EmployeeRepository employeeRepository;
    ModelMapper modelMapper;
    VacationDayRepository vacationDayRepository;
    UserRepository userRepository;

    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setVacationDayRepository(VacationDayRepository vacationDayRepository) {
        this.vacationDayRepository = vacationDayRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
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
    public EmployeeDto create(EmployeeDto employeeDto) {
        employeeDto.getCompanyDto().setId(1);
        return modelMapper.map(employeeRepository.save(modelMapper.map(employeeDto, Employee.class)), EmployeeDto.class);
    }

    @Override
    public List<EmployeeDto> findAll() {
        List<Employee> employeeList = new ArrayList<>();
        employeeRepository.findAll().iterator().forEachRemaining(employeeList::add);
        List<EmployeeDto> employeeDtoList = employeeList.stream().map(employee -> modelMapper.map(employee, EmployeeDto.class)).collect(Collectors.toList());
        return employeeDtoList;
    }


    @Override
    public EmployeeDto update(EmployeeDto employeeDto) throws RecordNotFoundException {
        if (employeeDto == null) throw new ArgumentException("EmployeeDto object should not be null");
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeDto.getId());
        if (optionalEmployee.isPresent()) {
            return modelMapper.map(employeeRepository.save(modelMapper.map(employeeDto, Employee.class)), EmployeeDto.class);
        } else {
            throw new RecordNotFoundException("EmployeeDto not found");
        }
    }

    @Override
    public List<EmployeeDto> findByUserType(int userType) {
        return null;
    }


    @Override
    public List<EmployeeDto> findByCompany(CompanyDto companyDto) throws RecordNotFoundException {
        if (companyDto == null) throw new RecordNotFoundException("null value not allowed");
        if (companyDto.getId() < 1) throw new RecordNotFoundException("value not allowed");

        return findAll().stream().filter(employeeDto -> employeeDto.getCompanyDto().getId() == companyDto.getId()).collect(Collectors.toList());
    }


    @Override
    public void delete(String id) {
        if (id == null) throw new ArgumentException("Id is not valid");
        Optional<Employee> optional = employeeRepository.findById(id);
        if (optional.isPresent()) {
            employeeRepository.deleteById(id);
        }

    }
}



