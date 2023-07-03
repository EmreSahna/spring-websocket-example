package com.emresahna.websocketexample.service;

import com.emresahna.websocketexample.dto.CreateUserRequest;
import com.emresahna.websocketexample.dto.LoginUserRequest;
import com.emresahna.websocketexample.dto.RegisterUserRequest;
import com.emresahna.websocketexample.models.User;

import java.util.List;

public interface UserService {
    void createUser(CreateUserRequest request);
    User login(LoginUserRequest request);
    User register(RegisterUserRequest request);
    List<User> getAllUsers();
}
