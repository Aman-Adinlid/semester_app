package se.lexicon.semester_app.validation;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Pattern;

@Service
public class EmailValidator implements Predicate<String> {

    @Override
    public boolean test(String email) {

        if(email==null ||email.isEmpty()){
            return false;
        }
        String emailRegex = "^(.+)@(\\S+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        if(pattern.matcher(email).matches()){
            return true;
        }
        return false;
    }

}
