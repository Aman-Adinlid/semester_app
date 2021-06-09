package se.lexicon.semester_app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import se.lexicon.semester_app.dto.VacationDayDto;
import se.lexicon.semester_app.entity.VacationDay;
import se.lexicon.semester_app.repo.VacationDayRepo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        if (id == 0) throw new IllegalArgumentException("id cannot be 0");
       Optional<VacationDay> optional = vacationDayRepo.findById(id);
       if (optional.isPresent()){
           VacationDayDto dto = modelMapper.map(optional, VacationDayDto.class);
           return dto;
       }
        return null;
    }

    @Override
    public List<VacationDayDto> findAll() {
        List<VacationDay> vacationDays = new ArrayList<>();
        vacationDayRepo.findAll().iterator().forEachRemaining(vacationDays::add);
        List<VacationDayDto> vacationDayDtos = vacationDays.stream().map(vacationDay -> modelMapper.map(vacationDay, VacationDayDto.class)).collect(Collectors.toList());
        return vacationDayDtos;
    }

    @Override
    public VacationDayDto create(VacationDayDto vacationDayDto) {
        if (vacationDayDto == null) throw new IllegalArgumentException("vacationDayDto cannot be null");
        if (vacationDayDto.getId() != 0) throw new IllegalArgumentException("id cannot exist to create");
        VacationDay vacationDayEntity = modelMapper.map(vacationDayDto, VacationDay.class);
        VacationDay savedEntity = vacationDayRepo.save(vacationDayEntity);
        VacationDayDto converted = modelMapper.map(savedEntity, VacationDayDto.class);
        return converted;
    }

    @Override
    public VacationDayDto update(VacationDayDto vacationDayDto) {
        if (vacationDayDto == null) throw new IllegalArgumentException("vacationDayDto cannot be null");
        if (vacationDayDto.getId() == 0) throw new IllegalArgumentException("id cannot be 0");
       Optional<VacationDay> optional =  vacationDayRepo.findById(vacationDayDto.getId());
       if (optional.isPresent())
           return modelMapper.map(vacationDayRepo.save(modelMapper.map(vacationDayDto, VacationDay.class)), VacationDayDto.class);
        return null;
    }

    @Override
    public void deleteById(int id) {
        if (id == 0) throw new IllegalArgumentException("id must exist");
       Optional<VacationDay> optional =  vacationDayRepo.findById(id);
       if (optional.isPresent())
           vacationDayRepo.deleteById(id);

    }

    @Override
    public VacationDayDto findByVacationDate(LocalDate vacationDate) {
        VacationDay vacationDay = vacationDayRepo.findByVacationDate(vacationDate);
        VacationDayDto dto = modelMapper.map(vacationDay, VacationDayDto.class);
        return dto;
    }

    @Override
    public List<VacationDayDto> findByApproved(boolean approved) {
        List<VacationDay> vacationDays = vacationDayRepo.findByApproved(approved);
        List<VacationDayDto> dtos = vacationDays.stream().map(vacationDay -> modelMapper.map(vacationDay, VacationDayDto.class)).collect(Collectors.toList());
        return dtos;
    }
}
