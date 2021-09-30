package se.lexicon.semester_app.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class EmployeeDto {
    private String id;
    private CompanyDto company;
    private String jobTitle;
    private int savedVacation;
    private int yearlyVacationDays;
    private LocalDate dateOfEmployment;
    private UserDto user;

    public EmployeeDto(CompanyDto companyDto, String jobTitle, int savedVacation, int yearlyVacationDays,
                       LocalDate dateOfEmployment, UserDto userDto) {
        this.company = companyDto;
        this.jobTitle = jobTitle;
        this.savedVacation = savedVacation;
        this.yearlyVacationDays = yearlyVacationDays;
        this.dateOfEmployment = dateOfEmployment;
        this.user = userDto;
    }
}
