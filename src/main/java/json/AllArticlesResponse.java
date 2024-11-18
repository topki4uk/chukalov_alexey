package json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import data.Article;
import java.util.List;

public class AllArticlesResponse {
  public final List<Article> articles;

  @JsonCreator
  public AllArticlesResponse(
      @JsonProperty("articles") List<Article> articles
  ) {
    this.articles = articles;
  }
}
