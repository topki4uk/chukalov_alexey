package com.example.homework.model.user;

import org.jetbrains.annotations.NotNull;

public record AuthenticationCredentials(@NotNull String email, @NotNull String password) {}
