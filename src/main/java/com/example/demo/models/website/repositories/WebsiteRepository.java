package com.example.demo.models.website.repositories;

import com.example.demo.models.user.UserId;
import com.example.demo.models.website.Website;
import com.example.demo.models.website.WebsiteId;
import com.example.demo.models.website.exceptions.WebsiteAlreadyExistsException;
import com.example.demo.models.website.exceptions.WebsiteNotFoundException;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public interface WebsiteRepository {
    Optional<Website> findById(@NotNull WebsiteId websiteId);

    /**
     * @throws WebsiteAlreadyExistsException if the website exist
     */
    @NotNull Website create(@NotNull Website website);

    @NotNull List<Website> getAll();

    @NotNull List<Website> findSubscribedWebsitesByUserId(@NotNull UserId creatorId);

    @NotNull List<Website> findUnSubscribedWebsitesByUserId(@NotNull UserId creatorId);

    /**
     * @throws WebsiteNotFoundException if the website does not exist
     * @throws WebsiteAlreadyExistsException if the website exist
     */
    void update(@NotNull Website website);

    /**
     * @throws WebsiteNotFoundException if the website does not exist
     */
    void delete(@NotNull WebsiteId userId, @NotNull UserId creatorId);

    void updateSubscribedWebsites(@NotNull List<WebsiteId> websites, @NotNull UserId userId);
}
