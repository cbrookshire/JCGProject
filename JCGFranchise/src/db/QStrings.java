/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

/**
 *
 * @author taylor
 */
final class QStrings {
    
    public QStrings(){ };
    
    // Fracnhise
    public String update_fran = "UPDATE Franchise SET Address = ?,"
                    + " City = ?, State = ?, Zip = ?, Phone = ?, Email = ?, "
                    + " AirportID = ? WHERE FranchiseNumber = ?";
    public String insert_fran = "INSERT INTO Franchise (Address, City,"
                    + " State, Zip, Phone, Email, AirportID) values "
                    + "(?, ?, ?, ?, ?, ?, ?)";
    
    public String delete_fran = "UPDATE Franchise SET Active = 'N' WHERE FranchiseNumber = ?";
    
// Vehicle
    public String insert_vehicle = "INSERT INTO Vehicle (VehicleID, VIN, Make "
             + "Model, Year, Mileage, Capacity, VCondition, Tablet, RentalPrice"
             + " FranchiceNumber VALUES(?,?,?,?,?,?,?,?,?,?,?)";
     
    public String update_vehicle = "UPDATE Vehicle SET VehicleID = ?, VIN = ?,"
            + " Make = ?, Model = ?, Year = ?, Mileage = ?, Capacity = ?, "
            + "VCondition = ?, Tablet = ?, RentalPrice = ?, FranchiseNumber = ? WHERE "
             + "VIndex = ?";
    
// Maintenance
    public String insert_maintenance = "INSERT INTO Maintenance(ServiceNumber, "
            + "VehicleID, ServiceType, ServiceCost) VALUES(?,?,?,?)";
    
    public String update_maintenance = "UPDATE Maintenance SET VehicleID = ?, "
            + "ServiceType = ?, ServiceCost = ?, ServiceNumber = ? WHERE MaintIndex = ?";
    
// Employee
    public String insert_employee = "INSERT INTO Employee(Fname, Surname,"
            + " Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, "
            + "Username) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
    
    public String update_employee = "UPDATE Employee SET Fname = ?, Surname = ?, "
            + "Address = ?, City = ?, State = ?, Zip = ?, Phone = ?, Email = ? "
            + "WHERE EmployeeID = ?";
            
    public String create_user = "CREATE USER ?@'localhost' IDENTIFIED BY ?";
    
    public String grant_temp = "grant all privileges on *.* to "
            + "?@'localhost' with grant option";
    
    public String revoke_temp = "REVOKE ALL PRIVILEGES, GRANT OPTION"
                    + "FROM ?@localhost'";
    
    public String grant_manager = "GRANT SELECT ON JCGroup.Airport to ?@'localhost' " +
                              "GRANT SELECT ON JCGroup.Franchise TO ?@'localhost' " +
                              "GRANT SELECT, INSERT, UPDATE, DELETE ON JCGroup.Vehicle TO ?@'localhost' " +
                              "GRANT SELECT, INSERT, UPDATE, DELETE ON JCGroup.Maintanence TO ?@'localhost' " +
                              "GRANT INSERT, DELETE ON JCGroup.Employee TO ?@'localhost' " +
                              "GRANT SELECT, UPDATE ON JCGroup.Employee TO ?@'localhost' " +
                              "GRANT INSERT, DELETE ON JCGroup.Customer TO ?@'localhost' " +
                              "GRANT SELECT, UPDATE ON JCGroup.Customer TO ?@'localhost' WITH GRANT OPTION " +
                              "GRANT SELECT ON JCGroup.Membership TO ?@'localhost' WITH GRANT OPTION " +
                              "GRANT SELECT, UPDATE ON JCGroup.Reservation TO ?@'localhost' WITH GRANT OPTION " +
                              "GRANT INSERT, DELETE ON JCGroup.Reservation TO ?@'localhost' " +
                              "GRANT CREATE USER ON *.* to ?@'localhost' " +
                              "FLUSH PRIVILEGES";
    
