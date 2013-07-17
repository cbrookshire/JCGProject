CREATE DATABASE IF NOT EXISTS JCGroup;
USE JCGroup;

DROP TABLE IF EXISTS Reservation;
DROP TABLE IF EXISTS Maintanence;
DROP TABLE IF EXISTS Vehicle;
DROP TABLE IF EXISTS Employee;
DROP TABLE IF EXISTS Franchise;
DROP TABLE IF EXISTS Airport;
DROP TABLE IF EXISTS Customer;
DROP TABLE IF EXISTS Membership;

CREATE TABLE IF NOT EXISTS Airport
(AirportID int Auto_Increment,
 AirportName varchar(40),
 Address varchar(30),
 City varchar(40),
 State varchar(15),
 Zip int,
PRIMARY KEY (AirportID));

CREATE TABLE IF NOT EXISTS Franchise
(FranchiseNumber int Auto_Increment,
 Address varchar(40),
 City varchar(40),
 State varchar(15),
 Zip int(5),
 Phone varchar(12) Unique,
 Email varchar(40) Unique,
 AirportID int(2),
  PRIMARY KEY (FranchiseNumber),
  FOREIGN KEY (AirportID)
   REFERENCES Airport (AirportID));

CREATE TABLE IF NOT EXISTS Employee
( EmployeeID int AUTO_INCREMENT,
  Fname varchar(20),
  Surname varchar(20),
  Address varchar(40),
  City varchar(40),
  State varchar(15),
  Zip int,
  Phone varchar(12) UNIQUE NOT NULL,
  Email varchar(40) UNIQUE NOT NULL,
  EmpType int NOT NULL,
  FranchiseNumber int,
  Username varchar(8),
  FirstLog ENUM('N', 'Y') DEFAULT 'Y',
  PRIMARY KEY (EmployeeID),
   FOREIGN KEY (FranchiseNumber)
    REFERENCES Franchise (FranchiseNumber)
) Auto_Increment = 9000;

CREATE TABLE IF NOT EXISTS Vehicle
( VIndex int AUTO_INCREMENT,
  VehicleID int,
  VIN varchar(20) UNIQUE NOT NULL,
  Make varchar(16) Not Null,
  Model varchar(30) Not Null,
  Year int Not Null,
  Millage int(6) Not Null,
  Capacity int Not Null,
  VCondition varchar(15) Not NUll,
  Tablet varchar(20) Not Null,
  RentalPrice decimal(5, 2) Not null,
  FranchiseNumber int,
  PRIMARY KEY (VehicleID, VIndex),
  KEY (VIndex),
  FOREIGN KEY (FranchiseNumber)
    REFERENCES Franchise (FranchiseNumber) );

CREATE TABLE IF NOT EXISTS Maintanence
( MaintIndex int AUTO_INCREMENT,
  ServiceNumber varchar(10) UNIQUE,
  VehicleID int,
  ServiceType varchar(20),
  ServiceCost double(4,2),
  PRIMARY KEY (ServiceNumber),
  KEY (MaintIndex),
  FOREIGN KEY (VehicleID)
    REFERENCES Vehicle (VehicleID) );

CREATE TABLE IF NOT EXISTS Membership
( MemberID varchar(10),
  Discount double(3, 2),
  MinAmount int,
  PRIMARY KEY (MemberID) );


CREATE TABLE IF NOT EXISTS Customer
( CustomerID int AUTO_INCREMENT,
  Fname varchar(20),
  Surname varchar(20),
  Address varchar(30),
  City varchar(40),
  State varchar(15),
  Zip int,
  Phone varchar(12) UNIQUE NOT NULL,
  Email varchar(40) UNIQUE NOT NULL,
  ReservationCount int DEFAULT 0,
  MemberID varchar(10) DEFAULT 'Bronze',
  UserName varchar(30),
  CType int DEFAULT 99,
  FirstLog ENUM('N', 'Y') DEFAULT 'Y',
  PRIMARY KEY (CustomerID),
  FOREIGN KEY (MemberID)
    REFERENCES Membership (MemberID) ) Auto_Increment = 1000;

CREATE TABLE IF NOT EXISTS Reservation
( ReservationNumber int AUTO_INCREMENT,
  FranchiseNumber int,
  VehicleID int,
  CustomerID int,
  Price double(4, 2),
  Statuse ENUM('Open', 'Closed') DEFAULT 'Open',
  drop_pick ENUM('Drop', 'Pickup'),
  Comment varchar(300),
  FlightNumber int NOT NULL,
  Airline varchar(20),
  FlightTime double(4, 2),
  PickUpTime double(4, 2),
  DropOffTime double(4, 2),
  `Date` varchar(11),
  AltAddress varchar(40),
  AltCity varchar(40),
  AltState varchar(15),
  AltZip int,
  PRIMARY KEY (ReservationNumber),
  FOREIGN KEY (VehicleID) REFERENCES Vehicle (VehicleID),
  FOREIGN KEY (FranchiseNumber) REFERENCES Franchise(FranchiseNumber),
  FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID))Auto_Increment = 1000;
	
