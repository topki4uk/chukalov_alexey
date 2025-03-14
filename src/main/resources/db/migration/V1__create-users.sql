CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    email text UNIQUE NOT NULL,
    password text NOT NULL,
    username text UNIQUE NOT NULL
);