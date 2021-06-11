package se.lexicon.semester_app.dto;

import lombok.Data;
import se.lexicon.semester_app.entity.UserType;

import javax.persistence.Column;
import java.util.UUID;

@Data
public class UserDto {
    private UUID uuid;
    private String FirstName;
    private String LastName;
    private String email;
    private String mobile;
    private UserType type;

}
