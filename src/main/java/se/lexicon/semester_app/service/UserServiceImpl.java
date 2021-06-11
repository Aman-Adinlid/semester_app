package se.lexicon.semester_app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.semester_app.dto.UserDto;
import se.lexicon.semester_app.exception.LoginError;
import se.lexicon.semester_app.exception.RecordNotFoundException;
import se.lexicon.semester_app.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
UserRepository userRepository;
ModelMapper modelMapper;
@Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
@Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UUID login(String user, String password) throws LoginError {
        return null;
    }

    @Override
    public UUID changePassword(UUID uuid, String oldPassword, String newPassword) throws LoginError {
        return null;
    }

    @Override
    public UUID createUser(UserDto newUser, String newPassword) throws LoginError {
        return null;
    }

    @Override
    public UserDto findById(UUID uuid) throws RecordNotFoundException {
        return null;
    }

    @Override
    public List<UserDto> findAll() {
        return null;
    }

    @Override
    public UserDto update(UserDto userDto) throws RecordNotFoundException {
        return null;
    }

    @Override
    public void delete(UUID uuid) throws RecordNotFoundException {

    }
}
