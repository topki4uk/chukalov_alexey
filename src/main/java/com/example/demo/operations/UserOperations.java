package com.example.demo.operations;

import com.example.demo.models.user.User;
import com.example.demo.models.user.UserData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/default")
public interface UserOperations {
  @Operation(summary = "Получение пользователя по id")
  @ApiResponse(responseCode = "200", description = "Пользователь найден")
  @GetMapping("/{id}")
  ResponseEntity<User> get(@Parameter(description = "ID пользователя") @PathVariable Long id);

  @Operation(summary = "Регистрация нового пользователя")
  @ApiResponse(responseCode = "201", description = "Пользователь создан")
  @PostMapping("/register")
  ResponseEntity<User> register(@RequestBody UserData userData);

  @Operation(summary = "Обновление данных о пользователе")
  @ApiResponse(responseCode = "200", description = "Пользователь обновлен")
  @PutMapping("/{id}")
  ResponseEntity<String> update(@RequestBody UserData userData, @Parameter(description = "ID пользователя") @PathVariable Long id);
}
