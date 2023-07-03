package com.emresahna.websocketexample.controller;

import com.emresahna.websocketexample.dto.LoginUserRequest;
import com.emresahna.websocketexample.dto.RegisterUserRequest;
import com.emresahna.websocketexample.models.User;
import com.emresahna.websocketexample.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginUserRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterUserRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }
}
