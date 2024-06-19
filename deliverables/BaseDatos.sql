-- Database: challenge

DROP DATABASE IF EXISTS challenge;

CREATE DATABASE challenge;

CREATE TABLE person (
	person_id BIGSERIAL NOT NULl,
	person_name varchar(50) NOT NULL,
	gender varchar(50) NOT NULL,
	age INT,
	identification varchar(13),
	address varchar(100),
	phone varchar(10),
	PRIMARY KEY(person_id)
);

CREATE TABLE client (	
	client_id BIGSERIAL NOT NULL,
	person_id BIGSERIAL NOT NULL,
	password varchar(100) NOT NULL,
	status BOOLEAN NOT NULL,
	PRIMARY KEY(client_id),
	FOREIGN KEY (person_id) REFERENCES person(person_id)
);

CREATE TABLE account (
	id BIGSERIAL NOT NULL,
	client_id BIGSERIAL NOT NULL,
	account_number varchar(10),
    account_type VARCHAR(50) NOT NULL,
    initial_balance DECIMAL(15, 2) NOT NULL,
    status BOOLEAN NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY (client_id) REFERENCES client(client_id)
);

CREATE TABLE movement (
	id BIGSERIAL NOT NULL,
	account_id BIGSERIAL NOT NULL,
    date DATE NOT NULL,
    movement_type VARCHAR(50) NOT NULL,
    amount DECIMAL(15, 2) NOT NULL,
    balance DECIMAL(15, 2) NOT NULL,
	status BOOLEAN NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY (account_id) REFERENCES account(id)
);




    