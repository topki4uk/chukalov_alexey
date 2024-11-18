package json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CommentDeleteRequest {
  public long articleId;
  public long commentId;

  @JsonCreator
  public CommentDeleteRequest(
      @JsonProperty("articleId") long articleId,
      @JsonProperty("commentId") long commentId
  ) {
    this.articleId = articleId;
    this.commentId = commentId;
  }
}
