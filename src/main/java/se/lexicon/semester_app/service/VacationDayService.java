package se.lexicon.semester_app.service;

import se.lexicon.semester_app.dto.EmployeeDto;
import se.lexicon.semester_app.dto.VacationDayDto;
import se.lexicon.semester_app.exception.RecordNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface VacationDayService {

    VacationDayDto findById(int id) throws RecordNotFoundException;

    VacationDayDto findByVacationDate(LocalDate vacationDate);

    List<VacationDayDto> findAll();

    VacationDayDto create(VacationDayDto vacationDayDto);

    VacationDayDto update(VacationDayDto vacationDayDto) throws RecordNotFoundException;

    List<VacationDayDto> findByApproved(boolean approved);

    boolean isApproved(VacationDayDto vacationDayDto);

    List<VacationDayDto> findByEmployee(EmployeeDto employeeDto);

    void delete(int id) throws RecordNotFoundException;
}
