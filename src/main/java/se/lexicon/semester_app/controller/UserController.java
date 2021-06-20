package se.lexicon.semester_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.semester_app.dto.UserDto;
import se.lexicon.semester_app.exception.RecordNotFoundException;
import se.lexicon.semester_app.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable("id") Integer id) throws RecordNotFoundException {
        if (id == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }


    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody @Valid UserDto userDto) throws RecordNotFoundException {
        if (userDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(userDto));
    }


    @PutMapping
    public ResponseEntity<UserDto> update(@RequestBody @Valid UserDto userDto) throws RecordNotFoundException {
        if (userDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(userDto));
    }


    @GetMapping("/email")
    public ResponseEntity<UserDto> findByEmail(@RequestParam(value = "email") String email) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findByEmail(email));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws RecordNotFoundException {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

}
