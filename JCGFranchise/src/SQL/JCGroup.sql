USE JCGroup;

create table Airport
(AirportID int Auto_Increment,
 AirportName varchar(40),
 Address varchar(30),
 City varchar(40),
 State varchar(15),
 Zip int,
Primary Key (AirportID));

create table Franchise
(FranchiseNumber int Auto_Increment,
 Address varchar(40),
 City varchar(40),
 State varchar(15),
 Zip int(5),
 Phone varchar(12) Unique,
 Email varchar(40) Unique,
 AirportID int(2),
  Primary Key (FranchiseNumber),
  Foreign Key (AirportID)
   references Airport (AirportID));

create table Employee
( EmployeeID int AUTO_INCREMENT,
  Fname varchar(20),
  Surname varchar(20),
  Address varchar(40),
  City varchar(40),
  State varchar(15),
  Zip int,
  Phone varchar(12) unique not null,
  Email varchar(40) unique not null,
  EmpType int not null,
  FranchiseNumber int,
  Username varchar(8),
  FirstLog int Default  0,
  Primary Key (EmployeeID),
   Foreign Key (FranchiseNumber)
    references Franchise (FranchiseNumber) ) Auto_Increment = 9000;




Create Table Vehicle
( VehicleID int,
  VIN varchar(20) Unique Not Null,
  Make varchar(16) Not Null,
  Model varchar(30) Not Null,
  Year int Not Null,
  Millage int(6) Not Null,
  Capacity int Not Null,
  VCondition varchar(15) Not NUll,
  Tablet varchar(20) Not Null,
  RentalPrice decimal(3, 2) Not null,
  FranchiseNumber int,
  Primary Key (VehicleID),
  Foreign key (FranchiseNumber)
    references Franchise (FranchiseNumber) );

create table Maintanence
( ServiceNumber varchar(10) Unique,
  VehicleID int,
  ServiceType varchar(20),
  ServiceCost double(4,2),
  Primary Key (ServiceNumber),
  Foreign Key (VehicleID)
    references Vehicle (VehicleID) );

create table Membership
( MemberID varchar(10),
  Discount double(3, 2),
  MinAmount int,
  Primary Key (MemberID) );


create table Customer
( CustomerID int AUTO_INCREMENT,
  Fname varchar(20),
  Surname varchar(20),
  Address varchar(30),
  City varchar(40),
  State varchar(15),
  Zip int,
  Phone varchar(12),
  Email varchar(40),
  ReservationCount int,
  MemberID varchar(10),
  UserName varchar(20),    
  Primary Key (CustomerID),
  Foreign Key (MemberID)
    references Membership (MemberID) ) Auto_Increment = 1000;


insert into Airport (AirportName, Address, City, State, Zip)
 values ('Hartsfield-Jackson',
         '6000 North Terminal Parkway',
         'Atlanta',
         'Georgia',
         30320 );

insert into Airport (AirportName, Address, City, State, Zip)
  values ('Los Angeles International',
          '1 World Way',
          'Los Angeles',
          'California',
          90045 );

insert into Airport (AirportName, Address, City, State, Zip)
  values ('OHare',
          '10000 West OHare Avenue',
          'Chicago',
          'Illinois',
          60666 );

insert into Franchise (Address, City, State, Zip, Phone, Email)
  values ('1092 Flatbush Ave',
          'Brooklyn',
          'New York',
          11226,
          '718-576-2336',
          'jcgBrooklyn@gmail.com');

insert into Franchise (Address, City, State, Zip, Phone, Email, AirportID)
  values ('2312 Peachtree St',
          'Atlanta',
          'Georgia',
          31318,
          '678-123-4567',
          'jcgAtlanta@gmail.com',
          1 );

insert into Franchise (Address, City, State, Zip, Phone, Email, AirportID)
  values ('4060 Peachtree Ave.',
          'Buckhead',
          'Georgia',
          30340,
          '404-987-6543',
          'jcgBuckhead@gmail.com',
          1 );
insert into Franchise (Address, City, State, Zip, Phone, Email, AirportID)
  values ('123 Venture BLVD',
          'Hollywood',
          'California',
          12345,
          '619-345-0987',
          'jcgHoolywood@gmail.com',
          2 );

insert into Franchise (Address, City, State, Zip, Phone, Email, AirportID)
  values ('687 Hills Way',
          'Los Angelas',
          'California',
          23456,
          '603-343-0909',
          'jcgLosAngelas@gmail.com',
          2 );
 
insert into Franchise (Address, City, State, Zip, Phone, Email, AirportID)
  values ('934 Windy St',
          'Dolton',
          'Illinois',
          45003,
          '302-454-8232',
          'jcgDolton@gmail.com',
          3 );
insert into Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES('James', 'Cheeves', '139 Flatbush Ave', 'Brooklyn', 'New York', 11217, '718-230-7703', 'JCheeves@gmail.com', 1, 1, 'JC7703' );
 
CREATE USER 'JC7703'@'localhost' IDENTIFIED BY 'JC7703';
grant CREATE USER on *.* to 'JC7703'@'localhost';
grant SELECT, UPDATE, DELETE, INSERT on JCGroup.Franchise to 'JC7703'@'localhost';
grant SELECT, UPDATE, DELETE, INSERT on JCGroup.Employee to 'JC7703'@'localhost';
grant SELECT, UPDATE on JCGroup.Membership to 'JC7703'@'localhost';
grant SELECT, UPDATE, DELETE, INSERT on JCGroup.Airport to 'JC7703'@'localhost';
FLUSH PRIVILEGES;

insert into Employee (Fname, Surname, Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, Username)
 VALUES('Henry', 'Henderson', '599 Sibly Blvd.', 'Dolton', 'Illinois', 60419, '708-841-7336', 'HenryH@gmail.com', 2, 6, 'HH7336' );
 
CREATE USER 'HH7336'@'localhost' IDENTIFIED BY 'HH7336';
grant select on JCGroup.Airport to 'HH7336'@'localhost';
grant select on JCGroup.Franchise to 'HH7336'@'localhost';
grant select, insert, update, delete on JCGroup.Vehicle to 'HH7336'@'localhost';                         
grant select, insert, update, delete on JCGroup.Maintanence to 'HH7336'@'localhost';
grant select, insert, update, delete on JCGroup.Employee to 'HH7336'@'localhost';
grant insert, update, delete on JCGroup.Customer to 'HH7336'@'localhost';
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
grant select on JCGroup.Franchise to 'JS9553'@'localhost';
grant select, insert, update, delete on JCGroup.Vehicle to 'FK7823'@'localhost';                         
grant select, insert, update, delete on JCGroup.Maintanence to 'FK7823'@'localhost';
grant select, insert, update, delete on JCGroup.Employee to 'FK7823'@'localhost';
grant insert, update, delete on JCGroup.Customer to 'FK7823'@'localhost';
grant select on JCGroup.Customer to 'FK7823'@'localhost' with grant option;
grant select on JCGroup.Membership to 'FK7823'@'localhost';
grant CREATE USER on *.* to 'FK7823'@'localhost' with grant option;
flush privileges;

insert into Membership(MemberID, Discount, MinAmount)
values('Diamond', 000.85, 50), ('Gold', 000.90, 35), ('Silver', 000.95, 20);
