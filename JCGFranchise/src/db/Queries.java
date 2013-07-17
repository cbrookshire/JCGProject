/*
 * 
 */
package db;

import JCGExceptions.BadConnectionException;
import JCGExceptions.DoubleEntryException;
import JCGExceptions.InvalidUserException;
import JCGExceptions.UnauthorizedUserException;
import bp.Customer;
import bp.Employee;
import bp.Franchise;
import bp.JCGlIO;
import bp.Membership;
import bp.Reservation;
import bp.Vehicle;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import bp.JCGlIO;
import java.sql.DriverManager;

/**
 *
 * @authors Taylor Reighard and Miles Leavens-Russell
 */
public class Queries extends JCGDatabase
{
    
    private Connection con = null;
    private ResultSet resultSet = null;
    private QStrings qs;
    private int code;
    
    public Queries(JCGlIO lio) throws InvalidUserException, BadConnectionException
    {

        super(lio);
        setConnection(super.connection);
        qs = new QStrings();

    }
        
    private void setConnection(Connection con)
    {
        this.con = con;
    }
      
/******************************************************************************
 *          All Queries to the Franchise table.                               * 
 *          Inserts, Updates authored by Taylor                               * 
 *          Selects, Deletes authored by Miles                                *
 ******************************************************************************/
    
    public int updateFranchise(Franchise fran) throws UnauthorizedUserException,
            BadConnectionException, DoubleEntryException {
        try
        {
            PreparedStatement pStmnt = con.prepareStatement(qs.update_fran);
            pStmnt.setString(1, fran.getAddress());
            pStmnt.setString(2, fran.getCity());
            pStmnt.setString(3, fran.getState());
            pStmnt.setInt(4, fran.getZip());
            pStmnt.setString(5, fran.getPhone());
            pStmnt.setString(6, fran.getEmail());
            pStmnt.setInt(7, fran.getAirportID());
            pStmnt.setInt(8, fran.getFranchiseID());
            pStmnt.executeUpdate();             
        }
        catch(SQLException sqlE) {
            if(sqlE.getErrorCode() == 1142)
                throw(new UnauthorizedUserException("AccessDenied"));
            else if(sqlE.getErrorCode() == 1062)
                throw(new DoubleEntryException("DoubleEntry"));
            else 
                throw(new BadConnectionException("BadConnection"));
        }
        return 1;
    }
    
    public int insertFranchise(Franchise fran) throws UnauthorizedUserException,
            BadConnectionException, DoubleEntryException {
        try {
            PreparedStatement pStmnt = con.prepareStatement(qs.insert_fran);
            pStmnt.setString(1, fran.getAddress());
            pStmnt.setString(2, fran.getCity());
            pStmnt.setString(3, fran.getState());
            pStmnt.setInt(4, fran.getZip());
            pStmnt.setString(5, fran.getPhone());
            pStmnt.setString(6, fran.getEmail());
            pStmnt.setInt(7, fran.getAirportID());
            pStmnt.executeUpdate();
            
        } catch(SQLException sqlE) {
            if(sqlE.getErrorCode() == 1142)
                throw(new UnauthorizedUserException("AccessDenied"));
            else if(sqlE.getErrorCode() == 1062)
                throw(new DoubleEntryException("DoubleEntry"));
            else 
                throw(new BadConnectionException("BadConnection"));
        }
        return 1;
    }
      
       //GET: List All Franchises
    public ArrayList<Franchise> GetAllFranchises()
            throws UnauthorizedUserException, BadConnectionException, DoubleEntryException
    {
        /* Variable Section Start */
            /* Database and Query Preperation */
            //PreparedStatement statment = null;
            Statement statment = null;
            ResultSet results = null;
            String statString = "SELECT * FROM Franchise";

            /* Return Parameter */
            ArrayList<Franchise> BPList = new ArrayList<Franchise>();
        /* Variable Section Stop */
        
        
        /* TRY BLOCK START */
            
            try
            {
            /* Preparing Statment Section Start */
                
                if(con.isValid(120));
                {
                    System.out.println("Connection is Good!");
                }
                
                if(!con.isValid(120));
                {
                    System.out.println("Connection is Bad!");
                }
                
                
                statment = con.createStatement();
                
                //statment = con.prepareStatement(statString);
                //statment.setString();
            /* Preparing Statment Section Stop */
            /* Query Section Start */
                results = statment.executeQuery(statString);
                
               
                while (results.next())
                {
                    System.out.println("in the loop");
                    Franchise temp = new Franchise();
                    
                    //rs.getBigDecimal("AMOUNT")
                    
                    temp.setAddress(results.getString("Address"));
                    temp.setAirportID(results.getInt("AirportID"));
                    //temp.setAirport(getFranchiseAirportName(results.getInt("AirportID")));
                    temp.setCity(results.getString("City"));
                    temp.setEmail(results.getString("Email"));
                    temp.setFranchiseID(results.getInt("FranchiseNumber"));
                    temp.setPhone(results.getString("Phone"));
                    temp.setState(results.getString("State"));
                    temp.setZip(results.getInt("Zip"));
                    
                    //System.out.print(temp.toString());
                    BPList.add(temp);
                }
                
            /* Query Section Stop */
            
            /* Metadata Section Start */
                //ResultSetMetaData metaData = results.getMetaData();
                //int columns = metaData.getColumnCount();
                //int rows = results.getRow(); 
            /* Metadata Section Start*/
                
            /* List Prepare Section Start */
                
            /* List Prepare Section Stop */
            }
            catch(SQLException sqlE)
            {
                if(sqlE.getErrorCode() == 1142)
                    throw(new UnauthorizedUserException("AccessDenied"));
                else if(sqlE.getErrorCode() == 1062)
                    throw(new DoubleEntryException("DoubleEntry"));
                else 
                    throw(new BadConnectionException("BadConnection"));
            }
            finally
            {
                try
                {
                    if (results != null) results.close();
                }
                catch (Exception e) {};
                try
                {
                    if (statment != null) statment.close();
                }
                catch (Exception e) {};
            }
        /* TRY BLOCK STOP*/
        
            
        /* Return to Buisness Section Start */ 
            return BPList;
        /* Return to Buisness Section Start */

    }
    
    //GET: List One Franchise
    public ArrayList<Franchise> GetOneFranchise(int FranID)
            throws UnauthorizedUserException, BadConnectionException, DoubleEntryException
    {
        /* Variable Section Start */
            /* Database and Query Preperation */
            PreparedStatement statment = null;
            ResultSet results = null;
            String statString = "SELECT * FROM `franchise` WHERE 'FranchiseNumber' = ?";

            /* Return Parameter */
            ArrayList<Franchise> BPList = new ArrayList<Franchise>();
        /* Variable Section Stop */
        
        
        /* TRY BLOCK START */
            
            try
            {
            /* Preparing Statment Section Start */                
                statment = con.prepareStatement(statString);
                statment.setInt(1, FranID);
            /* Preparing Statment Section Stop */
            /* Query Section Start */
                results = statment.executeQuery();
                
            /* Query Section Stop */
            
            /* Metadata Section Start */
                //ResultSetMetaData metaData = results.getMetaData();
            /* Metadata Section Start*/
                
            /* List Prepare Section Start */
                
                
                
                while (results.next())
                {
                    Franchise temp = new Franchise();
                    
                    //rs.getBigDecimal("AMOUNT")
                    
                    temp.setAddress(results.getString("Address"));
                    temp.setAirportID(results.getInt("AirportID"));
                    temp.setAirport(getFranchiseAirportName(results.getInt("AirportID")));
                    temp.setCity(results.getString("City"));
                    temp.setEmail(results.getString("Email"));
                    temp.setFranchiseID(results.getInt("FranchiseNumber"));
                    temp.setPhone(results.getString("Phone"));
                    temp.setState(results.getString("State"));
                    temp.setZip(results.getInt("Zip"));
                    
                    BPList.add(temp);
                }
            /* List Prepare Section Stop */
            }
            catch(SQLException sqlE)
            {
                if(sqlE.getErrorCode() == 1142)
                    throw(new UnauthorizedUserException("AccessDenied"));
                else if(sqlE.getErrorCode() == 1062)
                    throw(new DoubleEntryException("DoubleEntry"));
                else 
                    throw(new BadConnectionException("BadConnection"));
            }
            finally
            {
                try
                {
                    if (results != null) results.close();
                }
                catch (Exception e) {};
                try
                {
                    if (statment != null) statment.close();
                }
                catch (Exception e) {};
            }
        /* TRY BLOCK STOP*/
        
            
        /* Return to Buisness Section Start */ 
            return BPList;
        /* Return to Buisness Section Start */

    }
  
    public boolean RemoveFranchise(int ID)
            throws UnauthorizedUserException, BadConnectionException, DoubleEntryException
    {
        /* Variable Section Start */
        Statement query = null;
        ResultSet results = null;
        int success = -1;
        
        ArrayList<Integer> EmployeeIDs = new ArrayList<Integer>();
        
        /* Variable Section Stop */
        
        /* Preparing Statment Section Start */
        String MyQuery = "DELETE FROM `franchise` WHERE 'FranchiseNumber' = ?";
        //String to get All EmpIDs
        
        
        
        /* Delete Employee Section Start */
        
        System.out.println("Calling AllEmpIDInFranchise");
        EmployeeIDs = AllEmpIDInFranchise(ID);
        
        System.out.println("Entering For Loop");
        for(int c = 0; c < EmployeeIDs.size(); c++)
        {
            System.out.println("Running RemoveEmployee on " + c);
            RemoveEmployee(EmployeeIDs.get(c));
        }
        System.out.println("Exited For Loop");
        
        /* Delete Employee Section Stop */
        
            /* TRY BLOCK START */

            try
            {

                PreparedStatement statement = con.prepareStatement(MyQuery);

                statement.setInt(1, ID);
            
            
            /* Preparing Statment Section Stop */
  
            /* Query Section Start */
                System.out.println("Running Statement");
                success = statement.executeUpdate();
                System.out.println("Statement run " + success);
            /* Query Section Stop* /

            /* Return to Buisness Section Start */
                if(success == 1)
                    return true;     
                
                if(success == 0 || success == -1)
                    return false;     
            /* Return to Buisness Section Start */
                    
                   
                     
                    
            }
            catch(SQLException sqlE)
            {
                if(sqlE.getErrorCode() == 1142)
                    throw(new UnauthorizedUserException("AccessDenied"));
                else if(sqlE.getErrorCode() == 1062)
                    throw(new DoubleEntryException("DoubleEntry"));
                else 
                    System.out.println(sqlE);
                    throw(new BadConnectionException("BadConnection"));
            }
            finally
            {
                try
                {
                    if (results != null) results.close();
                }
                catch (Exception e) {};
                try
                {
                    if (query != null) query.close();
                }
                catch (Exception e) {};
            }
           /* TRY BLOCK STOP */
            
            return false;
    }
    
    public String getFranchiseAirportName(int ID)
            throws UnauthorizedUserException, BadConnectionException, DoubleEntryException
    {
        /* Variable Section Start */
            /* Database and Query Preperation */
            PreparedStatement statment = null;
            ResultSet results = null;
            String statString = "SELECT * FROM `airport` WHERE 'AirportID' = ?";

            /* Return Parameter */
            String AirName = null;
        /* Variable Section Stop */
        
        
        /* TRY BLOCK START */
            
            try
            {
            /* Preparing Statment Section Start */                
                statment = con.prepareStatement(statString);
                statment.setInt(1, ID);
            /* Preparing Statment Section Stop */
            /* Query Section Start */
                results = statment.executeQuery();
                
            /* Query Section Stop */
            
            /* Metadata Section Start */
                //ResultSetMetaData metaData = results.getMetaData();
            /* Metadata Section Start*/
                
            /* List Prepare Section Start */
                
                
                
                while (results.next())
                {
                    //Franchise temp = new Franchise();
                    
                    //rs.getBigDecimal("AMOUNT")
                    
                    AirName = (results.getString("AirportName"));
                  

                }
            /* List Prepare Section Stop */
            }
            catch(SQLException sqlE)
            {
                if(sqlE.getErrorCode() == 1142)
                    throw(new UnauthorizedUserException("AccessDenied"));
                else if(sqlE.getErrorCode() == 1062)
                    throw(new DoubleEntryException("DoubleEntry"));
                else 
                    throw(new BadConnectionException("BadConnection"));
            }
            finally
            {
                try
                {
                    if (results != null) results.close();
                }
                catch (Exception e) {};
                try
                {
                    if (statment != null) statment.close();
                }
                catch (Exception e) {};
            }
        /* TRY BLOCK STOP*/
        
            
        /* Return to Buisness Section Start */ 
            return AirName;
        /* Return to Buisness Section Start */

    }
 
/******************************************************************************
 *          All Queries for the Membership table                             *
 ******************************************************************************/
    
    
    public int updateMemeberShip(Membership mem) throws UnauthorizedUserException,
            BadConnectionException, DoubleEntryException {
        try {
            PreparedStatement pStmnt = con.prepareStatement(qs.update_membership);
            pStmnt.setDouble(1, mem.getDiscount());
            pStmnt.setString(1, mem.getMemberID());
            pStmnt.executeUpdate();
        } catch(SQLException sqlE) {
            if(sqlE.getErrorCode() == 1142)
                throw(new UnauthorizedUserException("AccessDenied"));
            else if(sqlE.getErrorCode() == 1062)
                throw(new DoubleEntryException("DoubleEntry"));
            else 
                throw(new BadConnectionException("BadConnection"));
        }
        return 1;
    }
        
