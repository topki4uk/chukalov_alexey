package com.example.demo.models.user.repositories;

import com.example.demo.models.user.AuthenticationCredentials;
import com.example.demo.models.user.User;
import com.example.demo.models.user.UserId;
import com.example.demo.models.user.exceptions.EmailConflictException;
import com.example.demo.models.user.exceptions.UserNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public final class InMemoryUserRepository implements UserRepository {
    List<User> users = new ArrayList<>(List.of(User.USER_1, User.USER_2));

    @Override
    public Optional<User> findById(@NotNull UserId userId) {
        for (User user : users) {
            if (user.id().equals(userId)) {
                return Optional.of(user);
            }
        }
        throw new UserNotFoundException(userId);
    }

    @Override
    public Optional<UserId> authenticate(@NotNull AuthenticationCredentials credentials) {
        return Optional.empty();
    }

    @Override
    public @NotNull User create(@NotNull User user) {
        for (User other : users) {
            if(other.email().equals(user.email())) {
                throw new EmailConflictException("Email is already used!");
            }
        }
        return users.get(1);
    }

    @Override
    public void update(@NotNull User user) {
        return;
    }

    @Override
    public void delete(@NotNull UserId userId) {

    }
}
