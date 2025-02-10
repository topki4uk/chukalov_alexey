package com.example.demo.models.website;

import org.jetbrains.annotations.NotNull;

public record WebsiteData(
    @NotNull String url,
    @NotNull String description,
    @NotNull Long userId
) {
}
