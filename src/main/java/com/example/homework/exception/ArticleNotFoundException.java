package com.example.homework.exception;

import com.example.homework.model.article.ArticleId;

public class ArticleNotFoundException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Article with id=%s not exists";

    public ArticleNotFoundException(final ArticleId articleId) {
        super(String.format(DEFAULT_MESSAGE, articleId.getId()));
    }
}
