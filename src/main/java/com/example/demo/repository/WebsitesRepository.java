package com.example.demo.repository;

import com.example.demo.model.user.UserId;
import com.example.demo.model.website.Website;
import com.example.demo.model.website.WebsiteId;
import com.example.demo.exception.WebsiteAlreadyExistsException;
import com.example.demo.exception.WebsiteNotFoundException;

import java.util.List;
import java.util.Optional;

public interface WebsitesRepository {
    Optional<Website> findById(WebsiteId websiteId);

    /**
     * @throws WebsiteAlreadyExistsException if the website exist
     */
    Website create(Website website);

    List<Website> getAll();

    List<Website> findSubscribedWebsitesByUserId(UserId creatorId);

    List<Website> findUnSubscribedWebsitesByUserId(UserId creatorId);

    /**
     * @throws WebsiteNotFoundException if the website does not exist
     * @throws WebsiteAlreadyExistsException if the website exist
     */
    void update(Website website);

    void updateUrl(Website website);

    /**
     * @throws WebsiteNotFoundException if the website does not exist
     */
    void delete(WebsiteId userId, UserId creatorId);

    void updateSubscribedWebsites(List<WebsiteId> websites, UserId userId);
}
