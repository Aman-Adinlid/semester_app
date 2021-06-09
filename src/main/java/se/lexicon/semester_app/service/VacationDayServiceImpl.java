package se.lexicon.semester_app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import se.lexicon.semester_app.dto.VacationDayDto;
import se.lexicon.semester_app.repo.VacationDayRepo;

import java.time.LocalDate;
import java.util.List;

public class VacationDayServiceImpl implements VacationDayService {
    VacationDayRepo vacationDayRepo;
    ModelMapper modelMapper;

    @Autowired
    public void setVacationDayRepo(VacationDayRepo vacationDayRepo) {
        this.vacationDayRepo = vacationDayRepo;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public VacationDayDto findById(int id) {
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
    public VacationDayDto update(VacationDayDto vacationDayDto) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public VacationDayDto findByVacationDate(LocalDate vacationDate) {
        return null;
    }

    @Override
    public List<VacationDayDto> findByApproved(boolean approved) {
        return null;
    }
}