INSERT INTO Airport (AirportName, Address, City, State, Zip)
 VALUES ('Hartsfield-Jackson', '6000 North Terminal Parkway', 'Atlanta', 'Georgia', 30320 );

INSERT INTO Airport (AirportName, Address, City, State, Zip)
  VALUES ('Los Angeles International', '1 World Way', 'Los Angeles', 'California', 90045 );

INSERT INTO Airport (AirportName, Address, City, State, Zip)
  VALUES ('OHare', '10000 West OHare Avenue', 'Chicago', 'Illinois', 60666 );

INSERT INTO Franchise (Address, City, State, Zip, Phone, Email)
  VALUES ('1092 Flatbush Ave', 'Brooklyn', 'New York', 11226, '718-576-2336', 'jcgBrooklyn@gmail.com');

INSERT INTO Franchise (Address, City, State, Zip, Phone, Email, AirportID)
  VALUES ('2312 Peachtree St', 'Atlanta', 'Georgia', 31318, '678-123-4567', 'jcgAtlanta@gmail.com', 1 );

INSERT INTO Franchise (Address, City, State, Zip, Phone, Email, AirportID)
  VALUES ('4060 Peachtree Ave.', 'Buckhead', 'Georgia', 30340, '404-987-6543', 'jcgBuckhead@gmail.com', 1 );
  
INSERT INTO Franchise (Address, City, State, Zip, Phone, Email, AirportID)
  VALUES ('123 Venture BLVD', 'Hollywood', 'California', 12345, '619-345-0987', 'jcgHoolywood@gmail.com', 2 );

INSERT INTO Franchise (Address, City, State, Zip, Phone, Email, AirportID)
  VALUES ('687 Hills Way', 'Los Angelas', 'California', 23456, '603-343-0909', 'jcgLosAngelas@gmail.com', 2 );
 
INSERT INTO Franchise (Address, City, State, Zip, Phone, Email, AirportID)
  VALUES ('934 Windy St', 'Dolton', 'Illinois', 45003, '302-454-8232', 'jcgDolton@gmail.com', 3 );

INSERT INTO Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username, FirstLog)
 VALUES ('James', 'Cheeves', '139 Flatbush Ave', 'Brooklyn', 'New York', 11217, '718-230-7703', 'JCheeves@gmail.com', 1, 1, 'JC7703', 'N');
 
CREATE USER 'JC7703'@'localhost' IDENTIFIED BY 'password';
GRANT INSERT, UPDATE, DELETE, SELECT ON JCGroup.Airport TO 'JC7703'@'localhost';
GRANT INSERT, UPDATE, DELETE, SELECT ON JCGroup.Franchise TO 'JC7703'@'localhost';
GRANT INSERT, UPDATE, DELETE, SELECT ON JCGroup.Employee TO 'JC7703'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Membership TO 'JC7703'@'localhost';
GRANT CREATE USER ON *.* TO 'JC7703'@'localhost' WITH GRANT OPTION;
FLUSH PRIVILEGES;

INSERT INTO Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username, FirstLog)
 VALUES ('Elwyn', 'Nick', '1622 Bedford Avenue', 'Brooklyn', 'New York', 11225, '347-770-8853', 'ElwynN@gmail.com', 98, 1, 'JCGAdmin', 'N');

INSERT INTO Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username, FirstLog)
 VALUES('Henry', 'Henderson', '599 Sibly Blvd.', 'Dolton', 'Illinois', 60419, '708-841-7336', 'HenryH@gmail.com', 2, 6, 'HH7336', 'N' );
 
CREATE USER 'HH7336'@'localhost' IDENTIFIED BY 'hulkhogan';
GRANT SELECT ON JCGroup.Airport to 'HH7336'@'localhost';
GRANT SELECT ON JCGroup.Franchise TO 'HH7336'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE ON JCGroup.Vehicle TO 'HH7336'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE ON JCGroup.Maintanence TO 'HH7336'@'localhost';
GRANT INSERT, DELETE ON JCGroup.Employee TO 'HH7336'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Employee TO 'HH7336'@'localhost';
GRANT INSERT, DELETE ON JCGroup.Customer TO 'HH7336'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Customer TO 'HH7336'@'localhost' WITH GRANT OPTION;
GRANT SELECT ON JCGroup.Membership TO 'HH7336'@'localhost' WITH GRANT OPTION;
GRANT SELECT, UPDATE ON JCGroup.Reservation TO 'HH7336'@'localhost' WITH GRANT OPTION;
GRANT INSERT, DELETE ON JCGroup.Reservation TO 'HH7336'@'localhost';
GRANT CREATE USER ON *.* to 'HH7336'@'localhost';
FLUSH PRIVILEGES;

insert into Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES('Jennifer', 'Smith', '6775 Santa Monica Blvd.', 'Los Angeles', 'California', 90038, '323-957-9553', 'JSmith@gmail.com', 2, 5, 'JS9553' );
 
