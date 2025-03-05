package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.ClassRule;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@Slf4j
public class MySqlContainerTest {

  @ClassRule
  public static PostgreSQLContainer<?> postgresql =
      new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"))
      .withDatabaseName("test")
      .withUsername("root")
      .withPassword("root")
      .withExposedPorts(5432);

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
