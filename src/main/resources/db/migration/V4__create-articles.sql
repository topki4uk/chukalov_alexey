CREATE TABLE articles (
    id SERIAL PRIMARY KEY,
    title text NOT NULL,
    url text UNIQUE NOT NULL,
    created_at timestamp NOT NULL,
    topic_id INTEGER,
    FOREIGN KEY (topic_id) REFERENCES topics(id) ON DELETE CASCADE,
    website_id INTEGER,
    FOREIGN KEY (website_id) REFERENCES websites(id) ON DELETE CASCADE
);