CREATE USER 'JS9553'@'localhost' IDENTIFIED BY 'JS9553';
GRANT SELECT ON JCGroup.Airport to 'JS9553'@'localhost';
GRANT SELECT ON JCGroup.Franchise TO 'JS9553'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE ON JCGroup.Vehicle TO 'JS9553'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE ON JCGroup.Maintanence TO 'JS9553'@'localhost';
GRANT INSERT, DELETE ON JCGroup.Employee TO 'JS9553'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Employee TO 'JS9553'@'localhost';
GRANT INSERT, DELETE ON JCGroup.Customer TO 'JS9553'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Customer TO 'JS9553'@'localhost' WITH GRANT OPTION;
GRANT SELECT ON JCGroup.Membership TO 'JS9553'@'localhost' WITH GRANT OPTION;
GRANT SELECT, UPDATE ON JCGroup.Reservation TO 'JS9553'@'localhost' WITH GRANT OPTION;
GRANT INSERT, DELETE ON JCGroup.Reservation TO 'JS9553'@'localhost';
GRANT CREATE USER ON *.* to 'JS9553'@'localhost';
FLUSH PRIVILEGES;

insert into Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES('Frank', 'Keisler', '6660 W Sunset Blvd.', 'Hollywood', 'California', 90028, '323-962-7823', 'FrankK@gmail.com', 2, 4, 'FK7823' );
 
CREATE USER 'FK7823'@'localhost' IDENTIFIED BY 'FK7823';
GRANT SELECT ON JCGroup.Airport to 'FK7823'@'localhost';
GRANT SELECT ON JCGroup.Franchise TO 'FK7823'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE ON JCGroup.Vehicle TO 'FK7823'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE ON JCGroup.Maintanence TO 'FK7823'@'localhost';
GRANT INSERT, DELETE ON JCGroup.Employee TO 'FK7823'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Employee TO 'FK7823'@'localhost';
GRANT INSERT, DELETE ON JCGroup.Customer TO 'FK7823'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Customer TO 'FK7823'@'localhost' WITH GRANT OPTION;
GRANT SELECT ON JCGroup.Membership TO 'FK7823'@'localhost' WITH GRANT OPTION;
GRANT SELECT, UPDATE ON JCGroup.Reservation TO 'FK7823'@'localhost' WITH GRANT OPTION;
GRANT INSERT, DELETE ON JCGroup.Reservation TO 'FK7823'@'localhost';
GRANT CREATE USER ON *.* to 'FK7823'@'localhost';
FLUSH PRIVILEGES;

insert into Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES('Ararat', 'Arsenio', '7431 N Point Pky', 'Alpharetta', 'Georgia', 30022, '770-518-1787', 'Arsenio_A@gmail.com', 2, 3, 'AA1787');
 
CREATE USER 'AA1787'@'localhost' IDENTIFIED BY 'AA1787';
GRANT SELECT ON JCGroup.Airport to 'AA1787'@'localhost';
GRANT SELECT ON JCGroup.Franchise TO 'AA1787'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE ON JCGroup.Vehicle TO 'AA1787'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE ON JCGroup.Maintanence TO 'AA1787'@'localhost';
GRANT INSERT, DELETE ON JCGroup.Employee TO 'AA1787'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Employee TO 'AA1787'@'localhost';
GRANT INSERT, DELETE ON JCGroup.Customer TO 'AA1787'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Customer TO 'AA1787'@'localhost' WITH GRANT OPTION;
GRANT SELECT ON JCGroup.Membership TO 'AA1787'@'localhost' WITH GRANT OPTION;
GRANT SELECT, UPDATE ON JCGroup.Reservation TO 'AA1787'@'localhost' WITH GRANT OPTION;
GRANT INSERT, DELETE ON JCGroup.Reservation TO 'AA1787'@'localhost';
GRANT CREATE USER ON *.* to 'AA1787'@'localhost';
FLUSH PRIVILEGES;

insert into Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES('Nolan', 'Kelly', '50 Lower Alabama St', 'Atlanta', 'Georgia', 30303, '404-589-4647', 'NKelly@gmail.com', 2, 2, 'NK4647');
 
CREATE USER 'NK4647'@'localhost' IDENTIFIED BY 'NK4647';
GRANT SELECT ON JCGroup.Airport to 'NK4647'@'localhost';
GRANT SELECT ON JCGroup.Franchise TO 'NK4647'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE ON JCGroup.Vehicle TO 'NK4647'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE ON JCGroup.Maintanence TO 'NK4647'@'localhost';
GRANT INSERT, DELETE ON JCGroup.Employee TO 'NK4647'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Employee TO 'NK4647'@'localhost';
GRANT INSERT, DELETE ON JCGroup.Customer TO 'NK4647'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Customer TO 'NK4647'@'localhost' WITH GRANT OPTION;
GRANT SELECT ON JCGroup.Membership TO 'NK4647'@'localhost' WITH GRANT OPTION;
GRANT SELECT, UPDATE ON JCGroup.Reservation TO 'NK4647'@'localhost' WITH GRANT OPTION;
GRANT INSERT, DELETE ON JCGroup.Reservation TO 'NK4647'@'localhost';
GRANT CREATE USER ON *.* to 'NK4647'@'localhost';
FLUSH PRIVILEGES;

