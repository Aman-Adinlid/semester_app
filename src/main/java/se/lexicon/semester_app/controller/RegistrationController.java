package se.lexicon.semester_app.controller;


import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.lexicon.semester_app.service.RegistrationRequest;
import se.lexicon.semester_app.service.RegistrationService;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Validated
@CrossOrigin("*")
public class RegistrationController {

    private final RegistrationService registrationService;


    @PostMapping("/register")
    public String register (@Valid @RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }
}