     //GET: Membership/Promotions Data
    public  ArrayList<Membership> GetPromotionsData()
             throws UnauthorizedUserException, BadConnectionException, DoubleEntryException
    {
        /* Variable Section Start */
            /* Database and Query Preperation */
            PreparedStatement statment = null;
            ResultSet results = null;
            String statString = "SELECT * FROM `membership`";

            /* Return Parameter */
            ArrayList<Membership> BPList = new ArrayList<Membership>();
        /* Variable Section Stop */
        
        
        /* TRY BLOCK START */
            
            try
            {
            /* Preparing Statment Section Start */                
                statment = con.prepareStatement(statString);
                //statment.setInt(1, FranID);
            /* Preparing Statment Section Stop */
            /* Query Section Start */
                results = statment.executeQuery();
            /* Query Section Stop */
            
            /* Metadata Section Start*/
                //ResultSetMetaData metaData = results.getMetaData();
            /* Metadata Section Start*/
                
            /* List Prepare Section Start */
                
                
                
                while (results.next())
                {
                    Membership temp = new Membership();
                    
                    //results.getBigDecimal("AMOUNT")
                    
                    temp.setDiscount(results.getString("Discount"));
                    temp.setMemberID(results.getString("MemberID"));
                    temp.setMinAmount(results.getString("MinAmount"));
                    
                    BPList.add(temp);
                }
            /* List Prepare Section Stop */
            }
            catch(SQLException sqlE)
            {
                if(sqlE.getErrorCode() == 1142)
                    throw(new UnauthorizedUserException("AccessDenied"));
                else if(sqlE.getErrorCode() == 1062)
                    throw(new DoubleEntryException("DoubleEntry"));
                else 
                    throw(new BadConnectionException("BadConnection"));
            }
            finally
            {
                try
                {
                    if (results != null) results.close();
                }
                catch (Exception e) {};
                try
                {
                    if (statment != null) statment.close();
                }
                catch (Exception e) {};
            }
        /* TRY BLOCK STOP*/
        
            
        /* Return to Buisness Section Start */ 
           
            
            
            return BPList;
        /* Return to Buisness Section Start */
        
        
        
    }
    
/******************************************************************************
 *          All Queries for the Vehicle table                                 *
 ******************************************************************************/
    
    public int insertVehicle(Vehicle car) throws UnauthorizedUserException,
            BadConnectionException, DoubleEntryException {
        try
        {
            PreparedStatement pStmnt = con.prepareStatement(qs.insert_vehicle);
            pStmnt.setInt(1, car.getVehicleID());
            pStmnt.setString(2, car.getVin());
            pStmnt.setString(3, car.getMake());
            pStmnt.setString(4, car.getModel());
            pStmnt.setString(5, car.getYear());
            pStmnt.setInt(6, car.getMileage());
            pStmnt.setInt(7, car.getCapacity());
            pStmnt.setString(8, car.getCondition());
            pStmnt.setString(9, car.getTablet());
            pStmnt.setDouble(10, car.getRate());
            pStmnt.setInt(11, car.getFranchiseNumber());
            pStmnt.executeUpdate();             
        }
        catch(SQLException sqlE) {
            if(sqlE.getErrorCode() == 1142)
                throw(new UnauthorizedUserException("AccessDenied"));
            else if(sqlE.getErrorCode() == 1062)
                throw(new DoubleEntryException("DoubleEntry"));
            else 
                throw(new BadConnectionException("BadConnection"));
        }
        return 1;
    }
    
    public int updateVehicle(Vehicle car) 
            throws UnauthorizedUserException, BadConnectionException, DoubleEntryException {
        try {
            PreparedStatement pStmnt = con.prepareStatement(qs.update_vehicle);
            pStmnt.setInt(1, car.getVehicleID());
            pStmnt.setString(2, car.getVin());
            pStmnt.setString(3, car.getMake());
            pStmnt.setString(4, car.getModel());
            pStmnt.setString(5, car.getYear());
            pStmnt.setInt(6, car.getMileage());
            pStmnt.setInt(7, car.getCapacity());
            pStmnt.setString(8, car.getCondition());
            pStmnt.setString(9, car.getTablet());
            pStmnt.setDouble(10, car.getRate());
            pStmnt.setInt(11, car.getFranchiseNumber());
            pStmnt.setInt(12, car.getvIndex());
            pStmnt.executeUpdate();
            
        } catch(SQLException sqlE) {
            if(sqlE.getErrorCode() == 1142)
                throw(new UnauthorizedUserException("AccessDenied"));
            else if(sqlE.getErrorCode() == 1062)
                throw(new DoubleEntryException("DoubleEntry"));
            else 
                throw(new BadConnectionException("BadConnection"));
        }
        return 1;
    }
       
    //GET: List Vehicles in a Franchise
    public  ArrayList<Vehicle> VehiclesForFranchise( int FranID)
             throws UnauthorizedUserException, BadConnectionException, DoubleEntryException
    {
        /* Variable Section Start */
            /* Database and Query Preperation */
            PreparedStatement statment = null;
            ResultSet results = null;
            String statString = "SELECT * FROM vehicle WHERE FranchiseNumber = ?";

            /* Return Parameter */
            ArrayList<Vehicle> BPList = new ArrayList<Vehicle>();
        /* Variable Section Stop */
        
        
        /* TRY BLOCK START */
            
            try
            {
            /* Preparing Statment Section Start */                
                statment = con.prepareStatement(statString);
                statment.setInt(1, FranID);
            /* Preparing Statment Section Stop */
            /* Query Section Start */
                results = statment.executeQuery();
            /* Query Section Stop */
            
            /* Metadata Section Start*/
                //ResultSetMetaData metaData = results.getMetaData();
            /* Metadata Section Start*/
                
            /* List Prepare Section Start */
                
                
                
                while (results.next())
                {
                    Vehicle temp = new Vehicle();
                    
                    //rs.getBigDecimal("AMOUNT")
                    
                    temp.setCapacity(results.getInt("Capacity"));
                    temp.setCondition(results.getString("VCondition"));
                    temp.setFranchiseNumber(results.getInt("FranchiseNumber"));
                    temp.setMake(results.getString("Make"));
                    temp.setMileage(results.getInt("Milage"));
                    temp.setModel(results.getString("Model"));
                    temp.setRate(results.getDouble("RentalPrice"));
                    temp.setTablet(results.getString("Tablet"));
                    temp.setVehicleID(results.getInt("VehicleID"));
                    temp.setVin(results.getString("VIN"));
                    temp.setYear(results.getInt("MakeYear"));
                    
                    BPList.add(temp);
                }
            /* List Prepare Section Stop */
            }
            catch(SQLException sqlE)
            {
                if(sqlE.getErrorCode() == 1142)
                    throw(new UnauthorizedUserException("AccessDenied"));
                else if(sqlE.getErrorCode() == 1062)
                    throw(new DoubleEntryException("DoubleEntry"));
                else 
                    throw(new BadConnectionException("BadConnection"));
            }
            finally
            {
                try
                {
                    if (results != null) results.close();
                }
                catch (Exception e) {};
                try
                {
                    if (statment != null) statment.close();
                }
                catch (Exception e) {};
            }
        /* TRY BLOCK STOP*/
        
            
        /* Return to Buisness Section Start */ 
           
            
            
            return BPList;
        /* Return to Buisness Section Start */
        
        
        
    }
        
    //DELETE: Limo
    public boolean RemoveLimo(int ID)
             throws UnauthorizedUserException, BadConnectionException, DoubleEntryException
    {
        /* Variable Section Start */
        Statement query = null;
        ResultSet results = null;
        int success = -1;
        /* Variable Section Stop */
        
        /* Preparing Statment Section Start */
        String MyQuery = "DELETE * FROM `employee` WHERE 'VehicleID' = ?";
        
            /* TRY BLOCK START */

            try
            {

                PreparedStatement statement = con.prepareStatement(MyQuery);

                statement.setInt(1, ID);
            
            
            /* Preparing Statment Section Stop */
  
            /* Query Section Start */
                success = statement.executeUpdate();
            /* Query Section Stop* /

            /* Return to Buisness Section Start */
                if(success == 1)
                    return true;     
                
                if(success == 0 || success == -1)
                    return false;     
            /* Return to Buisness Section Start */
                    
                   
                     
                    
            }
            catch(SQLException sqlE)
            {
                if(sqlE.getErrorCode() == 1142)
                    throw(new UnauthorizedUserException("AccessDenied"));
                else if(sqlE.getErrorCode() == 1062)
                    throw(new DoubleEntryException("DoubleEntry"));
                else 
                    throw(new BadConnectionException("BadConnection"));
            }
            finally
            {
                try
                {
                    if (results != null) results.close();
                }
                catch (Exception e) {};
                try
                {
                    if (query != null) query.close();
                }
                catch (Exception e) {};
            }
           /* TRY BLOCK STOP */
            
            return false;
    } 
    
     //GET: Show Cars in a Franchise
    public  ArrayList<Vehicle> GetOneVehicle(int VID)
             throws UnauthorizedUserException, BadConnectionException, DoubleEntryException
    {
        /* Variable Section Start */
            /* Database and Query Preperation */
            PreparedStatement statment = null;
            ResultSet results = null;
            String statString = "SELECT * FROM 'Vehicle' WHERE 'VehicleID' = ?";

            /* Return Parameter */
            ArrayList<Vehicle> BPList = new ArrayList<Vehicle>();
        /* Variable Section Stop */
        
        
        /* TRY BLOCK START */
            
            try
            {
            /* Preparing Statment Section Start */                
                statment = con.prepareStatement(statString);
                statment.setInt(1, VID);
            /* Preparing Statment Section Stop */
            /* Query Section Start */
                results = statment.executeQuery();
            /* Query Section Stop */
            
            /* Metadata Section Start*/
                //ResultSetMetaData metaData = results.getMetaData();
            /* Metadata Section Start*/
                
            /* List Prepare Section Start */
                
                
                
                while (results.next())
                {
                    Vehicle temp = new Vehicle();
                    
                    //rs.getBigDecimal("AMOUNT")
                    
                    temp.setCapacity(results.getInt("Capacity")); 
                    temp.setCondition(results.getString("VCondition")); 
                    temp.setFranchiseNumber(results.getInt("FranchiseNumber"));
                    temp.setMake(results.getString("Make")); 
                    temp.setMileage(results.getString("Millage")); 
                    temp.setModel(results.getString("Model")); 
                    temp.setRate(results.getDouble("RentalPrice")); 
                    temp.setTablet(results.getString("Tablet"));
                    temp.setVehicleID(results.getInt("VehicleID"));
                    temp.setVin(results.getString("VIN"));
                    temp.setYear(results.getInt("MakeYear"));
                    temp.setvIndex(results.getString("VIndex"));
                    
                    BPList.add(temp);
                }
            /* List Prepare Section Stop */
            }
            catch(SQLException sqlE)
            {
                if(sqlE.getErrorCode() == 1142)
                    throw(new UnauthorizedUserException("AccessDenied"));
                else if(sqlE.getErrorCode() == 1062)
                    throw(new DoubleEntryException("DoubleEntry"));
                else 
                    throw(new BadConnectionException("BadConnection"));
            }
            finally
            {
                try
                {
                    if (results != null) results.close();
                }
                catch (Exception e) {};
                try
                {
                    if (statment != null) statment.close();
                }
                catch (Exception e) {};
            }
        
            
        /* Return to Buisness Section Start */ 
           
            
            
            return BPList;
        /* Return to Buisness Section Start */
        
        
        
    }
    
