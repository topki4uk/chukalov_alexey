package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.ClassRule;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Slf4j
@Testcontainers
@SpringBootTest
public class MySqlContainerTest {

  @ClassRule
  public static PostgreSQLContainer<?> postgresql =
      new PostgreSQLContainer<>("postgres:13")
      .withDatabaseName("test")
      .withUsername("root")
      .withPassword("1234")
      .withExposedPorts(5432)
      .withInitScript("init.sql");

  @DynamicPropertySource
  static void initProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgresql::getJdbcUrl);
    registry.add("spring.datasource.username", postgresql::getUsername);
    registry.add("spring.datasource.password", postgresql::getPassword);
  }

  @Test
  public void testMySqlConnection() {
    String url = postgresql.getJdbcUrl();
    String host = postgresql.getHost();
    Integer port = postgresql.getMappedPort(5432);
    String database = postgresql.getDatabaseName();
    log.info("Connecting to database {}", database);
    log.info("Connecting to host {}", host);
    log.info("Connecting to port {}", port);
    log.info("Connecting to pg url {}", url);
  }
}
