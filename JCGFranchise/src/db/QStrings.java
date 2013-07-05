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
     
    public String update_vehicle = "UPDATE Vehicle SET Millage = ?, VCondition "
             + "= ?, Tablet = ?, RentalPrice = ?, FranchiseNumber = ? WHERE "
             + "VehicleID = ?";
    
    // Maintenance
    public String insert_maintenance = "INSERT INTO Maintenance(ServiceNumber, "
            + "VehicleID, ServiceType, ServiceCost) VALUES(?,?,?,?)";
    
    // Employee
    public String insert_employee = "INSERT INTO Employee(Fname, Surname,"
            + " Address, City, State, Zip, Phone, Email, EmpType, FranchiseNumber, "
            + "Username) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
    
    public String create_user = "CREATE USER ?@'localhost' IDENTIFIED BY ?";
    
    public String grant_temp = "grant all privileges on *.* to "
            + "?@'localhost' with grant option";
    
    public String revoke_temp = "REVOKE ALL PRIVILEGES, GRANT OPTION"
                    + "FROM ?@localhost'";
}
