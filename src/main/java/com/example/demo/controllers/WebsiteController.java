package com.example.demo.controllers;

import com.example.demo.models.user.UserId;
import com.example.demo.models.website.Website;
import com.example.demo.models.website.WebsiteData;
import com.example.demo.models.website.WebsiteId;
import com.example.demo.models.website.WebsiteList;
import com.example.demo.models.website.WebsiteUrlData;
import com.example.demo.operations.WebsiteOperations;
import com.example.demo.services.WebsiteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/websites")
@Tag(name = "Website API", description = "Управление сайтами")
public final class WebsiteController implements WebsiteOperations {
    private static final Logger LOG = LoggerFactory.getLogger(WebsiteController.class);

    @Autowired
    private final WebsiteService websiteService;

    public WebsiteController(WebsiteService websiteService) {
        this.websiteService = websiteService;
    }

    @Override
    public ResponseEntity<Website> get(Long id) {
        Optional<Website> website = websiteService.findById(new WebsiteId(id));
        LOG.debug("Website with id={} was found successfully", id);
        return ResponseEntity.ok(website.get());
    }

    @Override
    public ResponseEntity<WebsiteList> getUserWebsites(Long id) {
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
        LOG.debug("Website with id={} was created successfully", website.id().getId());
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
        Optional<Website> website = websiteService.findById(new WebsiteId(id));
        Website newWebsite = website.get().withUrl(urlData.url());
        websiteService.updateUrl(newWebsite);
        return ResponseEntity.ok("Url website updated!");
    }

    @Override
    public ResponseEntity<String> deleteWebsite(Long userId, Long websiteId) {
        websiteService.delete(new UserId(userId), new WebsiteId(websiteId));
        LOG.debug("Website with id={} was deleted successfully", websiteId);
        return ResponseEntity.ok("Website deleted!");
    }
}
