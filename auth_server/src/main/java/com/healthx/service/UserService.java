package com.healthx.service;

import com.healthx.exeptions.UserAlreadyExistsException;
import com.healthx.model.User;
import com.healthx.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public User addUser(User user) {
        Optional<User> existedUser = repository.findByUsername(user.getUsername());
        if (existedUser.isPresent()) {
            throw new UserAlreadyExistsException("User already exists.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getAuthorities().forEach(auth -> auth.setUser(user));
        repository.save(user);
        return user;
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }
}
