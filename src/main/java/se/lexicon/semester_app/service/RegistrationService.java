package se.lexicon.semester_app.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import se.lexicon.semester_app.entity.User;
import se.lexicon.semester_app.entity.UserType;
import se.lexicon.semester_app.validation.EmailValidator;
import se.lexicon.semester_app.validation.PassWordValidator;


@Service
@AllArgsConstructor
public class RegistrationService {

    private final EmailValidator emailValidator;
    private final UserService userService;
    private final PassWordValidator passWordValidator;

    public String register(RegistrationRequest request){
        boolean isValidEmail = emailValidator.test(request.getEmail());

        if(!isValidEmail){
            throw  new IllegalStateException("Invalid email");
        }

//         else if (emailValidator.test(request.getEmail()) == true && passWordValidator.isValidPassword(request.getPassword())==true)
                  else if (emailValidator.test(request.getEmail()) )
        {

            String token =   userService.signUp(new User(
                    request.getFirstName(),
                    request.getLastName(),
                    request.getMobile(),
                    request.getEmail(),
                    UserType.USER,
                    request.getPassword()));

            return token;
          }


throw new IllegalStateException("Invalid password, length must be at least 8-20 characters, and it must contains one lowercase character [a-z] ");

    }




}