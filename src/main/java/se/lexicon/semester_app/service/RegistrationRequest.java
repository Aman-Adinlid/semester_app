package se.lexicon.semester_app.service;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationRequest {

    private  String firstName;
    private  String lastName;
    private  String email;
    private  String password;
    private  String mobile;


}