package com.example.demo.repository;

import com.example.demo.model.user.User;
import com.example.demo.exception.EmailConflictException;
import com.example.demo.exception.UserNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {

    @Query("select u from User u")
    @NotNull
    List<User> findAll();

    @Query("from User u where (u.id = :userId)")
    @NotNull
    Optional<User> findById(@NotNull UUID userId);

    /**
     * @throws UserNotFoundException if the user does not exist
     * @throws EmailConflictException if an email conflict occurs
     */
    @Modifying
    @Query("update User u set u.email = :email, u.password = :password, u.username = :username where u.id = :userId")
    void updateUser(UUID userId, String email, String password, String username);

    /**
     * @throws UserNotFoundException if the user does not exist
     */
    @Modifying
    @Query("delete from User u where u.id = :userId")
    void deleteUserById(UUID userId);
}
