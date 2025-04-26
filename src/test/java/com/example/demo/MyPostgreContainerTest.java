package com.example.demo;


import com.example.demo.model.user.User;
import com.example.demo.repository.UsersRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MyPostgreContainerTest extends DbSuite {

  @Autowired
  UsersRepository userRepository;

  public static User USER_1 = new User("admin", "admin", "admin");
  public static User USER_2 = new User("user", "user", "user");

  @BeforeEach
  public void setup() {
    userRepository.save(USER_1);
  }

  @Test
  public void shouldCreateUser() {
    Assertions.assertTrue(userRepository.findById(USER_1.getId()).isPresent());
  }

  @Test
  public void shouldFindUser() {
    Optional<User> user = userRepository.findById(USER_1.getId());
    Assertions.assertTrue(user.isPresent());
    Assertions.assertEquals(USER_1.getEmail(), user.get().getEmail());
    Assertions.assertEquals(USER_1.getPassword(), user.get().getPassword());
    Assertions.assertEquals(USER_1.getUsername(), user.get().getUsername());
  }

  @Test
  public void shouldGetAllUsers() {
    userRepository.save(USER_2);
    List<User> users = userRepository.findAll();
    Assertions.assertEquals(2, users.size());
  }
}
