package se.lexicon.semester_app.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.semester_app.entity.UserType;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class EmployeeDtoTest {
    EmployeeDto employeeDto;
    List<VacationDayDto> vacationDayDtoList;
    UserDto userDto;

    @BeforeEach
    public void setUp() {
        employeeDto = new EmployeeDto();
        employeeDto.setFirstName("Test");
        employeeDto.setLastName("Test");
        employeeDto.setEmail("Test");
        employeeDto.setMobile("Test");
        employeeDto.setVacationDayDto(vacationDayDtoList);
        employeeDto.setSavedVacation(12);
        employeeDto.setYearlyVacationDays(2021);
        employeeDto.setDateOfEmployment(LocalDate.of(2021, 01, 12));
        employeeDto.setPassword("Test12");

        userDto = new UserDto();
        userDto.setEmail("Test");
        userDto.setType(UserType.USER);
    }

    @Test
    @DisplayName("Test1")
    public void test1_create_EmployeeDto() {
        Assertions.assertEquals("Test", employeeDto.getFirstName());
        Assertions.assertEquals("Test", employeeDto.getLastName());
        Assertions.assertEquals("Test", employeeDto.getEmail());
        Assertions.assertEquals("Test", employeeDto.getMobile());
        Assertions.assertEquals(vacationDayDtoList, employeeDto.getVacationDayDto());
        Assertions.assertEquals(12, employeeDto.getSavedVacation());
        Assertions.assertEquals(2021, employeeDto.getYearlyVacationDays());
        Assertions.assertEquals(LocalDate.of(2021, 01, 12), employeeDto.getDateOfEmployment());
        Assertions.assertEquals("Test12", employeeDto.getPassword());
    }

    @Test
    @DisplayName("Test2")
    public void test2_equal() {
        employeeDto = new EmployeeDto();
        employeeDto.setFirstName("Test");
        employeeDto.setLastName("Test");
        employeeDto.setEmail("Test");
        employeeDto.setMobile("Test");
        employeeDto.setVacationDayDto(vacationDayDtoList);
        employeeDto.setSavedVacation(12);
        employeeDto.setYearlyVacationDays(2021);
        employeeDto.setDateOfEmployment(LocalDate.of(2021, 01, 12));
        employeeDto.setPassword("Test12");
        employeeDto.setUserDto(userDto);
        Assertions.assertTrue(employeeDto.equals(employeeDto));

    }


    @Test
    @DisplayName("Test3")
    public void test3_hashCode() {
        employeeDto = new EmployeeDto();
        employeeDto.setFirstName("Test");
        employeeDto.setLastName("Test");
        employeeDto.setEmail("Test");
        employeeDto.setMobile("Test");
        employeeDto.setVacationDayDto(vacationDayDtoList);
        employeeDto.setSavedVacation(12);
        employeeDto.setYearlyVacationDays(2021);
        employeeDto.setDateOfEmployment(LocalDate.of(2021, 01, 12));
        employeeDto.setPassword("Test12");
        employeeDto.setUserDto(userDto);
        Assertions.assertEquals(employeeDto.hashCode(), employeeDto.hashCode());
    }
}
