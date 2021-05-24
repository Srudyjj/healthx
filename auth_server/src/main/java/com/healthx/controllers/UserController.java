package com.healthx.controllers;

import com.healthx.model.User;
import com.healthx.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return this.service.addUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return this.service.getAllUsers();
    }
}
