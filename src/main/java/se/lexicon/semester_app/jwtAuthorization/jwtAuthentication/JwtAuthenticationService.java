package se.lexicon.semester_app.jwtAuthorization.jwtAuthentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import se.lexicon.semester_app.entity.User;
import se.lexicon.semester_app.dto.*;
import se.lexicon.semester_app.service.*;
import se.lexicon.semester_app.exception.RecordNotFoundException;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JwtAuthenticationService {

    private UserService userService;
    private EmployeeService employeeService;
    private CompanyService companyService;
    private VacationDayService vacationDayService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public JwtAuthenticationResponse authenticate(JwtAuthenticationRequest request) throws RecordNotFoundException {

        User user = (User) userService.loadUserByUsername(request.getUsername());
        if (!user.isEnabled()) {
            throw new RecordNotFoundException("User account is not enabled");
        }

        boolean isVerified = verifyPassword(request.getPassword(), user.getPassword());
        if (!isVerified) {
            throw new RecordNotFoundException("Invalid user credentials");
        }

        return buildResponse(user);
    }

    private boolean verifyPassword(String plainPassword, String encryptedPassword) {
        return bCryptPasswordEncoder.matches(plainPassword, encryptedPassword);
    }

    private JwtAuthenticationResponse buildResponse(User user) throws RecordNotFoundException {
        // todo: inject secret programmatically + refactor to util function
        String secret = "semester-app.app.jwtSecret";
        Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());

        EmployeeDto employee = employeeService.findEmployeeByUserId(user.getId());
        CompanyDto company = companyService.findById(employee.getCompany().getId());
        List<VacationDayDto> vacationDays = vacationDayService.findByEmployeeId(employee.getId());

        String accessToken = JWT.create()
            .withSubject(user.getUsername())
            .withExpiresAt(new Date(System.currentTimeMillis() + 3600000)) // 1 hour
            .withClaim("company", company.getId())
            .withClaim("role", user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()))
            .sign(algorithm);

        String refreshToken = JWT.create()
            .withSubject(user.getUsername())
            .withExpiresAt(new Date(System.currentTimeMillis() + 31556952000L)) // 1 year
            .sign(algorithm);

        return new JwtAuthenticationResponse(accessToken, refreshToken, user, employee, company, vacationDays);
    }
}