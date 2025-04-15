package com.example.demo.model.website;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public record WebsiteData(
    @NotNull String url,
    @NotNull String description,
    @NotNull UUID userId
) {
}
