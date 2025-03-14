package com.example.demo.article;

import static org.junit.Assert.assertEquals;

import com.example.demo.model.website.Website;
import com.example.demo.model.website.WebsiteList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class GetMethodsEnd2EndTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void testGetUserArticles() {

  }

  @Test
  public void getTopicByIdTest() {

  }

  @Test
  public void getUserTopicsByIdTest() {

  }

  @Test
  public void getWebsiteByIdTest() {
    ResponseEntity<Website> response =
        restTemplate.getForEntity("http://localhost:" + port + "/api/websites" + "/1", Website.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(Website.WEBSITE_1, response.getBody());
  }

  @Test
  public void getUserWebsitesTest() {
    ResponseEntity<WebsiteList> response =
        restTemplate.getForEntity("http://localhost:" + port + "/api/websites" + "/user/1", WebsiteList.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(response.getBody(), new WebsiteList(List.of(Website.WEBSITE_1)));
  }

  @Test
  public void getUserById() {

  }
}
