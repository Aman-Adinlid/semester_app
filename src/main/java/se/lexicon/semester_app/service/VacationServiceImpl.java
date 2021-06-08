package se.lexicon.semester_app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.semester_app.dto.VacationDayDto;
import se.lexicon.semester_app.exception.RecordNotFoundException;
import se.lexicon.semester_app.repository.VacationDayRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class VacationServiceImpl implements VacationDayService{
    VacationDayRepository vacationDayRepository;
    ModelMapper modelMapper;

    @Autowired
    public void setVacationDayRepository(VacationDayRepository vacationDayRepository) {
        this.vacationDayRepository = vacationDayRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public VacationDayDto findById(int id) throws RecordNotFoundException {
        return null;
    }

    @Override
    public VacationDayDto findByLocalDate(LocalDate vacationDate) {
        return null;
    }

    @Override
    public List<VacationDayDto> findAll() {
        return null;
    }

    @Override
    public VacationDayDto create(VacationDayDto vacationDayDto) {
        return null;
    }

    @Override
    public VacationDayDto update(VacationDayDto vacationDayDto) throws RecordNotFoundException {
        return null;
    }

    @Override
    public List<VacationDayDto> getApproved(boolean approved) {
        return null;
    }

    @Override
    public boolean isApproved(VacationDayDto vacationDayDto) {
        return false;
    }

    @Override
    public void delete(int id) throws RecordNotFoundException {

    }
}
