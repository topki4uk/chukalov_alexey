package json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Set;

public class ArticleEditRequest {
  public Long articleId;
  public String title;
  public Set<String> tags;

  @JsonCreator
  public ArticleEditRequest(
      @JsonProperty("id") Long articleId,
      @JsonProperty("title") String title,
      @JsonProperty("tags") Set<String> tags
  ) {
    this.articleId = articleId;
    this.title = title;
    this.tags = tags;
  }
}
