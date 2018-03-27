DROP TABLE IF EXISTS users cascade;
CREATE TABLE users(
id serial PRIMARY KEY,
    name_first varchar(100),
    name_second varchar(100),
    name_third varchar(100),
    role varchar(10),
    email varchar(100),
    password varchar(100),
    avatar varchar(500),
    created timestamptz NOT NULL,
    updated timestamptz NOT NULL
);