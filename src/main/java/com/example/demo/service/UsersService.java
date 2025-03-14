package com.example.demo.service;

import com.example.demo.model.user.User;
import com.example.demo.model.user.UserData;
import com.example.demo.model.user.UserId;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UsersRepository;
import io.github.resilience4j.ratelimiter.RateLimiter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UsersService {
    private final UsersRepository userRepository;
    private final RateLimiter rateLimiter = RateLimiter.ofDefaults("apiRateLimiter");

  @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User findById(UserId userId) {
        return rateLimiter.executeSupplier(() ->
            userRepository
                .findById(userId.getId())
                .orElseThrow(() -> new UserNotFoundException(userId)));
    }

    @Transactional(readOnly = false)
    public User register(UserData userData) {
        return rateLimiter.executeSupplier(() -> {
                User user = new User();
                user.setEmail(userData.email());
                user.setPassword(userData.password());
                user.setUsername(userData.username());
                userRepository.save(user);
                return user;
            }
        );
    }

  @Transactional(readOnly = false)
    public void update(UserData userData,  Long id) {
        rateLimiter.executeRunnable(() -> {
          userRepository.updateUser(id, userData.email(), userData.password(), userData.username());
        });
    }

    @Transactional(readOnly = false)
    public void delete(Long id) {
      userRepository.deleteUserById(id);
    }
}