    //GET: Show Cars Available for a Reservation (NYI) 
    public  ArrayList<Vehicle> LimosAvailableAtTime(double time)
             throws UnauthorizedUserException, BadConnectionException, DoubleEntryException
    {
        /* Variable Section Start */
            /* Database and Query Preperation */
            PreparedStatement statment = null;
            ResultSet results = null;
            
            /* Query-Strings */
            String statString = "SELECT * FROM reservation"; //This is going to be to get all Reservations _Period_
            String vehicleSearch = "SELECT * FROM vehicle";
            //This is going to get Reservations at Pickup Times + 4 Hour Earlier
            //This is going to get Reservations at Dropoff Times + 1 Hour
            
            /* Storage */
            
            ArrayList<Reservation> AllReservations = new ArrayList<Reservation>();
            Set<Integer> BlockedLimoIDs = null; //This set is for holding the Limo Numbers that are Blocked Out

            /* Return Parameter */
            ArrayList<Vehicle> BPArrayList = new ArrayList<Vehicle>();
        /* Variable Section Stop */
        
            //Okay so this is going to be a doozy.
            //Times are Stored in... Reservations. Not Cars. 
            
                    /* So our Logic is going to have to go like this: */
            
            //Check Reservations for All times == Our Time.
                //Store the Limo Numbers within a Set
            //Check Reservations for All Pickup Times + 4 Hour Earlier == Our Time
                //Store the Limo Numbers within a Set
            //Check Reservations for All Dropoff Times + 1 Hour == Our Time
                //Store the Limo Numbers within a Set
            //Store this Set into an Array
            //Get All Vehicle ID. Store this Into an ArrayList
                //Check the Values of All Vehicle IDs against the ones that are going to be busy during the time window we need.
                //Remove any Duplicates
            //Return Pruned Array List
            //For All Vehicles Available at time Within Franchise, we do the same thing, only as a final step we do this:
            //Check each Vehicle in the List off of the FranID we've gotten. Remove any that don't match.
            //Return Double-Pruned Array List.
        
        /* TRY BLOCK START */
            
            try
            {
            /* Preparing Statment Section Start */                
                statment = con.prepareStatement(statString);
                //statment.setString();
            /* Preparing Statment Section Stop */
            /* Query Section Start */
                results = statment.executeQuery();
            /* Query Section Stop */
            
            /* Metadata Section Start*/
                //ResultSetMetaData metaData = results.getMetaData();
            /* Metadata Section Start*/
                
            /* ArrayList Prepare Section Start */
                
                
                
                while (results.next())
                {
                    Reservation temp = new Reservation();
                    
                    //rs.getBigDecimal("AMOUNT")
                    
                    temp.setAirline(results.getString("Airline"));
                    temp.setComment(results.getString("Comment"));
                    temp.setCustomerID(results.getInt("CustomerID"));
                    temp.setDate(results.getString("Date"));
                    temp.setDropOffTime(results.getDouble("DropOffTime"));
                    temp.setFlightNumber(results.getString("FlightNumber"));
                    temp.setFlightTime(results.getDouble("FlightTime"));
                    temp.setFranchiseNumber(results.getInt("FranchiseNumber"));
                    temp.setPickUpTime(results.getDouble("PickUpTime"));
                    temp.setPrice(results.getDouble("Price"));
                    temp.setReservationNumber(results.getString("ReservationNumber"));
                    temp.setStatus(results.getString("Status"));
                    temp.setVehicleID(results.getInt("VehicleID"));
                    temp.setAltAddress(results.getString("AltAddress"));
                    temp.setAltCity(results.getString("AltCity"));
                    temp.setAltState(results.getString("AltState"));
                    temp.setAltZip(results.getInt("AltZip"));
                    
                    AllReservations.add(temp);
                }
                
                //Convert ArrayList to Array here:
                
                //Reservation[] AllReservationsArray = AllReservations.toArray(new Reservation[AllReservations.size()]);
                
                //Check Reservations for All times == Our Time.
                //Store the Limo Numbers within a Set
            //Check Reservations for All Pickup Times + 4 Hour Earlier == Our Time
                
                for(int c = 0; c < AllReservations.size(); c++) //Now We're going to 
                {       
                    Reservation temp = new Reservation();
                    
                    temp = AllReservations.get(c);
                    
                    if(temp.getPickUpTime() == time)
                    {
                        BlockedLimoIDs.add(temp.getVehicleID());
                    }
                    
                    double DropoffSpacer = temp.getPickUpTime();
                    
                    for(int b = 0; b < 4; b++)
                    {
                        if( DropoffSpacer < 1)
                            DropoffSpacer = DropoffSpacer + 24;
                        else
                            DropoffSpacer = DropoffSpacer - 1;
                        
                        if( DropoffSpacer == time)
                        {
                            BlockedLimoIDs.add(temp.getVehicleID());
                        }
                    }
                    
                    DropoffSpacer = 0;
                    
                    
                    
                    DropoffSpacer = 1 + temp.getDropOffTime(); //And now Drop-off time + 1 Hour
                    
                    if(DropoffSpacer > 24)
                    {
                        DropoffSpacer = DropoffSpacer - 24;
                    }
                    
                    if(DropoffSpacer == time)
                    {
                        BlockedLimoIDs.add((temp.getVehicleID()));
                    }
                        
                    if(temp.getDropOffTime() == time)
                    {
                        BlockedLimoIDs.add(temp.getVehicleID());
                    }
                    
                }
                
                //Store the Limo Numbers within a Set
           
            //Store this Set into an Array
                
                
                int[] BlockedIDs = new int[BlockedLimoIDs.size()];

                int index = 0;

                for( Integer a : BlockedLimoIDs )
                {
                    BlockedIDs[index++] = a;
                }
                
                
                //Now we get all Vehicle IDs
                
                statment = con.prepareStatement(statString);
                
                
                results = statment.executeQuery();
                
                
                 /* Metadata Section Start*/
                //metaData = results.getMetaData();
                //columns = metaData.getColumnCount();
                //rows = results.getRow(); 
            /* Metadata Section Start*/
                
            /* ArrayList Prepare Section Start */
                
                
                
                while (results.next())
                {
                     Vehicle temp = new Vehicle();
                    
                    //rs.getBigDecimal("AMOUNT")
                    
                    temp.setCapacity(results.getInt("Capacity"));
                    temp.setCondition(results.getString("VCondition"));
                    temp.setFranchiseNumber(results.getInt("FranchiseNumber"));
                    temp.setMake(results.getString("Make"));
                    temp.setMileage(results.getInt("Milage"));
                    temp.setModel(results.getString("Model"));
                    temp.setRate(results.getDouble("RentalPrice"));
                    temp.setTablet(results.getString("Tablet"));
                    temp.setVehicleID(results.getInt("VehicleID"));
                    temp.setVin(results.getString("VIN"));
                    temp.setYear(results.getInt("MakeYear"));
                    
                    BPArrayList.add(temp);
                }

            //Get All Vehicle ID. Store this Into an ArrayList
                
                
                for(int x = 0; x < BlockedIDs.length; x++)
                {
                    for(int y = 0; y < BPArrayList.size(); y++)
                    {
                        Vehicle temp = BPArrayList.get(y);
                        
                        if(BlockedIDs[x] == temp.getVehicleID())
                            BPArrayList.remove(y);
                    }
                }
                //Check the Values of All Vehicle IDs against the ones that are going to be busy during the time window we need.
                //Remove any Duplicates
            //Return Pruned Array List
            //For All Vehicles Available at time Within Franchise, we do the same thing, only as a final step we do this:
            //Check each Vehicle in the List off of the FranID we've gotten. Remove any that don't match.
            //Return Double-Pruned Array List.
                
                
            /* ArrayList Prepare Section Stop */
            }
            catch(SQLException sqlE)
            {
                if(sqlE.getErrorCode() == 1142)
                    throw(new UnauthorizedUserException("AccessDenied"));
                else if(sqlE.getErrorCode() == 1062)
                    throw(new DoubleEntryException("DoubleEntry"));
                else 
                    throw(new BadConnectionException("BadConnection"));
            }
            finally
            {
                try
                {
                    if (results != null) results.close();
                }
                catch (Exception e) {};
                try
                {
                    if (statment != null) statment.close();
                }
                catch (Exception e) {};
            }
            
            /* TRY BLOCK STOP*/
        
            
        /* Return to Buisness Section Start */ 
           
            
            
            return BPArrayList;
        /* Return to Buisness Section Start */
        
        
        
    }
 
/******************************************************************************
 *          All Queries for the Employee table                                *
 *          includes Create and Grant Queries                                 *
 ******************************************************************************/
    
     
    public int insertEmployee(Employee emp) throws UnauthorizedUserException,
             BadConnectionException, DoubleEntryException {
        String username;
        username = generateEmpUsername(emp.getFirstName(), emp.getLastName(), emp.getPhone());
        
        try {
            PreparedStatement pStmnt = con.prepareStatement(qs.insert_employee);
            pStmnt.setString(1, emp.getFirstName());
            pStmnt.setString(2, emp.getLastName());
            pStmnt.setString(3, emp.getAddress());
            pStmnt.setString(4, emp.getCity());
            pStmnt.setString(5, emp.getState());
            pStmnt.setInt(6, emp.getZip());
            pStmnt.setString(7, emp.getPhone());
            pStmnt.setString(8, emp.getEmail());
            pStmnt.setInt(9, emp.getEmpType());
            pStmnt.setInt(10, emp.getFranchiseNumber());
            pStmnt.setString(11, username);
            pStmnt.executeUpdate();
            
            code = createUser(username, username);
            if(emp.getEmpType() == 2 && emp.getEmpType() < 100) {
                code = grantManagerRole(username);
            }else if(emp.getEmpType() == 3 && emp.getEmpType() < 100) {
                code = grantDriverRole(username);
            }    
        }catch(SQLException sqlE) {
            if(sqlE.getErrorCode() == 1142 || code == 1142)
                throw(new UnauthorizedUserException("AccessDenied"));
            else if(sqlE.getErrorCode() == 1062)
                throw(new DoubleEntryException("DoubleEntry"));
            else 
                throw(new BadConnectionException("BadConnection"));
        }
        return 1; 
    }
     
    public int updateEmployee(Employee emp) throws UnauthorizedUserException,
             BadConnectionException, DoubleEntryException {
        
        try {
            PreparedStatement pStmnt = con.prepareStatement(qs.update_employee);
            pStmnt.setString(1, emp.getFirstName());
            pStmnt.setString(2, emp.getLastName());
            pStmnt.setString(3, emp.getAddress());
            pStmnt.setString(4, emp.getCity());
            pStmnt.setString(5, emp.getState());
            pStmnt.setInt(6, emp.getZip());
            pStmnt.setString(7, emp.getPhone());
            pStmnt.setString(8, emp.getEmail());
            pStmnt.setInt(9, emp.getEmployeeID());
            pStmnt.executeUpdate();
            
        }catch(SQLException sqlE) {
            if(sqlE.getErrorCode() == 1142 || code == 1142)
                throw(new UnauthorizedUserException("AccessDenied"));
            else if(sqlE.getErrorCode() == 1062)
                throw(new DoubleEntryException("DoubleEntry"));
            else 
                throw(new BadConnectionException("BadConnection"));
        }
        return 1;
    }
       
    private int createUser(String userID, String password) throws SQLException {
        try {
            PreparedStatement pStmnt = con.prepareStatement(qs.create_user);
            pStmnt.setString(1, userID);
            pStmnt.setString(2, password);
            pStmnt.execute();
            return 1;
        }
        catch(SQLException sqlE) {
            throw(new SQLException());
        }
    }
     
    private String generateEmpUsername(String f_name, String l_name, String phone) {
        return new StringBuilder()
                   .append(f_name.charAt(0))
                   .append(l_name.charAt(0))
                   .append(phone.substring(phone.length()-4))
                   .toString();       
    }
    
    private String getEmpUsername(int type) throws SQLException{
        try {
            String temp = "";
            PreparedStatement pStmnt = con.prepareStatement(qs.get_username);
            pStmnt.setInt(1, type);
            resultSet = pStmnt.executeQuery();
            while(resultSet.next())
            {
                temp = resultSet.getString("Username");
            }
            return temp;
        }
        catch(SQLException sqlE) {
            throw(new SQLException());
        }
        finally {
            try {
                resultSet.close();
            }
            catch(Exception E) {
                
            }
        }
    }
     
