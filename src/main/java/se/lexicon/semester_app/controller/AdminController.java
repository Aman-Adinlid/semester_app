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
@RequestMapping("/admin/api/v1")
@AllArgsConstructor
public class AdminController {
    UserRepository appUserRepository;

    @GetMapping()
    public ResponseEntity<List<User>> findAllNormalUSERS () {
        adminValidation();
        List<User> users = appUserRepository.findUserByUserType(UserType.USER);
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id){
        adminValidation();
        Optional<User> user =  appUserRepository.findById(id);
        if(user.isPresent()){
            appUserRepository.delete(user.get());
            return  ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping("/email")
    public ResponseEntity<Optional<User>> findByEmail(@RequestParam(value = "email") String email){
        adminValidation();
        Optional<User> user = appUserRepository.findByEmail(email);
        if(user.isPresent()){
            throw new IllegalStateException("email not exist");
        }
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    public void adminValidation(){
        if(SignIn_Out.SignedInUser(SignIn_Out.user).getUserType().equals(UserType.USER)  ){
             throw new IllegalStateException("You dont have an ADMIN access");
        }
        if(SignIn_Out.SignedInUser(SignIn_Out.user).equals(null)){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user Signed in");
        }
    }

}