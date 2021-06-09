package se.lexicon.semester_app.repo;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.semester_app.entity.Person;

import java.util.List;

public interface PersonRepo extends CrudRepository<Person, Integer> {
    List<Person> findByFirstName(String firstName);

    List<Person> findByLastName(String lastName);
}
