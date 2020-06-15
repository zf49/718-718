-- TODO: Add your database init script here. This should initialize all your tables, and add any initial data required.

DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS article;
DROP TABLE IF EXISTS auth;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS user_detail;
DROP TABLE IF EXISTS avatar;

CREATE TABLE IF NOT EXISTS avatar
(
    id            INT NOT NULL AUTO_INCREMENT,
    name          VARCHAR(128),
    is_predefined BOOLEAN,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user_detail
(
    id         INT NOT NULL AUTO_INCREMENT,
    fname      VARCHAR(128),
    lname      VARCHAR(128),
    date_birth DATE,
    descrip    TEXT,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user
(
    id            INT          NOT NULL AUTO_INCREMENT,
    username      VARCHAR(128) NOT NULL UNIQUE,
    salt          CHAR(44)     NOT NULL,
    password_hash CHAR(88)     NOT NULL,
    avatar_id     INT,
    detail_id     INT,
    PRIMARY KEY (id),
    FOREIGN KEY (avatar_id) REFERENCES avatar (id),
    FOREIGN KEY (detail_id) REFERENCES user_detail (id)
);

CREATE TABLE IF NOT EXISTS article
(
    id           INT NOT NULL AUTO_INCREMENT,
    title        VARCHAR(256),
    content      MEDIUMTEXT,
    date_created DATETIME,
    author_id    INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (author_id) REFERENCES user (id)
);

CREATE TABLE IF NOT EXISTS comment
(
    id           INT NOT NULL AUTO_INCREMENT,
    content      MEDIUMTEXT,
    date_created DATETIME,
    author_id    INT NOT NULL,
    article_id   INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (author_id) REFERENCES user (id),
    FOREIGN KEY (article_id) REFERENCES article (id)
);

CREATE TABLE IF NOT EXISTS auth
(
    id          INT          NOT NULL AUTO_INCREMENT,
    token       VARCHAR(128) NOT NULL,
    expiry_date DATETIME     NOT NULL,
    user_id     INT,
    PRIMARY KEY (id),
    UNIQUE KEY (token),
    FOREIGN KEY (user_id) REFERENCES user (id)
);

INSERT INTO avatar (id, name, is_predefined)
VALUES (1, 'Pikachu.png', true),
       (2, 'Jigglypuff.png', true),
       (3, 'Bulbasaur.png', true),
       (4, 'Charmander.png', true),
       (5, 'Squirtle.png', true),
       (6, 'Geodude.png', true),
       (7, 'Farfetchd.png', true);
