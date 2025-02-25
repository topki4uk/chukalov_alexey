package com.example.demo.model.topic;

import com.example.demo.model.user.UserId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(name = "Topic", description = "Сущность топика")
@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class Topic {

    @Schema(description = "ID", example = "1")
    private TopicId id;

    @Schema(description = "description", example = "Test description")
    @NonNull private final String description;

    @Schema(description = "user_id", example = "1")
    @NonNull private final UserId userId;

    public static final Topic TOPIC_1 = new Topic(new TopicId(1L), "test_description_1", new UserId(1L));
    public static final Topic TOPIC_2 = new Topic(new TopicId(2L), "test_description_2", new UserId(2L));
}
