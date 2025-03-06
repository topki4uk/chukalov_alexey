package com.example.demo;

import com.example.demo.aspect.LoggingAspect;
import com.example.demo.model.website.Website;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AspectTest {

  @LocalServerPort
  private int port;

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private LoggingAspect loggingAspect;

  @BeforeEach
  void setUp() {
    loggingAspect.startCounter();
  }

  @Test
  void testAspect() {
    Assertions.assertEquals(0, loggingAspect.getCounter());
    restTemplate.getForEntity(
        "http://localhost:" + port + "/api/websites/1", Website.class
    );
    Assertions.assertEquals(2, loggingAspect.getCounter());
  }
}