    // Grants    
    private int grantTempRole(String username) {
        try {
            PreparedStatement pStmnt = con.prepareStatement(qs.grant_temp);
            pStmnt.setString(1, username);
            pStmnt.executeQuery();                    
            return 1;
        }
        catch(SQLException sqlE) {
            return sqlE.getErrorCode();
        }
    }
    
    private int revokeTempRole(String username) {
        try {
            PreparedStatement pStmnt = con.prepareStatement(qs.revoke_temp);
            pStmnt.setString(1, username);
            pStmnt.executeQuery();
            return 1;
        }
        catch(SQLException sqlE) {
            return sqlE.getErrorCode();
        }
    }
    
    private int grantManagerRole(String username) throws SQLException {
        try
        {
            if(grantTempRole(getEmpUsername(1)) == 1) {
                PreparedStatement pStmnt = con.prepareStatement(qs.grant_manager);
                for(int i = 1; i <= 9; i++) {
                    pStmnt.setString(i, username);
                }
                pStmnt.executeQuery();
            
                revokeTempRole(getEmpUsername(1));                
            }
            return 1;
        }catch(SQLException sqlE) {
            throw(new SQLException());
        }
    }
    
    private int grantDriverRole(String username) throws SQLException {
        try {
            PreparedStatement pStmnt = con.prepareStatement(qs.grant_driver);
            pStmnt.setString(1, username);
            pStmnt.setString(2, username);
            pStmnt.executeQuery();
            return 1;
        }catch(SQLException sqlE) {
            throw(new SQLException());
        }
    }
    
    private int grantOwnerRole(String username) throws SQLException {
        try {
            PreparedStatement pStmnt = con.prepareStatement(qs.grant_owner);
            for(int i = 1; i <= 4; i++) {
                pStmnt.setString(i, username);
            }
            pStmnt.executeQuery();
            return 1;
        }catch(SQLException sqlE) {
            throw(new SQLException());
        }
    }
     
    
         //GET: Get All Managers
    public ArrayList<Employee> AllManagers()
            throws UnauthorizedUserException, BadConnectionException, DoubleEntryException
    {
        /* Variable Section Start */
            /* Database and Query Preperation */
            PreparedStatement statment = null;
            ResultSet results = null;
            String statString = "SELECT * FROM `employee` WHERE 'EmployeeID' = 2";

            /* Return Parameter */
            ArrayList<Employee> BPList = new ArrayList<Employee>();
        /* Variable Section Stop */
        
        
        /* TRY BLOCK START */
            
            try
            {
            /* Preparing Statment Section Start */                
                statment = con.prepareStatement(statString);
                //statment.setInt(1, 2);
            /* Preparing Statment Section Stop */
            /* Query Section Start */
                results = statment.executeQuery();
            /* Query Section Stop */
            
            /* Metadata Section Start*/
                //ResultSetMetaData metaData = results.getMetaData();
            /* Metadata Section Start*/
                
            /* List Prepare Section Start */
                
                
                
                while (results.next())
                {
                    Employee temp = new Employee();
                    
                    //rs.getBigDecimal("AMOUNT")
                    
                    temp.setAddress(results.getString("Address"));
                    temp.setCity(results.getString("City"));
                    temp.setEmail(results.getString("Email"));
                    temp.setEmpType(results.getInt("EmpType"));
                    temp.setFirstName(results.getString("Fname"));
                    temp.setFranchiseNumber(results.getInt("FranchiseNumber"));
                    temp.setLastName(results.getString("Surname"));
                    //temp.setPassword(results.getString("Address"));
                    temp.setPhone(results.getString("Phone"));
                    temp.setState(results.getString("State"));
                    temp.setEmployeeID(results.getInt("EmployeeID"));
                    temp.setZip(results.getInt("Zip"));
                    
                    BPList.add(temp);
                }
            /* List Prepare Section Stop */
            }
            catch(SQLException sqlE)
            {
                if(sqlE.getErrorCode() == 1142)
                    throw(new UnauthorizedUserException("AccessDenied"));
                else if(sqlE.getErrorCode() == 1062)
                    throw(new DoubleEntryException("DoubleEntry"));
                else 
                    throw(new BadConnectionException("BadConnection"));
            }
            finally
            {
                try
                {
                    if (results != null) results.close();
                }
                catch (Exception e) {};
                try
                {
                    if (statment != null) statment.close();
                }
                catch (Exception e) {};
            }
        /* TRY BLOCK STOP*/
        
            
        /* Return to Buisness Section Start */ 
            return BPList;
        /* Return to Buisness Section Start */
 
    }
    
    //GET: Show One Employee's Data
    public ArrayList<Employee> SingleEmployeeData( int EmpID)
             throws UnauthorizedUserException, BadConnectionException, DoubleEntryException
    {
        /* Variable Section Start */
            /* Database and Query Preperation */
            PreparedStatement statment = null;
            ResultSet results = null;
            String statString = "SELECT * FROM `employee` WHERE 'EmployeeID' = ?";

            /* Return Parameter */
            ArrayList<Employee> BPList = new ArrayList<Employee>();
        /* Variable Section Stop */
        
        
        /* TRY BLOCK START */
            
            try
            {
            /* Preparing Statment Section Start */                
                statment = con.prepareStatement(statString);
                statment.setInt(1, EmpID);
            /* Preparing Statment Section Stop */
            /* Query Section Start */
                results = statment.executeQuery();
            /* Query Section Stop */
            
            /* Metadata Section Start*/
                //ResultSetMetaData metaData = results.getMetaData();
            /* Metadata Section Start*/
                
            /* List Prepare Section Start */
                
                
                
                while (results.next())
                {
                    Employee temp = new Employee();
                    
                    //rs.getBigDecimal("AMOUNT")
                    
                    temp.setAddress(results.getString("Address"));
                    temp.setCity(results.getString("City"));
                    temp.setEmail(results.getString("Email"));
                    temp.setEmpType(results.getInt("EmpType"));
                    temp.setFirstName(results.getString("Fname"));
                    temp.setFranchiseNumber(results.getInt("FranchiseNumber"));
                    temp.setLastName(results.getString("Surname"));
                    //temp.setPassword(results.getString("Address"));
                    temp.setPhone(results.getString("Phone"));
                    temp.setState(results.getString("State"));
                    temp.setEmployeeID(results.getInt("EmployeeID"));
                    temp.setZip(results.getInt("Zip"));
                    
                    BPList.add(temp);
                }
            /* List Prepare Section Stop */
            }
            catch(SQLException sqlE)
            {
                if(sqlE.getErrorCode() == 1142)
                    throw(new UnauthorizedUserException("AccessDenied"));
                else if(sqlE.getErrorCode() == 1062)
                    throw(new DoubleEntryException("DoubleEntry"));
                else 
                    throw(new BadConnectionException("BadConnection"));
            }
            finally
            {
                try
                {
                    if (results != null) results.close();
                }
                catch (Exception e) {};
                try
                {
                    if (statment != null) statment.close();
                }
                catch (Exception e) {};
            }
        /* TRY BLOCK STOP*/
        
            
        /* Return to Buisness Section Start */ 
           
            
            
            return BPList;
        /* Return to Buisness Section Start */
        
        
        
    }
    
             //GET: Get All Employees in a Franchise
    public ArrayList<Employee> AllEmployeesInFranchise(int FranID)
            throws UnauthorizedUserException, BadConnectionException, DoubleEntryException
    {
        /*
         * /* Variable Section Start */
            /* Database and Query Preperation * /
            //PreparedStatement statment = null;
            Statement statment = null;
            ResultSet results = null;
            String statString = "SELECT * FROM Franchise";

            /* Return Parameter * /
            ArrayList<Franchise> BPList = new ArrayList<Franchise>();
        /* Variable Section Stop * /
        
        
        /* TRY BLOCK START * /
            
            try
            {
            /* Preparing Statment Section Start * / 
                
                if(con.isValid(120));
                {
                    System.out.println("Connection is Good!");
                }
                
                if(!con.isValid(120));
                {
                    System.out.println("Connection is Bad!");
                }
                
                
                statment = con.createStatement();
                
                //statment = con.prepareStatement(statString);
                //statment.setString();
            /* Preparing Statment Section Stop * /
            /* Query Section Start * /
                results = statment.executeQuery(statString);
                
               
                while (results.next())
                {
                    System.out.println("in the loop");
                    Franchise temp = new Franchise();
                    
                    //rs.getBigDecimal("AMOUNT")
                    
                    temp.setAddress(results.getString("Address"));
                    temp.setAirportID(results.getInt("AirportID"));
                    //temp.setAirport(getFranchiseAirportName(results.getInt("AirportID")));
                    temp.setCity(results.getString("City"));
                    temp.setEmail(results.getString("Email"));
                    temp.setFranchiseID(results.getInt("FranchiseNumber"));
                    temp.setPhone(results.getString("Phone"));
                    temp.setState(results.getString("State"));
                    temp.setZip(results.getInt("Zip"));
                    
                    System.out.print(temp.toString());
                    BPList.add(temp);
                }
         */
            /* Variable Section Start */
            /* Database and Query Preperation */
            PreparedStatement statment = null;
            ResultSet results = null;
            String statString = "SELECT * FROM `employee` WHERE `FranchiseNumber` = ?";

            /* Return Parameter */
            ArrayList<Employee> BPList = new ArrayList<Employee>();
            /* Variable Section Stop */
        
        
        /* TRY BLOCK START */
            
            try
            {
            /* Preparing Statment Section Start */                
                statment = con.prepareStatement(statString);
                statment.setInt(1, FranID);
            /* Preparing Statment Section Stop */
            /* Query Section Start */
                results = statment.executeQuery();
                
            /* Query Section Stop */
            
            
                
            /* List Prepare Section Start */
                
                
                
                while (results.next())
                {
                    Employee temp = new Employee();
                    
                    //rs.getBigDecimal("AMOUNT")
                    
                    temp.setAddress(results.getString("Address"));
                    temp.setCity(results.getString("City"));
                    temp.setEmail(results.getString("Email"));
                    temp.setEmpType(results.getInt("EmpType"));
                    temp.setFirstName(results.getString("Fname"));
                    temp.setFranchiseNumber(results.getInt("FranchiseNumber"));
                    temp.setLastName(results.getString("Surname"));
                    //temp.setPassword(results.getString("Address"));
                    temp.setPhone(results.getString("Phone"));
                    temp.setState(results.getString("State"));
                    temp.setEmployeeID(results.getInt("EmployeeID"));
                    temp.setZip(results.getInt("Zip"));
                    
                    BPList.add(temp);
                }
                
                
                /* Metadata Section Start*/
                //ResultSetMetaData metaData = results.getMetaData();
            /* Metadata Section Start*/
            /* List Prepare Section Stop */
            }
            catch(SQLException sqlE)
            {
                if(sqlE.getErrorCode() == 1142)
                    throw(new UnauthorizedUserException("AccessDenied"));
                else if(sqlE.getErrorCode() == 1062)
                    throw(new DoubleEntryException("DoubleEntry"));
                else 
                    throw(new BadConnectionException("BadConnection"));
            }
            finally
            {
                try
                {
                    if (results != null) results.close();
                }
                catch (Exception e) {};
                try
                {
                    if (statment != null) statment.close();
                }
                catch (Exception e) {};
            }
        /* TRY BLOCK STOP*/
        
            
        /* Return to Buisness Section Start */ 
            return BPList;
        /* Return to Buisness Section Start */
 
    }
    
