package se.lexicon.semester_app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.semester_app.dto.EmployeeDto;
import se.lexicon.semester_app.dto.VacationDayDto;
import se.lexicon.semester_app.entity.Employee;
import se.lexicon.semester_app.exception.RecordNotFoundException;
import se.lexicon.semester_app.repository.EmployeeRepository;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeRepository employeeRepository;
    ModelMapper modelMapper;

    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Employee> findAll() {
        return null;
    }

    @Override
    public EmployeeDto findByid(UUID uuid) throws RecordNotFoundException {
        return null;
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto newEmployeeDto) throws RecordNotFoundException {
        return null;
    }

    @Override
    public List<VacationDayDto> updateVaccationDay(VacationDayDto vaccationDayDto) throws RecordNotFoundException {
        return null;
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) throws RecordNotFoundException {
        return null;
    }

    @Override
    public void deleteEmployee(UUID uuid) throws RecordNotFoundException {

    }

    @Override
    public List<VacationDayDto> updateVaccationDays(List<VacationDayDto> vaccationDaysDto) throws RecordNotFoundException {
        return null;
    }

    @Override
    public List<VacationDayDto> addVacationDay(VacationDayDto vacationDayDto) throws RecordNotFoundException {
        return null;
    }

    @Override
    public List<VacationDayDto> addVacationDays(List<VacationDayDto> vacationDaysDto) throws RecordNotFoundException {
        return null;
    }

    @Override
    public List<VacationDayDto> removeVacationDay(VacationDayDto vacationDayDto) throws RecordNotFoundException {
        return null;
    }

    @Override
    public List<VacationDayDto> removeVacationDays(List<VacationDayDto> vacationDaysDto) throws RecordNotFoundException {
        return null;
    }
}
