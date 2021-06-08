package se.lexicon.semester_app.service;

import se.lexicon.semester_app.dto.VacationDayDto;
import se.lexicon.semester_app.exception.RecordNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface VacationDayService {
    VacationDayDto findById(int id) throws RecordNotFoundException;

    VacationDayDto findByLocalDate(LocalDate vacationDate);

    List<VacationDayDto> findAll();

    VacationDayDto create(VacationDayDto vacationDayDto);

    VacationDayDto update(VacationDayDto vacationDayDto) throws RecordNotFoundException;

    List<VacationDayDto> getApproved(boolean approved); // ask M

    boolean isApproved (VacationDayDto vacationDayDto);

    void delete(int id) throws RecordNotFoundException;
}
