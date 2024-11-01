package json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import data.Comment;

import java.util.List;
import java.util.Set;

public class ArticleCreateRequest {
  public final String title;
  public final Set<String> tags;
  public final List<Comment> comments;

  @JsonCreator
  public ArticleCreateRequest(
          @JsonProperty("title") String title,
          @JsonProperty("tags") Set<String> tags,
          @JsonProperty("comments") List<Comment> comments
  ) {
    this.title = title;
    this.tags = tags;
    this.comments = comments;
  }
}
