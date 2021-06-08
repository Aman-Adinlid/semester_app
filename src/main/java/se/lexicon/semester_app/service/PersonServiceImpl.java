package se.lexicon.semester_app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.semester_app.dto.PersonDto;
import se.lexicon.semester_app.exception.RecordNotFoundException;
import se.lexicon.semester_app.repository.PersonRepository;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    PersonRepository personRepository;
    ModelMapper modelMapper;

    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PersonDto findById(int id) throws RecordNotFoundException {
        return null;
    }

    @Override
    public PersonDto findByFirstName(String firstName) {
        return null;
    }

    @Override
    public PersonDto findByLastName(String lastName) {
        return null;
    }

    @Override
    public List<PersonDto> findAll() {
        return null;
    }

    @Transactional
    @Override
    public PersonDto create(PersonDto personDto) {
        return null;
    }

    @Transactional
    @Override
    public PersonDto update(PersonDto personDto) throws RecordNotFoundException {
        return null;
    }

    @Override
    public void delete(int id) throws RecordNotFoundException {

    }
}
