package se.lexicon.semester_app.dto;

import lombok.Data;
import se.lexicon.semester_app.entity.UserType;

@Data
public class UserDto {
    private int id;
    private String email;
    private UserType userType;
}
