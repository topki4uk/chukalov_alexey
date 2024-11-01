package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import controller.ArticleController;
import repositories.ArticleRepository;
import repositories.CommentRepository;
import services.ArticleService;
import spark.Service;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    Service service = Service.ignite();
    ObjectMapper objectMapper = new ObjectMapper();
    Application application = new Application(
            List.of(
                    new ArticleController(
                            service,
                            objectMapper,
                            new ArticleService(
                                    new ArticleRepository(),
                                    new CommentRepository()
                            )
                    )
            )
    );
    application.start();
  }
}
