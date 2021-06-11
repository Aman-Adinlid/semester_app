package se.lexicon.semester_app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.semester_app.entity.User;
import se.lexicon.semester_app.repository.UserRepository;

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
}
