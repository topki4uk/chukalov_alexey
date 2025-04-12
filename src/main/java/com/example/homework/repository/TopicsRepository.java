package com.example.homework.repository;

import com.example.homework.model.topic.Topic;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicsRepository extends JpaRepository<Topic, Long> {

    @Query("from Topic t where (t.id = :topicId)")
    @NotNull
    Optional<Topic> findById(@NotNull Long topicId);

    @Modifying
    @Query("delete from Topic t where t.id = :topicId")
    void deleteTopicById(Long topicId);
}
