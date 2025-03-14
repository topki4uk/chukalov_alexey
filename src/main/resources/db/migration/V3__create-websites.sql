CREATE TABLE websites (
    id BIGSERIAL PRIMARY KEY,
    url text NOT NULL,
    description text NOT NULL,
    creator_id BIGINT,
    FOREIGN KEY (creator_id) REFERENCES users(id) ON DELETE CASCADE
);