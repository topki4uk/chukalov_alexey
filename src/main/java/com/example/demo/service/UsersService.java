package com.example.demo.service;

import com.example.demo.model.user.User;
import com.example.demo.model.user.UserId;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UsersRepository;
import io.github.resilience4j.ratelimiter.RateLimiter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public final class UsersService {
    private final UsersRepository userRepository;
    private final RateLimiter rateLimiter = RateLimiter.ofDefaults("apiRateLimiter");

    public User findById(UserId userId) {
        return rateLimiter.executeSupplier(() ->
            userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId)));
    }

    public User register(User user) {
        return rateLimiter.executeSupplier(() ->
            userRepository.create(user)
        );
    }

    public void update(User user) {
        rateLimiter.executeRunnable(() -> userRepository.update(user));
    }
}
