package com.example.homework.controller;

import com.example.homework.model.user.User;
import com.example.homework.model.user.UserData;
import com.example.homework.model.user.UserId;
import com.example.homework.operation.UserOperations;
import com.example.homework.service.UserAuditsService;
import com.example.homework.service.UsersService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("api/users")
@AllArgsConstructor
@Tag(name = "User API", description = "Управление пользователями")
public class UsersController implements UserOperations {

  private final UsersService userService;

  @Autowired
  private final UserAuditsService userAuditsService;

  @Override
  public ResponseEntity<List<User>> findAll() {
    return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<User> get(UUID id) {
    User user = userService.findById(new UserId(id));
    userAuditsService.saveAudit(user, "find", "User found successfully");

    log.debug("User with id={} found successfully", id);
    return ResponseEntity.ok(user);
  }

  @Override
  public ResponseEntity<User> register(UserData userData) {
    User user = userService.register(userData);
    userAuditsService.saveAudit(user, "register", "User registered successfully");

    log.debug("User with id={} created successfully", user.getId());
    return ResponseEntity.status(HttpStatus.CREATED).body(user);
  }

  @Override
  public ResponseEntity<String> update(UserData userData, UUID id) {
    userService.update(userData, id);
    User user = userService.findById(new UserId(id));
    userAuditsService.saveAudit(user, "update", "User updated successfully");

    log.debug("User with id={} updated successfully", id);
    return ResponseEntity.ok("User updated!");
  }

  @Override
  public ResponseEntity<String> delete(UUID id) {
    User user = userService.findById(new UserId(id));
    userService.delete(id);
    userAuditsService.saveAudit(user, "delete", "User deleted successfully");

    log.debug("User with id={} deleted successfully", id);
    return ResponseEntity.ok("User deleted successfully");
  }
}
