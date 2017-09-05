CREATE DATABASE IF NOT EXISTS timBuilder;
USE timBuilder;

CREATE TABLE RSU(
	rsu_id INT NOT NULL AUTO_INCREMENT,
	url VARCHAR(255) NOT NULL,
	rsu_username VARCHAR(255) NOT NULL,
	rsu_password VARCHAR(255) NOT NULL,
	latitude DECIMAL(11, 8) NOT NULL,
	longitude DECIMAL(11, 8) NOT NULL,
	milepost DECIMAL(38, 8),
	PRIMARY KEY (rsu_id)
);

CREATE TABLE MILEPOST(
	milepost_id INT NOT NULL AUTO_INCREMENT,
	route VARCHAR(255),
	milepost DECIMAL(38, 8),
	direction VARCHAR(255),
	latitude DECIMAL(11, 8),
	longitude DECIMAL(11, 8),
	elevation_ft DECIMAL(38, 8),
	bearing DECIMAL(38,8),	
	PRIMARY KEY (milepost_id)
);

CREATE TABLE CATEGORY(
	category_id INT NOT NULL AUTO_INCREMENT,
	category VARCHAR(255),	
	PRIMARY KEY (category_id)
);

CREATE TABLE ITIS_CODE(
	itis_code_id INT NOT NULL AUTO_INCREMENT,
	itis_code INT,
	description VARCHAR(255),
	category_id INT NOT NULL,
	PRIMARY KEY (itis_code_id),
	CONSTRAINT fk_category FOREIGN KEY (category_id) 	
	REFERENCES CATEGORY(category_id)
);

load data local infile '/var/lib/mysql-files/milepost.csv' into table MILEPOST columns terminated by ';' OPTIONALLY enclosed by '"' escaped by '"' (route, milepost, direction, latitude, longitude, elevation_ft, bearing);
load data local infile '/var/lib/mysql-files/rsu.csv' into table RSU columns terminated by ';' OPTIONALLY enclosed by '"' escaped by '"' (url, rsu_username, rsu_password, latitude, longitude);
load data local infile '/var/lib/mysql-files/category.csv' into table CATEGORY (category);
load data local infile '/var/lib/mysql-files/itiscode.csv' into table ITIS_CODE columns terminated by ';' OPTIONALLY enclosed by '"' escaped by '"' (description, category_id, itis_code);