package data;

import java.util.List;
import java.util.Set;

public record ArticleBody(
        String title,
        Set<String> tags,
        List<Comment> comments
) {
}
