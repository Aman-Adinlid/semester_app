package se.lexicon.semester_app.service;

import se.lexicon.semester_app.dto.PersonDto;
import se.lexicon.semester_app.exception.RecordNotFoundException;

import java.util.List;

public interface PersonService {

    PersonDto findById(int id) throws RecordNotFoundException;

    List<PersonDto> findByFirstName(String firstName);

    List<PersonDto> findByLastName(String lastName);

    // maybe a method for firstName and lastName

    List<PersonDto> findAll();

    PersonDto create(PersonDto personDto);

    PersonDto update(PersonDto personDto) throws RecordNotFoundException;

    void delete(int id) throws RecordNotFoundException;
}
