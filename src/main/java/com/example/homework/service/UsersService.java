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

  @Transactional(readOnly = true)
  public List<User> findAll() {
    return userRepository.findAll();
  }

  @Transactional(readOnly = true)
  public User findById(UserId userId) {
    return userRepository
        .findById(userId.getId())
        .orElseThrow(() -> new UserNotFoundException(userId));
  }

  @Transactional()
  public User register(UserData userData) {
    User user = new User();
    user.setEmail(userData.email());
    user.setPassword(userData.password());
    user.setUsername(userData.username());
    userRepository.save(user);

    return user;
  }

  @Transactional()
  public void update(UserData userData, UUID id) {
    userRepository.updateUser(id, userData.email(), userData.password(), userData.username());
  }

  @Transactional()
  public void delete(UUID id) {
    userRepository.deleteUserById(id);
  }
}
