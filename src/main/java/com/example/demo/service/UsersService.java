package com.example.demo.service;

import com.example.demo.model.user.User;
import com.example.demo.model.user.UserId;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public final class UsersService {
    private final UsersRepository userRepository;

    public User findById(UserId userId) {
        return userRepository
            .findById(userId)
            .orElseThrow(() -> new UserNotFoundException(userId));
    }

    public User register(User user) {
        return userRepository.create(user);
    }

    public void update(User user) {
        userRepository.update(user);
    }
}