insert into Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES('Esmail', 'Osama', '68 Broad St NW', 'Atlanta', 'Geaorgia', 30303, '404-522-2336', 'EsmailO@gmail.com', 3, 2, 'EO2336');
 
CREATE USER 'EO2336'@'localhost' IDENTIFIED BY 'EO2336';
GRANT SELECT, UPDATE ON JCGroup.Customer to 'EO2336'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Employee to 'EO2336'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Reservation TO 'EO2336'@'localhost';
GRANT SELECT ON JCGroup.Membership TO 'EO2336'@'localhost';
FLUSH PRIVILEGES;

insert into Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username, FirstLog)
 VALUES('Whitaker', 'Carter', '1295 SOUTH LAKE RD', 'WARNER ROBINS', 'Georgia', 31088, '478-987-4632', 'CarterW@gmail.com', 3, 2, 'WC4632', 'N');
 
CREATE USER 'WC4632'@'localhost' IDENTIFIED BY 'wilder';
GRANT SELECT, UPDATE ON JCGroup.Customer to 'WC4632'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Employee to 'WC4632'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Reservation TO 'WC4632'@'localhost';
GRANT SELECT ON JCGroup.Membership TO 'WC4632'@'localhost';
FLUSH PRIVILEGES;

insert into Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES('Branson', 'Sacheverell', '2925 WATSON RD', 'WARNER ROBINS', 'Georgia', 31088, '478-953-0964', 'BransonS@gmail.com', 3, 2, 'BS0964');
 
CREATE USER 'BS0964'@'localhost' IDENTIFIED BY 'BS0964';
GRANT SELECT, UPDATE ON JCGroup.Customer to 'BS0964'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Employee to 'BS0964'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Reservation TO 'BS0964'@'localhost';
GRANT SELECT ON JCGroup.Membership TO 'BS0964'@'localhost';
FLUSH PRIVILEGES;

insert into Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES('Mackenzie', 'Kory', '2105 MOODY RD', 'WARNER ROBINS', 'Georgia', 31088, '478-929-0008', 'MKory@yahoo.com', 3, 2, 'MK0008');
 
CREATE USER 'MK0008'@'localhost' IDENTIFIED BY 'MK0008';
GRANT SELECT, UPDATE ON JCGroup.Customer to 'MK0008'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Employee to 'MK0008'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Reservation TO 'MK0008'@'localhost';
GRANT SELECT ON JCGroup.Membership TO 'MK0008'@'localhost';
FLUSH PRIVILEGES;

insert into Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES('Bedros', 'August', '183 Edgewood Ave', 'Atlanta', 'Georgia', 30303, '404-8286', 'BedrosA@gmail.com', 3, 2, 'BA8286');
 
CREATE USER 'BA8286'@'localhost' IDENTIFIED BY 'BA8286';
GRANT SELECT, UPDATE ON JCGroup.Customer to 'BA8286'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Employee to 'BA8286'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Reservation TO 'BA8286'@'localhost';
GRANT SELECT ON JCGroup.Membership TO 'BA8286'@'localhost';
FLUSH PRIVILEGES;

insert into Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES('Konstantyn', 'Kuba', '4418 Roswell Rd', 'Atlanta', 'Georgia', 30342, '404-250-1625', 'KubaK@gmail.com', 3, 3, 'KK1625');

CREATE USER 'KK1625'@'localhost' IDENTIFIED BY 'KK1625';
GRANT SELECT, UPDATE ON JCGroup.Customer to 'KK1625'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Employee to 'KK1625'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Reservation TO 'KK1625'@'localhost';
GRANT SELECT ON JCGroup.Membership TO 'KK1625'@'localhost';
FLUSH PRIVILEGES;

insert into Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES('Petya', 'Luka', '1825 Piedmont Rd', 'Atlanta', 'Georgia', 30324, '404-876-2246', 'PetyaL@gmail.com', 3, 3, 'PL2246');
 
CREATE USER 'PL2246'@'localhost' IDENTIFIED BY 'PL2246';
GRANT SELECT, UPDATE ON JCGroup.Customer to 'PL2246'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Employee to 'PL2246'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Reservation TO 'PL2246'@'localhost';
GRANT SELECT ON JCGroup.Membership TO 'PL2246'@'localhost';
FLUSH PRIVILEGES;

insert into Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES('Benjamin', 'Patton', '2957 N DRUID HILLS RD', 'ATLANTA', 'Georgia', 30329, '404-329-9385', 'PattonB@gmail.com', 3, 3, 'BP9385');
 
