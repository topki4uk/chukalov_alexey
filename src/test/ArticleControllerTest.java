import com.fasterxml.jackson.databind.ObjectMapper;
import controller.ArticleController;
import json.*;
import org.example.Application;
import org.junit.jupiter.api.*;
import repositories.ArticleRepository;
import repositories.CommentRepository;
import services.ArticleService;
import spark.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Set;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArticleControllerTest {
  private Service service;

  @BeforeEach
  void beforeEach() {
    service = Service.ignite();
  }

  @AfterEach
  void afterEach() {
    service.stop();
    service.awaitStop();
  }

  @Test
  void mustHaveTestForPracticeTest() throws IOException, InterruptedException {
    ObjectMapper objectMapper = new ObjectMapper();
    ArticleService articleService = new ArticleService(
            new ArticleRepository(),
            new CommentRepository()
    );

    Application application = new Application(
            List.of(
                    new ArticleController(
                            service,
                            objectMapper,
                            articleService
                    )
            )
    );

    application.start();
    service.awaitInitialization();

    HttpClient.newHttpClient()
            .send(
                    HttpRequest.newBuilder()
                            .POST(
                              HttpRequest.BodyPublishers.ofString(
                                      """
                                              { "title": "Title", "tags": ["tag1", "tag2"] }"""
                              )
                    )
                            .uri(URI.create("http://localhost:%d/api/create/article".formatted(service.port())))
                            .build(),
                    HttpResponse.BodyHandlers.ofString(UTF_8)
            );

    HttpResponse<String> createCommentResponse = HttpClient.newHttpClient()
            .send(
                    HttpRequest.newBuilder()
                            .POST(
                                    HttpRequest.BodyPublishers.ofString(
                                            """
                                                    { "id": 1, "text": "Comment" }"""
                                    )
                            )
                            .uri(URI.create("http://localhost:%d/api/create/comment".formatted(service.port())))
                            .build(),
                    HttpResponse.BodyHandlers.ofString(UTF_8)
            );
    assertEquals(createCommentResponse.statusCode(), 200);

    HttpClient.newHttpClient()
            .send(
                    HttpRequest.newBuilder()
                            .POST(
                                    HttpRequest.BodyPublishers.ofString(
                                            """
                                                    { "id": 1, "title": "New title", "tags": ["new tag1"] }"""
                                    )
                            )
                            .uri(URI.create("http://localhost:%d/api/edit/article".formatted(service.port())))
                            .build(),
                    HttpResponse.BodyHandlers.ofString(UTF_8)
            );

    HttpClient.newHttpClient()
            .send(
                    HttpRequest.newBuilder()
                            .POST(
                                    HttpRequest.BodyPublishers.ofString(
                                            """
                                                    { "articleId": 1, "commentId": 1 }"""
                                    )
                            )
                            .uri(URI.create("http://localhost:%d/api/delete/comment".formatted(service.port())))
                            .build(),
                    HttpResponse.BodyHandlers.ofString(UTF_8)
            );

    HttpResponse<String> response = HttpClient.newHttpClient()
            .send(
                    HttpRequest.newBuilder()
                            .GET()
                            .uri(URI.create("http://localhost:%d/api/get?id=1".formatted(service.port())))
                            .build(),
                    HttpResponse.BodyHandlers.ofString(UTF_8)
            );

    ArticleGetTestResponse getResponse =
            objectMapper.readValue(response.body(), ArticleGetTestResponse.class);

    assertEquals(200, response.statusCode());
    assertEquals(getResponse.comments.size(), 0);
    assertEquals(getResponse.title, "New title");
    assertEquals(getResponse.tags, Set.of("new tag1"));
  }

  @Test
  void deleteArticleTest() throws IOException, InterruptedException {
    ObjectMapper objectMapper = new ObjectMapper();
    ArticleService articleService = new ArticleService(
            new ArticleRepository(),
            new CommentRepository()
    );

    Application application = new Application(
            List.of(
                    new ArticleController(
                            service,
                            objectMapper,
                            articleService
                    )
            )
    );

    application.start();
    service.awaitInitialization();

    for (int i = 0; i < 3; ++ i) {
      HttpClient.newHttpClient()
              .send(
                      HttpRequest.newBuilder()
                              .POST(
                                      HttpRequest.BodyPublishers.ofString(
                                              """
                                                      { "title": "Title", "tags": ["tag1", "tag2", "tag3"] }"""
                                      )
                              )
                              .uri(URI.create("http://localhost:%d/api/create/article".formatted(service.port())))
                              .build(),
                      HttpResponse.BodyHandlers.ofString(UTF_8)
              );
    }

    HttpResponse<String> delResponse = HttpClient.newHttpClient()
            .send(
                    HttpRequest.newBuilder()
                            .POST(
                                    HttpRequest.BodyPublishers.ofString(
                                            """
                                                    { "id": 1 }"""
                                    )
                            )
                            .uri(URI.create("http://localhost:%d/api/delete/article".formatted(service.port())))
                            .build(),
                    HttpResponse.BodyHandlers.ofString(UTF_8)
            );
    assertEquals(delResponse.statusCode(), 200);

    HttpResponse<String> response = HttpClient.newHttpClient()
            .send(
                    HttpRequest.newBuilder()
                            .GET()
                            .uri(URI.create("http://localhost:%d/api/get?id=1".formatted(service.port())))
                            .build(),
                    HttpResponse.BodyHandlers.ofString(UTF_8)
            );

    assertEquals(response.statusCode(), 404);
  }

  @Test
  void getAllArticlesTest() throws IOException, InterruptedException {
    ObjectMapper objectMapper = new ObjectMapper();
    ArticleService articleService = new ArticleService(
            new ArticleRepository(),
            new CommentRepository()
    );

    Application application = new Application(
            List.of(
                    new ArticleController(
                            service,
                            objectMapper,
                            articleService
                    )
            )
    );

    application.start();
    service.awaitInitialization();

    for (int i = 0; i < 3; ++ i) {
      HttpResponse<String> createResponse = HttpClient.newHttpClient()
              .send(
                      HttpRequest.newBuilder()
                              .POST(
                                      HttpRequest.BodyPublishers.ofString(
                                              """
                                                      { "title": "Title%d", "tags": ["tag1", "tag2", "tag3"] }""".formatted(i)
                                      )
                              )
                              .uri(URI.create("http://localhost:%d/api/create/article".formatted(service.port())))
                              .build(),
                      HttpResponse.BodyHandlers.ofString(UTF_8)
              );
      assertEquals(createResponse.statusCode(), 200);
    }

    HttpResponse<String> response = HttpClient.newHttpClient()
            .send(
                    HttpRequest.newBuilder()
                            .GET()
                            .uri(URI.create("http://localhost:%d/api/all".formatted(service.port())))
                            .build(),
                    HttpResponse.BodyHandlers.ofString(UTF_8)
            );

    assertEquals(response.statusCode(), 200);
  }

  @Test
  void editNonExistArticle() throws IOException, InterruptedException {
    ObjectMapper objectMapper = new ObjectMapper();
    ArticleService articleService = new ArticleService(
            new ArticleRepository(),
            new CommentRepository()
    );

    Application application = new Application(
            List.of(
                    new ArticleController(
                            service,
                            objectMapper,
                            articleService
                    )
            )
    );

    application.start();
    service.awaitInitialization();

    HttpResponse<String> response = HttpClient.newHttpClient()
            .send(
                    HttpRequest.newBuilder()
                            .POST(
                                    HttpRequest.BodyPublishers.ofString(
                                            """
                                                    { "id": 1, "title": "Title", "tags": ["tag1", "tag2", "tag3"] }"""
                                    )
                            )
                            .uri(URI.create("http://localhost:%d/api/edit/article".formatted(service.port())))
                            .build(),
                    HttpResponse.BodyHandlers.ofString(UTF_8)
            );

    assertEquals(response.statusCode(), 404);
  }
}
