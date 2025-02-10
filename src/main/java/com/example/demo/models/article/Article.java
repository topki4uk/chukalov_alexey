package com.example.demo.models.article;

import com.example.demo.models.article.exceptions.ArticleInitializationException;
import com.example.demo.models.topic.TopicId;
import com.example.demo.models.website.WebsiteId;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public record Article(
        @Nullable ArticleId id,
        @NotNull String title,
        @NotNull String url,
        @NotNull Timestamp createdAt,
        @NotNull TopicId topicId,
        @NotNull WebsiteId websiteId
        ) {
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

    public Article {}

    public Article(
            final @NotNull String title,
            final @NotNull String url,
            final @NotNull Timestamp createdAt,
            final @NotNull TopicId topicId,
            final @NotNull WebsiteId websiteId
    ) {
        this(null, title, url, createdAt, topicId, websiteId);
    }

    public Article initializeWithId(final @NotNull ArticleId newId) {
        if (id != null) {
            throw new ArticleInitializationException(newId);
        }

        return new Article(newId, title, url, createdAt, topicId, websiteId);
    }

    public Article withTitle(final @NotNull String newTitle) {
        return new Article(id, newTitle, url, createdAt, topicId, websiteId);
    }

    public Article withUrl(final @NotNull String newUrl) {
        return new Article(id, title, newUrl, createdAt, topicId, websiteId);
    }

    public Article withCreatedAt(final @NotNull Timestamp newCreatedAt) {
        return new Article(id, title, url, newCreatedAt, topicId, websiteId);
    }

    public Article withTopicId(final @NotNull TopicId newTopicId) {
        return new Article(id, title, url, createdAt, newTopicId, websiteId);
    }

    public Article withWebsiteId(final @NotNull WebsiteId newWebsiteId) {
        return new Article(id, title, url, createdAt, topicId, newWebsiteId);
    }

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
