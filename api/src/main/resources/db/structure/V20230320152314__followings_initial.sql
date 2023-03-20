CREATE TABLE follows
(
    id       SERIAL PRIMARY KEY,
    followee VARCHAR(16)  NOT NULL,
    follower VARCHAR(16) NOT NULL
);

CREATE UNIQUE INDEX follows_id_uq ON follows (id);
