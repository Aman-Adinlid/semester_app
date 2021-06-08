package se.lexicon.semester_app.service;

import se.lexicon.semester_app.dto.UserDto;
import se.lexicon.semester_app.exception.RecordNotFoundException;

import java.util.List;

public interface UserService {
    UserDto findById(int id) throws RecordNotFoundException;

    UserDto findByEmail(String email);

    List<UserDto> findAll();

    UserDto create(UserDto userDto);

    UserDto update(UserDto userDto) throws RecordNotFoundException;

    int login(String user, String password);

    void delete(int id) throws RecordNotFoundException;

}
