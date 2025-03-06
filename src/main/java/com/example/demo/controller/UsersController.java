package com.example.demo.controller;

import com.example.demo.model.user.User;
import com.example.demo.model.user.UserData;
import com.example.demo.model.user.UserId;
import com.example.demo.operation.UserOperations;
import com.example.demo.service.UsersService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/users")
@Tag(name = "User API", description = "Управление пользователями")
public class UsersController implements UserOperations {

    private static final Logger LOG = LoggerFactory.getLogger(UsersController.class);
    private final UsersService userService;

    public UsersController(UsersService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<User> get(Long id) {
        User user = userService.findById(new UserId(id));
        LOG.debug("User with id={} found successfully", id);
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<User> register(UserData userData) {
        User user = userService.register(new User(
                new UserId(null), userData.email(), userData.password(), userData.username()
        ));
        LOG.debug("User with id={} created successfully", user.getId().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @Override
    public ResponseEntity<String> update(UserData userData, Long id) {
        User newUser = new User(
            new UserId(id),
            userData.email(),
            userData.password(),
            userData.username()
        );
        userService.update(newUser);

        LOG.debug("User with id={} updated successfully", id);
        return ResponseEntity.ok("User updated!");
    }
}
