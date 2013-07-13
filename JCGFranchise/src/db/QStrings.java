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
    
// Fracnhise
    public String update_fran = "UPDATE Franchise SET Address = ?,"
                    + " City = ?, State = ?, Zip = ?, Phone = ?, Email = ?, "
                    + " AirportID = ? WHERE FranchiseNumber = ?";
    public String insert_fran = "INSERT INTO Franchise (Address, City,"
                    + " State, Zip, Phone, Email, AirportID) values "
                    + "(?, ?, ?, ?, ?, ?, ?)";
    
// Vehicle
    public String insert_vehicle = "INSERT INTO Vehicle (VehicleID, VIN, Make "
             + "Model, Year, Millage, Capacity, VCondition, Tablet, RentalPrice"
             + " FranchiceNumber VALUES(?,?,?,?,?,?,?,?,?,?,?)";
     
    public String update_vehicle = "UPDATE Vehicle SET VehicleID = ?, VIN = ?,"
            + " Make = ?, Model = ?, Year = ?, Millage = ?, Capacity = ?, "
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
    
    public String update_employee = "UPDATE Employee SET Fname = ?, Surname = ? "
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
                              "GRANT SELECT, INSERT, UPDATE, DELETE ON JCGroup.Employee TO ?@'localhost'" +
                              "GRANT SELECT, UPDATE, DELETE ON JCGroup.Customer TO ?@'localhost' " +
                              "GRANT SELECT ON JCGroup.Customer TO ?@'localhost' WITH GRANT OPTION " +
                              "GRANT SELECT ON JCGroup.Membership TO ?@'localhost' " +
                              //"GRANT SELECT, UPDATE ON JCGroup.Reservation TO ?@'localhost' WITH GRANT OPTION " +
                              //"GRANT INSERT, UPDATE ON JCGroup.Reservation TO ?@'localhost' " +
                              "GRANT CREATE USER ON *.* to ?@'localhost' WITH GRANT OPTION " +
                              "FLUSH PRIVILEGES";
    
    public String grant_driver = "GRANT SELECT ON JCGroup.Customer to ?@'localhost' "
                               + "GRANT SELECT, UPDATE ON JCGROUP.Employee to ?@'localhost'";
                                 //add in grant for reservation
    
    public String grant_owner = "GRANT INSERT, UPDATE, DELETE, SELECT ON JCGroup.Airport TO ?@'localhost' "
                              + "GRANT INSERT, UPDATE, DELETE, SELECT ON JCGroup.Franchise TO ?@'localhost' "
                              + "GRANT INSERT, UPDATE, DELETE, SELECT ON JCGroup.Employee TO ?@'localhost' "
                              + "GRANT SELECT, UPDATE ON JCGroup.Membership TO ?@'localhost'";   
    
    public String get_username = "SELECT Username FROM Employee "
                    + "WHERE Employee.EmpType = ?";    
    
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
    
    
// Admin
    public String get_emp_username = "SELECT Username FROM Employee WHERE EmpType != 98";
    public String get_cust_username = "SELECT Username FROM Customer";
    public String drop_user = "DROP USER ?@'localhost'";
}
