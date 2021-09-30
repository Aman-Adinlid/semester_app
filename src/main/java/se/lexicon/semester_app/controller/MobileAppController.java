package se.lexicon.semester_app.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import se.lexicon.semester_app.entity.User;
import se.lexicon.semester_app.dto.CompanyDto;
import se.lexicon.semester_app.dto.EmployeeDto;
import se.lexicon.semester_app.dto.UserDto;
import se.lexicon.semester_app.dto.VacationDayDto;
import se.lexicon.semester_app.service.CompanyService;
import se.lexicon.semester_app.service.EmployeeService;
import se.lexicon.semester_app.service.UserService;
import se.lexicon.semester_app.service.VacationDayService;
import se.lexicon.semester_app.exception.RecordNotFoundException;
import se.lexicon.semester_app.jwtAuthorization.filters.JwtResponseFilter;
import se.lexicon.semester_app.jwtAuthorization.jwtAuthentication.*;
import se.lexicon.semester_app.jwtAuthorization.jwtResponse.*;
import se.lexicon.semester_app.registration.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/api/v1/mobile")
@RequiredArgsConstructor
public class MobileAppController {

    private final JwtAuthenticationService jwtAuthenticationService;
    private final JwtResponseFilter responseFilter;
    private final MobileRegistrationService mobileRegistrationService;
    private final UserService userService;
    private final EmployeeService employeeService;
    private final CompanyService companyService;
    private final VacationDayService vacationDayService;

    @PostMapping("/authenticate")
    public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@Valid @RequestBody JwtAuthenticationRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(jwtAuthenticationService.authenticate(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    // Todo: Add password reset function
    @PostMapping("/resetPassword")
    public String resetPassword(@Valid @RequestBody JwtAuthenticationRequest request) {
        return "Password changed";
    }

    @GetMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refreshToken = authorizationHeader.substring("Bearer ".length());

                /* todo: inject secret programmatically + refactor to util function */
                String secret = "semester-app.app.jwtSecret";
                Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());

                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);

                String username = decodedJWT.getSubject();
                User user = (User) userService.loadUserByUsername(username);
                EmployeeDto employee = employeeService.findEmployeeByUserId(user.getId());
                CompanyDto company = companyService.findById(employee.getCompany().getId());

                String accessToken = JWT.create()
                    .withSubject(user.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 3600000))
                    .withClaim("company", company.getId())
                    .withClaim("role", user.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                    .sign(algorithm);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("accessToken", accessToken);
                tokens.put("refreshToken", refreshToken);

                response.setContentType(MediaType.TEXT_PLAIN_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);

            } catch (Exception e) {
                System.out.println("Error resetting token: " + e.getMessage());
                response.setHeader("Error", e.getMessage());
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);

                Map<String, String> error = new HashMap<>();
                error.put("Error message", e.getMessage());
                response.setContentType(MediaType.TEXT_PLAIN_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }

    @GetMapping("/getEmployeeList")
    public ResponseEntity<List<JwtEmployeeVacation>> getEmployeeListByCompanyId(@RequestParam(value = "companyId") int companyId) {
        if (companyId == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        try {
            List<JwtEmployeeVacation> employeeVacation = responseFilter.filterEmployeeVacationDates(companyId);
            return ResponseEntity.ok(employeeVacation);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/registerVacationDays")
    public ResponseEntity<List<VacationDayDto>> create(@RequestBody List<VacationDayDto> request) {
        if (request.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(vacationDayService.createMultiple(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Admin functions
    @GetMapping("/getAllEmployeeData")
    public ResponseEntity<List<JwtEmployeeData>> getAllEmployeeDataByCompanyId(@RequestParam(value = "companyId") int companyId) {
        if (companyId == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        try {
            List<JwtEmployeeData> employeeData = responseFilter.filterAllEmployeeData(companyId);
            return ResponseEntity.ok(employeeData);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/addNewEmployee")
    public ResponseEntity<String[]> register(@Valid @RequestBody MobileRegistrationRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(mobileRegistrationService.register(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ValidateUserEmail is checked when a new employee form email field is filled in and when form is submitted
    @GetMapping("/checkIfEmailExists")
    public ResponseEntity<String> findByEmail(@RequestParam(value = "email") String email) {
        try {
            UserDto userDto = userService.findByEmail(email);
            return ResponseEntity.status(HttpStatus.OK).body("Email exists");
        } catch (RecordNotFoundException e) {
            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
        }
    }
}
