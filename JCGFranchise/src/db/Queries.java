/*
 * 
 */
package db;

import JCGExceptions.BadConnectionException;
import JCGExceptions.DoubleEntryException;
import JCGExceptions.UnauthorizedUserException;
import bp.Customer;
import bp.Employee;
import bp.Franchise;
import bp.Membership;
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**a
 *
 * @authors Taylor Reighard and Miles Leavens-Russell
 */
public class Queries
{
    
    private Connection con = null;
    private ResultSet resultSet = null;
    private QStrings qs;
    private int code;
    
    public Queries(Connection con)
    {
        
        setConnection(con);
        
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
    /*
    public void updateFranchise(Franchise fran) throws UnauthorizedUserException,
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
            pStmnt.setInt(7, fran.getAirport());
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
    }
    
    public void insertFranchise(Franchise fran) throws UnauthorizedUserException,
            BadConnectionException, DoubleEntryException {
        try {
            PreparedStatement pStmnt = con.prepareStatement(qs.insert_fran);
            pStmnt.setString(1, fran.getAddress());
            pStmnt.setString(2, fran.getCity());
            pStmnt.setString(3, fran.getState());
            pStmnt.setInt(4, fran.getZip());
            pStmnt.setString(5, fran.getPhone());
            pStmnt.setString(6, fran.getEmail());
            pStmnt.setInt(7, fran.getAirport());
            pStmnt.executeUpdate();
            
        } catch(SQLException sqlE) {
            if(sqlE.getErrorCode() == 1142)
                throw(new UnauthorizedUserException("AccessDenied"));
            else if(sqlE.getErrorCode() == 1062)
                throw(new DoubleEntryException("DoubleEntry"));
            else 
                throw(new BadConnectionException("BadConnection"));
        }
    }
    */
    
      //GET: List All Franchises
    public ArrayList<Franchise> GetAllFranchises()
            throws UnauthorizedUserException, BadConnectionException, DoubleEntryException
    {
        /* Variable Section Start */
            /* Database and Query Bullshit */
            PreparedStatement statment = null;
            ResultSet results = null;
            String statString = "SELECT * FROM franchise";

            /* Return Parameter */
            ArrayList<Franchise> BPList = new ArrayList<Franchise>();
        /* Variable Section Stop */
        
        
        /* TRY BLOCK START */
            
            try
            {
            /* Preparing Statment Section Start */                
                statment = con.prepareStatement(statString);
                //statment.setString();
            /* Preparing Statment Section Stop */
            /* Query Section Start */
                results = statment.executeQuery();
                
                if(statment != null)
                    statment.close();
                
            /* Query Section Stop */
            
            /* Metadata Section Start */
                ResultSetMetaData metaData = results.getMetaData();
                int columns = metaData.getColumnCount();
                int rows = results.getRow(); 
            /* Metadata Section Start*/
                
            /* List Prepare Section Start */
                
                
                results.beforeFirst();
                while (results.next() && rows > 0)
                {
                    Franchise temp = new Franchise();
                    
                    //rs.getBigDecimal("AMOUNT")
                    
                    temp.setAddress(results.getString("Address"));
                    temp.setAirport(results.getString("AirportID"));
                    temp.setCity(results.getString("City"));
                    temp.setEmail(results.getString("Email"));
                    temp.setFranchiseID(results.getString("FranchiseNumber"));
                    temp.setPhone(results.getString("Phone"));
                    temp.setState(results.getString("State"));
                    temp.setZip(results.getString("Zip"));
                    
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
            /* Database and Query Bullshit */
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
                
                if(statment != null)
                    statment.close();
                
            /* Query Section Stop */
            
            /* Metadata Section Start */
                ResultSetMetaData metaData = results.getMetaData();
                int columns = metaData.getColumnCount();
                int rows = results.getRow(); 
            /* Metadata Section Start*/
                
            /* List Prepare Section Start */
                
                
                results.beforeFirst();
                while (results.next() && rows > 0)
                {
                    Franchise temp = new Franchise();
                    
                    //rs.getBigDecimal("AMOUNT")
                    
                    temp.setAddress(results.getString("Address"));
                    temp.setAirport(results.getString("AirportID"));
                    temp.setCity(results.getString("City"));
                    temp.setEmail(results.getString("Email"));
                    temp.setFranchiseID(results.getString("FranchiseNumber"));
                    temp.setPhone(results.getString("Phone"));
                    temp.setState(results.getString("State"));
                    temp.setZip(results.getString("Zip"));
                    
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
        /* TRY BLOCK STOP*/
        
            
        /* Return to Buisness Section Start */ 
            return BPList;
        /* Return to Buisness Section Start */

    }
  
/******************************************************************************
 *          All Queries for the Membership table                             *
 ******************************************************************************/
    
    /*
    public void updateMemeberShip(Membership mem) throws UnauthorizedUserException,
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
    }
    */
    
/******************************************************************************
 *          All Queries for the Vehicle table                                 *
 ******************************************************************************/
    /*
    public void insertVehicle(Vehicle car) throws UnauthorizedUserException,
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
    }
    
    public void updateVehicle(Vehicle car) 
            throws UnauthorizedUserException, BadConnectionException, DoubleEntryException {
        try {
            PreparedStatement pStmnt = con.prepareStatement(qs.update_vehicle);
            pStmnt.setInt(1, car.getMileage());
            pStmnt.setString(2, car.getCondition());
            pStmnt.setString(3, car.getTablet());
            pStmnt.setDouble(4, car.getRate());
            pStmnt.setInt(5, car.getFranchiseNumber());
            pStmnt.setInt(6, car.getVehicleID());
            pStmnt.executeUpdate();
            
        } catch(SQLException sqlE) {
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
 *          All Queries for the Employee table                                *
 *          includes Create and Grant Queries                                 *
 ******************************************************************************/
    
     
     public void insertEmployee(Employee emp) throws UnauthorizedUserException,
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
    }
       
    private int createUser(String userID, String password) {
        try {
            PreparedStatement pStmnt = con.prepareStatement(qs.create_user);
            pStmnt.setString(1, userID);
            pStmnt.setString(2, password);
            pStmnt.execute();
            return 1;
        }
        catch(SQLException sqlE) {
            return sqlE.getErrorCode();
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
    
    private int grantManagerRole(String username) {
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
            return sqlE.getErrorCode();
        }
    }
    
    private int grantDriverRole(String username) {
        try {
            PreparedStatement pStmnt = con.prepareStatement(qs.grant_driver);
            pStmnt.setString(1, username);
            pStmnt.executeQuery();
            return 1;
        }catch(SQLException sqlE) {
            return sqlE.getErrorCode();
        }
    }
    
    private int grantOwnerRole(String username) {
        try {
            PreparedStatement pStmnt = con.prepareStatement(qs.grant_owner);
            for(int i = 1; i <= 4; i++) {
                pStmnt.setString(i, username);
            }
            pStmnt.executeQuery();
            return 1;
        }catch(SQLException sqlE) {
            return sqlE.getErrorCode();
        }
    }
     
    
         //GET: Get All Managers
    public ArrayList<Employee> AllManagers()
            throws UnauthorizedUserException, BadConnectionException, DoubleEntryException
    {
        /* Variable Section Start */
            /* Database and Query Bullshit */
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
                
                if(statment != null)
                    statment.close();
                
            /* Query Section Stop */
            
            /* Metadata Section Start*/
                ResultSetMetaData metaData = results.getMetaData();
                int columns = metaData.getColumnCount();
                int rows = results.getRow(); 
            /* Metadata Section Start*/
                
            /* List Prepare Section Start */
                
                
                results.beforeFirst();
                while (results.next() && rows > 0)
                {
                    Employee temp = new Employee();
                    
                    //rs.getBigDecimal("AMOUNT")
                    
                    temp.setAddress(results.getString("Address"));
                    temp.setCity(results.getString("City"));
                    temp.setEmail(results.getString("Email"));
                    temp.setEmpType(results.getString("EmpType"));
                    temp.setFirstName(results.getString("Fname"));
                    temp.setFranchiseNumber(results.getString("FranchiseNumber"));
                    temp.setLastName(results.getString("Surname"));
                    //temp.setPassword(results.getString("Address"));
                    temp.setPhone(results.getString("Phone"));
                    temp.setState(results.getString("State"));
                    //temp.setUserid(results.getString("EmployeeID"));
                    temp.setZip(results.getString("Address"));
                    
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
        /* TRY BLOCK STOP*/
        
            
        /* Return to Buisness Section Start */ 
            return BPList;
        /* Return to Buisness Section Start */
 
    }
    
/******************************************************************************
 *          All Queries for the Maintenance table                             *
 ******************************************************************************/
    /*
    public void insertMaintenance(Maintenance fixit) throws UnauthorizedUserException,
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