CREATE USER 'BP9385'@'localhost' IDENTIFIED BY 'BP9385';
GRANT SELECT, UPDATE ON JCGroup.Customer to 'BP9385'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Employee to 'BP9385'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Reservation TO 'BP9385'@'localhost';
GRANT SELECT ON JCGroup.Membership TO 'BP9385'@'localhost';
FLUSH PRIVILEGES;

insert into Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES('Reilly', 'Mackenzie', '2050 LAWRENCEVILLE HWY', 'DECATUR', 'Georgia', 30033, '404-320-6919', 'ReillyM@aol.com', 3, 3, 'RM6919');
 
CREATE USER 'RM6919'@'localhost' IDENTIFIED BY 'RM6919';
GRANT SELECT, UPDATE ON JCGroup.Customer to 'RM6919'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Employee to 'RM6919'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Reservation TO 'RM6919'@'localhost';
GRANT SELECT ON JCGroup.Membership TO 'RM6919'@'localhost';
FLUSH PRIVILEGES;

insert into Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES('Nnamdi', 'Ladislao', '1705 SCOTT BLVD', 'DECATUR', 'Georgia', 30033, '404-633-5396', 'NnamdiL@gmail.com', 3, 3, 'NL5396');
 
CREATE USER 'NL5396'@'localhost' IDENTIFIED BY 'NL5396';
GRANT SELECT, UPDATE ON JCGroup.Customer to 'NL5396'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Employee to 'NL5396'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Reservation TO 'NL5396'@'localhost';
GRANT SELECT ON JCGroup.Membership TO 'NL5396'@'localhost';
FLUSH PRIVILEGES;

insert into Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES('Aleksander', 'Vincenc', '6051 Hollywood Blvd', 'Hollywood', 'California', 90028, '323-469-9480', 'VincencA@gmail.com', 3, 4, 'AV9480');

CREATE USER 'AV9480'@'localhost' IDENTIFIED BY 'AV9480';
GRANT SELECT, UPDATE ON JCGroup.Customer to 'AV9480'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Employee to 'AV9480'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Reservation TO 'AV9480'@'localhost';
GRANT SELECT ON JCGroup.Membership TO 'AV9480'@'localhost';
FLUSH PRIVILEGES;

insert into Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES('Mehrdad', 'Gevorg', '5537 Sunset Blvd.', 'Hollywood', 'California', 90028, '323-871-1090', 'GevorgM@gmail.com', 3, 4, 'MG1090');

CREATE USER 'MG1090'@'localhost' IDENTIFIED BY 'MG1090';
GRANT SELECT, UPDATE ON JCGroup.Customer to 'MG1090'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Employee to 'MG1090'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Reservation TO 'MG1090'@'localhost';
GRANT SELECT ON JCGroup.Membership TO 'MG1090'@'localhost';
FLUSH PRIVILEGES;

insert into Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES('Klemens', 'Salvatore', '4314 SOUTH STREET', 'LAKEWOOD', 'California', 90712, '562-531-0345', 'KlemensS@wow.com', 3, 4, 'KS0345');
 
CREATE USER 'KS0345'@'localhost' IDENTIFIED BY 'KS0345';
GRANT SELECT, UPDATE ON JCGroup.Customer to 'KS0345'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Employee to 'KS0345'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Reservation TO 'KS0345'@'localhost';
GRANT SELECT ON JCGroup.Membership TO 'KS0345'@'localhost';
FLUSH PRIVILEGES;

insert into Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES('Gennaro', 'Eustachio', '17420 SOUTH WESTERN', 'GARDENA', 'California', 90248, '310-327-7707', 'GennaroE@aol.com', 3, 4, 'GE7707');
 
CREATE USER 'GE7707'@'localhost' IDENTIFIED BY 'GE7707';
GRANT SELECT, UPDATE ON JCGroup.Customer to 'GE7707'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Employee to 'GE7707'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Reservation TO 'GE7707'@'localhost';
GRANT SELECT ON JCGroup.Membership TO 'GE7707'@'localhost';
FLUSH PRIVILEGES;

insert into Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES('Raimondo', 'Rocco', '14305 LAKEWOOD', 'DOWNEY', 'California', 90242, '562-630-0875', 'RoccoR@funtime.com', 3, 4, 'RR0875');
 
CREATE USER 'RR0875'@'localhost' IDENTIFIED BY 'RR0875';
GRANT SELECT, UPDATE ON JCGroup.Customer to 'RR0875'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Employee to 'RR0875'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Reservation TO 'RR0875'@'localhost';
GRANT SELECT ON JCGroup.Membership TO 'RR0875'@'localhost';
FLUSH PRIVILEGES;

insert into Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES('Dorotheos', 'Vasilis', '7040 Sunset Blvd', 'Los Angeles', 'California', 90068, '323-851-5501', 'VasilisD@gmail.com', 3, 5, 'DV5501');

