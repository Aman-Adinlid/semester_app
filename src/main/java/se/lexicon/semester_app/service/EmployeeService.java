package se.lexicon.semester_app.service;

import se.lexicon.semester_app.dto.EmployeeDto;
import se.lexicon.semester_app.dto.UserDto;
import se.lexicon.semester_app.dto.VacationDayDto;
import se.lexicon.semester_app.entity.VacationDay;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    //todo :
    // CRUD
    // List <VacationDays> getVacationDays (UUID id)
    // List<VacationDays> saveVacationDays (UUID id, List<VacationDays> vacationDays)

    EmployeeDto findById(UUID id);

    List<EmployeeDto> findAll();

    EmployeeDto create(EmployeeDto employeeDto);

    EmployeeDto update(EmployeeDto employeeDto);

    void deleteById(UUID id);

    List<VacationDayDto> findByVacationDay(UUID id);



}
