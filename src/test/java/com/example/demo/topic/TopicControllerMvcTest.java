package com.example.demo.topic;

import com.example.demo.controllers.TopicController;
import com.example.demo.models.topic.Topic;
import com.example.demo.models.topic.TopicId;
import com.example.demo.models.topic.exceptions.TopicNotFoundException;
import com.example.demo.models.user.UserId;
import com.example.demo.services.TopicService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(controllers = TopicController.class)
public class TopicControllerMvcTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private TopicService topicService;

  @Test
  public void getTopicByIdTest() throws Exception {
    Topic mockTopic = new Topic(
        new TopicId(1L),
        "Test description",
        new UserId(1L)
    );

    Mockito.when(topicService.findById(new TopicId(1L))).thenReturn(mockTopic);

    mockMvc.perform(
        MockMvcRequestBuilders.get("/api/topics/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id.id").value(1))
        .andExpect(jsonPath("$.description").value("Test description"))
        .andExpect(jsonPath("$.userId.id").value(1));
  }

  @Test
  public void createTopicTest() throws Exception {
    Topic inputTopic = new Topic(null, "Test description", new UserId(1L));
    Topic mockTopic = new Topic(
        new TopicId(1L),
        "Test description",
        new UserId(1L)
    );

    Mockito.when(topicService.create(Mockito.any(Topic.class))).thenReturn(mockTopic);
    String topicJson = String.format(
            "{\"description\":\"%s\", \"userId\":%s}",
            inputTopic.description(), inputTopic.userId().getId()
    );

    mockMvc.perform(
        MockMvcRequestBuilders.post("/api/topics/create")
            .contentType("application/json")
            .content(topicJson))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id.id").value(1L))
        .andExpect(jsonPath("$.description").value("Test description"))
        .andExpect(jsonPath("$.userId.id").value(1L));
  }

  @Test
  public void getAllTopicsTest() throws Exception {
    Long testUserId = 9L;

    Mockito.when(topicService.getUserTopics(testUserId)).thenThrow(TopicNotFoundException.class);

    mockMvc.perform(
        MockMvcRequestBuilders.get("/api/topics/user/9")
    )
        .andExpect(status().isNotFound());
  }
}
