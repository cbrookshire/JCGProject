
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Login {
    
    static final String DATABASE_URL = "jdbc:mysql://localhost/tester";
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    
    private PreparedStatement loginStatus = null;
    private PreparedStatement empType = null;
    private PreparedStatement update_password = null;
    private PreparedStatement update_status = null;
    private int code;
    
    public Login(String username, String password) throws SQLException {
        try {
            connection = DriverManager.getConnection(DATABASE_URL, username, password);            
        }
        catch(SQLException sqlE) {
            throw(new SQLException());
        }
     
        loginStatus = connection.prepareStatement(
                "SELECT FirstLog from Employee where Username = ?");
        
        empType = connection.prepareStatement(
                "SELECT EmpType from Employee where Username = ?");
        
        update_password = connection.prepareStatement(
                "SET PASSWORD FOR ?@'localhost' = PASSWORD(?)");
    
        update_status = connection.prepareStatement(
                "UPDATE Employee SET FIRSTLOG = 1 WHERE Username = ?");
    }
    
    public int login2(String username, String password)
    {
        code = getLoginStatus(username);
        if(code == 0)
            return 55;
        code = getEmpType(username);
            return code;
    }
    
    int getLoginStatus(String username)
    {
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
    
    int getEmpType(String username) {
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
    
    int updatePassword(String username, String password)
    {
        try {
            update_password.setString(1, username);
            update_password.setString(2, password);
            update_password.execute();
            code = updateLogStatus(username);
            return code;
        }
        catch(SQLException sqlE)
        {
            return sqlE.getErrorCode();
        }
    }
    
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
    
    public void logOff()
    {
        try {
                connection.close();
            }catch(Exception exception) {
            }
    }
    
}
