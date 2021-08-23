package se.lexicon.semester_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.semester_app.dto.CompanyDto;
import se.lexicon.semester_app.exception.RecordNotFoundException;
import se.lexicon.semester_app.service.CompanyService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/company")
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
    public ResponseEntity<CompanyDto> findById(@PathVariable("id") Integer id) throws RecordNotFoundException {
        if (id == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.status(HttpStatus.OK).body(companyService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CompanyDto> create(@RequestBody @Valid CompanyDto companyDto) throws RecordNotFoundException {
        if (companyDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(companyService.create(companyDto));

    }

    @PutMapping
    public ResponseEntity<CompanyDto> update(@RequestBody @Valid CompanyDto companyDto) throws RecordNotFoundException {
        if (companyDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(companyService.update(companyDto));
    }

    @GetMapping("/name")
    public ResponseEntity<List<CompanyDto>> findCompanyByName(@RequestParam(value = "name") String name) {
        return ResponseEntity.status(HttpStatus.OK).body(companyService.findCompanyByName(name));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws RecordNotFoundException {
        companyService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/public")
    public ResponseEntity<CompanyDto> findCompanyByWorkspace(
            @RequestParam(value = "workspace") String workspace) throws RecordNotFoundException  {
        return ResponseEntity.status(HttpStatus.OK).body(companyService.findCompanyByWorkspace(workspace));
    }
}
