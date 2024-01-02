-- Postgresql initialization script
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS users (
    user_id uuid DEFAULT uuid_generate_v4() UNIQUE NOT NULL,
    username VARCHAR(20) UNIQUE NOT NULL,
    user_email VARCHAR(50) UNIQUE NOT NULL,
    user_password VARCHAR NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS logs (
    log_id uuid DEFAULT uuid_generate_v4() UNIQUE NOT NULL,
    user_id uuid NOT NULL,
    log_type VARCHAR NOT NULL,
    log_date TIMESTAMP NOT NULL,
    PRIMARY KEY (log_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE IF NOT EXISTS user_roles (
    user_id uuid NOT NULL,
    role_id uuid NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (role_id) REFERENCES roles(role_id)
);

CREATE TABLE IF NOT EXISTS roles (
    role_id uuid DEFAULT uuid_generate_v4(),
    role_name VARCHAR NOT NULL,
    PRIMARY KEY (role_id)
);

CREATE TABLE IF NOT EXISTS user_passwords (
    user_id uuid NOT NULL,
    password VARCHAR NOT NULL,
    PRIMARY KEY (user_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE IF NOT EXISTS song_analytics {
    song_id uuid 
}