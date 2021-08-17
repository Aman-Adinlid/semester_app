package se.lexicon.semester_app.service;

import se.lexicon.semester_app.dto.UserDto;
import se.lexicon.semester_app.entity.User;
import se.lexicon.semester_app.exception.RecordNotFoundException;

import java.util.List;

public interface UserService  {

    User findById(int id) throws RecordNotFoundException;

    UserDto findByEmail(String email);

    List<UserDto> findAll();

    UserDto create(UserDto userDto) throws RecordNotFoundException;

    UserDto update(UserDto userDto) throws RecordNotFoundException;

    void delete(int id) throws RecordNotFoundException;

    String signUp(User user);

    int enableUser(String email);
}
