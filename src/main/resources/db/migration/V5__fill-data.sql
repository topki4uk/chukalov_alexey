INSERT INTO users VALUES (1, 'first@mail.ru', 'hahash', 'first');
INSERT INTO users VALUES (2, 'second@mail.ru', 'hahahash', 'second');

INSERT INTO topics VALUES (1, 'first description', 1);
INSERT INTO topics VALUES (2, 'second description', 2);
INSERT INTO topics VALUES (3, 'third description', 2);

INSERT INTO websites VALUES (1, 'https://first.ru', 'first desc', 1);
INSERT INTO websites VALUES (2, 'https://second.ru', 'second desc', 2);
INSERT INTO websites VALUES (3, 'https://third.ru', 'third desc', 1);

INSERT INTO articles VALUES (1, 'first title', 'https://first.ru', CURRENT_TIMESTAMP, 1, 2);
INSERT INTO articles VALUES (2, 'second title', 'https://second.ru', CURRENT_TIMESTAMP, 2, 1);