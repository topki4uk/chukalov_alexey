package com.example.demo.repository;

import com.example.demo.model.user.AuthenticationCredentials;
import com.example.demo.model.user.User;
import com.example.demo.model.user.UserId;
import com.example.demo.exception.EmailConflictException;
import com.example.demo.exception.UserNotFoundException;

import java.util.Optional;

public interface UsersRepository {
    Optional<User> findById(UserId userId);

    Optional<UserId> authenticate(AuthenticationCredentials credentials);

    /**
     * @throws EmailConflictException if an email conflict occurs
     */
    User create(User user);

    /**
     * @throws UserNotFoundException if the user does not exist
     * @throws EmailConflictException if an email conflict occurs
     */
    void update(User user);

    /**
     * @throws UserNotFoundException if the user does not exist
     */
    void delete(UserId userId);
}
