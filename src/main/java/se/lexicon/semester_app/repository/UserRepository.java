package se.lexicon.semester_app.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.semester_app.entity.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByEmail(String email);
    
    @Transactional
    @Modifying
    @Query("UPDATE User a " +
               "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);

    List<User> findAppUserByEnabled(Boolean enable);

}
