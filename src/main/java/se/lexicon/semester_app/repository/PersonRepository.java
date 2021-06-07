package se.lexicon.semester_app.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.semester_app.entity.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {
}
