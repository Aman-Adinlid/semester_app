package se.lexicon.semester_app.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.semester_app.entity.User;
import se.lexicon.semester_app.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/api/v1")
@AllArgsConstructor
public class AdminController {
    UserRepository appUserRepository;

    @GetMapping()
    public ResponseEntity<List<User>> findAllEnabledUsers () {

        return ResponseEntity.status(HttpStatus.OK).body(appUserRepository.findAppUserByEnabled(true));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id){
        Optional<User> user =  appUserRepository.findById(id);
        if(user.isPresent()){
            appUserRepository.delete(user.get());
            return  ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping("/email")
    public ResponseEntity<Optional<User>> findByEmail(@RequestParam(value = "email") String email){
        Optional<User> user = appUserRepository.findByEmail(email);
        if(user.isPresent()){
            throw new IllegalStateException("email not exist");
        }
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}