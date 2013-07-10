/*
 * Class JCGDatabase will be the connection class to the MySQL database
 * and will also allow the user to change their password upon their 
 * first login.
 */
package db;

import JCGExceptions.BadConnectionException;
import JCGExceptions.InvalidUserException;
import JCGExceptions.NewUserException;
import bp.JCGlIO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Taylor Reighard
 */
public class JCGDatabase {
  static final String DATABASE_URL = "jdbc:mysql://localhost/tester";
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    
    private PreparedStatement loginStatus = null;
    private PreparedStatement empType = null;
    private PreparedStatement update_password = null;
    private PreparedStatement update_status = null;
    private int code;
    
    // Constructor test for connection and user/password
    public JCGDatabase(JCGlIO jcgLio) 
            throws InvalidUserException, BadConnectionException {
        try {
            connection = DriverManager.getConnection(DATABASE_URL,
                    jcgLio.getU(), jcgLio.getP());            
             
            loginStatus = connection.prepareStatement(
                "SELECT FirstLog from Employee where Username = ?");
        
            empType = connection.prepareStatement(
                "SELECT EmpType from Employee where Username = ?");
        
            update_password = connection.prepareStatement(
                "SET PASSWORD FOR ?@'localhost' = PASSWORD(?)");
    
            update_status = connection.prepareStatement(
                "UPDATE Employee SET FIRSTLOG = 1 WHERE Username = ?");
            }
        catch(SQLException sqlE) {
            if(sqlE.getErrorCode() == 1044 || sqlE.getErrorCode() == 1045)
                throw(new InvalidUserException("InvalidUserNamePassword"));
            else
                throw(new BadConnectionException("BadConnection"));
        }
    }
    
/******************************************************************************
 * Checks for new user, then gets the employee type
 * @param username
  * @return integer for employee type or 55 for new password screen 
 ******************************************************************************/
    public int login(JCGlIO jcgLio) 
            throws NewUserException, BadConnectionException
    {
        if(getLoginStatus(jcgLio.getU()) == 0)
            throw(new NewUserException("UserNotFound"));
        else if(getLoginStatus(jcgLio.getU()) != 1 || getLoginStatus(jcgLio.getU()) != 0)
            throw(new BadConnectionException("BadConnection"));
        return getEmpType(jcgLio.getU());    
   }
     
/******************************************************************************
 * Used by login to get check for new user
 * @param username
 * @returns returns 1 for success or SQL error code number
 ******************************************************************************/ 
    private int getLoginStatus(String username) {
        try {
            loginStatus.setString(1, username);
            resultSet = loginStatus.executeQuery();
            while(resultSet.next())
            {
                code = resultSet.getInt("FirstLog");
            }
            return code;
        }
        catch(SQLException sqlE) {
            return sqlE.getErrorCode();
        }
    }
    
    
/******************************************************************************
 * Used by login2 to get the employee type
 * @param username
 * @returns integer empType or SQL error code number 
 ******************************************************************************/
    private int getEmpType(String username) {
        try {
            empType.setString(1, username);
            resultSet = empType.executeQuery();
            while(resultSet.next())
            {
                code = resultSet.getInt("EmpType");
            }
            return code;
        }
        catch(SQLException sqlE) {
            return sqlE.getErrorCode();
        }
    }
    
    
 /*****************************************************************************
  * Used to update the password for a given user in the system.
  * @param username
  * @param password
  * @returns 1 for success or SQL error code. 
  *****************************************************************************/    
    public int updatePassword(JCGlIO jcgLio) 
            throws InvalidUserException, BadConnectionException 
    {
        try {
            update_password.setString(1, jcgLio.getU());
            update_password.setString(2, jcgLio.getP());
            update_password.execute();
            code = updateLogStatus(jcgLio.getU());
            return code;
        }
        catch(SQLException sqlE)
        {
            if(sqlE.getErrorCode() == 1044 || sqlE.getErrorCode() == 1045)
                throw(new InvalidUserException("InvalidUserNamePassword"));
            else
                throw(new BadConnectionException("BadConnection"));
        }
    }
    
    
 /*****************************************************************************
  * Used to by updatePassword to set the user to non-new user.
  * @param username
  * @returns 1 for success or SQL error code. 
  *****************************************************************************/
    private int updateLogStatus(String username) {
        try {
            update_status.setString(1, username);
            update_status.execute();
            return 1;
        }
        catch(SQLException sqlE)
        {
            return sqlE.getErrorCode();
        }
    }
    
    
/******************************************************************************
 * Closes the connection to the database for current user.
 ******************************************************************************/
    public void logOff() {
        try {
                connection.close();
            }catch(Exception exception) {
            }
    }    
}//end JCGDatabase
