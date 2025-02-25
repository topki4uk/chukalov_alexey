package com.example.demo.model.website;

import java.util.Objects;

public final class WebsiteId {
    private final Long id;

    public WebsiteId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        WebsiteId websiteId = (WebsiteId) o;
        return Objects.equals(id, websiteId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
