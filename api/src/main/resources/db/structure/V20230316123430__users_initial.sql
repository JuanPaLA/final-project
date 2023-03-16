CREATE TABLE users (
    id                  SERIAL PRIMARY KEY,
    name                VARCHAR(16) NOT NULL,
    password            VARCHAR(50) NOT NULL,
    session_token       VARCHAR(300) NULL
);

CREATE UNIQUE INDEX users_name_uq ON users(LOWER(name));
