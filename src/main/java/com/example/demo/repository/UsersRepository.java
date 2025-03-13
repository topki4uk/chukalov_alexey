package com.example.demo.repository;

import com.example.demo.model.user.User;
import com.example.demo.exception.EmailConflictException;
import com.example.demo.exception.UserNotFoundException;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {

    @Query("select u from User u")
    @NotNull
    List<User> findAll();

    @Transactional
    @Query("from User u where (u.id = :userId)")
    @NotNull
    Optional<User> findById(@NotNull Long userId);

    /**
     * @throws UserNotFoundException if the user does not exist
     * @throws EmailConflictException if an email conflict occurs
     */
    @Modifying
    @Transactional
    @Query("update User u set u.email = :email, u.password = :password, u.username = :username where u.id = :userId")
    void update(Long userId, String email, String password, String username);

    /**
     * @throws UserNotFoundException if the user does not exist
     */
    @Transactional
    @Modifying
    @Query("delete from User u where u.id = :userId")
    void deleteUserById(Long userId);
}
