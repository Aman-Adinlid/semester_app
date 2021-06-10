package se.lexicon.semester_app.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.semester_app.entity.Person;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PersonRepositoryTest {
    PersonRepository personRepository;
    Person person;

    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @BeforeEach
    public void setUp() {
        person = new Person();
        person.setFirstName("Test");
        person.setLastName("Test");
        personRepository.save(person);

    }

    @Test
    @DisplayName("Test1")
    public void test1_findByFirstName() {
        List<Person> personList = new ArrayList<>();
        personRepository.findByFirstNameIgnoreCase("Test").iterator().forEachRemaining(personList::add);
        assertEquals(0, personRepository.findByFirstNameIgnoreCase("Test").size());
    }

    @Test
    @DisplayName("Test2")
    public void test2_findByLastName() {
        List<Person> personList = new ArrayList<>();
        personRepository.findByLastNameIgnoreCase("Test").iterator().forEachRemaining(personList::add);
        assertEquals(0, personRepository.findByFirstNameIgnoreCase("Test").size());
    }

    //test findById**


    @Test
    @DisplayName("Test3")
    public void test3_findAll_save() {
        List<Person> personList = new ArrayList<>();
        personRepository.findAll().iterator().forEachRemaining(personList::add);
        assertEquals(0, personList.size());
    }

    @Test
    @DisplayName("Test4")
    public void test4_delete() {
        List<Person> personList = new ArrayList<>();
        personRepository.delete(person);
        List<Person> list = new ArrayList<>();
        personRepository.findAll().iterator().forEachRemaining(list::add);
        assertEquals(personList, list);
    }
}
