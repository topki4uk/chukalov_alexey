CREATE TABLE topics (
    id SERIAL PRIMARY KEY,
    description text NOT NULL,
    user_id INTEGER,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);