package se.lexicon.semester_app.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PerssonDtoTest {
    PersonDto personDto;

    @BeforeEach
    public void setUp() {
        personDto = new PersonDto();
        personDto.setFirstName("Test");
        personDto.setLastName("Test");
    }

    @Test
    @DisplayName("Test1")
    public void test1_create_PersonDto() {
        Assertions.assertEquals("Test", personDto.getFirstName());
        Assertions.assertEquals("Test", personDto.getLastName());
    }

    @Test
    @DisplayName("Test2")
    public void test2_equal() {
        PersonDto personDto = new PersonDto();
        personDto.setFirstName("Test");
        personDto.setLastName("Test");
        Assertions.assertTrue(personDto.equals(personDto));
    }

    @Test
    @DisplayName("Test3")
    public void test3_hashCode() {
        PersonDto personDto = new PersonDto();
        personDto.setFirstName("Test");
        personDto.setLastName("Test");
        Assertions.assertEquals(personDto.hashCode(), personDto.hashCode());
    }

}
