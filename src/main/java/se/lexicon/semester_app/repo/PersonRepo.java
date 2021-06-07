package se.lexicon.semester_app.repo;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.semester_app.entity.Person;

public interface PersonRepo extends CrudRepository<Person,Integer> {
}