    //GET: Get EmployeeIDs
    public ArrayList<Integer> AllEmpIDInFranchise(int FranID)
            throws UnauthorizedUserException, BadConnectionException, DoubleEntryException
    {
        /* Variable Section Start */
            /* Database and Query Preperation */
            PreparedStatement statment = null;
            ResultSet results = null;
            String statString = "SELECT * FROM `employee` WHERE 'EmployeeID' = ?";

            /* Return Parameter */
            ArrayList<Integer> BPList = new ArrayList<Integer>();
        /* Variable Section Stop */
        
        
        /* TRY BLOCK START */
            
            try
            {
            /* Preparing Statment Section Start */                
                statment = con.prepareStatement(statString);
                statment.setInt(1, FranID);
            /* Preparing Statment Section Stop */
            /* Query Section Start */
                results = statment.executeQuery();
                
            /* Query Section Stop */
            
            /* Metadata Section Start*/
                //ResultSetMetaData metaData = results.getMetaData();
            /* Metadata Section Start*/
                
            /* List Prepare Section Start */
                
                
                
                while (results.next())
                {
                    int temp = 0;
                    
                    //rs.getBigDecimal("AMOUNT")
                    
                    
                    temp = results.getInt("EmployeeID");
                    
                    BPList.add(temp);
                }
            /* List Prepare Section Stop */
            }
            catch(SQLException sqlE)
            {
                if(sqlE.getErrorCode() == 1142)
                    throw(new UnauthorizedUserException("AccessDenied"));
                else if(sqlE.getErrorCode() == 1062)
                    throw(new DoubleEntryException("DoubleEntry"));
                else 
                    throw(new BadConnectionException("BadConnection"));
            }
            finally
            {
                try
                {
                    if (results != null) results.close();
                }
                catch (Exception e) {};
                try
                {
                    if (statment != null) statment.close();
                }
                catch (Exception e) {};
            }
        /* TRY BLOCK STOP*/
        
            
        /* Return to Buisness Section Start */ 
            return BPList;
        /* Return to Buisness Section Start */
 
    }
    
    
     //GET: List Drivers in a Franchise
    public ArrayList<Employee> DriversInAFranchise(int FranID)
            throws UnauthorizedUserException, BadConnectionException, DoubleEntryException
    {
        /* Variable Section Start */
            /* Database and Query Preperation */
            PreparedStatement statment = null;
            ResultSet results = null;
            String statString = "SELECT * FROM `employee` WHERE 'EmpType' = 3 AND 'FranchiseNumber' = ?";

            /* Return Parameter */
            ArrayList<Employee> BPList = new ArrayList<Employee>();
        /* Variable Section Stop */
        
        
        /* TRY BLOCK START */
            
            try
            {
                System.out.println("In Try Block");
            /* Preparing Statment Section Start */                
                statment = con.prepareStatement(statString);
                statment.setInt(1, FranID);
                System.out.println("Executed Query");
            /* Preparing Statment Section Stop */
            /* Query Section Start */
                results = statment.executeQuery();
                
            /* Query Section Stop */
            
            /* Metadata Section Start*/
                //ResultSetMetaData metaData = results.getMetaData();
            /* Metadata Section Start*/
                
            /* List Prepare Section Start */
                
                
                
                while (results.next())
                {
                    System.out.println("In While Loop");
                    Employee temp = new Employee();
                    
                    //rs.getBigDecimal("AMOUNT")
                    
                    temp.setAddress(results.getString("Address"));
                    temp.setCity(results.getString("City"));
                    temp.setEmail(results.getString("Email"));
                    temp.setEmpType(results.getInt("EmpType"));
                    temp.setFirstName(results.getString("Fname"));
                    temp.setFranchiseNumber(results.getInt("FranchiseNumber"));
                    temp.setLastName(results.getString("Surname"));
                    //temp.setPassword(results.getString("Address"));
                    temp.setPhone(results.getString("Phone"));
                    temp.setState(results.getString("State"));
                    temp.setEmployeeID(results.getInt("EmployeeID"));
                    temp.setZip(results.getInt("Zip"));
                    
                    System.out.print(temp.toString());
                    BPList.add(temp);
                }
            /* List Prepare Section Stop */
            }
            catch(SQLException sqlE)
            {
                if(sqlE.getErrorCode() == 1142)
                    throw(new UnauthorizedUserException("AccessDenied"));
                else if(sqlE.getErrorCode() == 1062)
                    throw(new DoubleEntryException("DoubleEntry"));
                else
                {
                    System.out.println(sqlE);
                    throw(new BadConnectionException("BadConnection"));
                }
            }
            finally
            {
                try
                {
                    if (results != null) results.close();
                }
                catch (Exception e) {};
                try
                {
                    if (statment != null) statment.close();
                }
                catch (Exception e) {};
            }
        /* TRY BLOCK STOP*/
        
            
        /* Return to Buisness Section Start */ 
           
            
            
            return BPList;
        /* Return to Buisness Section Start */
        
        
        
    }
    
     //DELETE: Employee
    public boolean RemoveEmployee(int ID)
            throws UnauthorizedUserException, BadConnectionException, DoubleEntryException
    {
        /* Variable Section Start */
        Statement query = null;
        PreparedStatement statement = null;
        PreparedStatement statement2 = null;
        PreparedStatement statement3 = null;
        ResultSet results = null;
        int success = -1;
        String Username = null;
        /* Variable Section Stop */
        
        /* Preparing Statment Section Start */
        String MyQuery = "DELETE * FROM `employee` WHERE 'EmployeeID' = ?";
        String statString = "SELECT * FROM `employee` WHERE 'EmployeeID' = ?";
        String DropUser = "DROP USER ?@'localhost'";

        /* Preparing Statment  Section Stop */
        
        
        /* TRY BLOCK START */
            
            try
            {
            /* Preparing Statment Section Start */                
                statement = con.prepareStatement(statString);
                statement.setInt(1, ID);
            /* Preparing Statment Section Stop */
            /* Query Section Start */
                results = statement.executeQuery();
                
                
            /* Query Section Stop */
            
            /* Metadata Section Start*/
                //ResultSetMetaData metaData = results.getMetaData();
            /* Metadata Section Start*/
                
            /* Drop Use Preperation Section Start*/
                Username = results.getString("Username");
                
                statement2 = con.prepareStatement(DropUser);
                statement2.setString(1, Username);
            /* Drop Use Preperation Section Start*/
                
        
            /* MySQL User Drop Section Start*/
                success = statement.executeUpdate(); //This is where we attempt to drop the user.
                
                if(success == 1)
                {
                    /* Delete Employee Preperation Section Start*/
                    
                    statement3 = con.prepareStatement(MyQuery);
                    
                    statement3.setInt(1, ID);
                    
                    
                    /* Query Section Start */
                    success = statement3.executeUpdate();
                    /* Query Section Stop* /

                    
                    /* Delete Employee Preperation Section Stop*/
                    
                    /* Return to Buisness Section Start */
                    if(success == 1)
                        return true;
                    
                    if(success == 0 || success == -1)
                        return false; 
                    /* Return to Buisness Section Start */
                    //return true;
                }
                
                if(success == 0 || success == -1)
                    return false;     
            /* MySQL User Drop Section Stop*/    
            }
            catch(SQLException sqlE)
            {
                if(sqlE.getErrorCode() == 1142)
                    throw(new UnauthorizedUserException("AccessDenied"));
                else if(sqlE.getErrorCode() == 1062)
                    throw(new DoubleEntryException("DoubleEntry"));
                else 
                    throw(new BadConnectionException("BadConnection"));
            }
            finally
            {
                try
                {
                    if (results != null) results.close();
                }
                catch (Exception e) {};
                try
                {
                    if (query != null) query.close();
                }
                catch (Exception e) {};
            }
           /* TRY BLOCK STOP */
            
            return false;
    } 
    
/******************************************************************************
 *          All Queries for the Maintenance table                             *
 ******************************************************************************/
    /*
    public int insertMaintenance(Maintenance fixit) throws UnauthorizedUserException,
            BadConnectionException, DoubleEntryException {
    
        try {
           PreparedStatement pStmnt = 
                   con.prepareStatement(qs.insert_maintenance);
           pStmnt.setString(1, fixit.getServiceNumber());
           pStmnt.setInt(2, fixit.getVehicleID());
           pStmnt.setString(3, fixit.getServiceType());
           pStmnt.setDouble(4, fixit.getServiceCost());
           pStmnt.executeQuery();
        }
        catch(SQLException sqlE) {
            if(sqlE.getErrorCode() == 1142)
                throw(new UnauthorizedUserException("AccessDenied"));
            else if(sqlE.getErrorCode() == 1062)
                throw(new DoubleEntryException("DoubleEntry"));
            else 
                throw(new BadConnectionException("BadConnection"));
        }
        return 1;
    }
    
    public void updateMaintenance(Maintenance fixit) throws UnauthorizedUserException,
            BadConnectionException, DoubleEntryException {
    
        try {
           PreparedStatement pStmnt = 
                   con.prepareStatement(qs.insert_maintenance);
           pStmnt.setInt(1, fixit.getVehicleID());
           pStmnt.setString(2, fixit.getServiceType());
           pStmnt.setDouble(3, fixit.getServiceCost());
           pStmnt.setString(4, fixit.getServiceNumber());
           pStmnt.setInt(5, fixit.getMaintIndex());
           pStmnt.executeQuery();
        }
        catch(SQLException sqlE) {
            if(sqlE.getErrorCode() == 1142)
                throw(new UnauthorizedUserException("AccessDenied"));
            else if(sqlE.getErrorCode() == 1062)
                throw(new DoubleEntryException("DoubleEntry"));
            else 
                throw(new BadConnectionException("BadConnection"));
        }
    }
    */
    
/******************************************************************************
 *          ALl Queries for the Customer table
 ******************************************************************************/
    
    public int insertCustomer(Customer cust) throws UnauthorizedUserException,
             BadConnectionException, DoubleEntryException {
        String username;
        username = cust.getEmail().substring(0, cust.getEmail().indexOf("@"));
        
         try {
            PreparedStatement pStmnt = con.prepareStatement(qs.insert_customer);
            pStmnt.setString(1, cust.getFirstName());
            pStmnt.setString(2, cust.getLastName());
            pStmnt.setString(3, cust.getAddress());
            pStmnt.setString(4, cust.getCity());
            pStmnt.setString(5, cust.getState());
            pStmnt.setInt(6, cust.getZip());
            pStmnt.setString(7, cust.getPhone());
            pStmnt.setString(8, cust.getEmail());
            pStmnt.setString(9, username);
            pStmnt.executeUpdate();
            
            code = createUser(username, username);
            code = grantCustomerRole(username);
        }catch(SQLException sqlE) {
            if(sqlE.getErrorCode() == 1142 || code == 1142)
                throw(new UnauthorizedUserException("AccessDenied"));
            else if(sqlE.getErrorCode() == 1062)
                throw(new DoubleEntryException("DoubleEntry"));
            else 
                throw(new BadConnectionException("BadConnection"));
        }
        return 1;
    }
    
    public int updateCustomer(Customer cust) throws UnauthorizedUserException,
             BadConnectionException, DoubleEntryException {
               
         try {
            PreparedStatement pStmnt = con.prepareStatement(qs.update_customer);
            pStmnt.setString(1, cust.getFirstName());
            pStmnt.setString(2, cust.getLastName());
            pStmnt.setString(3, cust.getAddress());
            pStmnt.setString(4, cust.getCity());
            pStmnt.setString(5, cust.getState());
            pStmnt.setInt(6, cust.getZip());
            pStmnt.setString(7, cust.getPhone());
            pStmnt.setString(8, cust.getEmail());
            pStmnt.setInt(9, cust.getCustomerID());
            pStmnt.executeUpdate();
                        
        }catch(SQLException sqlE) {
            if(sqlE.getErrorCode() == 1142 || code == 1142)
                throw(new UnauthorizedUserException("AccessDenied"));
            else if(sqlE.getErrorCode() == 1062)
                throw(new DoubleEntryException("DoubleEntry"));
            else 
                throw(new BadConnectionException("BadConnection"));
        }
        return 1;
    }
    
