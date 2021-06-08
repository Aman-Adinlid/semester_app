package se.lexicon.semester_app.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.semester_app.entity.Person;

import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Integer> {
    Optional<Person> findByFirstNameIgnoreCase(String firstName);

    Optional<Person> findByLastNameIgnoreCase(String lastName);

}
