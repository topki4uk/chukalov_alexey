package com.example.demo.controller;

import com.example.demo.model.user.UserId;
import com.example.demo.model.website.Website;
import com.example.demo.model.website.WebsiteData;
import com.example.demo.model.website.WebsiteId;
import com.example.demo.model.website.WebsiteList;
import com.example.demo.model.website.WebsiteUrlData;
import com.example.demo.operation.WebsiteOperations;
import com.example.demo.service.WebsitesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/websites")
@Tag(name = "Website API", description = "Управление сайтами")
public class WebsitesController implements WebsiteOperations {
    private static final Logger LOG = LoggerFactory.getLogger(WebsitesController.class);
    private final WebsitesService websiteService;

    public WebsitesController(WebsitesService websiteService) {
        this.websiteService = websiteService;
    }

    @Override
    public ResponseEntity<Website> get(Long id) {
        Website website = websiteService.findById(new WebsiteId(id));
        LOG.debug("Website with id={} was found successfully", id);
        return ResponseEntity.ok(website);
    }

    @Override
    public ResponseEntity<WebsiteList> getUserWebsites(UUID id) {
        List<Website> websites = websiteService.findSubscribedWebsitesByUserId(new UserId(id));
        LOG.debug("Websites for user with id={} was found successfully", id);
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
        LOG.debug("Website with id={} was created successfully", website.getId().getId());
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
        LOG.debug("Website with id={} was updated successfully", id);
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
        LOG.debug("Website with id={} was deleted successfully", websiteId);
        return ResponseEntity.ok("Website deleted!");
    }
}
