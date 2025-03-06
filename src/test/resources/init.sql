CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       name TEXT NOT NULL,
                       age INTEGER NOT NULL,
                       nickname TEXT NOT NULL,
                       birthday TIMESTAMP NOT NULL
);

CREATE TABLE articles (
                          id SERIAL PRIMARY KEY,
                          title TEXT NOT NULL,
                          url TEXT NOT NULL,
                          created_at TIMESTAMP NOT NULL,
                          author_id INT, -- Add this line to define the foreign key column
                          FOREIGN KEY (author_id) REFERENCES users(id) -- Ensure this references the correct table
);