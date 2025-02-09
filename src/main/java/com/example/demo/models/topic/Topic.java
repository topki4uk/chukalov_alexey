package com.example.demo.models.topic;

import com.example.demo.models.user.exceptions.UserInitializationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public record Topic(
        @Nullable TopicId id,
        @NotNull String description
) {
    public static final Topic TOPIC_1 = new Topic(new TopicId(1L), "test_description_1");
    public static final Topic TOPIC_2 = new Topic(new TopicId(2L), "test_description_2");
    public Topic {}

    public Topic(final @NotNull String description){
        this(null, description);
    }

    public Topic initializeWithId(final @NotNull TopicId newId) {
        if (id != null) {
            throw new UserInitializationException("Topic is already initialized");
        }

        return new Topic(newId, description);
    }

    public Topic withDescription(final @NotNull String newDescription) {
        return new Topic(id, newDescription);
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Topic topic)) {
            return false;
        }

        return id != null && id.equals(topic.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
