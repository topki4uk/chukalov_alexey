package controller;

import data.Article;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import services.ArticleService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Service;
import spark.template.freemarker.FreeMarkerEngine;

public class ArticleFreeMarkerController implements Controller {
  private final Service service;
  private final ArticleService articleService;
  private final FreeMarkerEngine freeMarkerEngine;


  public ArticleFreeMarkerController(
      Service service,
      ArticleService articleService,
      FreeMarkerEngine freeMarkerEngine
  ) {
    this.service = service;
    this.articleService = articleService;
    this.freeMarkerEngine = freeMarkerEngine;
  }


  @Override
  public void initEndpoints() {
    getAllArticles();
  }

  private void getAllArticles() {
    service.get(
        "/api/get/articles",
        (Request request, Response response) -> {
          response.type("text/html; charset=utf-8");
          List<Article> articles = articleService.getAll();
          List<Map<String, String>> articleMapList =
              articles.stream()
                  .map(article -> Map.of(
                      "title", article.getTitle(),
                      "commentCount", String.valueOf(article.getComments().size()))
                  )
                  .toList();

          Map<String, Object> model = new HashMap<>();
          model.put("articles", articleMapList);
          return freeMarkerEngine.render(
              new ModelAndView(model, "index.ftl")
          );
        }
    );
  }
}
