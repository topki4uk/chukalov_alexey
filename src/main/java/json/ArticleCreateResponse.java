package json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import data.ArticleID;

public class ArticleCreateResponse {
  public final ArticleID id;

  @JsonCreator
  public ArticleCreateResponse(
          @JsonProperty("id") ArticleID id
  ) {
    this.id = id;
  }
}
