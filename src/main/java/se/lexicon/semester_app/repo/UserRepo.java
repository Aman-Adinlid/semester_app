package se.lexicon.semester_app.repo;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.semester_app.entity.User;

public interface UserRepo extends CrudRepository<User,Integer> {
}
