package com.example.demo.model.topic;

import com.example.demo.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@Schema(name = "Topic", description = "Сущность топика")
@Table(name = "topics")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID", example = "1")
    private Long id;

    @Schema(description = "description", example = "Test description")
    @NotNull(message = "Description have to build field")
    private String description;

    @Schema(description = "user_id", example = "1")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    public Topic() {}

    public Topic(String description, User user) {
        this.description = description;
        this.user = user;
    }
}
