package com.emresahna.websocketexample.service.impl;

import com.emresahna.websocketexample.dto.CreateUserRequest;
import com.emresahna.websocketexample.dto.LoginUserRequest;
import com.emresahna.websocketexample.dto.RegisterUserRequest;
import com.emresahna.websocketexample.exception.UserCredentialsException;
import com.emresahna.websocketexample.models.User;
import com.emresahna.websocketexample.repository.UserRepository;
import com.emresahna.websocketexample.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void createUser(CreateUserRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .build();

        userRepository.save(user);
    }

    @Override
    public User login(LoginUserRequest request) {
        var user = userRepository.findByUsernameAndPassword(request.getUsername(), request.getPassword());
        if (user.isEmpty()) {
            throw throwUserCredentialsException();
        }
        return user.get();
    }

    private UserCredentialsException throwUserCredentialsException() {
        return new UserCredentialsException("User credentials are not valid");
    }

    @Override
    public User register(RegisterUserRequest request) {
        var user = userRepository.findByUsername(request.getUsername());
        if (user.isPresent()) {
            throw throwUsernameAlreadyTakenException();
        }
        return userRepository.save(User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .build());
    }

    private UserCredentialsException throwUsernameAlreadyTakenException() {
        return new UserCredentialsException("Username is already taken");
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
