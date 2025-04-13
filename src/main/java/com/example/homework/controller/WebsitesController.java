package com.example.homework.controller;

import com.example.homework.model.user.UserId;
import com.example.homework.model.website.*;
import com.example.homework.operation.WebsiteOperations;
import com.example.homework.service.WebsitesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("api/websites")
@AllArgsConstructor
@Tag(name = "Website API", description = "Управление сайтами")
public class WebsitesController implements WebsiteOperations {

  private final WebsitesService websiteService;

  @Override
  public ResponseEntity<Website> get(Long id) {
    Website website = websiteService.findById(new WebsiteId(id));

    log.debug("Website with id={} was found successfully", id);
    return ResponseEntity.ok(website);
  }

  @Override
  public ResponseEntity<WebsiteList> getUserWebsites(UUID id) {
    List<Website> websites = websiteService.findSubscribedWebsitesByUserId(new UserId(id));
    log.debug("Websites for user with id={} was found successfully", id);
    return ResponseEntity.ok(new WebsiteList(websites));
  }

  @Override
  public ResponseEntity<Website> createWebsite(WebsiteData websiteData) {
    Website website = websiteService.create(
        new Website(
            new WebsiteId(null),
            websiteData.url(),
            websiteData.description(),
            new UserId(websiteData.userId())
        )
    );

    log.debug("Website with id={} was created successfully", website.getId().getId());
    return ResponseEntity.ok(website);
  }

  @Override
  public ResponseEntity<String> updateWebsite(Long id, WebsiteData websiteData) {
    Website newWebsite = new Website(
        new WebsiteId(id),
        websiteData.url(),
        websiteData.description(),
        new UserId(websiteData.userId())
    );
    websiteService.update(newWebsite);

    log.debug("Website with id={} was updated successfully", id);
    return ResponseEntity.ok("Website updated!");
  }

  @Override
  public ResponseEntity<String> updateWebsiteUrl(Long id, WebsiteUrlData urlData) {
    Website website = websiteService.findById(new WebsiteId(id));
    Website newWebsite = website.toBuilder()
        .url(urlData.url())
        .build();
    websiteService.updateUrl(newWebsite);
    return ResponseEntity.ok("Url website updated!");
  }

  @Override
  public ResponseEntity<String> deleteWebsite(UUID userId, Long websiteId) {
    websiteService.delete(new UserId(userId), new WebsiteId(websiteId));
    log.debug("Website with id={} was deleted successfully", websiteId);
    return ResponseEntity.ok("Website deleted!");
  }
}
