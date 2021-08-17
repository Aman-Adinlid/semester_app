package se.lexicon.semester_app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.semester_app.controller.SignIn_Out;
import se.lexicon.semester_app.dto.CompanyDto;
import se.lexicon.semester_app.dto.EmployeeDto;
import se.lexicon.semester_app.entity.Company;
import se.lexicon.semester_app.entity.Employee;
import se.lexicon.semester_app.entity.User;
import se.lexicon.semester_app.exception.ArgumentException;
import se.lexicon.semester_app.exception.RecordNotFoundException;
import se.lexicon.semester_app.repository.EmployeeRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static se.lexicon.semester_app.controller.SignIn_Out.SignedInUser;
import static se.lexicon.semester_app.controller.SignIn_Out.user;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    EmployeeRepository employeeRepository;
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
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
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
    @Transactional
    @Override
    public EmployeeDto create(EmployeeDto employeeDto) throws RecordNotFoundException {
        if (employeeDto == null) throw new ArgumentException("EmployeeDto object should not be null");
        if (employeeDto.getId() != null) throw new IllegalArgumentException("Id should be null");

        Employee employeeEntity = modelMapper.map(employeeDto, Employee.class);
        Employee savedEmployeeEntity = employeeRepository.save(employeeEntity);
        EmployeeDto convertedEntityToDto = modelMapper.map(savedEmployeeEntity, EmployeeDto.class);

         if(SignIn_Out.SignedInUser(user) == null){
             throw new IllegalStateException("You have to sign in");
         }
         CompanyDto companyDto = companyService.findById(convertedEntityToDto.getCompany().getId());
         convertedEntityToDto.setCompany(companyDto);

         User user = userService.findById(convertedEntityToDto.getUser().getId());
          convertedEntityToDto.setUser(user);


        return convertedEntityToDto;
    }



    @Override
    public List<EmployeeDto> findAll() {
        List<Employee> employeeList = new ArrayList<>();
        employeeRepository.findAll().iterator().forEachRemaining(employeeList::add);
        List<EmployeeDto> employeeDtoList = employeeList.stream().map(employee -> modelMapper.map(employee, EmployeeDto.class)).collect(Collectors.toList());
        return employeeDtoList;
    }


    @Transactional
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
    public List<EmployeeDto> findByCompany(CompanyDto companyDto) {
        if (companyDto.getId() == null) throw new ArgumentException("Id should not be null");
        List<Employee> employees = employeeRepository.findByCompany(modelMapper.map(companyDto, Company.class));
        List<EmployeeDto> employeeDtoList = employees.stream().map(employee -> modelMapper.map(employee, EmployeeDto.class)).collect(Collectors.toList());
        return employeeDtoList;
    }

    @Override
    public void delete(String id) {
        if (id == null) throw new ArgumentException("Id is not valid");
        Optional<Employee> optional = employeeRepository.findById(id);
        if (optional.isPresent()) {
            employeeRepository.deleteById(id);
        }

    }

    @Override
    public List<EmployeeDto> findByRequest(String request) {
        List<Employee> employeeList=  employeeRepository.findEmployeesByRequest(request);
        List<EmployeeDto> employeeDtoList = employeeList.stream().map(employee -> modelMapper.map(employee,EmployeeDto.class)).collect(Collectors.toList());
        return employeeDtoList;
    }
}



