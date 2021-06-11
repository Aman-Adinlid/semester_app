package se.lexicon.semester_app.repo;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.semester_app.entity.Company;

public interface CompanyRepo extends CrudRepository<Company,Integer> {
}
