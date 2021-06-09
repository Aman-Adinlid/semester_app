package se.lexicon.semester_app.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class VacationDayTest {
    VacationDay vacationDay;

    @BeforeEach
    public void setUp() {
        vacationDay = new VacationDay();
        vacationDay.setVacationDate(LocalDate.now());
        vacationDay.setApproved(vacationDay.isApproved());
        vacationDay.setVacationTyp(VacationType.VACATION);
    }

    @Test
    @DisplayName("Test1")
    public void test1_create_VacationDay() {
        Assertions.assertEquals(LocalDate.now(), vacationDay.getVacationDate());
        Assertions.assertEquals(VacationType.VACATION, vacationDay.getVacationTyp());
    }

    @Test
    @DisplayName("Test2")
    public void test2_equal() {
        VacationDay vacationDay = new VacationDay();
        vacationDay.setVacationDate(LocalDate.now());
        vacationDay.setApproved(vacationDay.isApproved());
        vacationDay.setVacationTyp(VacationType.VACATION);
        Assertions.assertTrue(vacationDay.equals(vacationDay));
    }

    @Test
    @DisplayName("Test3")
    public void test3_hashCode() {
        VacationDay vacationDay = new VacationDay();
        vacationDay.setVacationDate(LocalDate.now());
        vacationDay.setApproved(vacationDay.isApproved());
        vacationDay.setVacationTyp(VacationType.VACATION);
        Assertions.assertEquals(vacationDay.hashCode(), vacationDay.hashCode());

    }
}
