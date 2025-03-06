package com.example.demo.model.article;

import java.sql.Timestamp;

public record ArticleData(
        String title,
        String url,
        Timestamp createdAt,
        String topicDescription
) {}
