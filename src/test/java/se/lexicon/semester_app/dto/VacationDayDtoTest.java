package se.lexicon.semester_app.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class VacationDayDtoTest {
    VacationDayDto vacationDayDto;

    @BeforeEach
    public void setUp() {
        vacationDayDto = new VacationDayDto();
        vacationDayDto.setVacationDate(LocalDate.now());
        vacationDayDto.setApproved(vacationDayDto.isApproved());

    }

    @Test
    @DisplayName("Test1")
    public void test1_create_VacationDayDto() {
        Assertions.assertEquals(LocalDate.now(), vacationDayDto.getVacationDate());
        Assertions.assertEquals(vacationDayDto.isApproved(), vacationDayDto.isApproved());
    }

    @Test
    @DisplayName("Test2")
    public void test2_equal() {
        VacationDayDto vacationDayDto = new VacationDayDto();
        vacationDayDto.setVacationDate(LocalDate.now());
        vacationDayDto.setApproved(vacationDayDto.isApproved());
        Assertions.assertTrue(vacationDayDto.equals(vacationDayDto));
    }

    @Test
    @DisplayName("Test3")
    public void test3_hashCode() {
        VacationDayDto vacationDayDto = new VacationDayDto();
        vacationDayDto.setVacationDate(LocalDate.now());
        vacationDayDto.setApproved(vacationDayDto.isApproved());
        Assertions.assertEquals(vacationDayDto.hashCode(), vacationDayDto.hashCode());
    }
}
