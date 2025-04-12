package com.example.homework.repository;

import com.example.homework.model.user.UserId;
import com.example.homework.model.website.Website;
import com.example.homework.model.website.WebsiteId;
import com.example.homework.exception.WebsiteAlreadyExistsException;
import com.example.homework.exception.WebsiteNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryWebsitesRepository implements WebsitesRepository {
    List<Website> websites = new ArrayList<>(List.of(Website.WEBSITE_1, Website.WEBSITE_2));

    @Override
    public Optional<Website> findById(WebsiteId websiteId) {
        for (Website website : websites) {
            if (website.getId().equals(websiteId)) {
                return Optional.of(website);
            }
        }
        throw new WebsiteNotFoundException(websiteId);
    }

    @Override
    public Website create(Website website) {
        for (Website other : websites) {
            if (other.getUrl().equals(website.getUrl())) {
                throw new WebsiteAlreadyExistsException(website.getId(), website.getUrl());
            }
        }
        return websites.get(1);
    }

    @Override
    public List<Website> getAll() {
        return websites;
    }

    @Override
    public List<Website> findSubscribedWebsitesByUserId(UserId creatorId) {
        return List.of(Website.WEBSITE_1);
    }

    @Override
    public List<Website> findUnSubscribedWebsitesByUserId(UserId creatorId) {
        return List.of(Website.WEBSITE_2);
    }

    @Override
    public void update(Website website) {

    }

    @Override
    public void updateUrl(Website website) {
        update(website);
    }

    @Override
    public void delete(WebsiteId userId, UserId creatorId) {

    }

    @Override
    public void updateSubscribedWebsites(List<WebsiteId> websites, UserId userId) {

    }
}
