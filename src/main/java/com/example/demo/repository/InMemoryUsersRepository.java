package com.example.demo.repository;

import com.example.demo.model.user.AuthenticationCredentials;
import com.example.demo.model.user.User;
import com.example.demo.model.user.UserId;
import com.example.demo.exception.EmailConflictException;
import com.example.demo.exception.UserNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public final class InMemoryUsersRepository implements UsersRepository {
    List<User> users = new ArrayList<>(List.of(User.USER_1, User.USER_2));

    @Override
    public Optional<User> findById(UserId userId) {
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return Optional.of(user);
            }
        }
        throw new UserNotFoundException(userId);
    }

    @Override
    public Optional<UserId> authenticate(AuthenticationCredentials credentials) {
        return Optional.empty();
    }

    @Override
    public User create(User user) {
        for (User other : users) {
            if(other.getEmail().equals(user.getEmail())) {
                throw new EmailConflictException("Email is already used!");
            }
        }
        return users.get(1);
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(UserId userId) {

    }
}
