package se.lexicon.semester_app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.semester_app.dto.UserDto;
import se.lexicon.semester_app.entity.User;
import se.lexicon.semester_app.exception.ArgumentException;
import se.lexicon.semester_app.exception.RecordNotFoundException;
import se.lexicon.semester_app.repo.UserRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    UserRepo userRepo;
    ModelMapper modelMapper;

    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto findById(int id) {
        if (id == 0) throw new IllegalArgumentException("id is not zero");
        Optional<User> optional = userRepo.findById(id);
        if (optional.isPresent()) {
            UserDto dto = modelMapper.map(optional, UserDto.class);
            return dto;
        }
        return null;
    }

    @Override
    public List<UserDto> findAll() {
        List<User> users = new ArrayList<>();
        userRepo.findAll().iterator().forEachRemaining(users::add);
        List<UserDto> userDtos = users.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
        return userDtos;
    }



    @Override
    public UserDto create(UserDto userDto) {
        return modelMapper.map(userRepo.save(modelMapper.map(userDto, User.class)),UserDto.class);

    }


    @Override
    public void deleteById(int id) {
        if (id == 0) throw new IllegalArgumentException("id cannot be zero");
        Optional<User> optional = userRepo.findById(id);
        if (optional.isPresent())
            userRepo.deleteById(id);
    }

    @Override
    public UserDto findByEmail(String email) {
        return null;
    }

    @Override
    public UserDto update(UserDto userDto) throws RecordNotFoundException {
        if (userDto == null) throw new ArgumentException("The dto object should not be null");
        if (userDto.getId() < 1) throw new ArgumentException("The UserId is not null");

        Optional<User> bookOptional = userRepo.findById(userDto.getId());
        if (bookOptional.isPresent()) {
            return modelMapper.map(userRepo.save(modelMapper.map(userDto, User.class)), UserDto.class);
        } else {
            throw new RecordNotFoundException("UserDto not found");
        }
    }
}
