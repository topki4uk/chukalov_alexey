package com.example.demo.operations;

import com.example.demo.models.website.Website;
import com.example.demo.models.website.WebsiteData;
import com.example.demo.models.website.WebsiteList;
import com.example.demo.models.website.WebsiteUrlData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/default")
public interface WebsiteOperations {
  @GetMapping("/{id}")
  @Operation(summary = "Получить сайт по ID")
  @ApiResponse(responseCode = "200", description = "Сайт найден")
  ResponseEntity<Website> get(@Parameter(description = "ID сайта") @PathVariable Long id);

  @GetMapping("/user/{id}")
  @Operation(summary = "Получить сайты по ID пользователя")
  @ApiResponse(responseCode = "200", description = "Сайты найдены")
  ResponseEntity<WebsiteList> getUserWebsites(@Parameter(description = "ID пользователя") @PathVariable Long id);

  @PostMapping("/user")
  @Operation(summary = "Создать сайт")
  @ApiResponse(responseCode = "201", description = "Сайты создан")
  ResponseEntity<Website> createWebsite(@RequestBody WebsiteData websiteData);

  @PutMapping("/user/{id}")
  @Operation(summary = "Обновить сайт")
  @ApiResponse(responseCode = "200", description = "Сайт обновлен")
  ResponseEntity<String> updateWebsite(@Parameter(description = "ID сайта") @PathVariable Long id, @RequestBody WebsiteData websiteData);


  @PatchMapping("/user/{id}")
  @Operation(summary = "Обновить url сайта")
  @ApiResponse(responseCode = "200", description = "Сайт обновлен")
  ResponseEntity<String> updateWebsiteUrl(
      @Parameter(description = "ID сайта") @PathVariable Long id,
      @RequestBody WebsiteUrlData urlData
  );


  @DeleteMapping("/{userId}/{websiteId}")
  @Operation(summary = "Удалить сайт")
  @ApiResponse(responseCode = "200", description = "Сайт удален")
  ResponseEntity<String> deleteWebsite(
      @Parameter(description = "ID пользователя") @PathVariable Long userId,
      @Parameter(description = "ID сайта") @PathVariable Long websiteId
  );
}
