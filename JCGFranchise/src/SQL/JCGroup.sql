CREATE DATABASE IF NOT EXISTS JCGroup;
USE JCGroup;

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
  RentalPrice decimal(3, 2) Not null,
  FranchiseNumber int,
  PRIMARY KEY (VehicleID),
  FOREIGN KEY (FranchiseNumber)
    REFERENCES Franchise (FranchiseNumber) );

CREATE TABLE IF NOT EXISTS Maintanence
( ServiceNumber varchar(10) UNIQUE,
  VehicleID int,
  ServiceType varchar(20),
  ServiceCost double(4,2),
  PRIMARY KEY (ServiceNumber),
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

INSERT INTO Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES ('James', 'Cheeves', '139 Flatbush Ave', 'Brooklyn', 'New York', 11217, '718-230-7703', 'JCheeves@gmail.com', 1, 1, 'JC7703' );
 
CREATE USER 'JC7703'@'localhost' IDENTIFIED BY 'JC7703';
GRANT CREATE USER ON *.* TO 'JC7703'@'localhost';
GRANT SELECT, UPDATE, DELETE, INSERT ON JCGroup.Franchise TO 'JC7703'@'localhost';
GRANT SELECT, UPDATE, DELETE, INSERT ON JCGroup.Employee TO 'JC7703'@'localhost';
GRANT SELECT, UPDATE ON JCGroup.Membership TO 'JC7703'@'localhost';
GRANT SELECT, UPDATE, DELETE, INSERT ON JCGroup.Airport TO 'JC7703'@'localhost';
FLUSH PRIVILEGES;

INSERT INTO Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username, FirstLog)
 VALUES ('Elwyn', 'Nick', '1622 Bedford Avenue', 'Brooklyn', 'New York', 11225, '347-770-8853', 'ElwynN@gmail.com', 98, 1, 'JCGAdmin', 1);

INSERT INTO Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES('Henry', 'Henderson', '599 Sibly Blvd.', 'Dolton', 'Illinois', 60419, '708-841-7336', 'HenryH@gmail.com', 2, 6, 'HH7336' );
 
CREATE USER 'HH7336'@'localhost' IDENTIFIED BY 'HH7336';
grant select on JCGroup.Airport to 'HH7336'@'localhost';
grant select on JCGroup.Franchise to 'HH7336'@'localhost';
grant select, insert, update, delete on JCGroup.Maintanence to 'HH7336'@'localhost';
grant SELECT, UPDATE, DELETE, INSERT on JCGroup.Employee to 'HH7336'@'localhost';
grant insert, update, delete on JCGroup.Customer to 'HH7336'@'localhost';
grant SELECT, UPDATE, DELETE, INSERT on JCGroup.Vehicle to 'HH7336'@'localhost';
grant select on JCGroup.Customer to 'HH7336'@'localhost' with grant option;
grant select on JCGroup.Membership to 'HH7336'@'localhost';
grant CREATE USER on *.* to 'HH7336'@'localhost' with grant option;
flush privileges;

insert into Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES('Jennifer', 'Smith', '6775 Santa Monica Blvd.', 'Los Angeles', 'California', 90038, '323-957-9553', 'JSmith@gmail.com', 2, 5, 'JS9553' );
 
CREATE USER 'JS9553'@'localhost' IDENTIFIED BY 'JS9553';
grant select on JCGroup.Airport to 'JS9553'@'localhost';
grant select on JCGroup.Franchise to 'JS9553'@'localhost';
grant select, insert, update, delete on JCGroup.Vehicle to 'JS9553'@'localhost';
grant select, insert, update, delete on JCGroup.Maintanence to 'JS9553'@'localhost';
grant select, insert, update, delete on JCGroup.Employee to 'JS9553'@'localhost';
grant insert, update, delete on JCGroup.Customer to 'JS9553'@'localhost';
grant select on JCGroup.Customer to 'JS9553'@'localhost' with grant option;
grant select on JCGroup.Membership to 'JS9553'@'localhost';
grant CREATE USER on *.* to 'JS9553'@'localhost' with grant option;
flush privileges;

