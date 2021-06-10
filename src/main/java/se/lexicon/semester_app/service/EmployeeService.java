package se.lexicon.semester_app.service;

import se.lexicon.semester_app.dto.EmployeeDto;
import se.lexicon.semester_app.dto.VacationDayDto;
import se.lexicon.semester_app.exception.RecordNotFoundException;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    EmployeeDto findById(UUID id) throws RecordNotFoundException;

    EmployeeDto save(EmployeeDto employeeDto);

    EmployeeDto create(EmployeeDto employeeDto) throws RecordNotFoundException;

    EmployeeDto update(EmployeeDto employeeDto) throws RecordNotFoundException;

    List<EmployeeDto> findByUserType(int userType);

    List<EmployeeDto> findAll();

    // List<VacationDay> getVacationDays(UUID id);

    List<VacationDayDto> findByVacationDay(UUID id);

    // List<VacationDay> saveVacationDays(UUID id, List<VacationDay> vacationDays);

    void delete(UUID id);

}
