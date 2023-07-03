package com.emresahna.websocketexample.controller;

import com.emresahna.websocketexample.dto.CreateUserRequest;
import com.emresahna.websocketexample.models.User;
import com.emresahna.websocketexample.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public void createUser(@RequestBody CreateUserRequest request) {
        userService.createUser(request);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