insert into Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES('Frank', 'Keisler', '6660 W Sunset Blvd.', 'Hollywood', 'California', 90028, '323-962-7823', 'FrankK@gmail.com', 2, 4, 'FK7823' );
 
CREATE USER 'FK7823'@'localhost' IDENTIFIED BY 'FK7823';
grant select on JCGroup.Airport to 'FK7823'@'localhost';
grant select on JCGroup.Franchise to 'FK7823'@'localhost';
grant select, insert, update, delete on JCGroup.Vehicle to 'FK7823'@'localhost';
grant select, insert, update, delete on JCGroup.Maintanence to 'FK7823'@'localhost';
grant select, insert, update, delete on JCGroup.Employee to 'FK7823'@'localhost';
grant insert, update, delete on JCGroup.Customer to 'FK7823'@'localhost';
grant select on JCGroup.Customer to 'FK7823'@'localhost' with grant option;
grant select on JCGroup.Membership to 'FK7823'@'localhost';
grant CREATE USER on *.* to 'FK7823'@'localhost' with grant option;
flush privileges;

insert into Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES('Ararat', 'Arsenio', '7431 N Point Pky', 'Alpharetta', 'Georgia', 30022, '770-518-1787', 'Arsenio_A@gmail.com', 2, 3, 'AA1787');
 
CREATE USER 'AA1787'@'localhost' IDENTIFIED BY 'AA1787';
grant select on JCGroup.Airport to 'AA1787'@'localhost';
grant select on JCGroup.Franchise to 'AA1787'@'localhost';
grant select, insert, update, delete on JCGroup.Vehicle to 'AA1787'@'localhost';
grant select, insert, update, delete on JCGroup.Maintanence to 'AA1787'@'localhost';
grant select, insert, update, delete on JCGroup.Employee to 'AA1787'@'localhost';
grant insert, update, delete on JCGroup.Customer to 'AA1787'@'localhost';
grant select on JCGroup.Customer to 'AA1787'@'localhost' with grant option;
grant select on JCGroup.Membership to 'AA1787'@'localhost';
grant CREATE USER on *.* to 'AA1787'@'localhost' with grant option;
flush privileges;

insert into Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES('Nolan', 'Kelly', '50 Lower Alabama St', 'Atlanta', 'Georgia', 30303, '404-589-4647', 'NKelly@gmail.com', 2, 2, 'NK4647');
 
CREATE USER 'NK4647'@'localhost' IDENTIFIED BY 'NK4647';
grant select on JCGroup.Airport to 'NK4647'@'localhost';
grant select on JCGroup.Franchise to 'NK4647'@'localhost';
grant select, insert, update, delete on JCGroup.Vehicle to 'NK4647'@'localhost';
grant select, insert, update, delete on JCGroup.Maintanence to 'NK4647'@'localhost';
grant select, insert, update, delete on JCGroup.Employee to 'NK4647'@'localhost';
grant insert, update, delete on JCGroup.Customer to 'NK4647'@'localhost';
grant select on JCGroup.Customer to 'NK4647'@'localhost' with grant option;
grant select on JCGroup.Membership to 'NK4647'@'localhost';
grant CREATE USER on *.* to 'NK4647'@'localhost' with grant option;
flush privileges;

insert into Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES('Esmail', 'Osama', '68 Broad St NW', 'Atlanta', 'Geaorgia', 30303, '404-522-2336', 'EsmailO@gmail.com', 3, 2, 'EO2336');
 
CREATE USER 'EO2336'@'localhost' IDENTIFIED BY 'EO2336';
GRANT SELECT ON JCGroup.Customer TO 'EO2336'@'localhost';
flush privileges;

