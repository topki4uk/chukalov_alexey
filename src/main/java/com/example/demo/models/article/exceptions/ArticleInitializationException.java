package com.example.demo.models.article.exceptions;

import com.example.demo.models.article.ArticleId;

public class ArticleInitializationException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Article wih id=%s already initialized";

    public ArticleInitializationException(final ArticleId articleId) {
        super(String.format(DEFAULT_MESSAGE, articleId.getId()));
    }
}
