CREATE TABLE IF NOT EXISTS websites (
    id BIGSERIAL PRIMARY KEY,
    url text NOT NULL,
    description text NOT NULL,
    creator_id UUID,
    FOREIGN KEY (creator_id) REFERENCES users(id) ON DELETE CASCADE
);