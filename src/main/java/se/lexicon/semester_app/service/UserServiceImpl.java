package se.lexicon.semester_app.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.semester_app.dto.CompanyDto;
import se.lexicon.semester_app.dto.UserDto;
import se.lexicon.semester_app.entity.Company;
import se.lexicon.semester_app.entity.User;
import se.lexicon.semester_app.exception.ArgumentException;
import se.lexicon.semester_app.exception.RecordNotFoundException;
import se.lexicon.semester_app.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
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
    public UserDto findById(int id) throws RecordNotFoundException {
        return modelMapper.map(userRepository.findById(id).orElseThrow(() ->
                                                                               new RecordNotFoundException("UserDto not found")), UserDto.class);
    }
    @Override
    public UserDto findByEmail(String email) throws RecordNotFoundException {

        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return modelMapper.map(user.get(), UserDto.class);
        } else {
            throw new RecordNotFoundException("Email not Found");

        }
    }

    @Override
    public List<UserDto> findAll() {
        List<User> userList = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(userList::add);
        List<UserDto> userDtoList = userList.stream().map(user -> modelMapper.map(user, UserDto.class)).
                collect(Collectors.toList());
        return userDtoList;
    }
    @Transactional
    @Override
    public UserDto create(UserDto userDto) {
        return modelMapper.map(userRepository.save(modelMapper.map(userDto, User.class)), UserDto.class);
    }
    @Transactional
    @Override
    public UserDto update(UserDto userDto) throws RecordNotFoundException {
        if (userDto == null) throw new ArgumentException("UserDto object should not be null");
        if (userDto.getId() == 0) throw new IllegalArgumentException("UserId should not be null");
        Optional<User> userOptional = userRepository.findById(userDto.getId());
        if (userOptional.isPresent()) {
            return modelMapper.map(userRepository.save(modelMapper.map(userDto, User.class)), UserDto.class);
        } else {
            throw new RecordNotFoundException("UserDto not found");
        }
    }
    @Override
    public void delete(int id) throws RecordNotFoundException {
        if (id == 0) throw new ArgumentException("Id is not valid");
        userRepository.delete(modelMapper.map(userRepository.findById(id)
                                                      .orElseThrow(() -> new RecordNotFoundException("Id ")), User.class));
    }


    @Override
    public String signUp(User user){
        Optional<User> email =  userRepository.findByEmail(user.getEmail());
        if(email.isPresent()){
            throw new IllegalStateException("Email already exist");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        // Autoenable user until company admin layer is implemented
        userRepository.enableAppUser(user.getEmail());
        String token =  UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;
    }
    @Override
    public int enableUser(String email) {
        return userRepository.enableAppUser(email);
    }


}


