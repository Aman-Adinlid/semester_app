package se.lexicon.semester_app.jwtAuthorization.jwtAuthentication;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class JwtAuthenticationRequest {

    private final String username;
    private final String password;
}