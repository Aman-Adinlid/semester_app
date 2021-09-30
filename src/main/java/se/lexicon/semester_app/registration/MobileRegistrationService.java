package se.lexicon.semester_app.registration;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.semester_app.dto.CompanyDto;
import se.lexicon.semester_app.dto.EmployeeDto;
import se.lexicon.semester_app.dto.UserDto;
import se.lexicon.semester_app.entity.User;
import se.lexicon.semester_app.exception.RecordNotFoundException;
import se.lexicon.semester_app.registration.token.ConfirmationToken;
import se.lexicon.semester_app.registration.token.ConfirmationTokenService;
import se.lexicon.semester_app.service.CompanyService;
import se.lexicon.semester_app.service.EmployeeService;
import se.lexicon.semester_app.service.UserService;
import se.lexicon.semester_app.validation.EmailValidator;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class MobileRegistrationService {

    private final EmailValidator emailValidator;
    private final UserService userService;
    private final EmployeeService employeeService;
    private final CompanyService companyService;
    private final ConfirmationTokenService confirmationTokenService;

    public String[] register(MobileRegistrationRequest request) throws RecordNotFoundException {
        boolean isValidEmail = emailValidator.test(request.getEmail());

        if (!isValidEmail) {
            throw new IllegalStateException("Invalid email");
        } else {
            // userService.signup() returns [generatedPassword, token, userId]
            String[] userResponse = userService.mobileSignUp(new User(
                    request.getFirstName(),
                    request.getLastName(),
                    request.getMobile(),
                    request.getEmail(),
                    request.getUserType()));

            UserDto userDto = userService.findById(Integer.parseInt(userResponse[2]));
            CompanyDto companyDto = companyService.findById(request.getCompanyId());
            employeeService.create(new EmployeeDto(
                    companyDto,
                    request.getJobTitle(),
                    request.getSavedVacation(),
                    request.getYearlyVacationDays(),
                    request.getDateOfEmployment(),
                    userDto));

            return new String[]{userResponse[0], userResponse[1]};
        }
    }


    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                                     new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }


        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        userService.enableUser(
                confirmationToken.getUser().getEmail());
        return "confirmed";
    }


}