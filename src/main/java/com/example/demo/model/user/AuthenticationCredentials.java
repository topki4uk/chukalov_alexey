package com.example.demo.model.user;

import org.jetbrains.annotations.NotNull;

public record AuthenticationCredentials(@NotNull String email, @NotNull String password) {}
