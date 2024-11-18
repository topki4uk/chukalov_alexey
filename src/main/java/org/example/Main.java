package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import controller.ArticleController;
import controller.ArticleFreeMarkerController;
import free_marker.TemplateFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repositories.ArticleRepository;
import repositories.CommentRepository;
import services.ArticleService;
import spark.Service;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    Service service = Service.ignite();
    ObjectMapper objectMapper = new ObjectMapper();
    ArticleService articleService = new ArticleService(
        new ArticleRepository(),
        new CommentRepository()
    );

    Application application =
        new Application(
            List.of(
                new ArticleController(
                    service,
                    objectMapper,
                    articleService
                ),
                new ArticleFreeMarkerController(
                    service,
                    articleService,
                    TemplateFactory.freeMarkerEngine()
                )
            )
        );
    application.start();
  }
}
