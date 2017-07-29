DROP TABLE IF EXISTS client_coupon;
DROP TABLE IF EXISTS coupon;
DROP TABLE IF EXISTS shopping_cart;
DROP TABLE IF EXISTS client;
DROP TABLE IF EXISTS movie;
DROP TABLE IF EXISTS category;


CREATE TABLE category (
    category_id     INT            NOT NULL AUTO_INCREMENT,
    title           VARCHAR(255)   NOT NULL UNIQUE,
    PRIMARY KEY (category_id)
) ENGINE = InnoDB;


CREATE TABLE movie (
    movie_id        INT            NOT NULL AUTO_INCREMENT,
    title           VARCHAR(255)   NOT NULL,
    release_date    DATE           NOT NULL,
    rating          INT            NOT NULL,
    price           DOUBLE         NOT NULL,
    category_id     INT            NOT NULL,
    PRIMARY KEY (movie_id),
    FOREIGN KEY (category_id) REFERENCES category(category_id) ON DELETE CASCADE
) ENGINE = InnoDB;


CREATE TABLE client (
    client_id       INT            NOT NULL AUTO_INCREMENT,
    username        VARCHAR(255)   NOT NULL UNIQUE,
    password        VARCHAR(255)   NOT NULL,
    PRIMARY KEY (client_id)
) ENGINE = InnoDB;


CREATE TABLE shopping_cart (
    order_id       INT             NOT NULL AUTO_INCREMENT,
    client_id      INT             NOT NULL,
    movie_id       INT             NOT NULL,
    order_date     DATE            NOT NULL,
    PRIMARY KEY (order_id),
    FOREIGN KEY (client_id) REFERENCES client(client_id) ON DELETE CASCADE,
    FOREIGN KEY (movie_id) REFERENCES movie(movie_id) ON DELETE CASCADE
) ENGINE = InnoDB;


CREATE TABLE coupon (
    coupon_id          INT            NOT NULL AUTO_INCREMENT,
    code               VARCHAR(255)   NOT NULL,
    discount           DOUBLE         NOT NULL,
    receiving_date     DATE           NOT NULL,
    PRIMARY KEY (coupon_id)
) ENGINE = InnoDB;


CREATE TABLE client_coupon (
    id                 INT            NOT NULL AUTO_INCREMENT,
    client_id          INT            NOT NULL UNIQUE,
    coupon_id          INT            NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (client_id) REFERENCES client(client_id) ON DELETE CASCADE,
    FOREIGN KEY (coupon_id) REFERENCES coupon(coupon_id) ON DELETE CASCADE
) ENGINE = InnoDB;