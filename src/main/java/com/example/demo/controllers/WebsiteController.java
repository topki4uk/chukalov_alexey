package com.example.demo.controllers;

import com.example.demo.models.user.UserId;
import com.example.demo.models.website.Website;
import com.example.demo.models.website.WebsiteData;
import com.example.demo.models.website.WebsiteId;
import com.example.demo.services.WebsiteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/websites")
public final class WebsiteController {
    private final WebsiteService websiteService;

    public WebsiteController(WebsiteService websiteService) {
        this.websiteService = websiteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Website> get(@PathVariable Long id) {
        Optional<Website> website = websiteService.findById(new WebsiteId(id));
        return ResponseEntity.ok(website.get());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Website>> getUserWebsites(@PathVariable Long id) {
        List<Website> websites = websiteService.findSubscribedWebsitesByUserId(new UserId(id));
        return ResponseEntity.ok(websites);
    }

    @PostMapping("/user")
    public ResponseEntity<Website> createWebsite(@RequestBody WebsiteData websiteData) {
        Website website = websiteService.create(
                new Website(
                        new WebsiteId(null),
                        websiteData.url(),
                        websiteData.description(),
                        new UserId(websiteData.userId())
                )
        );
        return ResponseEntity.ok(website);
    }

    @DeleteMapping("/{userId}/{websiteId}")
    public ResponseEntity<String> deleteWebsite(@PathVariable Long userId, @PathVariable Long websiteId) {
        websiteService.delete(new UserId(userId), new WebsiteId(websiteId));
        return ResponseEntity.ok("Website deleted!");
    }
}
