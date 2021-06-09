package se.lexicon.semester_app.service;

import se.lexicon.semester_app.dto.UserDto;

import java.util.List;

public interface UserService {
    // todo:

    // int login (String user , String password)

    UserDto findById(int id);

    List<UserDto> findAll();

    UserDto create(UserDto userDto);

    UserDto update(UserDto userDto);

    void deleteById(int id);

    UserDto findByEmail(String email);

}
