DROP TABLE IF EXISTS category;
CREATE TABLE category (
    category_id     INT            NOT NULL AUTO_INCREMENT,
    title           VARCHAR(255)   NOT NULL UNIQUE,
    PRIMARY KEY (category_id)
) ENGINE = InnoDB CHARACTER SET = UTF8;


DROP TABLE IF EXISTS movie;
CREATE TABLE movie (
    movie_id        INT            NOT NULL AUTO_INCREMENT,
    title           VARCHAR(255)   NOT NULL,
    release_date    DATE           NOT NULL,
    rating          INT            NOT NULL,
    price           INT            NOT NULL,
    category_id     INT            NOT NULL,
    PRIMARY KEY (category_id),
    FOREIGN KEY (category_id) REFERENCES category(category_id) ON DELETE CASCADE
) ENGINE = InnoDB CHARACTER SET = UTF8;


DROP TABLE IF EXISTS client;
CREATE TABLE client (
    client_id       INT            NOT NULL AUTO_INCREMENT,
    login           VARCHAR(255)   NOT NULL,
    password        VARCHAR(255)   NOT NULL,
    PRIMARY KEY (client_id)
) ENGINE = InnoDB CHARACTER SET = UTF8;


DROP TABLE IF EXISTS shopping_cart;
CREATE TABLE shopping_cart (
    record_id      INT             NOT NULL AUTO_INCREMENT,
    client_id      INT             NOT NULL,
    movie_id       INT             NOT NULL,
    PRIMARY KEY (record_id),
    FOREIGN KEY (client_id) REFERENCES client(client_id) ON DELETE CASCADE
    FOREIGN KEY (movie_id) REFERENCES movie(movie_id) ON DELETE CASCADE
) ENGINE = InnoDB CHARACTER SET = UTF8;


DROP TABLE IF EXISTS shopping_cart_movie;
CREATE TABLE shopping_cart_movie (
    id                 INT        NOT NULL AUTO_INCREMENT,
    shopping_cart_id   INT        NOT NULL,
    movie_id           INT        NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (shopping_cart_id) REFERENCES shopping_cart(record_id) ON DELETE CASCADE
    FOREIGN KEY (movie_id) REFERENCES movie(movie_id) ON DELETE CASCADE
) ENGINE = InnoDB CHARACTER SET = UTF8;


DROP TABLE IF EXISTS coupon;
CREATE TABLE coupon (
    coupon_id          INT            NOT NULL AUTO_INCREMENT,
    code               VARCHAR(255)   NOT NULL,
    discount           INT            NOT NULL,
    PRIMARY KEY (coupon_id),
) ENGINE = InnoDB CHARACTER SET = UTF8;


DROP TABLE IF EXISTS client_coupon;
CREATE TABLE shopping_cart_movie (
    id                 INT            NOT NULL AUTO_INCREMENT,
    client_id          INT            NOT NULL,
    coupon_id          INT            NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (client_id) REFERENCES client(client_id) ON DELETE CASCADE
    FOREIGN KEY (coupon_id) REFERENCES coupon(coupon_id) ON DELETE CASCADE
) ENGINE = InnoDB CHARACTER SET = UTF8;