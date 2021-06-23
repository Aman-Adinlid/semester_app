package se.lexicon.semester_app.registration;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.semester_app.entity.User;
import se.lexicon.semester_app.entity.UserType;
import se.lexicon.semester_app.registration.token.ConfirmationToken;
import se.lexicon.semester_app.registration.token.ConfirmationTokenService;
import se.lexicon.semester_app.service.UserService;
import se.lexicon.semester_app.validation.EmailValidator;
import se.lexicon.semester_app.validation.PassWordValidator;

import java.time.LocalDateTime;



@Service
@AllArgsConstructor
public class RegistrationService {

    private final EmailValidator emailValidator;
    private final UserService userService;
    private final ConfirmationTokenService confirmationTokenService;
    private final PassWordValidator passWordValidator;

    public String register(RegistrationRequest request){
        boolean isValidEmail = emailValidator.test(request.getEmail());

        if(!isValidEmail){
            throw  new IllegalStateException("Invalid email");
        }
          else if  (emailValidator.test(request.getEmail()) ==true && passWordValidator.isValidPassword(request.getPassword())==true){
            String token =   userService.signUp(new User(
                    request.getFirstName(),
                    request.getLastName(),
                    request.getMobile(),
                    request.getEmail(),
                    UserType.SUPERVISOR,
                    request.getPassword()));
            return token;
          }

         throw new IllegalStateException("Invalid password, length must be at least 8-20 characters, and it must contains one lowercase character [a-z] ");

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