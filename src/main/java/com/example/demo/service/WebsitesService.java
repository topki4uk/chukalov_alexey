package com.example.demo.service;

import com.example.demo.model.user.UserId;
import com.example.demo.model.website.Website;
import com.example.demo.model.website.WebsiteId;
import com.example.demo.exception.WebsiteNotFoundException;
import com.example.demo.repository.WebsitesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public final class WebsitesService {
    private final WebsitesRepository websiteRepository;

    public Website findById(WebsiteId websiteId) {
        return websiteRepository
            .findById(websiteId)
            .orElseThrow(() -> new WebsiteNotFoundException(websiteId));
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
