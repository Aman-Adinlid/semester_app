package se.lexicon.semester_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.semester_app.dto.PersonDto;
import se.lexicon.semester_app.exception.RecordNotFoundException;
import se.lexicon.semester_app.service.PersonService;

import java.util.List;

@RestController
@RequestMapping("api/v1/person")
public class PersonController {

    PersonService personService;

    @Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<PersonDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(personService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> findById(@PathVariable("id") Integer id) throws RecordNotFoundException {
        if (id == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.status(HttpStatus.OK).body(personService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PersonDto> create(@RequestBody PersonDto personDto) {
        if (personDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.create(personDto));
    }

    @PutMapping
    public ResponseEntity<PersonDto> update(@RequestBody PersonDto personDto) throws RecordNotFoundException {
        if (personDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(personService.update(personDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws RecordNotFoundException {
        personService.delete(id);
        return ResponseEntity.ok().build();
    }

}
