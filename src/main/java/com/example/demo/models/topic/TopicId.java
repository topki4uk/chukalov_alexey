package com.example.demo.models.topic;

import com.example.demo.models.article.ArticleId;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class TopicId {
    private final Long id;

    public TopicId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TopicId topicId = (TopicId) o;
        return Objects.equals(id, topicId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
