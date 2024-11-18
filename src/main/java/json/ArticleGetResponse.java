package json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import data.Comment;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ArticleGetResponse {
  public final String title;
  public final Set<String> tags;
  public final List<String> comments;

  @JsonCreator
  public ArticleGetResponse(
      @JsonProperty("title") String title,
      @JsonProperty("tags") Set<String> tags,
      @JsonProperty("comments") List<Comment> comments
  ) {
    this.title = title;
    this.tags = tags;
    this.comments = comments.stream()
        .map(Comment::getText)
        .collect(Collectors.toList());
  }
}
