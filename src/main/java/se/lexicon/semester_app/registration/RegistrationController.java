package se.lexicon.semester_app.registration;


import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Validated
public class RegistrationController {

    private final RegistrationService registrationService;


    @PostMapping("/register")
    public String register (@Valid @RequestBody RegistrationRequest request) {
        request.setPassword("1234567a");
        return registrationService.register(request);
    }
}
