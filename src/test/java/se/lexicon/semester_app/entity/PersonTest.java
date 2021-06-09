package se.lexicon.semester_app.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PersonTest {
    Person person;

    @BeforeEach
    public void setUp() {
        person = new Person();
        person.setFirstName("Test");
        person.setLastName("Test1");

    }

    @Test
    @DisplayName("Test1")
    public void test1_create_Person() {
        Assertions.assertEquals("Test", person.getFirstName());
        Assertions.assertEquals("Test1", person.getLastName());
    }

    @Test
    @DisplayName("Test2")
    public void test2_equal() {
        Person person = new Person();
        person.setFirstName("Test");
        person.setLastName("Test1");
        Assertions.assertTrue(person.equals(person));
    }

    @Test
    @DisplayName("Test3")
    public void test3_hashCode() {
        Person person = new Person();
        person.setFirstName("Test");
        person.setLastName("Test1");
        Assertions.assertEquals(person.hashCode(), person.hashCode());
    }
}

