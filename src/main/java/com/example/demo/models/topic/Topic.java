package com.example.demo.models.topic;

import com.example.demo.models.user.UserId;
import com.example.demo.models.user.exceptions.UserInitializationException;
import io.swagger.v3.oas.annotations.media.Schema;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@Schema(name = "Topic", description = "Сущность топика")
public record Topic(
        @Schema(description = "ID", example = "1")
        @Nullable TopicId id,
        @Schema(description = "description", example = "Test description")
        @NotNull String description,
        @Schema(description = "user_id", example = "1")
        @NotNull UserId userId
        ) {
    public static final Topic TOPIC_1 = new Topic(new TopicId(1L), "test_description_1", new UserId(1L));
    public static final Topic TOPIC_2 = new Topic(new TopicId(2L), "test_description_2", new UserId(2L));

    public Topic initializeWithId(final @NotNull TopicId newId) {
        if (id != null) {
            throw new UserInitializationException("Topic is already initialized");
        }
        return new Topic(newId, description, userId);
    }

    public Topic withDescription(final @NotNull String newDescription) {
        return new Topic(id, newDescription, userId);
    }

    public Topic withUserId(final @NotNull UserId newUserId) {
        return new Topic(id, description, newUserId);
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
