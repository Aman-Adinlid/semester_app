package se.lexicon.semester_app.jwtAuthorization.jwtResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import se.lexicon.semester_app.dto.VacationDayDto;
import se.lexicon.semester_app.entity.UserType;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtEmployeeData {

    private String employeeId;
    private String jobTitle;
    private int savedVacation;
    private int yearlyVacationDays;
    private LocalDate dateOfEmployment;
    private List<VacationDayDto> vacationDays;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private UserType userType;
    private boolean enabled;
}
