package se.lexicon.semester_app.dto;

import lombok.Data;
import se.lexicon.semester_app.entity.VacationDays;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class EmployeeDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private List<VacationDays> vacationDays;
    private int savedVacation;
    private int yearlyVacationDays;
    private LocalDate dateOfEmployment;
    private int userType;
    private String password;
}
