package se.lexicon.semester_app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.semester_app.dto.VacationDayDto;
import se.lexicon.semester_app.entity.VacationDay;
import se.lexicon.semester_app.exception.ArgumentException;
import se.lexicon.semester_app.exception.RecordNotFoundException;
import se.lexicon.semester_app.repo.VacationDayRepo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VacationServiceImpl implements VacationDayService {

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
    public VacationDayDto findById(int id) throws RecordNotFoundException {
        return modelMapper.map(vacationDayRepo.findById(id).orElseThrow(() ->
                new RecordNotFoundException("VacationDayDto not found")), VacationDayDto.class);
    }

    @Override
    public VacationDayDto findByVacationDate(LocalDate vacationDate) {
        return modelMapper.map(vacationDayRepo.findByVacationDate(vacationDate), VacationDayDto.class);
    }

    @Override
    public List<VacationDayDto> findAll() {
        List<VacationDay> vacationDayList = new ArrayList<>();
        vacationDayRepo.findAll().iterator().forEachRemaining(vacationDayList::add);
        List<VacationDayDto> vacationDayDtoList = vacationDayList.stream().map(vacationDay ->
                modelMapper.map(vacationDay, VacationDayDto.class)).collect(Collectors.toList());
        return vacationDayDtoList;
    }
@Transactional
    @Override
    public VacationDayDto create(VacationDayDto vacationDayDto) {
        return modelMapper.map(vacationDayRepo.save(modelMapper.map(vacationDayDto, VacationDay.class)), VacationDayDto.class);

    }
@Transactional
    @Override
    public VacationDayDto update(VacationDayDto vacationDayDto) throws RecordNotFoundException {
        if (vacationDayDto == null) throw new ArgumentException("VacationDayDto object should not be null");
        if (vacationDayDto.getId() < 1) throw new IllegalArgumentException("VacationDayId should not be null");
        Optional<VacationDay> vacationDayOptional = vacationDayRepo.findById(vacationDayDto.getId());
        if (vacationDayOptional.isPresent()) {
            return modelMapper.map(vacationDayRepo.save(modelMapper.map(vacationDayDto, VacationDay.class)), VacationDayDto.class);
        } else {
            throw new RecordNotFoundException("VacationDayDto not found");
        }
    }

    public VacationServiceImpl() {
        super();
    }

    @Override
    public boolean isApproved(VacationDayDto vacationDayDto) {
        return false;
    }

    @Override
    public void delete(int id) throws RecordNotFoundException {
        if (id == 0) throw new ArgumentException("Id is not valid");
        vacationDayRepo.delete(modelMapper.map(vacationDayRepo.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Id ")), VacationDay.class));
    }


    @Override
    public List<VacationDayDto> findByApproved(boolean approved) {
        List<VacationDay> vacationDays = vacationDayRepo.findByApproved(approved);
        List<VacationDayDto> vacationDayDtoList = vacationDays.stream().map(vacationDay -> modelMapper.map(vacationDay, VacationDayDto.class)).collect(Collectors.toList());
        return vacationDayDtoList;
    }
    }
