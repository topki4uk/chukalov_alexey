package com.example.homework.model.article;

public record ArticleData(
        String title,
        String url,
        Long topicId,
        Long websiteId
) {}
