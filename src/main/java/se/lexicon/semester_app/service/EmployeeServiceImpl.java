package se.lexicon.semester_app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.semester_app.dto.EmployeeDto;
import se.lexicon.semester_app.dto.VacationDayDto;
import se.lexicon.semester_app.entity.Employee;
import se.lexicon.semester_app.entity.VacationDay;
import se.lexicon.semester_app.repo.EmployeeRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeRepo employeeRepo;
    ModelMapper modelMapper;

    @Autowired
    public void setEmployeeRepo(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public EmployeeDto findById(UUID id) {
        if (id == null) throw new IllegalArgumentException("id cannot be 0");
        Optional<Employee> optional = employeeRepo.findById(id);
        if (optional.isPresent()) {
            EmployeeDto dto = modelMapper.map(optional, EmployeeDto.class);
            return dto;
        }
        return null;
    }

    @Override
    public List<EmployeeDto> findAll() {
        List<Employee> employees = new ArrayList<>();
        employeeRepo.findAll().iterator().forEachRemaining(employees::add);
        List<EmployeeDto> employeeDtos = employees.stream().map(employee -> modelMapper.map(employee, EmployeeDto.class)).collect(Collectors.toList());
        return employeeDtos;
    }

    @Override
    public EmployeeDto create(EmployeeDto employeeDto) {
        if (employeeDto == null) throw new IllegalArgumentException("employeeDto cannot be null");
        if (employeeDto.getId() != null) throw new IllegalArgumentException("id cannot exist to create");
        Employee employeeEntity = modelMapper.map(employeeDto, Employee.class);
        Employee savedEntity = employeeRepo.save(employeeEntity);
        EmployeeDto converted = modelMapper.map(savedEntity, EmployeeDto.class);
        return converted;
    }

    @Override
    public EmployeeDto update(EmployeeDto employeeDto) {
        if (employeeDto == null) throw new IllegalArgumentException("employeeDto cannot be null");
        if (employeeDto.getId() == null) throw new IllegalArgumentException("id cannot be null");
        Optional<Employee> optional = employeeRepo.findById(employeeDto.getId());
        if (optional.isPresent())
            return modelMapper.map(employeeRepo.save(modelMapper.map(employeeDto, Employee.class)), EmployeeDto.class);
        return null;
    }

    @Override
    public void deleteById(UUID id) {
        if (id == null) throw new IllegalArgumentException("id must exist");
        Optional<Employee> optional = employeeRepo.findById(id);
        if (optional.isPresent()) {
            employeeRepo.deleteById(id);
        }

    }

    @Override
    public List<VacationDayDto> findByVacationDay(UUID id) {
        List<VacationDay> vacationDays = employeeRepo.findByVacationDay(id);
        List<VacationDayDto> vacationDayDtos = vacationDays.stream().map(vacationDay -> modelMapper.map(vacationDay, VacationDayDto.class)).collect(Collectors.toList());
        return vacationDayDtos;


    }
}
