
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
    private int code;
    
    public Login(String username, String password) throws SQLException {
        try {
            connection = DriverManager.getConnection(DATABASE_URL, username, password);            
        }
        catch(SQLException sqlE) {
            throw(new SQLException());
        }
     
        loginStatus = connection.prepareStatement(
                "select * from Employee where Username = ?");
        
    
    }
    
    public int Login(String username, String password)
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
    
    int updatePassword(String username, String password)
    {
        try {
            statement.execute("SET PASSWORD FOR '" + username + "'@'localhost' = PASSWORD('" + password + "')");
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
            statement.execute("UPDATE Employee SET FIRSTLOG = 1 "
                    + "where Username = '" + username + "'");
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
