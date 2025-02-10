package com.example.demo.models.article;

import java.sql.Timestamp;

public record ArticleData(
        String title,
        String url,
        Timestamp createdAt,
        String topicDescription
) {}
