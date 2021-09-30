package se.lexicon.semester_app.jwtAuthorization.jwtAuthentication;

import lombok.Data;
import lombok.NoArgsConstructor;
import se.lexicon.semester_app.dto.CompanyDto;
import se.lexicon.semester_app.dto.EmployeeDto;
import se.lexicon.semester_app.dto.VacationDayDto;
import se.lexicon.semester_app.entity.User;
import se.lexicon.semester_app.entity.UserType;

import java.time.LocalDate;
import java.util.List;

/**
 * Returns object to be used for initializing mobile app
 */
@Data
@NoArgsConstructor
public class JwtAuthenticationResponse {

    private String accessToken;
    private String refreshToken;

    private int companyId;
    private String companyName;
    private String companyWorkspace;
    private boolean companyPaused;

    private String employeeId;
    private String jobTitle;
    private LocalDate dateOfEmployment;
    private int yearlyVacationDays;
    private int savedVacation;

    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private UserType userType;
    private boolean isEnabled;

    private List<VacationDayDto> vacationDays;

    public JwtAuthenticationResponse(String accessToken, String refreshToken, User user, EmployeeDto employee,
                                     CompanyDto company, List<VacationDayDto> vacationDays) {

        this.accessToken = accessToken;
        this.refreshToken = refreshToken;

        this.companyId = company.getId();
        this.companyName = company.getName();
        this.companyWorkspace = company.getWorkspace();
        this.companyPaused = company.isPaused();

        this.employeeId = employee.getId();
        this.jobTitle = employee.getJobTitle();
        this.dateOfEmployment = employee.getDateOfEmployment();
        this.yearlyVacationDays = employee.getYearlyVacationDays();
        this.savedVacation = employee.getSavedVacation();

        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.mobile = user.getMobile();
        this.userType = user.getUserType();
        this.isEnabled = user.isEnabled();

        this.vacationDays = vacationDays;
    }
}