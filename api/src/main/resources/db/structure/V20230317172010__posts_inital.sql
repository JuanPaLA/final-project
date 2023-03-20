CREATE TABLE posts (
                       id                SERIAL PRIMARY KEY,
                       userId            VARCHAR(16) NOT NULL,
                       content           VARCHAR(140) NOT NULL,
                       date              TIMESTAMP NOT NULL
);

CREATE UNIQUE INDEX posts_id_uq ON posts(id);
