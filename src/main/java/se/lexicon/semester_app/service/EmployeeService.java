package se.lexicon.semester_app.service;

import se.lexicon.semester_app.dto.EmployeeDto;
import se.lexicon.semester_app.exception.RecordNotFoundException;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    EmployeeDto findById(UUID id) throws RecordNotFoundException;
    EmployeeDto save(EmployeeDto dto);
    EmployeeDto update(EmployeeDto dto) throws RecordNotFoundException ;
    List<EmployeeDto> findAll();



}
