package com.example.demo.model.website;

import com.example.demo.model.user.UserId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Schema(name = "Website", example = "Сущность сайта")
@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class Website {

    @Schema(description = "ID", example = "1")
    private WebsiteId id;

    @Schema(description = "url", example = "http://test.com")
    @NonNull
    private final String url;

    @Schema(description = "description", example = "Test description")
    @NonNull
    private final String description;

    @Schema(description = "creator_id", example = "1")
    @NonNull
    private final UserId creatorId;

    public static final Website WEBSITE_1 = new Website(
        new WebsiteId(1L),
        "http://url1.ru",
        "test_description_1",
        new UserId(1L)
    );

    public static final Website WEBSITE_2 = new Website(
        new WebsiteId(2L),
        "http://url2.ru",
        "test_description_2",
        new UserId(2L)
    );

}
