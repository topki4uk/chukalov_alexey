package com.example.demo.models.article;

import java.util.Objects;

public final class ArticleId {
    private final Long id;

    public ArticleId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ArticleId articleId = (ArticleId) o;
        return Objects.equals(id, articleId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
