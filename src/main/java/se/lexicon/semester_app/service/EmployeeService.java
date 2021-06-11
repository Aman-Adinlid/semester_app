package se.lexicon.semester_app.service;

import se.lexicon.semester_app.dto.EmployeeDto;
import se.lexicon.semester_app.dto.VacationDayDto;
import se.lexicon.semester_app.entity.Employee;
import se.lexicon.semester_app.entity.VacationDay;
import se.lexicon.semester_app.exception.RecordNotFoundException;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    List<Employee> findAll();
    EmployeeDto findByid(UUID uuid) throws RecordNotFoundException;
    EmployeeDto createEmployee(EmployeeDto newEmployeeDto) throws RecordNotFoundException;
    EmployeeDto updateEmployee(EmployeeDto employeeDto) throws RecordNotFoundException;
    void deleteEmployee(UUID uuid) throws RecordNotFoundException;

    List<VacationDayDto> updateVaccationDays(List<VacationDayDto> vaccationDaysDto) throws RecordNotFoundException;
    List<VacationDayDto> addVacationDay(VacationDayDto vacationDayDto) throws RecordNotFoundException;
    List<VacationDayDto> addVacationDays(List<VacationDayDto> vacationDaysDto) throws RecordNotFoundException;;
    List<VacationDayDto> removeVacationDay(VacationDayDto vacationDayDto) throws RecordNotFoundException;;
    List<VacationDayDto> removeVacationDays(List<VacationDayDto> vacationDaysDto) throws RecordNotFoundException;;

}
