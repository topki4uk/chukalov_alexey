package com.example.demo.endpoint;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
@Endpoint(id = "random-uuid")
@Tag(name = "Случайный UUID", description = "Получение UUID")
public class RandomUuidEndpoint {

  @ReadOperation
  @Operation(summary = "Получить случайный UUID")
  @ApiResponse(responseCode = "200", description = "UUID получен")
  public Map<String, UUID> randomUuid() {
    return Map.of("uuid", UUID.randomUUID());
  }
}
