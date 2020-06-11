-- TODO: Add your database init script here. This should initialize all your tables, and add any initial data required.

DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS article;
DROP TABLE IF EXISTS user;

CREATE TABLE IF NOT EXISTS user
(
    id            INT          NOT NULL AUTO_INCREMENT,
    username      VARCHAR(128) NOT NULL,
    salt          CHAR(44)     NOT NULL,
    password_hash VARCHAR(128) NOT NULL,
    PRIMARY KEY (id)
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
