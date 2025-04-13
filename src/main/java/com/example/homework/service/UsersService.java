package com.example.homework.service;

import com.example.homework.model.user.User;
import com.example.homework.model.user.UserData;
import com.example.homework.model.user.UserId;
import com.example.homework.exception.UserNotFoundException;
import com.example.homework.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UsersService {

  private final UsersRepository userRepository;
  private final UserAuditsService userAuditsService;

  @Transactional(readOnly = true)
  public List<User> findAll() {
    return userRepository.findAll();
  }

  @Transactional(readOnly = true)
  public User findById(UserId userId) {
    User user = userRepository.findById(userId.getId())
        .orElseThrow(() -> {
              userAuditsService.saveAudit(new User(userId.getId(), null, null, null), "not found", "user not found");
              return new UserNotFoundException(userId);
            }
        );
    userAuditsService.saveAudit(user, "find", "User found successfully");

    return user;
  }

  @Transactional()
  public User register(UserData userData) {
    User user = new User();
    user.setEmail(userData.email());
    user.setPassword(userData.password());
    user.setUsername(userData.username());
    userRepository.save(user);
    userAuditsService.saveAudit(user, "register", "User registered successfully");

    return user;
  }

  @Transactional()
  public void update(UserData userData, UUID id) {
    userRepository.updateUser(id, userData.email(), userData.password(), userData.username());
    User user = userRepository.findById(id)
        .orElseThrow(() -> {
          userAuditsService.saveAudit(new User(id, null, null, null), "not update", "user not found");
          return new UserNotFoundException(new UserId(id));
        });
    userAuditsService.saveAudit(user, "update", "User updated successfully");
  }

  @Transactional()
  public void delete(UUID id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> {
          userAuditsService.saveAudit(new User(id, null, null, null), "not delete", "user not found");
          return new UserNotFoundException(new UserId(id));
        });
    userRepository.deleteUserById(id);
    userAuditsService.saveAudit(user, "delete", "User deleted successfully");
  }
}
