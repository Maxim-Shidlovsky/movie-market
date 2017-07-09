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


INSERT INTO coupon (coupon_id, code, discount)
VALUES
(1, '123', 20);


INSERT INTO client (client_id, login, password)
VALUES
(1, 'user', '111');