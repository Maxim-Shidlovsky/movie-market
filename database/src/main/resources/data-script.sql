INSERT INTO category (category_id, title)
VALUES
(1, 'Thriller'),
(2, 'Historical'),
(3, 'Fantastic'),
(4, 'Cartoon'),
(5, 'Catastrophe');


INSERT INTO movie (movie_id, title, release_date, rating, price, category_id)
VALUES
(1, 'Terminator', '1984-10-26', 8, 5, 1),
(2, 'Transporter', '2002-10-02', 7, 4, 1),
(3, 'Troy', '2004-05-09', 9, 6, 2),
(4, 'Gladiator', '2000-05-01', 9, 6, 2),
(5, 'Braveheart', '1995-05-19', 8, 4, 2),
(6, 'Alexander', '2004-11-16', 7, 3, 2),
(7, 'Star Wars', '1999-05-19', 7, 3, 3),
(8, 'The Lord of the Rings', '2003-03-03', 8, 5, 3),
(9, 'Avatar', '2009-12-10', 7, 3, 3),
(10, 'Lion King', '1994-06-15', 9, 4, 4),
(11, 'Finding Nemo', '2003-05-30', 7, 3, 4),
(12, 'Shrek', '2001-04-2', 8, 5, 4),
(13, 'Titanic', '1997-11-18', 8, 6, 5),
(14, 'The Impossible', '2012-10-11', 8, 3, 5);


INSERT INTO coupon (coupon_id, code, discount, receiving_date)
VALUES
(1, '123', 20, '2017-01-01'),
(2, '111', 10, '2016-01-01'),
(3, '333', 15, '2015-01-01');


INSERT INTO client (client_id, username, password)
VALUES
(1, 'admin', 'admin'),
(2, 'user2', '111'),
(3, 'user3', '111');


INSERT INTO shopping_cart (order_id, client_id, movie_id, order_date)
VALUES
(1, 2, 1, '2017-01-01'),
(2, 2, 2, '2017-01-01'),
(3, 3, 10, '2017-01-01'),
(4, 3, 11, '2017-01-01'),
(5, 3, 12, '2017-01-01');


INSERT INTO client_coupon (id, client_id, coupon_id)
VALUES
(1, 3, 1);
