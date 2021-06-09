package se.lexicon.semester_app.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.semester_app.entity.Company;

public interface CompanyRepository extends CrudRepository<Company, Integer> {
    Company findByNameIgnoreCase(String name); //not sure, should it be a list??
}
