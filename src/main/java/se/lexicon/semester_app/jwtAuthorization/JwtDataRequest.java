package se.lexicon.semester_app.jwtAuthorization;

import lombok.Data;

@Data
public class JwtDataRequest {

    private int companyId;
    private String accessToken;
}
