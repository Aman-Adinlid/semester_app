package se.lexicon.semester_app.controller;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import se.lexicon.semester_app.dto.EmployeeDto;
import se.lexicon.semester_app.entity.User;
import se.lexicon.semester_app.entity.UserType;
import se.lexicon.semester_app.exception.RecordNotFoundException;
import se.lexicon.semester_app.repository.UserRepository;
import se.lexicon.semester_app.service.EmployeeServiceImpl;
import java.util.List;
import java.util.Optional;

import static se.lexicon.semester_app.controller.SignIn_Out.SignedInUser;

@RestController
@RequestMapping("/admin/api/v1")
@AllArgsConstructor
@CrossOrigin("*")
public class AdminController {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository appUserRepository;
    public static User user;
    private EmployeeServiceImpl employeeService;

    @Autowired
    public void setAppUserRepository(UserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }
    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping()
    public ResponseEntity<List<User>> findAllNormalUSERS () {
        adminValidation();
        List<User> users = appUserRepository.findUserByUserType(UserType.USER);
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/request")
    public ResponseEntity<List<EmployeeDto>> getVacationRequest(){
       List<EmployeeDto> employeeDtoList =  employeeService.findByRequest("Pending");
       return ResponseEntity.status(HttpStatus.OK).body(employeeDtoList);
    }
    @PostMapping("/request/accept/{id}")
    public ResponseEntity<EmployeeDto> acceptVacationRequest(@PathVariable String id) throws RecordNotFoundException {
        EmployeeDto employeeDto1 = employeeService.findById(id);
        employeeDto1.setRequest("ACCEPT");
        return ResponseEntity.status(HttpStatus.OK).body( employeeService.update(employeeDto1));
    }
    @PostMapping ("/request/decline/{id}")
    public ResponseEntity<EmployeeDto> declineVacationRequest(@PathVariable String id) throws RecordNotFoundException {
        EmployeeDto employeeDto1 = employeeService.findById(id);
        employeeDto1.setRequest("DECLINE");
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.update(employeeDto1));
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
    @PostMapping("/signin")
    public ResponseEntity<String> signInAdmin(@RequestBody User user){
        Optional<User> user2 = appUserRepository.findByEmail(user.getEmail());
        if(!user2.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        }
        user.setId(user2.get().getId());
        user.setUserType(user2.get().getUserType());

        if(user.getUserType().equals(UserType.USER)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("You dont have access");
            
        }

        if(user.getUserType().equals(UserType.ADMIN) || user.getUserType().equals(UserType.SUPERVISOR)){

            if(user.getPassword()== null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Password cannot be null");
            }
        }
        if( !bCryptPasswordEncoder.matches(user.getPassword(), user2.get().getPassword()) ){
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
    public void adminValidation(){
        if(SignedInUser(SignIn_Out.user).getUserType().equals(UserType.USER)  ){
             throw new IllegalStateException("You dont have an ADMIN access");
        }
        if(SignedInUser(SignIn_Out.user).equals(null)){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user Signed in");
        }
    }

}