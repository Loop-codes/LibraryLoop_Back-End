package com.example.LibraryLoop.service;

import com.example.LibraryLoop.Repository.userRepository;
import com.example.LibraryLoop.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final userRepository userRepository;

    public UserService(userRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }
}
