package com.example.demo.model.article;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "articles")
@Schema(name = "Article", description = "Сущность статьи")
public class Article {

    @Id
    @Schema(description = "ID", example = "1")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "title", example = "test-title")
    @NotNull(message = "Title have to build field")
    private String title;

    @Schema(description = "url", example = "https://test.ru")
    @NotNull(message = "Url have to build field")
    private String url;

    @Schema(description = "created_at")
    @NotNull(message = "Created at have to build field")
    private Timestamp createdAt;

    @Schema(description = "topic_id", example = "1")
    @NotNull(message = "Topic ID have to build field")
    private Long topicId;

    @Schema(description = "website_id", example = "1")
    @NotNull(message = "Website ID have to build field")
    private Long websiteId;

    public Article() {}

    public Article(String title, String url, Timestamp createdAt, Long topicId, Long websiteId) {
        this.title = title;
        this.url = url;
        this.createdAt = createdAt;
        this.topicId = topicId;
        this.websiteId = websiteId;
    }
}
