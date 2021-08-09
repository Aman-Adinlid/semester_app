package se.lexicon.semester_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.semester_app.dto.CompanyDto;
import se.lexicon.semester_app.entity.UserType;
import se.lexicon.semester_app.exception.RecordNotFoundException;
import se.lexicon.semester_app.service.CompanyService;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/company")
@CrossOrigin("*")
public class CompanyController {

    CompanyService companyService;

    @Autowired
    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<CompanyDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(companyService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> findById(@PathVariable("id") String id) throws RecordNotFoundException {
        if (id == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.status(HttpStatus.OK).body(companyService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CompanyDto> create(@RequestBody CompanyDto companyDto) throws RecordNotFoundException {
        if (companyDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(companyService.create(companyDto));

    }

    @PutMapping
    public ResponseEntity<CompanyDto> update(@RequestBody CompanyDto companyDto) throws RecordNotFoundException {
        if (companyDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(companyService.update(companyDto));
    }


    @GetMapping("/name")
    public ResponseEntity<List<CompanyDto>> findCompanyByName(@RequestParam(value = "name") String name) {
        return ResponseEntity.status(HttpStatus.OK).body(companyService.findCompanyByName(name));
    }
    @PutMapping("/pause/{id}")
    public ResponseEntity<CompanyDto> updatePause(@PathVariable("id") String id) throws RecordNotFoundException {
        checkForSignedInUser();
        CompanyDto companyDto = companyService.findById(id);
        if(companyDto.isPause() ==false){
            companyDto.setPause(true);
            return ResponseEntity.status(HttpStatus.OK).body(companyService.update(companyDto));
        }
        companyDto.setPause(false);
        return ResponseEntity.status(HttpStatus.OK).body(companyService.update(companyDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") String id) throws RecordNotFoundException {
        checkForSignedInUser();
        companyService.delete(id);
        return ResponseEntity.ok().build();
    }
    private void checkForSignedInUser() {
        if(SignIn_Out.SignedInUser(SignIn_Out.user)==null){
            throw new IllegalStateException("You need to sign in as a superVisor");
        }
        if(SignIn_Out.SignedInUser(SignIn_Out.user).getUserType().equals(UserType.ADMIN) || SignIn_Out.SignedInUser(SignIn_Out.user).getUserType().equals(UserType.USER)) {
            throw new IllegalStateException("You do not have supervisor access");
        }
    }
}
