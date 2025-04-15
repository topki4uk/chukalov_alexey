package com.example.demo.model.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@Schema(name = "User", description = "Сущность пользователя")
@Table(name = "users")
public class User {

    @Id
    @Schema(description = "ID", example = "1")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Schema(description = "E-mail", example = "test@mail.ru")
    @NotNull(message = "E-mail have to build field")
    private String email;

    @Schema(description = "password", example = "test1234")
    @NotNull(message = "Password have to build field")
    private String password;

    @Schema(description = "username", example = "test")
    @NotNull(message = "Username have to build field")
    private String username;

    public User() {}

    public User(String email, String password, String username) {
      this.email = email;
      this.password = password;
      this.username = username;
    }
}