    private int grantCustomerRole(String username) throws SQLException {
        try {
            PreparedStatement pStmnt = con.prepareStatement(qs.grant_customer);
            pStmnt.setString(1, username);
            pStmnt.setString(2, username);
            pStmnt.executeQuery();
            return 1;
        }catch(SQLException sqlE) {
            throw(new SQLException());
        }
        
    }
    
    
    //GET: Show One Customer's Data
    public  ArrayList<Customer> SingleCustomerData(int CustID)
             throws UnauthorizedUserException, BadConnectionException, DoubleEntryException
    {
        /* Variable Section Start */
            /* Database and Query Preperation */
            PreparedStatement statment = null;
            ResultSet results = null;
            String statString = "SELECT * FROM `customer` WHERE 'CustomerID' = ?";

            /* Return Parameter */
            ArrayList<Customer> BPArrayList = new ArrayList<Customer>();
        /* Variable Section Stop */
        
        
        /* TRY BLOCK START */
            
            try
            {
            /* Preparing Statment Section Start */                
                statment = con.prepareStatement(statString);
                statment.setInt(1, CustID);
            /* Preparing Statment Section Stop */
            /* Query Section Start */
                results = statment.executeQuery();
                
            /* Query Section Stop */
            
            /* Metadata Section Start*/
                //ResultSetMetaData metaData = results.getMetaData();
            /* Metadata Section Start*/
                
            /* ArrayList Prepare Section Start */
                
                
                
                while (results.next())
                {
                    Customer temp = new Customer();
                    
                    //rs.getBigDecimal("AMOUNT")
                    
                    temp.setAddress(results.getString("Address"));
                    temp.setCity(results.getString("City"));
                    temp.setCustomerID(results.getInt("CustomerID"));
                    temp.setEmail(results.getString("Email"));
                    temp.setFirstName(results.getString("Fname"));
                    temp.setLastName(results.getString("Surname"));
                    temp.setMemberID(results.getString("MemberID"));
                    //temp.setPassword(statString);
                    temp.setPhone(results.getString("Phone"));
                    temp.setReservationCount(results.getInt("ReservationCount"));
                    temp.setState(results.getString("State"));
                    //temp.setUserID(results.getString("Address"));
                    temp.setZip(results.getInt("Zip"));
                    
                    BPArrayList.add(temp);
                }
            /* ArrayList Prepare Section Stop */
            }
            catch(SQLException sqlE)
            {
                if(sqlE.getErrorCode() == 1142)
                    throw(new UnauthorizedUserException("AccessDenied"));
                else if(sqlE.getErrorCode() == 1062)
                    throw(new DoubleEntryException("DoubleEntry"));
                else 
                    throw(new BadConnectionException("BadConnection"));
            }
            finally
            {
                try
                {
                    if (results != null) results.close();
                }
                catch (Exception e) {};
                try
                {
                    if (statment != null) statment.close();
                }
                catch (Exception e) {};
            }
        /* TRY BLOCK STOP*/
        
            
        /* Return to Buisness Section Start */ 
           
            
            
            return BPArrayList;
        /* Return to Buisness Section Start */
        
        
        
    }
    
        //GET: Show All Customers' Data (For One Franchise) (NYI)
    public  ArrayList<Customer> AllCustomersInFranchise( int FranID)
             throws UnauthorizedUserException, BadConnectionException, DoubleEntryException
    {
        /* Variable Section Start */
            /* Database and Query Preperation */
            PreparedStatement statment = null;
            ResultSet results = null;
            String statString = "SELECT * FROM `customer` WHERE 'CustomerID' = ?";
            //Set<Integer> CustomerIDHolder;
            ArrayList<Integer> CustomerIDs = new ArrayList<Integer>();
            

            /* Return Parameter */
            ArrayList<Customer> BPArrayList = new ArrayList<Customer>();
        /* Variable Section Stop */
        
        
        /* TRY BLOCK START */
            
            //So we need to...
            
            //Call up Reservations for a Franchise
            //Get all UNIQUE Customer IDs out of all of those reservations.
            //Get all Customers for those IDs.
            
            /* Customer IDs Acquisition Start */
            System.out.println("Calling CustomerIDsFromReservationsForFranchise");
                CustomerIDs = CustomerIDsFromReservationsForFranchise(FranID);
            /* Customer IDs Acquisition Start */
            
            try
            {
                if(!results.next())
                {
                    System.out.println("Start of loop");
                    
                    Customer temp = new Customer();

                                //rs.getBigDecimal("AMOUNT")

                                temp.setAddress(null);
                                temp.setCity(null);
                                temp.setCustomerID(null);
                                temp.setEmail(null);
                                temp.setFirstName("No Customers ");
                                temp.setLastName("Booked at Your Franchise.");
                                temp.setMemberID(null);
                                //temp.setPassword(statString);
                                temp.setPhone(null);
                                temp.setReservationCount(null);
                                temp.setState(null);
                                //temp.setUserID(results.getString("Address"));
                                temp.setZip(null);

                                BPArrayList.add(temp);
                }
                else if(CustomerIDs.size() > 0 && CustomerIDs.get(0) > 0 && results.next())
                {
                    for(int c = 0; c < CustomerIDs.size(); c++)
                    {
                        /* Preparing Statment Section Start */                
                            statment = con.prepareStatement(statString);
                            statment.setInt(1, CustomerIDs.get(c));
                        /* Preparing Statment Section Stop */
                        /* Query Section Start */
                            results = statment.executeQuery();

                        /* Query Section Stop */

                        /* Metadata Section Start*/
                           // ResultSetMetaData metaData = results.getMetaData();
                           // int columns = metaData.getColumnCount();
                           // int rows = results.getRow(); 
                        /* Metadata Section Start*/

                        /* ArrayList Prepare Section Start */



                            while (results.next())
                            {
                                System.out.println("Start of loop");
                                Customer temp = new Customer();

                                //rs.getBigDecimal("AMOUNT")

                                temp.setAddress(results.getString("Address"));
                                temp.setCity(results.getString("City"));
                                temp.setCustomerID(results.getInt("CustomerID"));
                                temp.setEmail(results.getString("Email"));
                                temp.setFirstName(results.getString("Fname"));
                                temp.setLastName(results.getString("Surname"));
                                temp.setMemberID(results.getString("MemberID"));
                                //temp.setPassword(statString);
                                temp.setPhone(results.getString("Phone"));
                                temp.setReservationCount(results.getInt("ReservationCount"));
                                temp.setState(results.getString("State"));
                                //temp.setUserID(results.getString("Address"));
                                temp.setZip(results.getInt("Zip"));

                                BPArrayList.add(temp);
                                System.out.println("End of loop");
                            }
                    }
                }
                else //If there's no IDs being returned...
                {
                    System.out.println("Start of loop");
                    
                    Customer temp = new Customer();

                                //rs.getBigDecimal("AMOUNT")

                                temp.setAddress(null);
                                temp.setCity(null);
                                temp.setCustomerID(null);
                                temp.setEmail(null);
                                temp.setFirstName("No Customers ");
                                temp.setLastName("Booked at Your Franchise.");
                                temp.setMemberID(null);
                                //temp.setPassword(statString);
                                temp.setPhone(null);
                                temp.setReservationCount(null);
                                temp.setState(null);
                                //temp.setUserID(results.getString("Address"));
                                temp.setZip(null);

                                BPArrayList.add(temp);
                }
            /* ArrayList Prepare Section Stop */
            }
            catch(SQLException sqlE)
            {
                if(sqlE.getErrorCode() == 1142)
                    throw(new UnauthorizedUserException("AccessDenied"));
                else if(sqlE.getErrorCode() == 1062)
                    throw(new DoubleEntryException("DoubleEntry"));
                else 
                    throw(new BadConnectionException("BadConnection"));
            }
            finally
            {
                try
                {
                    if (results != null) results.close();
                }
                catch (Exception e) {};
                try
                {
                    if (statment != null) statment.close();
                }
                catch (Exception e) {};
            }
        /* TRY BLOCK STOP*/
        
            
        /* Return to Buisness Section Start */ 
           
            
            
            return BPArrayList;
        /* Return to Buisness Section Start */
        
        
        
    }
    
     //GET: List CustomerIDs For Franchise
    public  ArrayList<Integer> CustomerIDsFromReservationsForFranchise(int FranID)
             throws UnauthorizedUserException, BadConnectionException, DoubleEntryException
    {
        System.out.println("Start of Method.");
        /* Variable Section Start */
            /* Database and Query Preperation */
            PreparedStatement statment = null;
            ResultSet results = null;
            String statString = "SELECT * FROM `reservation` WHERE 'FranchiseNumber' = ?";
            Set<Integer> CustomerIDHolder = null;
            ArrayList<Integer> list = new ArrayList<Integer>();
            boolean holder;
            
            System.out.println("Made Variables.");

            /* Return Parameter */
            //ArrayList<Reservation> BPArrayList = new ArrayList<Reservation>();
        /* Variable Section Stop */
        
        
        /* TRY BLOCK START */
            
            try
            {
            /* Preparing Statment Section Start */                
                statment = con.prepareStatement(statString);
                statment.setInt(1, FranID);
                //statment.setInt(2, CustID);
            /* Preparing Statment Section Stop */
            /* Query Section Start */
                System.out.println("Getting Results");
                results = statment.executeQuery();
                System.out.println("Got Results.");
                
            /* Query Section Stop */
            
            /* Metadata Section Start*/
                //ResultSetMetaData metaData = results.getMetaData();
            /* Metadata Section Start*/
                
            /* ArrayList Prepare Section Start */
                
                System.out.println("Checking for Null.");
                if(results != null && !results.next())
                {
                    System.out.println("If it's not Null...");
                    while (results != null && results.next())
                    {
                        System.out.println("In the While Loop");
                        

                        //results.getBigDecimal("AMOUNT")
                        
                        System.out.println("Trying to add a CustID...");
                        System.out.println("AddingID: " + results.getInt("CustomerID"));

                        holder = CustomerIDHolder.add(results.getInt("CustomerID"));
                        System.out.println("Holder is: " + holder);

                        //BPArrayList.add(temp);
                    }
                    
                    if(!CustomerIDHolder.isEmpty() && results != null)
                    {
                        System.out.println("Got into CustomerIDHolder.size() != 0");
                        int[] CustIDs = new int[CustomerIDHolder.size()];

                        int index = 0;

                        for( Integer a : CustomerIDHolder )
                        {
                            System.out.println("Adding Customer IDs");
                            CustIDs[index++] = a;
                        }


                        
                        for (int s : CustIDs)
                        {
                            list.add(s);
                        }
                        
                        return list;
                    }
                    
                    if(!CustomerIDHolder.isEmpty() || !results.next() || results == null)
                    {
                         System.out.println("If it is Null");
                            //ArrayList<Integer> list = new ArrayList<Integer>();
                            list.add(-1);
                            return list;
                    }
                }
                else 
                {
                    System.out.println("If it is Null");
                    //ArrayList<Integer> list = new ArrayList<Integer>();
                    list.add(-1);
                    return list;
                }
                
            /* ArrayList Prepare Section Stop */
                
                return list;
            }
            catch(SQLException sqlE)
            {
                System.out.println(sqlE.getErrorCode() + "\n" + sqlE.getMessage());
                if(sqlE.getErrorCode() == 1142)
                    throw(new UnauthorizedUserException("AccessDenied"));
                else if(sqlE.getErrorCode() == 1062)
                    throw(new DoubleEntryException("DoubleEntry"));
                else 
                    throw(new BadConnectionException("BadConnection"));
            }
            finally
            {
                try
                {
                    if (results != null) results.close();
                }
                catch (Exception e) {};
                try
                {
                    if (statment != null) statment.close();
                }
                catch (Exception e) {};
            }
        /* TRY BLOCK STOP*/
        
            
        /* Return to Buisness Section Start */ 
//            return list;
        /* Return to Buisness Section Start */
    }
    