    public String grant_driver = "GRANT SELECT, UPDATE ON JCGroup.Customer to ?@'localhost' "
                               + "GRANT SELECT, UPDATE ON JCGroup.Employee to ?@'localhost' "
                               + "GRANT SELECT, UPDATE ON JCGroup.Reservation TO ?@'localhost' "
                               + "GRANT SELECT ON JCGroup.Membership TO ?@'localhost' "
                               + "FLUSH PRIVILEGES";
                               
    
    public String grant_owner = "GRANT INSERT, UPDATE, DELETE, SELECT ON JCGroup.Airport TO ?@'localhost' "
                              + "GRANT INSERT, UPDATE, DELETE, SELECT ON JCGroup.Franchise TO ?@'localhost' "
                              + "GRANT INSERT, UPDATE, DELETE, SELECT ON JCGroup.Employee TO ?@'localhost' "
                              + "GRANT SELECT, UPDATE ON JCGroup.Membership TO ?@'localhost' "
                              + "GRANT CREATE USER ON *.* TO ?@'localhost' WITH GRANT OPTION "
                              + "FLUSH PRIVILEGES";   
    
    public String get_username = "SELECT Username FROM Employee "
                    + "WHERE Employee.EmpType = ?";
    
    public String owner_view_emp = "SELECT * FROM `employee` WHERE `FranchiseNumber` = ? "
            + "OR EmpType = 2";
    
    public String man_view_emp = "SELECT * FROM `employee` WHERE `FranchiseNumber` = ?";
    
    public String remove_fran_emp = "DELETE FROM Employee WHERE Username = ?";
    
// Customer
    public String insert_customer = "INSERT INTO Customer(Fname, Surname,"
            + " Address, City, State, Zip, Phone, Email, Username "
            + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    public String update_customer = "UPDATE Customer SET Fname = ?, Surname = ?, "
            + "Adress = ?, City = ?, State = ?, Zip = ?, Phone = ?, "
            + "Email = ? WHERE CustomerID = ?";
    
     public String grant_customer = "GRANT INSERT, UPDATE, DELETE, SELECT ON JCGroup.Customer TO ?@'localhost' "
                                 + "GRANT SELECT ON JCGroup.Vehicle TO ?@'localhost'";
    
// Membership
    public String update_membership = "UPDATE Membership SET Discount = ? "
            + "WHERE MemberID = ?";
    
// Airport
    public String insert_airport = "INSERT INTO Airport(AirportName, Address, City, State, Zip) "
            + "VALUES (?, ?, ?, ?, ?)";
    
    public String update_airport = "UPDATE Airport SET AirportName = ?, Address = ?, "
            + "City = ?, State = ? Zip = ? WHERE AirportID = ?";
    
    //reservation
    public String get_rental_price = "SELECT RentalPrice FROM Vehicle WHERE VehicleID = ?";
    
    public String get_discount = "SELECT Discount FROM Membership m, Customer c " +
               "WHERE m.MemberID = c.MemberID && c.CustomerID = ?";
    
    public String insert_res = "insert into Reservation(FranchiseNumber, VehicleID, CustomerID, "
            + "Price, drop_pick, FlightNumber, Airline, FlightTime, PickUpTime, DropOffTime,  Date, "
            + "AltAddress, AltCity, AltState, AltZip) Values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    public String update_res_m = "UPDATE Reservation SET FlightTime = ?, PickupTime = ?, DropOffTime = ? "
            + "AltAddress = ?, AltCity = ?, AltState = ?, AltZip = ? WHERE ReservationNumber = ?";
    
    public String update_res_d = "UPDATE Reservation SET Status = 'Close', Comment = ? WHERE = ?";
    
    public String update_res_count = "Update Customer SET ReservationCount = ReservationCount + 1, " 
                                   + "MemberID = (select MemberID from Membership where MinAmount "
                                   + "<= ReservationCount ORDER BY MinAmount desc limit 1) WHERE CustomerID = ?";
    
// Admin
    public String get_emp_username = "SELECT Username FROM Employee WHERE EmpType != 98";
    
    public String get_fran_username = "SELECT Username FROM Employee WHERE FranchiseNumber = ? AND "
            + "EmpType != 98";
    
    public String get_cust_username = "SELECT Username FROM Customer";
    public String drop_user = "DROP USER ?@'localhost'";
    
}
