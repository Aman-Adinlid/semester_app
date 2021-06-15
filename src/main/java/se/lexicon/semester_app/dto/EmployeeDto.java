package se.lexicon.semester_app.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EmployeeDto {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private List<VacationDayDto> vacationDayDto;
    private int savedVacation;
    private int yearlyVacationDays;
    private LocalDate dateOfEmployment;
    private UserDto userDto;
}
