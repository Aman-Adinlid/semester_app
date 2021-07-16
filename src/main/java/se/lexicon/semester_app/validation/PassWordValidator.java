package se.lexicon.semester_app.validation;

import org.springframework.stereotype.Service;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PassWordValidator  {


    public static boolean isValidPassword(String password) {

        String regex = "^(?=.*[a-z])(?=\\S+$).{8,20}$";
        Pattern p = Pattern.compile(regex);
        if (password == null) {
            return false;
        }

        Matcher m = p.matcher(password);
        return m.matches();
    }

}