insert into Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES('Bedros', 'August', '183 Edgewood Ave', 'Atlanta', 'Georgia', 30303, '404-8286', 'BedrosA@gmail.com', 3, 2, 'BA8286');
 
CREATE USER 'BA8286'@'localhost' IDENTIFIED BY 'BA8286';
GRANT SELECT ON JCGroup.Customer TO 'BA8286'@'localhost';
flush privileges;

insert into Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES('Konstantyn', 'Kuba', '4418 Roswell Rd', 'Atlanta', 'Georgia', 30342, '404-250-1625', 'KubaK@gmail.com', 3, 3, 'KK1625');

CREATE USER 'KK1625'@'localhost' IDENTIFIED BY 'KK1625';
GRANT SELECT ON JCGroup.Customer TO 'KK1625'@'localhost';
flush privileges;

insert into Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES('Petya', 'Luka', '1825 Piedmont Rd', 'Atlanta', 'Georgia', 30324, '404-876-2246', 'PetyaL@gmail.com', 3, 3, 'PL2246');
 
CREATE USER 'PL2246'@'localhost' IDENTIFIED BY 'PL2246';
GRANT SELECT ON JCGroup.Customer TO 'PL2246'@'localhost';
flush privileges;

insert into Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES('Aleksander', 'Vincenc', '6051 Hollywood Blvd', 'Hollywood', 'California', 90028, '323-469-9480', 'VincencA@gmail.com', 3, 4, 'AV9480');

CREATE USER 'AV9480'@'localhost' IDENTIFIED BY 'AV9480';
GRANT SELECT ON JCGroup.Customer TO 'AV9480'@'localhost';
flush privileges;

insert into Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES('Mehrdad', 'Gevorg', '5537 Sunset Blvd.', 'Hollywood', 'California', 90028, '323-871-1090', 'GevorgM@gmail.com', 3, 4, 'MG1090');

CREATE USER 'MG1090'@'localhost' IDENTIFIED BY 'MG1090';
GRANT SELECT ON JCGroup.Customer TO 'MG1090'@'localhost';
flush privileges;

insert into Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES('Dorotheos', 'Vasilis', '7040 Sunset Blvd', 'Los Angeles', 'California', 90068, '323-851-5501', 'VasilisD@gmail.com', 3, 5, 'DV5501');

CREATE USER 'DV5501'@'localhost' IDENTIFIED BY 'DV5501';
GRANT SELECT ON JCGroup.Customer TO 'DV5501'@'localhost';
flush privileges;

insert into Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES('Sevan', 'Henrik', '1075 N Western Ave', 'Los Angeles', 'California', 90029, '323-856-0990', 'HenrikS@gmail.com', 3, 5, 'SH0990');

CREATE USER 'SH0990'@'localhost' IDENTIFIED BY 'SH0990';
GRANT SELECT ON JCGroup.Customer TO 'SH0990'@'localhost';
flush privileges;

insert into Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES('Krikor', 'Rudolf', '700 E. Tri-State', ' S Holland', 'Illinois', 60473, '224-277-4356', 'KrikorR@gmail.com', 3, 6, 'KR4356');

CREATE USER 'KR4356'@'localhost' IDENTIFIED BY 'KR4356';
GRANT SELECT ON JCGroup.Customer TO 'KR4356'@'localhost';
flush privileges;

insert into Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES('Voski', 'Aleksandr', '578 Torrence Ave', 'Calumet City', 'Illinois', 60409, '708-832-0283', 'VoskiA@gmail.com', 3, 6, 'VA0283');

CREATE USER 'VA0283'@'localhost' IDENTIFIED BY 'VA0283';
GRANT SELECT ON JCGroup.Customer TO 'VA0283'@'localhost';
flush privileges;

insert into Membership(MemberID, Discount, MinAmount)
values('Diamond', 000.85, 50), ('Gold', 000.90, 35), ('Silver', 000.95, 20), ('Bronze' , 1.00, 0);