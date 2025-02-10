package com.example.demo.models.user;

import com.example.demo.models.user.exceptions.UserInitializationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public record User(
        @Nullable UserId id,
        @NotNull String email,
        @NotNull String password,
        @NotNull String username
) {
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

    public User {}

    public User(
            final @NotNull String email, final @NotNull String password,
            final @NotNull String username
    ) {
        this(null, email, password, username);
    }

    public User initializeWithId(final @NotNull UserId newId) {
        if (id != null) {
            throw new UserInitializationException("User is already initialized");
        }

        return new User(newId, email, password, username);
    }

    public User withEmail(final @NotNull String newEmail) {
        return new User(id, newEmail, password, username);
    }

    public User withPassword(final @NotNull String newPassword) {
        return new User(id, email, newPassword, username);
    }

    public User withUsername(final @NotNull String newUsername) {
        return new User(id, email, password, newUsername);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof User user)) {
            return false;
        }

        return id != null && id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
