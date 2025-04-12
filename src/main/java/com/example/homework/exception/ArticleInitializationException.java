package com.example.homework.exception;


import com.example.homework.model.article.ArticleId;

public class ArticleInitializationException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Article wih id=%s already initialized";

    public ArticleInitializationException(final ArticleId articleId) {
        super(String.format(DEFAULT_MESSAGE, articleId.getId()));
    }
}
