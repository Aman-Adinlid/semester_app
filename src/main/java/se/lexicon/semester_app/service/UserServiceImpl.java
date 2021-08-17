package se.lexicon.semester_app.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.semester_app.dto.UserDto;
import se.lexicon.semester_app.entity.User;
import se.lexicon.semester_app.exception.ArgumentException;
import se.lexicon.semester_app.exception.RecordNotFoundException;
import se.lexicon.semester_app.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public User loadUserByUsername(String email)  {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            return user.get();
        }
      else throw new IllegalStateException("User Not Found, Invalid Email");

    }
    @Override
    public User findById(int id) throws RecordNotFoundException {
        if(userRepository.findById(id).isPresent()){
            return  userRepository.findById(id).get();
        }
       throw new RecordNotFoundException("Id not found");
    }
    @Override
    public UserDto findByEmail(String email) {
        return modelMapper.map(userRepository.findByEmail(email), UserDto.class);
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
        if(user.getPassword() != null){
           user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        userRepository.save(user);
        userRepository.enableAppUser(user.getEmail());
        return "User has been registered";
    }
    @Override
    public int enableUser(String email) {
        return userRepository.enableAppUser(email);
    }



}


