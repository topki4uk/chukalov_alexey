package com.example.demo.services;

import com.example.demo.models.user.User;
import com.example.demo.models.user.UserId;
import com.example.demo.models.user.repositories.InMemoryUserRepository;
import com.example.demo.models.user.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public final class UserService {
    private final UserRepository userRepository;

    public UserService(InMemoryUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findById(UserId userId) {
        return userRepository.findById(userId);
    }

    public User register(User user) {
        return userRepository.create(user);
    }

    public void update(User user) {
        userRepository.update(user);
    }
}
