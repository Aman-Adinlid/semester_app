package se.lexicon.semester_app.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeDto {
    private String id;
    private CompanyDto company;
    private int savedVacation;
    private int yearlyVacationDays;
    private LocalDate dateOfEmployment;
    private UserDto user;
}
