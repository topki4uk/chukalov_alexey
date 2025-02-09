package com.example.demo.models.user.repositories;

import com.example.demo.models.user.AuthenticationCredentials;
import com.example.demo.models.user.User;
import com.example.demo.models.user.UserId;
import com.example.demo.models.user.exceptions.EmailConflictException;
import com.example.demo.models.user.exceptions.UserNotFoundException;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(@NotNull UserId userId);

    Optional<UserId> authenticate(@NotNull AuthenticationCredentials credentials);

    /**
     * @throws EmailConflictException if an email conflict occurs
     */
    @NotNull User create(@NotNull User user);

    /**
     * @throws UserNotFoundException if the user does not exist
     * @throws EmailConflictException if an email conflict occurs
     */
    void update(@NotNull User user);

    /**
     * @throws UserNotFoundException if the user does not exist
     */
    void delete(@NotNull UserId userId);
}