CREATE USER 'DV5501'@'localhost' IDENTIFIED BY 'DV5501';
GRANT SELECT, UPDATE ON JCGroup.Customer to 'DV5501'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Employee to 'DV5501'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Reservation TO 'DV5501'@'localhost';
GRANT SELECT ON JCGroup.Membership TO 'DV5501'@'localhost';
FLUSH PRIVILEGES;

insert into Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES('Sevan', 'Henrik', '1075 N Western Ave', 'Los Angeles', 'California', 90029, '323-856-0990', 'HenrikS@gmail.com', 3, 5, 'SH0990');

CREATE USER 'SH0990'@'localhost' IDENTIFIED BY 'SH0990';
GRANT SELECT, UPDATE ON JCGroup.Customer to 'SH0990'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Employee to 'SH0990'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Reservation TO 'SH0990'@'localhost';
GRANT SELECT ON JCGroup.Membership TO 'SH0990'@'localhost';
FLUSH PRIVILEGES;

insert into Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES('Colombo', 'Ottaviano', '7135 SUNSET BLVD', 'LOS ANGELES', 'California', 90046, '323-876-1925', 'Colombo@gmail.com', 3, 5, 'CO1925');
 
CREATE USER 'CO1925'@'localhost' IDENTIFIED BY 'CO1925';
GRANT SELECT, UPDATE ON JCGroup.Customer to 'CO1925'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Employee to 'CO1925'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Reservation TO 'CO1925'@'localhost';
GRANT SELECT ON JCGroup.Membership TO 'CO1925'@'localhost';
FLUSH PRIVILEGES;

insert into Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES('Giancarlo', 'Xuan', '1305 N. VERMONT AVE.', 'HOLLYWOOD', 'California', 90027, '323-663-7387', 'G_Xuan@gmail.com', 3, 5, 'GX7387');
 
CREATE USER 'GX7387'@'localhost' IDENTIFIED BY 'GX7387';
GRANT SELECT, UPDATE ON JCGroup.Customer to 'GX7387'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Employee to 'GX7387'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Reservation TO 'GX7387'@'localhost';
GRANT SELECT ON JCGroup.Membership TO 'GX7387'@'localhost';
FLUSH PRIVILEGES;

insert into Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES('Celino', 'Aroldo', '3019 SOUTH HOOVER', 'LOS ANGELES', 'California', 90007, '213-746-8072', 'CelinoA@hotmail.com', 3, 5, 'CA8072');
 
CREATE USER 'CA8072'@'localhost' IDENTIFIED BY 'CA8072';
GRANT SELECT, UPDATE ON JCGroup.Customer to 'CA8072'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Employee to 'CA8072'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Reservation TO 'CA8072'@'localhost';
GRANT SELECT ON JCGroup.Membership TO 'CA8072'@'localhost';
FLUSH PRIVILEGES;

insert into Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES('Krikor', 'Rudolf', '700 E. Tri-State', ' S Holland', 'Illinois', 60473, '224-277-4356', 'KrikorR@gmail.com', 3, 6, 'KR4356');

CREATE USER 'KR4356'@'localhost' IDENTIFIED BY 'KR4356';
GRANT SELECT, UPDATE ON JCGroup.Customer to 'KR4356'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Employee to 'KR4356'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Reservation TO 'KR4356'@'localhost';
GRANT SELECT ON JCGroup.Membership TO 'KR4356'@'localhost';
FLUSH PRIVILEGES;

insert into Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES('Voski', 'Aleksandr', '578 Torrence Ave', 'Calumet City', 'Illinois', 60409, '708-832-0283', 'VoskiA@gmail.com', 3, 6, 'VA0283');

CREATE USER 'VA0283'@'localhost' IDENTIFIED BY 'VA0283';
GRANT SELECT, UPDATE ON JCGroup.Customer to 'VA0283'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Employee to 'VA0283'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Reservation TO 'VA0283'@'localhost';
GRANT SELECT ON JCGroup.Membership TO 'VA0283'@'localhost';
FLUSH PRIVILEGES;

insert into Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES('Patrizio', 'Modesto', '12723 S ASHLAND AVE', 'CALUMET PARK', 'Illinois', 60827, '708-389-1646', 'PModesto@yahoo.com', 3, 6, 'PM1646');
 
CREATE USER 'PM1646'@'localhost' IDENTIFIED BY 'PM1646';
GRANT SELECT, UPDATE ON JCGroup.Customer to 'PM1646'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Employee to 'PM1646'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Reservation TO 'PM1646'@'localhost';
GRANT SELECT ON JCGroup.Membership TO 'PM1646'@'localhost';
FLUSH PRIVILEGES;

insert into Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES('Vince', 'Hayk', '170 WEST ST', 'S.HOLLAND', 'Illinois', 60473, '708-596-3318', 'VHayk@gmail.com', 3, 6, 'VK3318');
 
CREATE USER 'VK3318'@'localhost' IDENTIFIED BY 'VK3318';
GRANT SELECT, UPDATE ON JCGroup.Customer to 'VK3318'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Employee to 'VK3318'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Reservation TO 'VK3318'@'localhost';
GRANT SELECT ON JCGroup.Membership TO 'VK3318'@'localhost';
FLUSH PRIVILEGES;

