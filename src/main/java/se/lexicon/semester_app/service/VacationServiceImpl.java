package se.lexicon.semester_app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.semester_app.dto.VacationDayDto;
import se.lexicon.semester_app.entity.VacationDay;
import se.lexicon.semester_app.exception.ArgumentException;
import se.lexicon.semester_app.exception.RecordNotFoundException;
import se.lexicon.semester_app.repository.VacationDayRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VacationServiceImpl implements VacationDayService {
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
        return modelMapper.map(vacationDayRepository.findById(id).orElseThrow(() ->
                new RecordNotFoundException("VacationDayDto not found")), VacationDayDto.class);
    }

    @Override
    public VacationDayDto findByLocalDate(LocalDate vacationDate) {
        return null;
    }

    @Override
    public List<VacationDayDto> findAll() {
        List<VacationDay> vacationDayList = new ArrayList<>();
        vacationDayRepository.findAll().iterator().forEachRemaining(vacationDayList::add);
        List<VacationDayDto> vacationDayDtoList = vacationDayList.stream().map(vacationDay ->
                modelMapper.map(vacationDay, VacationDayDto.class)).collect(Collectors.toList());
        return vacationDayDtoList;
    }

    @Transactional
    @Override
    public VacationDayDto create(VacationDayDto vacationDayDto) {
        return modelMapper.map(vacationDayRepository.save(modelMapper.map(vacationDayDto, VacationDay.class)), VacationDayDto.class);

    }

    @Transactional
    @Override
    public VacationDayDto update(VacationDayDto vacationDayDto) throws RecordNotFoundException {
        if (vacationDayDto == null) throw new ArgumentException("VacationDayDto object should not be null");
        if (vacationDayDto.getId() < 1) throw new IllegalArgumentException("VacationDayId should not be null");
        Optional<VacationDay> vacationDayOptional = vacationDayRepository.findById(vacationDayDto.getId());
        if (vacationDayOptional.isPresent()) {
            return modelMapper.map(vacationDayRepository.save(modelMapper.map(vacationDayDto, VacationDay.class)), VacationDayDto.class);
        } else {
            throw new RecordNotFoundException("VacationDayDto not found");
        }
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
        if (id < 1) throw new ArgumentException("Is is not valid");
        vacationDayRepository.delete(modelMapper.map(vacationDayRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Id ")), VacationDay.class));
    }
}
