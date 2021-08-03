package se.lexicon.semester_app.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.semester_app.entity.User;
import se.lexicon.semester_app.entity.UserType;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE User a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);
  List<User> findUserByUserType(UserType userType);

    List<User> findAll();

    List<User> findAppUserByEnabled(Boolean enable);

}
