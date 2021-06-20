package se.lexicon.semester_app.repo;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.semester_app.entity.Company;

import java.util.List;

public interface CompanyRepo extends CrudRepository<Company,Integer> {
    List<Company>findCompanyByNameContainsIgnoreCase(String name);
}
