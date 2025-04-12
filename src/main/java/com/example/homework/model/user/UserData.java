package com.example.homework.model.user;

import org.jetbrains.annotations.NotNull;

public record UserData(
        @NotNull String email,
        @NotNull String password,
        @NotNull String username
) {
}
