package se.lexicon.semester_app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.semester_app.dto.EmployeeDto;
import se.lexicon.semester_app.dto.VacationDayDto;
import se.lexicon.semester_app.entity.Employee;
import se.lexicon.semester_app.entity.VacationDay;
import se.lexicon.semester_app.exception.ArgumentException;
import se.lexicon.semester_app.exception.RecordNotFoundException;
import se.lexicon.semester_app.repository.EmployeeRepository;
import se.lexicon.semester_app.repository.UserRepository;
import se.lexicon.semester_app.repository.VacationDayRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
    public EmployeeDto findById(UUID id) throws RecordNotFoundException {
        if (id == null) throw new IllegalArgumentException("id should not be null");
        Optional<Employee> employee = employeeRepository.findById(id);
        if (!employee.isPresent())
            throw new RecordNotFoundException("Employee Id is not valid - data not fond");
        return modelMapper.map(employee.get(), EmployeeDto.class);
    }

    @Override
    public EmployeeDto save(EmployeeDto employeeDto) {
        if (employeeDto == null) throw new IllegalArgumentException("EmployeeDto object should not be null");
        if (employeeDto.getId() != null) throw new IllegalArgumentException("Id should be null");
        Employee employeeEntity = modelMapper.map(employeeDto, Employee.class);
        Employee savedEmployeeEntity = employeeRepository.save(employeeEntity);
        EmployeeDto convertedEntityToDto = modelMapper.map(savedEmployeeEntity, EmployeeDto.class);
        return convertedEntityToDto;
    }

    @Transactional
    @Override
    public EmployeeDto create(EmployeeDto employeeDto) {
        return modelMapper.map(employeeRepository.save(modelMapper.map(employeeDto, Employee.class)), EmployeeDto.class);
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

    // @Override
    //public List<VacationDay> getVacationDays (UUID id){
    // return null;//** need to fix

    @Override
    public List<VacationDayDto> findByVacationDay(UUID id) {
        List<VacationDay> vacationDays = employeeRepository.findByVacationDay(id);
        List<VacationDayDto> vacationDayDtoList = vacationDays.stream().map(vacationDay -> modelMapper.map(vacationDay, VacationDayDto.class)).collect(Collectors.toList());
        return vacationDayDtoList;
    }

   // @Override
  //  public List<VacationDay> saveVacationDays(UUID id, List<VacationDay> vacationDays) {
        //return null;


    @Override
    public void delete(UUID id) {
        if (id == null) throw new ArgumentException("Id is not valid");
        Optional<Employee> optional = employeeRepository.findById(id);
        if (optional.isPresent()) {
            employeeRepository.deleteById(id);
        }

    }
}
