package com.example.demo.model.user;

import org.jetbrains.annotations.NotNull;

public record UserData(
        @NotNull String email,
        @NotNull String password,
        @NotNull String username
) {
}
