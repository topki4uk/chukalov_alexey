CREATE TABLE IF NOT EXISTS users (
    id uuid PRIMARY KEY,
    email text UNIQUE NOT NULL,
    password text NOT NULL,
    username text UNIQUE NOT NULL
);