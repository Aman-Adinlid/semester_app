package se.lexicon.semester_app.dto;

import lombok.Data;
import se.lexicon.semester_app.entity.UserType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserDto {
    private int id;
    @NotNull
    @Size(min = 2, max = 30)
    private String firstName;
    @NotNull
    @Size(min = 2, max = 30)
    private String lastName;
    private String mobile;
    private String email;
    private UserType userType;
}
