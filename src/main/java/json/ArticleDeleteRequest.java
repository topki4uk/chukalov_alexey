package json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ArticleDeleteRequest {
  public Long id;

  @JsonCreator

  public ArticleDeleteRequest(
      @JsonProperty("id") Long id
  ) {
    this.id = id;
  }
}
