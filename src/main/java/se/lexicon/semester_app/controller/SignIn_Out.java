package se.lexicon.semester_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import se.lexicon.semester_app.entity.User;
import se.lexicon.semester_app.entity.UserType;
import se.lexicon.semester_app.repository.UserRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/signin")
@CrossOrigin("*")
public class SignIn_Out {
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository appUserRepository;
    public static User user;

    @Autowired
    public void setAppUserRepository(UserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }
    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody User user){
        Optional<User> user2 = appUserRepository.findByEmail(user.getEmail());
        if(!user2.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        }
       user.setId(user2.get().getId());
       user.setUserType(user2.get().getUserType());
       user.setMobile(user2.get().getMobile());
        if(user.getUserType().equals(UserType.ADMIN) || user.getUserType().equals(UserType.SUPERVISOR)){
            if(!bCryptPasswordEncoder.matches(user.getPassword(),user2.get().getPassword())){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wrong password");
            }
            if(user.getPassword()== null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Password cannot be null");
            }
        }
        if( user.getPassword() != null && !bCryptPasswordEncoder.matches(user.getPassword(), user2.get().getPassword()) ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wrong password");
        }
       if(user.getPassword() == null){
            SignedInUser(user2.get());
            return ResponseEntity.status(HttpStatus.OK).body("Login Successful");
        }
        else
            SignedInUser(user2.get());
        return ResponseEntity.status(HttpStatus.OK).body("Login Successful");
    }

    @GetMapping("/signin")
    public  ResponseEntity<User> myUser(){
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
    @PostMapping("/signout")
    public  ResponseEntity<User> signOut(){
        user = null;
        SignedInUser(null);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/myuser")
    public ResponseEntity<String> MySignedInUser (){
        if(SignedInUser(user)==null){
            return ResponseEntity.status(HttpStatus.OK).body("No account signed in");
        }
        return  ResponseEntity.status(HttpStatus.OK).body(SignedInUser(user).toString());
    }
    @PostMapping("/password")
    public ResponseEntity<String> forgotPassword(@RequestBody User user){
        Optional<User> user2 = appUserRepository.findByEmail(user.getEmail());

        if(!user2.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        }
        user2.get().setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        appUserRepository.save(user2.get());
        return ResponseEntity.status(HttpStatus.OK).body("Password has been changed");
    }
    public static User SignedInUser (User appUser){
        user= appUser;
        return  appUser;
    }
}
