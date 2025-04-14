CREATE TABLE IF NOT EXISTS articles (
    id BIGSERIAL PRIMARY KEY,
    title text NOT NULL,
    url text UNIQUE NOT NULL,
    created_at timestamp NOT NULL,
    topic_id BIGINT,
    FOREIGN KEY (topic_id) REFERENCES topics(id) ON DELETE CASCADE,
    website_id BIGINT,
    FOREIGN KEY (website_id) REFERENCES websites(id) ON DELETE CASCADE
);