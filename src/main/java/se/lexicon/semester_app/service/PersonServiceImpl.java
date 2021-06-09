package se.lexicon.semester_app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import se.lexicon.semester_app.dto.PersonDto;
import se.lexicon.semester_app.entity.Person;
import se.lexicon.semester_app.repo.PersonRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PersonServiceImpl implements PersonService {
    PersonRepo personRepo;
    ModelMapper modelMapper;

    @Autowired
    public void setPersonRepo(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PersonDto findById(int id) {
        if (id == 0) throw new IllegalArgumentException("id cannot be null");
        Optional<Person> optional = personRepo.findById(id);
        if (optional.isPresent()) {
            PersonDto dto = modelMapper.map(optional, PersonDto.class);
            return dto;
        }
        return null;
    }

    @Override
    public List<PersonDto> findAll() {
        List<Person> personList = new ArrayList<>();
        personRepo.findAll().iterator().forEachRemaining(personList::add);
        List<PersonDto> personDtos = personList.stream().map(person -> modelMapper.map(person, PersonDto.class)).collect(Collectors.toList());
        return personDtos;
    }

    @Override
    public PersonDto create(PersonDto personDto) {
        if (personDto == null) throw new IllegalArgumentException("personDto cannot be null");
        if (personDto.getId() != 0) throw new IllegalArgumentException("id cannot exist to create");
        Person personEntity = modelMapper.map(personDto, Person.class);
        Person savedEntity = personRepo.save(personEntity);
        PersonDto converted = modelMapper.map(savedEntity, PersonDto.class);
        return converted;
    }

    @Override
    public PersonDto update(PersonDto personDto) {
        if (personDto == null) throw new IllegalArgumentException("personDto cannot be null");
        if (personDto.getId() == 0) throw new IllegalArgumentException("id cannot be 0");
        Optional<Person> optional = personRepo.findById(personDto.getId());
        if (optional.isPresent())
            return modelMapper.map(personRepo.save(modelMapper.map(personDto, Person.class)), PersonDto.class);
        return null;
    }

    @Override
    public void deleteById(int id) {
        if (id == 0) throw new IllegalArgumentException("id must exist");
        Optional<Person> optional = personRepo.findById(id);
        if (optional.isPresent())
            personRepo.deleteById(id);

    }

    @Override
    public List<PersonDto> findByFirstName(String firstName) {
        List<Person> personList = personRepo.findByFirstName(firstName);
        List<PersonDto> personDtos = personList.stream().map(person -> modelMapper.map(person, PersonDto.class)).collect(Collectors.toList());
        return personDtos;
    }

    @Override
    public List<PersonDto> findByLastName(String lastName) {
        List<Person> personList = personRepo.findByLastName(lastName);
        List<PersonDto> personDtos = personList.stream().map(person -> modelMapper.map(person, PersonDto.class)).collect(Collectors.toList());
        return personDtos;
    }
}
