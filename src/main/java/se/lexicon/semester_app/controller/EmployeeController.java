package se.lexicon.semester_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.semester_app.dto.EmployeeDto;
import se.lexicon.semester_app.exception.RecordNotFoundException;
import se.lexicon.semester_app.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {

    EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> findById(@PathVariable("id") String id) throws RecordNotFoundException {
        if (id == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        try {
            EmployeeDto dto = employeeService.findById(id);
            return ResponseEntity.ok(dto);
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> create(@RequestBody EmployeeDto employeeDto) throws RecordNotFoundException {
        if (employeeDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.create(employeeDto));
    }

    @PutMapping
    public ResponseEntity<EmployeeDto> update(@RequestBody EmployeeDto employeeDto) throws RecordNotFoundException {
        if (employeeDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.update(employeeDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") String id) {
        employeeService.delete(id);
        return ResponseEntity.ok().build();
    }

}
