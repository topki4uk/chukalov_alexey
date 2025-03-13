package com.example.demo.model.article;

public record ArticleData(
        String title,
        String url,
        Long topicId,
        Long websiteId
) {}