insert into Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES('Conrad', 'Wenzeslaus', '1200 E. SIBLEY BLVD', 'DOLTON', 'Illinois', 60419, '708-849-9555', 'ConradW@hotmail.com', 3, 6, 'CW9555');
 
CREATE USER 'CW9555'@'localhost' IDENTIFIED BY 'CW9555';
GRANT SELECT, UPDATE ON JCGroup.Customer to 'CW9555'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Employee to 'CW9555'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Reservation TO 'CW9555'@'localhost';
GRANT SELECT ON JCGroup.Membership TO 'CW9555'@'localhost';
FLUSH PRIVILEGES;

insert into Membership(MemberID, Discount, MinAmount)
values('Diamond', 000.85, 50), ('Gold', 000.90, 35), ('Silver', 000.95, 20), ('Bronze' , 1.00, 0);

INSERT INTO Customer(Fname, Surname, Address, City, State, Zip, Phone, Email, ReservationCount, MemberID, Username, FirstLog)
  VALUES ('Edur', 'Arkaitz', '750 Vine St', 'Los Angelas', 'California', 90038, '323-465-1025', 'EArkaitz@aol.com', 30, 'Silver', 'EArkaitz', 'N');
 
 CREATE USER 'EArkaitz'@'localhost' IDENTIFIED BY 'born2bwild';
 GRANT INSERT, UPDATE, DELETE, SELECT ON JCGroup.Customer TO 'EArkaitz'@'localhost';
 GRANT SELECT ON JCGroup.Vehicle TO 'EArkaitz'@'localhost';
 FLUSH PRIVILEGES;

INSERT INTO Customer(Fname, Surname, Address, City, State, Zip, Phone, Email, ReservationCount, MemberID, Username, FirstLog)
  VALUES ('Noga', 'Daniel', '7079 Sunset Blvd', 'Hollywood', 'California', 90028, '323-469-2587', 'NogaD@hotmail.com', 3, 'Bronze', 'NogaD', 'N');
 
 CREATE USER 'NogaD'@'localhost' IDENTIFIED BY 'lonewolf';
 GRANT INSERT, UPDATE, DELETE, SELECT ON JCGroup.Customer TO 'NogaD'@'localhost';
 GRANT SELECT ON JCGroup.Vehicle TO 'NogaD'@'localhost';
 FLUSH PRIVILEGES;

INSERT INTO Customer(Fname, Surname, Address, City, State, Zip, Phone, Email, ReservationCount, MemberID, Username, FirstLog)
  VALUES ('Jean', 'Baudouin', '199 Northside Drive', 'Atlanta', 'Georgia', 30313, '404-523-3100', 'JeanB@yahoo.com', 55, 'Gold', 'JeanB', 'N');
 
CREATE USER 'JeanB'@'localhost' IDENTIFIED BY 'firefly';
GRANT INSERT, UPDATE, DELETE, SELECT ON JCGroup.Customer TO 'JeanB'@'localhost';
GRANT SELECT ON JCGroup.Vehicle TO 'JeanB'@'localhost';
FLUSH PRIVILEGES;
 
INSERT INTO Vehicle (VehicleID, VIN, Make, Model, Year, Millage, Capacity, VCondition, Tablet, RentalPrice, FranchiseNumber)
 VALUES (2001, '1GNGS18Z4D0212001', 'Lincoln', 'Town Car', 2013, 13203, 3, 'Exellent', 'CDQE34PDZDWET', 040.00, 2),
        (7301, '1MWAW18Z3D4117301', 'Mercedes Benz', 'S550', 2013, 12003, 3, 'Exellent', 'FETL3PF3PSJEO', 055.00, 2),
        (4321, '1FEDS12F5C6344321', 'Chevy', 'Suburban', 2012, 34023, 6, 'Great', 'HSOEN3PE0PFJ7', 075.00, 2),
        (1002, '1COAC12F3B5121002', 'Cadillac', 'Escalade ESV', 2011, 45023, 6, 'Exellent', 'JDOWJEOS98PSL', 085.00, 2),
        (3203, '1GNGT34E4A0013203', 'Lincoln', 'Stretch Limousine', 2010, 53002, 10, 'Great', 'JDOWJ349IEWOS', 115.00, 2),
        (5203, '1MWAW58S6C0005203', 'Mercedes', 'Sprinter Executive Van', 2012, 8302, 13, 'Exellent', 'SLKEOS03LDJW9', 130.00, 2);
        
