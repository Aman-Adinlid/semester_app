package se.lexicon.semester_app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.semester_app.dto.PersonDto;
import se.lexicon.semester_app.entity.Person;
import se.lexicon.semester_app.exception.ArgumentException;
import se.lexicon.semester_app.exception.RecordNotFoundException;
import se.lexicon.semester_app.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return modelMapper.map(personRepository.findById(id).orElseThrow(() ->
                new RecordNotFoundException("PersonDto not found")), PersonDto.class);
    }

    @Override
    public List<PersonDto> findByFirstName(String firstName) {
        List<Person> personList = personRepository.findByFirstNameIgnoreCase(firstName);
        List<PersonDto> personDtoList = personList.stream().map(person -> modelMapper.map(person, PersonDto.class)).collect(Collectors.toList());
        return personDtoList;

    }

    @Override
    public List<PersonDto> findByLastName(String lastName) {
        List<Person> personList = personRepository.findByLastNameIgnoreCase(lastName);
        List<PersonDto> personDtoList = personList.stream().map(person -> modelMapper.map(person, PersonDto.class)).collect(Collectors.toList());
        return personDtoList;

    }

    @Override
    public List<PersonDto> findAll() {
        List<Person> personList = new ArrayList<>();
        personRepository.findAll().iterator().forEachRemaining(personList::add);
        List<PersonDto> personDtoList = personList.stream().map(person -> modelMapper.map(person, PersonDto.class)).
                collect(Collectors.toList());
        return personDtoList;
    }

    @Transactional
    @Override
    public PersonDto create(PersonDto personDto) {
        return modelMapper.map(personRepository.save(modelMapper.map(personDto, Person.class)), PersonDto.class);
    }

    @Transactional
    @Override
    public PersonDto update(PersonDto personDto) throws RecordNotFoundException {
        if (personDto == null) throw new ArgumentException("PersonDto object should not be null");
        if (personDto.getId() < 1) throw new IllegalArgumentException("PersonId should not be null");
        Optional<Person> personOptional = personRepository.findById(personDto.getId());
        if (personOptional.isPresent()) {
            return modelMapper.map(personRepository.save(modelMapper.map(personDto, Person.class)), PersonDto.class);
        } else {
            throw new RecordNotFoundException("PersonDto not found");
        }
    }

    @Override
    public void delete(int id) throws RecordNotFoundException {
        if (id ==0) throw new ArgumentException("Id is not valid");
        personRepository.delete(modelMapper.map(personRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Id ")), Person.class));
    }
}
