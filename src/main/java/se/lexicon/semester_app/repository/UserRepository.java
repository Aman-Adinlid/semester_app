package se.lexicon.semester_app.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.semester_app.entity.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByEmail(String email);

}
