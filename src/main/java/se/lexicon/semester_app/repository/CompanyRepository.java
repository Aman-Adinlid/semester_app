package se.lexicon.semester_app.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.semester_app.entity.Company;
import se.lexicon.semester_app.exception.RecordNotFoundException;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends CrudRepository<Company, Integer> {

    List<Company> findCompanyByNameContainsIgnoreCase(String name);

    Optional<Company> findCompanyByWorkspace(String workspace) throws RecordNotFoundException;

}
