package se.lexicon.semester_app.registration;

import lombok.AllArgsConstructor;
import lombok.Data;
import se.lexicon.semester_app.entity.UserType;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class MobileRegistrationRequest {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String mobile;
    private final UserType userType;

    private final int companyId;
    private final String jobTitle;
    private final int savedVacation;
    private final int yearlyVacationDays;
    private final LocalDate dateOfEmployment;
}
