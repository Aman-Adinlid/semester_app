package se.lexicon.semester_app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.semester_app.dto.UserDto;
import se.lexicon.semester_app.entity.User;
import se.lexicon.semester_app.repo.UserRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
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
        if (id == 0) throw new IllegalArgumentException("id cannot be 0");
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
        if (userDto == null) throw new IllegalArgumentException("userDto cannot be null");
        if (userDto.getId() != 0) throw new IllegalArgumentException("id cannot exist to create");
        User userEntity = modelMapper.map(userDto, User.class);
        User savedEntity = userRepo.save(userEntity);
        UserDto converted = modelMapper.map(savedEntity, UserDto.class);
        return converted;
    }

    @Override
    public UserDto update(UserDto userDto) {
        if (userDto == null) throw new IllegalArgumentException("userDto cannot be null");
        if (userDto.getId() == 0) throw new IllegalArgumentException("id cannot be 0");
        Optional<User> optional = userRepo.findById(userDto.getId());
        if (optional.isPresent())
            return modelMapper.map(userRepo.save(modelMapper.map(userDto, User.class)), UserDto.class);
        return null;
    }

    @Override
    public void deleteById(int id) {
        if (id == 0) throw new IllegalArgumentException("id cannot be 0");
        Optional<User> optional = userRepo.findById(id);
        if (optional.isPresent())
            userRepo.deleteById(id);

    }

    @Override
    public UserDto findByEmail(String email) {
        User user = userRepo.findByEmail(email);
        UserDto dto = modelMapper.map(user, UserDto.class);
        return dto;
    }
}
