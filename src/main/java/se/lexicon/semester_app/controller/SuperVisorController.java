package se.lexicon.semester_app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.semester_app.entity.User;
import se.lexicon.semester_app.entity.UserType;
import se.lexicon.semester_app.repository.UserRepository;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/supervisor/api/v1")
@AllArgsConstructor
public class SuperVisorController {
    UserRepository appUserRepository;

    @PutMapping
    public ResponseEntity<User> update(@RequestBody User appUser){
        supervisorValidation();
        if(appUser == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(appUserRepository.save(appUser));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id){
        supervisorValidation();
        Optional<User> user =  appUserRepository.findById(id);
        if(user.isPresent()){
            appUserRepository.delete(user.get());
            return  ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping("/email")
    public ResponseEntity<Optional<User>> findByEmail(@RequestParam(value = "email") String email){
        supervisorValidation();
        Optional<User> user = appUserRepository.findByEmail(email);
        if(user.isPresent()){
            throw new IllegalStateException("email not exist");
        }
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
    @GetMapping()
    public ResponseEntity<List<User>> findAllUSERS () {
        supervisorValidation();
      List<User> users = appUserRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    public void supervisorValidation(){
        if(SignIn_Out.SignedInUser(SignIn_Out.user).getUserType().equals(UserType.USER)|| SignIn_Out.SignedInUser(SignIn_Out.user).getUserType().equals(UserType.ADMIN) ){
            throw new IllegalStateException("You dont have SUPERVISOR access ");
        }
        if(SignIn_Out.SignedInUser(SignIn_Out.user).equals(null)){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user Signed in");
        }
    }
}