INSERT INTO Vehicle (VehicleID, VIN, Make, Model, Year, Millage, Capacity, VCondition, Tablet, RentalPrice, FranchiseNumber)
 VALUES (2341, '1GNGS18Z4D0212341', 'Lincoln', 'Town Car', 2013, 17203, 3, 'Exellent', 'CDSLEO30SLW9S', 040.00, 3),
        (7341, '1MWAW18Z3D4117341', 'Mercedes Benz', 'S550', 2013, 22003, 3, 'Exellent', 'FETL40SJ30ND3', 055.00, 3),
        (4781, '1FEDS12F5C6344781', 'Chevy', 'Suburban', 2012, 36023, 6, 'Great', 'HSOENK302KSIS', 075.00, 3),
        (1212, '1COAC12F3B5121212', 'Cadillac', 'Escalade ESV', 2011, 42023, 6, 'Exellent', 'JDNSNWNAOW93J', 085.00, 3),
        (3003, '1GNGT34E4A0013003', 'Lincoln', 'Stretch Limousine', 2010, 57002, 10, 'Great', 'JDOWKSOW03SJW', 115.00, 3),
        (5213, '1MWAW58S6C0005213', 'Mercedes', 'Sprinter Executive Van', 2012, 8532, 13, 'Exellent', 'SLLLSOWNFSW93', 130.00, 3);

INSERT INTO Vehicle (VehicleID, VIN, Make, Model, Year, Millage, Capacity, VCondition, Tablet, RentalPrice, FranchiseNumber)
 VALUES (2022, '1GNGS18Z4D0212022', 'Lincoln', 'Town Car', 2013, 13203, 3, 'Exellent', 'PEGI94SOE2ISW', 040.00, 4),
        (7001, '1MWAW18Z3D4117001', 'Mercedes Benz', 'S550', 2013, 12003, 3, 'Exellent', 'QPSLXI40SOE3R', 055.00, 4),
        (4902, '1FEDS12F5C6344902', 'Chevy', 'Suburban', 2012, 34023, 6, 'Great', 'LSOEND3IS08AW4', 075.00, 4),
        (1086, '1COAC12F3B5121086', 'Cadillac', 'Escalade ESV', 2011, 45023, 6, 'Exellent', 'ZLAOW9DSIW2U2', 085.00, 4),
        (3703, '1GNGT34E4A0013703', 'Lincoln', 'Stretch Limousine', 2010, 53002, 10, 'Great', 'FHREE04IID0D9', 115.00, 4),
        (5273, '1MWAW58S6C0005273', 'Mercedes', 'Sprinter Executive Van', 2012, 8302, 13, 'Exellent', 'LSKWE30LSJEO0', 130.00, 4);

INSERT INTO Vehicle (VehicleID, VIN, Make, Model, Year, Millage, Capacity, VCondition, Tablet, RentalPrice, FranchiseNumber)
 VALUES (2932, '1GNGS18Z4D0212932', 'Lincoln', 'Town Car', 2013, 13203, 3, 'Exellent', 'OPWD30PSIWEWO', 40.00, 5),
        (7022, '1MWAW18Z3D4117022', 'Mercedes Benz', 'S550', 2013, 12003, 3, 'Exellent', 'MSN2IQWAGTUE9', 55.00, 5),
        (4823, '1FEDS12F5C6344823', 'Chevy', 'Suburban', 2012, 34023, 6, 'Great', 'JSKWIF839JSHW', 75.00, 5),
        (1593, '1COAC12F3B5121593', 'Cadillac', 'Escalade ESV', 2011, 45023, 6, 'Exellent', 'NSKWOCPPQLXMJ', 85.00, 5),
        (3590, '1GNGT34E4A0013590', 'Lincoln', 'Stretch Limousine', 2010, 53002, 10, 'Great', 'SDKJWISJDHEND', 115.00, 5),
        (5310, '1MWAW58S6C0005310', 'Mercedes', 'Sprinter Executive Van', 2012, 8302, 13, 'Exellent', 'LSSIOWID09SIO', 130.00, 5);
        
INSERT INTO Vehicle (VehicleID, VIN, Make, Model, Year, Millage, Capacity, VCondition, Tablet, RentalPrice, FranchiseNumber)
 VALUES (2734, '1GNGS18Z4D0212734', 'Lincoln', 'Town Car', 2013, 13203, 3, 'Exellent', 'TRIU39UIWJSOP', 40.00, 6),
        (7596, '1MWAW18Z3D4117596', 'Mercedes Benz', 'S550', 2013, 12003, 3, 'Exellent', 'HUE93OW0WOQUW', 55.00, 6),
        (4814, '1FEDS12F5C6344814', 'Chevy', 'Suburban', 2012, 34023, 6, 'Great', 'IO09IOSJOXZQQ', 75.00, 6),
        (1306, '1COAC12F3B5121306', 'Cadillac', 'Escalade ESV', 2011, 45023, 6, 'Exellent', 'NNSW23SWQAIOP', 85.00, 6),
        (3412, '1GNGT34E4A0013412', 'Lincoln', 'Stretch Limousine', 2010, 53002, 10, 'Great', 'SSUIOWPQ09OSJ', 115.00, 6),
        (5001, '1MWAW58S6C0005001', 'Mercedes', 'Sprinter Executive Van', 2012, 8302, 13, 'Exellent', 'LSUIW92SIWMWI', 130.00, 6);