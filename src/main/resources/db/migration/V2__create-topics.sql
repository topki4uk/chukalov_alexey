CREATE TABLE topics (
    id BIGSERIAL PRIMARY KEY,
    description text NOT NULL,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);