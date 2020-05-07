CREATE DATABASE sample;

\c sample;

CREATE TABLE IF NOT EXISTS _role (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS _user (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    role_id INT REFERENCES _role(id)
);

CREATE TABLE IF NOT EXISTS rule (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS role_rule (
    id SERIAL PRIMARY KEY,
    role_id INT REFERENCES _role(id),
    rule_id INT REFERENCES rule(id)
);

CREATE TABLE IF NOT EXISTS _state (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS item (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    state_id INT REFERENCES  _state(id),
    category_id INT REFERENCES  category(id),
    user_id INT REFERENCES  _user(id)
);

CREATE TABLE IF NOT EXISTS comment (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    item_id INT REFERENCES comment(id)
);

CREATE TABLE IF NOT EXISTS attachment (
    id SERIAL PRIMARY KEY,
    description TEXT,
    item_id INT REFERENCES comment(id)
);

INSERT INTO _role(id, name) VALUES (1, 'role 1');
INSERT INTO _role(id, name) VALUES (2, 'role 2');
INSERT INTO _role(id, name) VALUES (3, 'role 3');

INSERT INTO _user(id, name, role_id) VALUES (1, 'user 1', 1);
INSERT INTO _user(id, name, role_id) VALUES (2, 'user 2', 2);
INSERT INTO _user(id, name, role_id) VALUES (3, 'user 3', 3);

-- \i filename.sql