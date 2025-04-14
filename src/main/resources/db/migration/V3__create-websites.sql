CREATE TABLE websites (
    id BIGSERIAL PRIMARY KEY,
    url text NOT NULL,
    description text NOT NULL,
    creator_id uuid,
    FOREIGN KEY (creator_id) REFERENCES users(id) ON DELETE CASCADE
);