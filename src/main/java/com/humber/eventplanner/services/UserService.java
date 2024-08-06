package com.humber.eventplanner.services;

import com.humber.eventplanner.models.User;
import com.humber.eventplanner.models.User;
import com.humber.eventplanner.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    private UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public void createUser(User user) {
        if(userRepository.findUserByUsername(user.getUsername()) != null) {
            throw new IllegalStateException("Club with name "+ user.getEmail()+" already exists");
        }
        userRepository.save(user);
    }

    public void updateUser(int id, User user) {
        if (userRepository.existsById(id)){
            user.setId(id);
            userRepository.save(user);
        } else {
            throw new IllegalStateException("User with id " + id + " does not exist! Cannot update.");
        }
    }

    public void deleteUser(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new IllegalStateException("User with id " + id + " does not exist! Cannot delete.");
        }
    }

    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}
