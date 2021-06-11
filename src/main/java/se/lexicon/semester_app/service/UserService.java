package se.lexicon.semester_app.service;

import se.lexicon.semester_app.dto.UserDto;
import se.lexicon.semester_app.exception.LoginError;
import se.lexicon.semester_app.exception.RecordNotFoundException;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UUID login(String user, String password) throws LoginError;

    UUID changePassword(UUID uuid, String oldPassword, String newPassword) throws LoginError;

    UUID createUser(UserDto newUser, String newPassword) throws LoginError;

    UserDto findById(UUID uuid) throws RecordNotFoundException;

    List<UserDto> findAll();

    UserDto update(UserDto userDto) throws RecordNotFoundException;

    void delete(UUID uuid) throws RecordNotFoundException;
}
