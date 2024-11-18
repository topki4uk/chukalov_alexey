package json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Set;

public class ArticleGetTestResponse {
  public final String title;
  public final Set<String> tags;
  public final List<String> comments;

  @JsonCreator
  public ArticleGetTestResponse(
          @JsonProperty("title") String title,
          @JsonProperty("tags") Set<String> tags,
          @JsonProperty("comments") List<String> comments
  ) {
    this.title = title;
    this.tags = tags;
    this.comments = comments;
  }
}

