CREATE DATABASE IF NOT EXISTS moviedb;
USE moviedb;


DROP TABLE IF EXISTS employees;
create table employees( 
	email varchar(50) primary key, 
	password varchar(20) not null,
	fullname varchar(100)
);


DROP TABLE IF EXISTS movies;
CREATE TABLE movies (
    id varchar(10) PRIMARY KEY,
    title varchar(100) NOT NULL,
    year int(11) NOT NULL,
    director varchar(100) NOT NULL
);

DROP TABLE IF EXISTS stars;
CREATE TABLE stars (
    id varchar(10) PRIMARY KEY,
    name varchar(100) NOT NULL,
    birthYear int
);

DROP TABLE IF EXISTS stars_in_movies;
CREATE TABLE stars_in_movies (
    starId varchar(10) NOT NULL REFERENCES stars(id),
    movieId varchar(10) NOT NULL REFERENCES movies(id)
);


DROP TABLE IF EXISTS genres;
CREATE TABLE genres (
    id int PRIMARY KEY AUTO_INCREMENT,
    name varchar(32) NOT NULL
);

DROP TABLE IF EXISTS genres_in_movies;
CREATE TABLE genres_in_movies (
    genreId int NOT NULL REFERENCES genres(id),
    movieId varchar(10) NOT NULL REFERENCES movies(id)
);

DROP TABLE IF EXISTS customers;
CREATE TABLE customers (
    id int PRIMARY KEY AUTO_INCREMENT,
    firstName varchar(50) NOT NULL, 
    lastName varchar(50) NOT NULL,
    ccId varchar(20) NOT NULL REFERENCES creditcards(id),
    address varchar(200) NOT NULL,
    email varchar(50) NOT NULL,
    password varchar(20) NOT NULL
);

DROP TABLE IF EXISTS sales;
CREATE TABLE sales (
    id int PRIMARY KEY AUTO_INCREMENT,
    customerId int NOT NULL REFERENCES customers(id),
    movieId varchar(10) NOT NULL REFERENCES movies(id),
    saleDate DATE NOT NULL
);


DROP TABLE IF EXISTS creditcards;
CREATE TABLE creditcards (
    id varchar(20) PRIMARY KEY,
    firstName varchar(50) NOT NULL, 
    lastName varchar(50) NOT NULL,
    expiration DATE NOT NULL
);


DROP TABLE IF EXISTS ratings;
CREATE TABLE ratings (
    movieId varchar(10) NOT NULL REFERENCES movies(id),
    rating float NOT NULL,
    numVotes int NOT NULL
);