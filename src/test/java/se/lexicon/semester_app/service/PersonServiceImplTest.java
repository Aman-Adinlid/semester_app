package se.lexicon.semester_app.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.semester_app.dto.PersonDto;
import se.lexicon.semester_app.exception.RecordNotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PersonServiceImplTest {
    PersonService personService;
    PersonDto personDto;

    @Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @BeforeEach
    public void setUp() {
        personDto = new PersonDto();
        personDto.setFirstName("Test");
        personDto.setLastName("Test");
        personService.create(personDto);
    }

    @Test
    @DisplayName("Test1")
    public void test1_findById() throws RecordNotFoundException {
        PersonDto createdPersonDto = personService.create(personDto);
        personService.findById(createdPersonDto.getId());
        assertEquals(1, personService.findById(1).getId());
    }


    @Test
    @DisplayName("Test2")
    public void test2_findByFirstName() {
        assertEquals("Test", personService.findByFirstName("Test").get(0).getFirstName());
    }


    @Test
    @DisplayName("Test3")
    public void test3_findByLastName() {
        assertEquals("Test", personService.findByLastName("Test").get(0).getLastName());
    }

    @Test
    @DisplayName("Test4")
    public void test4_findAll() {
        List<PersonDto> personDtoList = personService.findAll();
        System.out.println(personDtoList);
        assertEquals("Test", personService.findAll().get(0).getFirstName());
    }

    @Test
    @DisplayName("Test5")
    public void test5_create() {
        assertEquals("Test", personService.create(personDto).getFirstName());
    }

    @Test
    @DisplayName("Test6")
    public void test6_update() throws RecordNotFoundException {
        //  assertEquals(1, personService.update(personDto).getId());
    }

    @Test
    @DisplayName("Test7")
    public void test7_delete() throws RecordNotFoundException {
        PersonDto createdPersonDto = personService.create(personDto);
        personService.delete(createdPersonDto.getId());
        assertEquals(1, personService.findAll().size());
    }
}
