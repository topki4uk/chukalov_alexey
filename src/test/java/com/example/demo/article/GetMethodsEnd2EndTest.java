package com.example.demo.article;

import static org.junit.Assert.assertEquals;

import com.example.demo.model.article.Article;
import com.example.demo.model.article.ArticleListData;
import com.example.demo.model.topic.Topic;
import com.example.demo.model.topic.TopicList;
import com.example.demo.model.user.User;
import com.example.demo.model.website.Website;
import com.example.demo.model.website.WebsiteList;
import java.util.List;
import java.util.Objects;

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
    ResponseEntity<ArticleListData> response =
        restTemplate.getForEntity("http://localhost:" + port + "/api/articles" + "/user/1",
            ArticleListData.class
    );
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(
        Objects.requireNonNull(response.getBody()).articleDataList().getFirst().title(),
        Article.ARTICLE_1.getTitle()
    );
  }

  @Test
  public void getTopicByIdTest() {
    ResponseEntity<Topic> response =
        restTemplate.getForEntity("http://localhost:" + port + "/api/topics" + "/1", Topic.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(Topic.TOPIC_1, response.getBody());
  }

  @Test
  public void getUserTopicsByIdTest() {
    ResponseEntity<TopicList> response =
        restTemplate.getForEntity("http://localhost:" + port + "/api/topics" + "/user/1", TopicList.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(response.getBody(), new TopicList(List.of(Topic.TOPIC_1)));
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
    ResponseEntity<User> response =
        restTemplate.getForEntity("http://localhost:" + port + "/api/users" + "/1", User.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(User.USER_1, response.getBody());
  }
}
