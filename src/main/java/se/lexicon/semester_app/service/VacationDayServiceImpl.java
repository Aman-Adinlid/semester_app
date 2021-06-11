package se.lexicon.semester_app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.semester_app.dto.VacationDayDto;
import se.lexicon.semester_app.exception.RecordNotFoundException;
import se.lexicon.semester_app.repository.VacationDayRepository;

import java.util.List;
import java.util.UUID;

@Service
public class VacationDayServiceImpl implements VacationDayService{
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
    public List<VacationDayDto> findAll() {
        return null;
    }

    @Override
    public VacationDayDto create(VacationDayDto vacationDayDto) throws RecordNotFoundException {
        return null;
    }

    @Override
    public VacationDayDto findById(UUID uuid) throws RecordNotFoundException {
        return null;
    }

    @Override
    public VacationDayDto update(VacationDayDto vacationDayDto) throws RecordNotFoundException {
        return null;
    }

    @Override
    public void delete(UUID uuid) throws RecordNotFoundException {

    }
}