     //DELETE: Customer
    public boolean RemoveCustomer(int ID)
            throws UnauthorizedUserException, BadConnectionException, DoubleEntryException
    {
        /* Variable Section Start */
        Statement query = null;
        PreparedStatement statement = null;
        ResultSet results = null;
        int success = -1;
        String Username = null;
        /* Variable Section Stop */
        
        /* Preparing Statment Section Start */
        String MyQuery = "DELETE * FROM `customer` WHERE 'CustomerID' = ?";
        String statString = "SELECT * FROM `customer` WHERE 'CustomerID' = ?";
        String DropUser = "DROP USER ?@'localhost'";

        /* Preparing Statment  Section Stop */
        
        
        /* TRY BLOCK START */
            
            try
            {
            /* Preparing Statment Section Start */                
                statement = con.prepareStatement(statString);
                statement.setInt(1, ID);
            /* Preparing Statment Section Stop */
            /* Query Section Start */
                results = statement.executeQuery();
                
                
            /* Query Section Stop */
            
            /* Metadata Section Start*/
                //ResultSetMetaData metaData = results.getMetaData();
            /* Metadata Section Start*/
                
            /* Drop Use Preperation Section Start*/
                Username = results.getString("Username");
                
                statement = con.prepareStatement(DropUser);
                statement.setString(1, Username);
            /* Drop Use Preperation Section Start*/
                
        
            /* MySQL User Drop Section Start*/
                success = statement.executeUpdate(); //This is where we attempt to drop the user.
                
                if(success == 1)
                {
                    /* Delete Employee Preperation Section Start*/
                    
                    statement = con.prepareStatement(MyQuery);
                    
                    statement.setInt(1, ID);
                    
                    
                    /* Query Section Start */
                    success = statement.executeUpdate();
                    /* Query Section Stop* /

                    
                    /* Delete Employee Preperation Section Stop*/
                    
                    /* Return to Buisness Section Start */
                    if(success == 1)
                        return true;
                    
                    if(success == 0 || success == -1)
                        return false; 
                    /* Return to Buisness Section Start */
                    //return true;
                }
                
                if(success == 0 || success == -1)
                    return false;     
            /* MySQL User Drop Section Stop*/    
            }
            catch(SQLException sqlE)
            {
                if(sqlE.getErrorCode() == 1142)
                    throw(new UnauthorizedUserException("AccessDenied"));
                else if(sqlE.getErrorCode() == 1062)
                    throw(new DoubleEntryException("DoubleEntry"));
                else 
                    throw(new BadConnectionException("BadConnection"));
            }
            finally
            {
                try
                {
                    if (results != null) results.close();
                }
                catch (Exception e) {};
                try
                {
                    if (query != null) query.close();
                }
                catch (Exception e) {};
            }
           /* TRY BLOCK STOP */
            
            return false;
    } 
    
    
/******************************************************************************
 *          All Queries for the Airport table                                 *
 ******************************************************************************/
    /*
    public int insertAirport(Airport air) throws UnauthorizedUserException,
            DoubleEntryException, BadConnectionException {
        try {
            PreparedStatement pStmnt = con.prepareStatement(qs.insert_airport);
            pStmnt.setString(1, air.getAirportName);
            pStmnt.setString(2, air.getAddress);
            pStmnt.setString(3, air.getCity);
            pStmnt.setString(4, air.getState);
            pStmnt.setInt(5, air.getZip);
            pStmnt.executeQuery();
            return 1;
        }catch(SQLException sqlE) {
            if(sqlE.getErrorCode() == 1142)
                throw(new UnauthorizedUserException("AccessDenied"));
            else if(sqlE.getErrorCode() == 1062)
                throw(new DoubleEntryException("DoubleEntry"));
            else 
                throw(new BadConnectionException("BadConnection"));
        }
    }
    
    public int updateAirport(Airport air) throws UnauthorizedUserException,
            DoubleEntryException, BadConnectionException {
        try {
            PreparedStatement pStmnt = con.prepareStatement(qs.update_airport);
            pStmnt.setString(1, air.getAirportName);
            pStmnt.setString(2, air.getAddress);
            pStmnt.setString(3, air.getCity);
            pStmnt.setString(4, air.getState);
            pStmnt.setInt(5, air.getZip);
            pStmnt.setInt(6, air.getAirportID);
            pStmnt.executeQuery();
            return 1;
        }catch(SQLException sqlE) {
            if(sqlE.getErrorCode() == 1142)
                throw(new UnauthorizedUserException("AccessDenied"));
            else if(sqlE.getErrorCode() == 1062)
                throw(new DoubleEntryException("DoubleEntry"));
            else 
                throw(new BadConnectionException("BadConnection"));
        }
    }
    */
    
/******************************************************************************
 *          All Queries for the Reservation table                                 *
 ******************************************************************************/
    //GET: Show Reservations by Customer in all Franchises
    public  ArrayList<Reservation> ReservationsOneCustomerAllFranchises(int CustID)
             throws UnauthorizedUserException, BadConnectionException, DoubleEntryException
    {
        /* Variable Section Start */
            /* Database and Query Preperation */
            PreparedStatement statment = null;
            ResultSet results = null;
            String statString = "SELECT * FROM `reservation` WHERE 'CustomerID' = ?";

            /* Return Parameter */
            ArrayList<Reservation> BPArrayList = new ArrayList<Reservation>();
        /* Variable Section Stop */
        
        
        /* TRY BLOCK START */
            
            try
            {
            /* Preparing Statment Section Start */                
                statment = con.prepareStatement(statString);
                statment.setInt(1, CustID);
                //statment.setInt(2, CustID);
            /* Preparing Statment Section Stop */
            /* Query Section Start */
                results = statment.executeQuery();
                
            /* Query Section Stop */
            
            /* Metadata Section Start*/
                //ResultSetMetaData metaData = results.getMetaData();
            /* Metadata Section Start*/
                
            /* ArrayList Prepare Section Start */
                
                
                
                while (results.next())
                {
                    Reservation temp = new Reservation();
                    
                    //results.getBigDecimal("AMOUNT")
                    
                    
                    temp.setAirline(results.getString("Airline"));
                    temp.setComment(results.getString("Comment"));
                    temp.setCustomerID(results.getInt("CustomerID"));
                    temp.setDate(results.getString("Date"));
                    temp.setDropOffTime(results.getDouble("DropOffTime"));
                    temp.setFlightNumber(results.getString("FlightNumber"));
                    temp.setFlightTime(results.getDouble("FlightTime"));
                    temp.setFranchiseNumber(results.getInt("FranchiseNumber"));
                    temp.setPickUpTime(results.getDouble("PickUpTime"));
                    temp.setPrice(results.getDouble("Price"));
                    temp.setReservationNumber(results.getString("ReservationNumber"));
                    temp.setStatus(results.getString("Status"));
                    temp.setVehicleID(results.getInt("VehicleID"));
                    temp.setAltAddress(results.getString("AltAddress"));
                    temp.setAltCity(results.getString("AltCity"));
                    temp.setAltState(results.getString("AltState"));
                    temp.setAltZip(results.getInt("AltZip"));
                    
                    BPArrayList.add(temp);
                }
            /* ArrayList Prepare Section Stop */
            }
            catch(SQLException sqlE)
            {
                if(sqlE.getErrorCode() == 1142)
                    throw(new UnauthorizedUserException("AccessDenied"));
                else if(sqlE.getErrorCode() == 1062)
                    throw(new DoubleEntryException("DoubleEntry"));
                else 
                    throw(new BadConnectionException("BadConnection"));
            }
            finally
            {
                try
                {
                    if (results != null) results.close();
                }
                catch (Exception e) {};
                try
                {
                    if (statment != null) statment.close();
                }
                catch (Exception e) {};
            }
        /* TRY BLOCK STOP*/
        
            
        /* Return to Buisness Section Start */ 
            return BPArrayList;
        /* Return to Buisness Section Start */
    }
    
    //GET: Show Reservations by Customer in one Franchise
    public  ArrayList<Reservation> ReservationsByCustomerForFranchise( int FranID, int CustID)
             throws UnauthorizedUserException, BadConnectionException, DoubleEntryException
    {
        /* Variable Section Start */
            /* Database and Query Preperation */
            PreparedStatement statment = null;
            ResultSet results = null;
            String statString = "SELECT * FROM `reservation` WHERE 'FranchiseNumber' = ? AND 'CustomerID' = ?";

            /* Return Parameter */
            ArrayList<Reservation> BPArrayList = new ArrayList<Reservation>();
        /* Variable Section Stop */
        
        
        /* TRY BLOCK START */
            
            try
            {
            /* Preparing Statment Section Start */                
                statment = con.prepareStatement(statString);
                statment.setInt(1, FranID);
                statment.setInt(2, CustID);
            /* Preparing Statment Section Stop */
            /* Query Section Start */
                results = statment.executeQuery();
                
            /* Query Section Stop */
            
            /* Metadata Section Start*/
                //ResultSetMetaData metaData = results.getMetaData();
            /* Metadata Section Start*/
                
            /* ArrayList Prepare Section Start */
                
                
                
                while (results.next())
                {
                    Reservation temp = new Reservation();
                    
                    //results.getBigDecimal("AMOUNT")
                    
                    
                    temp.setAirline(results.getString("Airline"));
                    temp.setComment(results.getString("Comment"));
                    temp.setCustomerID(results.getInt("CustomerID"));
                    temp.setDate(results.getString("Date"));
                    temp.setDropOffTime(results.getDouble("DropOffTime"));
                    temp.setFlightNumber(results.getString("FlightNumber"));
                    temp.setFlightTime(results.getDouble("FlightTime"));
                    temp.setFranchiseNumber(results.getInt("FranchiseNumber"));
                    temp.setPickUpTime(results.getDouble("PickUpTime"));
                    temp.setPrice(results.getDouble("Price"));
                    temp.setReservationNumber(results.getString("ReservationNumber"));
                    temp.setStatus(results.getString("Status"));
                    temp.setVehicleID(results.getInt("VehicleID"));
                    temp.setAltAddress(results.getString("AltAddress"));
                    temp.setAltCity(results.getString("AltCity"));
                    temp.setAltState(results.getString("AltState"));
                    temp.setAltZip(results.getInt("AltZip"));
                    
                    BPArrayList.add(temp);
                }
            /* ArrayList Prepare Section Stop */
            }
            catch(SQLException sqlE)
            {
                if(sqlE.getErrorCode() == 1142)
                    throw(new UnauthorizedUserException("AccessDenied"));
                else if(sqlE.getErrorCode() == 1062)
                    throw(new DoubleEntryException("DoubleEntry"));
                else 
                    throw(new BadConnectionException("BadConnection"));
            }
            finally
            {
                try
                {
                    if (results != null) results.close();
                }
                catch (Exception e) {};
                try
                {
                    if (statment != null) statment.close();
                }
                catch (Exception e) {};
            }
        /* TRY BLOCK STOP*/
        
            
        /* Return to Buisness Section Start */ 
            return BPArrayList;
        /* Return to Buisness Section Start */
    }
    
     //GET: List Reservations in a Franchise
    public  ArrayList<Reservation> ReservationsForFranchise(int FranID)
             throws UnauthorizedUserException, BadConnectionException, DoubleEntryException
    {
        /* Variable Section Start */
            /* Database and Query Preperation */
            PreparedStatement statment = null;
            ResultSet results = null;
            String statString = "SELECT * FROM `reservation` WHERE 'FranchiseNumber' = ?";

            /* Return Parameter */
            ArrayList<Reservation> BPArrayList = new ArrayList<Reservation>();
        /* Variable Section Stop */
        
        
        /* TRY BLOCK START */
            
            try
            {
            /* Preparing Statment Section Start */                
                statment = con.prepareStatement(statString);
                statment.setInt(1, FranID);
                //statment.setInt(2, CustID);
            /* Preparing Statment Section Stop */
            /* Query Section Start */
                results = statment.executeQuery();
                
            /* Query Section Stop */
            
            /* Metadata Section Start*/
                //ResultSetMetaData metaData = results.getMetaData();
            /* Metadata Section Start*/
                
            /* ArrayList Prepare Section Start */
                
                
                
                while (results.next())
                {
                    Reservation temp = new Reservation();
                    
                    //results.getBigDecimal("AMOUNT")
                    
                    
                    temp.setAirline(results.getString("Airline"));
                    temp.setComment(results.getString("Comment"));
                    temp.setCustomerID(results.getInt("CustomerID"));
                    temp.setDate(results.getString("Date"));
                    temp.setDropOffTime(results.getDouble("DropOffTime"));
                    temp.setFlightNumber(results.getString("FlightNumber"));
                    temp.setFlightTime(results.getDouble("FlightTime"));
                    temp.setFranchiseNumber(results.getInt("FranchiseNumber"));
                    temp.setPickUpTime(results.getDouble("PickUpTime"));
                    temp.setPrice(results.getDouble("Price"));
                    temp.setReservationNumber(results.getString("ReservationNumber"));
                    temp.setStatus(results.getString("Status"));
                    temp.setVehicleID(results.getInt("VehicleID"));
                    temp.setAltAddress(results.getString("AltAddress"));
                    temp.setAltCity(results.getString("AltCity"));
                    temp.setAltState(results.getString("AltState"));
                    temp.setAltZip(results.getInt("AltZip"));
                    
                    BPArrayList.add(temp);
                }
            /* ArrayList Prepare Section Stop */
            }
            catch(SQLException sqlE)
            {
                if(sqlE.getErrorCode() == 1142)
                    throw(new UnauthorizedUserException("AccessDenied"));
                else if(sqlE.getErrorCode() == 1062)
                    throw(new DoubleEntryException("DoubleEntry"));
                else 
                    throw(new BadConnectionException("BadConnection"));
            }
            finally
            {
                try
                {
                    if (results != null) results.close();
                }
                catch (Exception e) {};
                try
                {
                    if (statment != null) statment.close();
                }
                catch (Exception e) {};
            }
        /* TRY BLOCK STOP*/
        
            
        /* Return to Buisness Section Start */ 
            return BPArrayList;
        /* Return to Buisness Section Start */
    }
    
