package se.lexicon.semester_app.service;

import se.lexicon.semester_app.dto.PersonDto;

import java.util.List;

public interface PersonService {
    PersonDto findById(int id);

    List<PersonDto> findAll();

    PersonDto create(PersonDto personDto);

    PersonDto update(PersonDto personDto);

    void deleteById(int id);

    List<PersonDto> findByFirstName(String firstName);

    List<PersonDto> findByLastName(String lastName);
}
