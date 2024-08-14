package com.humber.eventplanner.services;

import com.humber.eventplanner.config.SecurityConfig;
import com.humber.eventplanner.models.User;

import com.humber.eventplanner.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public void createUser(User user) {
        if(userRepository.findUserByUsername(user.getUsername()) != null) {
            throw new IllegalStateException("Username "+ user.getUsername()+" already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void updateUser(String id, User user) {
        if (userRepository.existsById(id)){
            user.setId(id);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        } else {
            throw new IllegalStateException("User with id " + id + " does not exist! Cannot update.");
        }
    }

    public void deleteUser(String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new IllegalStateException("User with id " + id + " does not exist! Cannot delete.");
        }
    }

    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public boolean verifyPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public User authenticateUser(String username, String rawPassword) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        if (verifyPassword(rawPassword, user.getPassword())) {
            return user;
        } else {
            throw new IllegalArgumentException("Invalid password");
        }
    }
}

