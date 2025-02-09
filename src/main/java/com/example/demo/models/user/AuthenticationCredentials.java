package com.example.demo.models.user;

import org.jetbrains.annotations.NotNull;

public record AuthenticationCredentials(@NotNull String email, @NotNull String password) {}
