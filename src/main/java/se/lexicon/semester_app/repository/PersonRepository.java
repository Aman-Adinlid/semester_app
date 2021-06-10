package se.lexicon.semester_app.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.semester_app.entity.Person;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Integer> {

    List<Person> findByFirstNameIgnoreCase(String firstName);

    List<Person> findByLastNameIgnoreCase(String lastName);

}