        //GET: List Reservations on a Specific Date (Format should be same as stored in MySQL (MM/DD/YYYY))
      public  ArrayList<Reservation> ReservationsForDate(String date)
             throws UnauthorizedUserException, BadConnectionException, DoubleEntryException
    {
        /* Variable Section Start */
            /* Database and Query Preperation */
            PreparedStatement statment = null;
            ResultSet results = null;
            String statString = "SELECT * FROM `reservation` WHERE 'Date' = ?";

            /* Return Parameter */
            ArrayList<Reservation> BPArrayList = new ArrayList<Reservation>();
        /* Variable Section Stop */
        
        
        /* TRY BLOCK START */
            
            try
            {
            /* Preparing Statment Section Start */                
                statment = con.prepareStatement(statString);
                statment.setString(1, date);
                //statment.setInt(2, CustID);
            /* Preparing Statment Section Stop */
            /* Query Section Start */
                results = statment.executeQuery();
                
            /* Query Section Stop */
            
            /* Metadata Section Start*/
                //ResultSetMetaData metaData = results.getMetaData();
            /* Metadata Section Start*/
                
            /* ArrayList Prepare Section Start */
                
                
                
                while (results.next())
                {
                    Reservation temp = new Reservation();
                    
                    //results.getBigDecimal("AMOUNT")
                    
                    
                    temp.setAirline(results.getString("Airline"));
                    temp.setComment(results.getString("Comment"));
                    temp.setCustomerID(results.getInt("CustomerID"));
                    temp.setDate(results.getString("Date"));
                    temp.setDropOffTime(results.getDouble("DropOffTime"));
                    temp.setFlightNumber(results.getString("FlightNumber"));
                    temp.setFlightTime(results.getDouble("FlightTime"));
                    temp.setFranchiseNumber(results.getInt("FranchiseNumber"));
                    temp.setPickUpTime(results.getDouble("PickUpTime"));
                    temp.setPrice(results.getDouble("Price"));
                    temp.setReservationNumber(results.getString("ReservationNumber"));
                    temp.setStatus(results.getString("Status"));
                    temp.setVehicleID(results.getInt("VehicleID"));
                    temp.setAltAddress(results.getString("AltAddress"));
                    temp.setAltCity(results.getString("AltCity"));
                    temp.setAltState(results.getString("AltState"));
                    temp.setAltZip(results.getInt("AltZip"));
                    
                    BPArrayList.add(temp);
                }
            /* ArrayList Prepare Section Stop */
            }
            catch(SQLException sqlE)
            {
                if(sqlE.getErrorCode() == 1142)
                    throw(new UnauthorizedUserException("AccessDenied"));
                else if(sqlE.getErrorCode() == 1062)
                    throw(new DoubleEntryException("DoubleEntry"));
                else 
                    throw(new BadConnectionException("BadConnection"));
            }
            finally
            {
                try
                {
                    if (results != null) results.close();
                }
                catch (Exception e) {};
                try
                {
                    if (statment != null) statment.close();
                }
                catch (Exception e) {};

            }
        /* TRY BLOCK STOP*/
        
            
        /* Return to Buisness Section Start */ 
            return BPArrayList;
        /* Return to Buisness Section Start */
    }
    
    
        //DELETE: Reservation
    public boolean RemoveReservation(int ID)
             throws UnauthorizedUserException, BadConnectionException, DoubleEntryException
    {
        /* Variable Section Start */
        Statement query = null;
        ResultSet results = null;
        int success = -1;
        /* Variable Section Stop */
        
        /* Preparing Statment Section Start */
        String MyQuery = "DELETE * FROM `reservation` WHERE 'ReservationNumber' = ?";
        
            /* TRY BLOCK START */

            try
            {

                PreparedStatement statement = con.prepareStatement(MyQuery);

                statement.setInt(1, ID);
            
            
            /* Preparing Statment Section Stop */
  
            /* Query Section Start */
                success = statement.executeUpdate();
            /* Query Section Stop* /

            /* Return to Buisness Section Start */
                if(success == 1)
                    return true;     
                
                if(success == 0 || success == -1)
                    return false;     
            /* Return to Buisness Section Start */
                    
                   
                     
                    
            }
            catch(SQLException sqlE)
            {
                if(sqlE.getErrorCode() == 1142)
                    throw(new UnauthorizedUserException("AccessDenied"));
                else if(sqlE.getErrorCode() == 1062)
                    throw(new DoubleEntryException("DoubleEntry"));
                else 
                    throw(new BadConnectionException("BadConnection"));
            }
            finally
            {
                try
                {
                    if (results != null) results.close();
                }
                catch (Exception e) {};
                try
                {
                    if (query != null) query.close();
                }
                catch (Exception e) {};
                try
                {
                    if (con != null) con.close();
                }
                catch (Exception e) {};
            }
           /* TRY BLOCK STOP */
            
            return false;
    }
    
    public int insertReservation(Reservation res) throws UnauthorizedUserException, DoubleEntryException, BadConnectionException {
        
        PreparedStatement pStmnt = null;
        double rPrice = 0.00;
        double dRate = 0.00;
        // get the rental price
        try {
            // get the rental price
            pStmnt = con.prepareStatement(qs.get_rental_price);
            pStmnt.setInt(1, res.getVehicleID());
            resultSet = pStmnt.executeQuery();
            
            while(resultSet.next()) {
                rPrice = resultSet.getDouble("RentalPrice");
            }
            
            // get the discount rate
            pStmnt = con.prepareStatement(qs.get_discount);
            pStmnt.setInt(1, res.getCustomerID());
            resultSet = pStmnt.executeQuery();
            
            while(resultSet.next()) {
                dRate = resultSet.getDouble("Discount");
            }
            
            // multiple them together
            rPrice = rPrice * dRate;
            
            // insert data into the db
            pStmnt = con.prepareStatement(qs.insert_res);
            pStmnt.setInt(1, res.getFranchiseNumber());
            pStmnt.setInt(2, res.getVehicleID());
            pStmnt.setInt(3, res.getCustomerID());
            pStmnt.setDouble(4, rPrice);
            pStmnt.setString(5, res.getDropPick());
            pStmnt.setInt(5, res.getFlightNumber());
            pStmnt.setString(6, res.getAirline());
            pStmnt.setDouble(7, res.getFlightTime());
            pStmnt.setDouble(8, (res.getFlightTime() - 1.00));
            pStmnt.setDouble(9, (res.getFlightTime() + 1.00));
            pStmnt.setString(10, res.getDate());
            pStmnt.setString(11, res.getAltAddress());
            pStmnt.setString(12, res.getAltCity());
            pStmnt.setString(13, res.getAltState());
            pStmnt.setInt(14, res.getAltZip());                       
            pStmnt.executeQuery();
            
            
        }catch(SQLException sqlE) {
            if(sqlE.getErrorCode() == 1142)
                    throw(new UnauthorizedUserException("AccessDenied"));
                else if(sqlE.getErrorCode() == 1062)
                    throw(new DoubleEntryException("DoubleEntry"));
                else 
                    throw(new BadConnectionException("BadConnection"));            
        }finally {
            try {
                if(pStmnt != null) pStmnt.close();
                if(resultSet != null) resultSet.close();
            }catch(Exception e) {
                
            }           
        }        
        return 1;
    }
    
    public int manUpdateReservation(Reservation res) throws UnauthorizedUserException, BadConnectionException {
        
        try {
           PreparedStatement pStmnt = con.prepareStatement(qs.update_res_m);
           pStmnt.setDouble(1, res.getFlightTime());
           pStmnt.setDouble(2, (res.getFlightTime() - 1.00));
           pStmnt.setDouble(3, (res.getFlightTime() + 1.00));
           pStmnt.setString(4, res.getAltAddress());
           pStmnt.setString(5, res.getAltCity());
           pStmnt.setString(6, res.getAltState());
           //pStmnt.setInt(7, res.getAltZip());
           pStmnt.setInt(8, res.getReservationNumber());
           pStmnt.executeQuery();
           
        }catch(SQLException sqlE) {
            if(sqlE.getErrorCode() == 1142)
                throw(new UnauthorizedUserException("AccessDenied"));
            else 
                throw(new BadConnectionException("BadConnection"));
        }
        return 1;
    }
    
    public int dUpdateReservation(Reservation res) throws UnauthorizedUserException, BadConnectionException {
        PreparedStatement pStmnt = null;
        
        // close a reservation and leave a comment
        try {
            pStmnt = con.prepareStatement(qs.update_res_d);
            pStmnt.setString(1, res.getComment());
            pStmnt.setInt(2, res.getReservationNumber());
            pStmnt.executeQuery();            
        }
        catch(SQLException sqlE) {
            if(sqlE.getErrorCode() == 1142)
                throw(new UnauthorizedUserException("AccessDenied"));
            else 
                throw(new BadConnectionException("BadConnection")); 
        }
        finally {
             try {
                 if(pStmnt != null) pStmnt.close();
            } catch (Exception ex) {
                
            }
        }
                     
        // update the customer reservation count and memberID (if needed)
        try {
            pStmnt = con.prepareStatement(qs.update_res_count);
            pStmnt.setInt(1, res.getCustomerID());
        }
        catch(SQLException sqlE) {
            if(sqlE.getErrorCode() == 1142)
                throw(new UnauthorizedUserException("AccessDenied"));
            else 
                throw(new BadConnectionException("BadConnection")); 
        }
        finally {
             try {
                 if(pStmnt != null) pStmnt.close();
            } catch (Exception ex) {
                
            }
        }
        return 1;
    }
    
    
/******************************************************************************
 *          Administrative Section                                                     *  
 ******************************************************************************/
    
    public int resetDatabase() 
            throws FileNotFoundException, BadConnectionException {
        
        // drop all customers
        code = dropCustomers();
        if(code != 1)
            throw(new  BadConnectionException("BadConnection"));
        
        // drop all employees
        code = dropEmployees();
        if(code == 1) {
            Scanner s;
            s = new Scanner(new File("src\\SQL\\JCGroup.sql"));
            s.useDelimiter(";(\r)?\n|(--\n)");
            Statement st = null;

            try {
                st = con.createStatement();
                while(s.hasNext()) {
                    String line = s.next();
                    if(line.startsWith("/*!") && line.endsWith("*/"))                    
                    {
                        int i = line.indexOf(' ');
                        line = line.substring(i + 1, line.length() - " */".length());
                    }
                    if(line.trim().length() > 0) {
                        System.out.println(line);
                        st.execute(line);
                    }               
                }//end while
            }catch(SQLException sqlE) {
                throw(new BadConnectionException(sqlE.getMessage()));
            }catch(Exception e) {
                throw(new FileNotFoundException("FileNotFound"));
            }finally {
                if(st != null)
                    try {
                    st.close();
                } catch (SQLException ex) {

                }
            }
            return 1;
        }
        else
            throw(new BadConnectionException("BadConnection"));
                  
    }
    
    private int dropEmployees() {
        List<String> results = null;
        PreparedStatement pStmnt = null;
        
        try {
            pStmnt = con.prepareStatement(qs.get_emp_username);
            resultSet = pStmnt.executeQuery();
            results = new ArrayList< String >();
            
            while(resultSet.next()) {
                results.add(resultSet.getString("Username"));
            }
            
            for(int i = 0; i < results.size(); i++) {
                pStmnt = con.prepareStatement(qs.drop_user);
                pStmnt.setString(1, results.get(i));
                pStmnt.execute();
            }
        
        }catch(SQLException sqlE) {
            return sqlE.getErrorCode();
        } finally {
            try {
                if(!results.isEmpty()) {
                    results.clear();
                }
                resultSet.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
           return 1;        
        }
    }
    
    private int dropCustomers() {
        List<String> results = null;
        PreparedStatement pStmnt = null;
        
        try {
            pStmnt = con.prepareStatement(qs.get_cust_username);
            resultSet = pStmnt.executeQuery();
            results = new ArrayList< String >();
            
            //get each user
            while(resultSet.next()) {
                results.add(resultSet.getString("Username"));
            }
            
            //drop each user
            for(int i = 0; i < results.size(); i++) {
                pStmnt = con.prepareStatement(qs.drop_user);
                pStmnt.setString(1, results.get(i));
                pStmnt.execute();
            }
        
        }catch(SQLException sqlE) {
            return sqlE.getErrorCode();
        } finally {
            try {
                if(!results.isEmpty()) {
                    results.clear();
                }
                resultSet.close();
            } catch(Exception e) {
                
            }
           return 1;        
        }
    }
    
}// End Class Queries
