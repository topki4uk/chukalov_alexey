package com.example.demo.model.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Schema(name = "User", description = "Сущность пользователя")
@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {
    @Schema(description = "ID", example = "1")
    @NonNull private UserId id;

    @Schema(description = "E-mail", example = "test@mail.ru")
    @NonNull private final String email;

    @Schema(description = "password", example = "test1234")
    @NonNull private final String password;

    @Schema(description = "username", example = "test")
    @NonNull private final String username;

    public static final User USER_1 = new User(
            new UserId(1L),
            "test1@mail.ru",
            "test1234",
            "test1"
    );
    public static final User USER_2 = new User(
            new UserId(2L),
            "test2@mail.ru",
            "test5678",
            "test2"
    );
}
