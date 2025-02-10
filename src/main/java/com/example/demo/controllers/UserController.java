package com.example.demo.controllers;

import com.example.demo.models.user.User;
import com.example.demo.models.user.UserData;
import com.example.demo.models.user.UserId;
import com.example.demo.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("api/user")
public final class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Long id) {
        Optional<User> user = userService.findById(new UserId(id));
        return ResponseEntity.ok(user.get());
    }

    @PostMapping("/register")
    public ResponseEntity<User> update(@RequestBody UserData userData) {
        User user = userService.register(new User(
                new UserId(null), userData.email(), userData.password(), userData.username()
        ));
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
