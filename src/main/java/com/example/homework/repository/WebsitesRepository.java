package com.example.homework.repository;

import com.example.homework.model.user.UserId;
import com.example.homework.model.website.Website;
import com.example.homework.model.website.WebsiteId;
import com.example.homework.exception.WebsiteAlreadyExistsException;
import com.example.homework.exception.WebsiteNotFoundException;

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
