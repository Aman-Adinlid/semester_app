package se.lexicon.semester_app.service;

import se.lexicon.semester_app.dto.VacationDayDto;
import se.lexicon.semester_app.entity.VacationDay;

import java.time.LocalDate;
import java.util.List;

public interface VacationDayService {
    VacationDayDto findById(int id);

    List<VacationDayDto> findAll();

    VacationDayDto create(VacationDayDto vacationDayDto);

    VacationDayDto update(VacationDayDto vacationDayDto);

    void deleteById(int id);

    VacationDayDto findByVacationDate(LocalDate vacationDate);

    List<VacationDayDto> findByApproved(boolean approved);

}
