package com.example.homework.service;

import com.example.homework.exception.WebsiteGetByIdRuntimeException;
import com.example.homework.model.user.UserId;
import com.example.homework.model.website.Website;
import com.example.homework.model.website.WebsiteId;
import com.example.homework.exception.WebsiteNotFoundException;
import com.example.homework.repository.WebsitesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WebsitesService {
    private final WebsitesRepository websiteRepository;

    public Website findById(WebsiteId websiteId) {
        return websiteRepository
            .findById(websiteId)
            .orElseThrow(() -> new WebsiteNotFoundException(websiteId));
    }

    public Website create(Website website) {
        try {
            return websiteRepository.create(website);
        } catch (Exception e) {
            throw new WebsiteGetByIdRuntimeException("Website could not be created");
        }
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
