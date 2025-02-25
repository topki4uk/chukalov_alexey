package com.example.demo.model.article;

import com.example.demo.model.topic.TopicId;
import com.example.demo.model.website.WebsiteId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.NonNull;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Schema(name = "Article", description = "Сущность статьи")
@Data
@Builder(toBuilder = true)
public class Article {
    @Schema(description = "ID", example = "1")
    private ArticleId id;

    @Schema(description = "title", example = "test-title")
    @NonNull
    private final String title;

    @Schema(description = "url", example = "https://test.ru")
    @NonNull
    private final String url;

    @Schema(description = "created_at")
    @NonNull
    private final Timestamp createdAt;

    @Schema(description = "topic_id", example = "1")
    @NonNull
    private final TopicId topicId;

    @Schema(description = "website_id", example = "1")
    @NonNull
    private final WebsiteId websiteId;

    public static final Article ARTICLE_1 = new Article(
            new ArticleId(1L),
            "title_1",
            "http://url1.ru",
            new Timestamp(new Date().getTime()),
            new TopicId(1L),
            new WebsiteId(1L)
    );
    public static final Article ARTICLE_2 = new Article(
            new ArticleId(1L),
            "title_2",
            "http://url2.ru",
            new Timestamp(new Date().getTime()),
            new TopicId(2L),
            new WebsiteId(2L)
    );

    @Override
    public boolean equals(final Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Article article = (Article) o;
        return id != null && id.equals(article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
