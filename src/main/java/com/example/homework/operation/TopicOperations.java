package com.example.homework.operation;

import com.example.homework.model.topic.Topic;
import com.example.homework.model.topic.TopicData;
import com.example.homework.model.topic.TopicList;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/default")
public interface TopicOperations {
  @GetMapping("/{id}")
  @Operation(summary = "Получение топика по id")
  @ApiResponse(responseCode = "200", description = "Топик найден")
  ResponseEntity<TopicData> get(@Parameter(description = "ID топика") @PathVariable Long id);

  @PostMapping("/create")
  @Operation(summary = "Создание топика")
  @ApiResponse(responseCode = "201", description = "Топик создан")
  ResponseEntity<Topic> create(@RequestBody TopicData topicData);

  @DeleteMapping("/{topicId}")
  @Operation(summary = "Удаление топика")
  @ApiResponse(responseCode = "200", description = "Топик удален")
  ResponseEntity<String> deleteTopic(@Parameter(description = "ID топика") @PathVariable Long topicId);

  @GetMapping("/user/{userId}")
  @Operation(summary = "Получение топиков по ID пользователя")
  @ApiResponse(responseCode = "200", description = "Топики найдены")
  ResponseEntity<TopicList> getUserTopics(@Parameter(description = "ID пользователя") @PathVariable Long userId);
}
