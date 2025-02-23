package com.example.demo.services;

import com.example.demo.models.user.UserId;
import com.example.demo.models.website.Website;
import com.example.demo.models.website.WebsiteId;
import com.example.demo.models.website.repositories.InMemoryWebsiteRepository;
import com.example.demo.models.website.repositories.WebsiteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public final class WebsiteService {
    private final WebsiteRepository websiteRepository;


    public WebsiteService(InMemoryWebsiteRepository websiteRepository) {
        this.websiteRepository = websiteRepository;
    }

    public Optional<Website> findById(WebsiteId websiteId) {
        return websiteRepository.findById(websiteId);
    }

    public Website create(Website website) {
        return websiteRepository.create(website);
    }

    public void update(Website website) {
        websiteRepository.update(website);
    }

    public void updateUrl(Website website) {
        websiteRepository.updateUrl(website);
    }

    public List<Website> getAll() {
        return websiteRepository.getAll();
    }

    public void delete(UserId userId, WebsiteId websiteId) {
        websiteRepository.delete(websiteId, userId);
    }

    public List<Website> findSubscribedWebsitesByUserId(UserId creatorId) {
        return websiteRepository.findSubscribedWebsitesByUserId(creatorId);
    }

    public List<Website> findUnSubscribedWebsitesByUserId(UserId creatorId) {
        return websiteRepository.findUnSubscribedWebsitesByUserId(creatorId);
    }
}
