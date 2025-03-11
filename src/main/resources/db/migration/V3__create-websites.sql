CREATE TABLE websites (
    id SERIAL PRIMARY KEY,
    url text NOT NULL,
    description text NOT NULL,
    creator_id INTEGER,
    FOREIGN KEY (creator_id) REFERENCES users(id) ON DELETE CASCADE
);