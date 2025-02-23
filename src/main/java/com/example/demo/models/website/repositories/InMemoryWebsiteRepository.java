package com.example.demo.models.website.repositories;

import com.example.demo.models.user.UserId;
import com.example.demo.models.website.Website;
import com.example.demo.models.website.WebsiteId;
import com.example.demo.models.website.exceptions.WebsiteAlreadyExistsException;
import com.example.demo.models.website.exceptions.WebsiteNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryWebsiteRepository implements WebsiteRepository {
    List<Website> websites = new ArrayList<>(List.of(Website.WEBSITE_1, Website.WEBSITE_2));

    @Override
    public Optional<Website> findById(@NotNull WebsiteId websiteId) {
        for (Website website : websites) {
            if (website.id().equals(websiteId)) {
                return Optional.of(website);
            }
        }
        throw new WebsiteNotFoundException(websiteId);
    }

    @Override
    public @NotNull Website create(@NotNull Website website) {
        for (Website other : websites) {
            if (other.url().equals(website.url())) {
                throw new WebsiteAlreadyExistsException(website.id(), website.url());
            }
        }
        return websites.get(1);
    }

    @Override
    public @NotNull List<Website> getAll() {
        return websites;
    }

    @Override
    public @NotNull List<Website> findSubscribedWebsitesByUserId(@NotNull UserId creatorId) {
        return List.of(Website.WEBSITE_1);
    }

    @Override
    public @NotNull List<Website> findUnSubscribedWebsitesByUserId(@NotNull UserId creatorId) {
        return List.of(Website.WEBSITE_2);
    }

    @Override
    public void update(@NotNull Website website) {

    }

    @Override
    public void updateUrl(@NotNull Website website) {
        update(website);
    }

    @Override
    public void delete(@NotNull WebsiteId userId, @NotNull UserId creatorId) {

    }

    @Override
    public void updateSubscribedWebsites(@NotNull List<WebsiteId> websites, @NotNull UserId userId) {

    }
}
