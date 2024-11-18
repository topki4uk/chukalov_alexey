package json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class ArticleCreateRequest {
  public final String title;
  public final Set<String> tags;

  @JsonCreator
  public ArticleCreateRequest(
          @JsonProperty("title") String title,
          @JsonProperty("tags") Set<String> tags
  ) {
    this.title = title;
    this.tags = tags;
  }
}
