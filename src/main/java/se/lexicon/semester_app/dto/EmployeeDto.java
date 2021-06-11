package se.lexicon.semester_app.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class EmployeeDto {
    private UUID uuid;
    private UserDto user;
    private List<VacationDayDto> vacationDays;
    private int yearlyVacationDays;
    private LocalDate dateOfEmployment;
}
