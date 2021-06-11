package se.lexicon.semester_app.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.semester_app.entity.User;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
}
