
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Login {
    
    static final String DATABASE_URL = "jdbc:mysql://localhost/tester";
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    int code;
    
    
    
    public Login() {
    
    }
    
 /*****************************************************************************
  * This method is used to validate that the current user is in the system 
  * and matches the password to the username.
  * @param username
  * @param password 
  * @returns 1 = Franchisor, 2 = Franchisee, 3 = Driver, 55 = prompt new password
  *          1045/1044 access denied for user.
  */
    public int login(String username, String password)
    {
        
        try
        {
            connection = DriverManager.getConnection(DATABASE_URL, username, password);
            code = getLoginStatus(username);
            if(code == 0)
                return 55;
            code = getEmpType(username);
            return code;
        }
        catch(SQLException sqlException)
        {
            return sqlException.getErrorCode();            
        }
        
    }
    
 /*******************************************************************
  * This method is called form Login to see if a user is new to the
  * system. If the user is new int 0 is to signal login to send back
  * error code 55.
  * 
  * @param username
  * @return integer
  */
    private int getLoginStatus(String username)
    {
        try {             
            statement = connection.createStatement();
            resultSet = statement.executeQuery(
                    "select * from Employee where Username = '" + username + "'");
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
  * This method is used by Login to get the employee type.
  * 
  * @param username
  * @return int EmpType
  */
    private int getEmpType(String username) {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT EmpType from Employee"
                + " where Username = '" + username + "'");
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
    
/*******************************************************************************
 * This method is used to create a new password for the user to login to the
 * database.
 * @param username
 * @param password (this is the new password)
 * @return 
 */
    
    int updatePassword(String username, String password)
    {
        try {
            statement.execute("SET PASSWORD FOR '" + username +
                    "'@'localhost' = PASSWORD('" + password + "')");
            code = updateLogStatus(username);
            return code;
        }
        catch(SQLException sqlE)
        {
            return sqlE.getErrorCode();
        }
    }
    
 /******************************************************************************
  * This method is used by updatePassword and updates FirstLog in the DB to 1
  * when the user creates their new password.
  * 
  * @param username
  * @return 
  */
    private int updateLogStatus(String username) {
        try {
            statement.execute("UPDATE Employee SET FIRSTLOG = 1 "
                    + "where Username = '" + username + "'");
            return 1;
        }
        catch(SQLException sqlE)
        {
            return sqlE.getErrorCode();
        }
    }
}
