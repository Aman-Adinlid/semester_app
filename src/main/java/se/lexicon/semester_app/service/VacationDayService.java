package se.lexicon.semester_app.service;

import se.lexicon.semester_app.dto.VacationDayDto;
import se.lexicon.semester_app.exception.RecordNotFoundException;

import java.util.List;
import java.util.UUID;

public interface VacationDayService {
    List<VacationDayDto> findAll();

    VacationDayDto create(VacationDayDto vacationDayDto) throws RecordNotFoundException;

    VacationDayDto findById(UUID uuid) throws RecordNotFoundException;

    VacationDayDto update(VacationDayDto vacationDayDto) throws RecordNotFoundException;

    void delete(UUID uuid) throws RecordNotFoundException;

}
