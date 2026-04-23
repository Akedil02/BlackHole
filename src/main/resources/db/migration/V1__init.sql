CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) not null UNIQUE,
    password VARCHAR(255) not null,
    email VARCHAR(255) not null UNIQUE
)