package json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import data.ArticleID;

public class CommentCreateRequest {
  public ArticleID articleId;
  public String text;

  @JsonCreator
  public CommentCreateRequest(
      @JsonProperty("id") ArticleID articleId,
      @JsonProperty("text") String text
  ) {
    this.articleId = articleId;
    this.text = text;
  }
}
