package com.example.homework.controller;

import com.example.homework.model.user.User;
import com.example.homework.model.user.UserData;
import com.example.homework.model.user.UserId;
import com.example.homework.operation.UserOperations;
import com.example.homework.service.UsersService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

  @Override
  public ResponseEntity<List<User>> findAll() {
    return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<User> get(UUID id) {
    User user = userService.findById(new UserId(id));

    log.debug("User with id={} found successfully", id);
    return ResponseEntity.ok(user);
  }

  @Override
  public ResponseEntity<User> register(UserData userData) {
    User user = userService.register(userData);

    log.debug("User with id={} created successfully", user.getId());
    return ResponseEntity.status(HttpStatus.CREATED).body(user);
  }

  @Override
  public ResponseEntity<String> update(UserData userData, UUID id) {
    userService.update(userData, id);

    log.debug("User with id={} updated successfully", id);
    return ResponseEntity.ok("User updated!");
  }

  @Override
  public ResponseEntity<String> delete(UUID id) {
    userService.delete(id);

    log.debug("User with id={} deleted successfully", id);
    return ResponseEntity.ok("User deleted successfully");
  }
}
