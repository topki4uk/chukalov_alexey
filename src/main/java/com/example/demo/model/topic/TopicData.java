package com.example.demo.model.topic;

import java.util.UUID;

public record TopicData(
        String description,
        UUID userId
) {
}
