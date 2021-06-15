package se.lexicon.semester_app.service;

import se.lexicon.semester_app.dto.EmployeeDto;
import se.lexicon.semester_app.dto.VacationDayDto;
import se.lexicon.semester_app.exception.RecordNotFoundException;

import java.util.List;

public interface EmployeeService {

    EmployeeDto findById(String id) throws RecordNotFoundException;

    EmployeeDto save(EmployeeDto employeeDto);

    EmployeeDto create(EmployeeDto employeeDto) throws RecordNotFoundException;

    EmployeeDto update(EmployeeDto employeeDto) throws RecordNotFoundException;

    List<EmployeeDto> findByUserType(int userType);

    List<EmployeeDto> findAll();

    // have to fix a query for it in repo, need to make sure if it is correct to have this method in here??
    List<VacationDayDto> findAllByVacationDay(String id);

    void delete(String id);

}