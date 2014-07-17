create an Apache Derby database called "ba"

using ij, like so :

 connect 'jdbc:derby:ba;create=true';

then :



CREATE SEQUENCE BA_SEQ AS INT MINVALUE 1000 MAXVALUE 999999 CYCLE;
CREATE TABLE persons(PersonID int,lastName varchar(255),firstName varchar(255),address varchar(255),city varchar(255), PRIMARY KEY (PersonID));
CREATE TABLE bookings (personID int,bookingId int,flightnum varchar(255),tickets int,cabin  varchar(255), PRIMARY KEY (bookingId));
CREATE TABLE flights(flightnum varchar(255),origin varchar(255),destination varchar(255),PRIMARY KEY (flightnum));
CREATE TABLE loyalty(PersonID int, tier varchar(255), points int);
CREATE TABLE flights(flightnum varchar(255),origin varchar(255),destination varchar(255),PRIMARY KEY (flightnum));