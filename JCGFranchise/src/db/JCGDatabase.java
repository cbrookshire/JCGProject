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
  static final String DATABASE_URL = "jdbc:mysql://localhost/JCGroup";
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;   
    
    private PreparedStatement loginStatus = null;
    private PreparedStatement empType = null;
    private PreparedStatement update_password = null;
    private PreparedStatement update_status = null;
    private PreparedStatement cust_loginStatus = null;
    private PreparedStatement custType = null;
    private PreparedStatement update_cust_status = null;
    private int code;
    private String status;
    
    // Constructor test for connection and user/password
    public JCGDatabase(JCGlIO jcgLio) 
            throws InvalidUserException, BadConnectionException {
        try {
            
            
            connection = DriverManager.getConnection(DATABASE_URL,
                    jcgLio.getU(), jcgLio.getP());
            
            
             
            loginStatus = connection.prepareStatement(
                "SELECT FirstLog from Employee where Username = ?");
            
            cust_loginStatus = connection.prepareStatement(
                    "SELECT FirstLog from Customer where Username = ?");
        
            empType = connection.prepareStatement(
                "SELECT EmpType from Employee where Username = ?");
            
            custType = connection.prepareStatement(
                    "SELECT CType from Customer where Username = ?");
        
            update_password = connection.prepareStatement(
                "SET PASSWORD FOR ?@'localhost' = PASSWORD(?)");
    
            update_status = connection.prepareStatement(
                "UPDATE Employee SET FIRSTLOG = 'N' WHERE Username = ?");
            
            update_cust_status = connection.prepareStatement(
                "UPDATE Customer SET FIRSTLOG = 'N' WHERE Username = ?");
            
                //saves Connection object in JCGlIO for use in 
                //instantiation of Queries 
                jcgLio.setSessionConnection(connection);               
                
            }
        catch(SQLException sqlE) {
            
            if(sqlE.getErrorCode() == 1044 || sqlE.getErrorCode() == 1045) {
                System.out.println("constructor invalid user");
                throw(new InvalidUserException("InvalidUserNamePassword"));
            }else {
                System.out.println("constructor bad user");
                throw(new BadConnectionException("BadConnection"));
            }
                
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
        code = 999;
        // check to see if the login is an employee
        try {
            loginStatus.setString(1, jcgLio.getU());
            resultSet = loginStatus.executeQuery();
            while(resultSet.next())
            {
                status = resultSet.getString("FirstLog");                
            }
            if("Y".equals(status))      // First log in for a Employee
            {
                throw(new NewUserException("Please enter new password"));
            }
            else if("N".equals(status)) // Employee has logged in before
            {
                System.out.println(status);
                empType.setString(1, jcgLio.getU());
                resultSet = empType.executeQuery();
                while(resultSet.next())
                {
                    code = resultSet.getInt("EmpType");
                }                
            }
            else                                                               // Not an Employee, Check for customer               
            {
                cust_loginStatus.setString(1, jcgLio.getU());
                resultSet = cust_loginStatus.executeQuery();
                while(resultSet.next())
                {
                    status = resultSet.getString("FirstLog");
                }
                if("Y".equals(status))
                {                                
                    throw(new NewUserException("Please enter new password")); // First login for a Customer
                }
                else if ("N".equals(status))  {                               // Not first login for a Customer
                    custType.setString(1, jcgLio.getU());                     // Will double check to make sure 
                    resultSet = custType.executeQuery();                      // for customer in system.
                    while(resultSet.next())
                    {
                        code = resultSet.getInt("CType");
                    }                                   
                }
                else                                                          // invalid user accessing system
                {
                    throw(new BadConnectionException("BadConnection"));
                }
            }
        }catch(SQLException sqlE) {
            throw(new BadConnectionException("BadConnection"));
        }finally {
            try{
                if(resultSet != null)
                    resultSet.close();
            }catch(Exception e) {
                
            }
        }
        return code;
    }
     
/******************************************************************************
 * Used by login to get check for new user
 * @param username
 * @returns returns 1 for success or SQL error code number
 ******************************************************************************/ 
    private int getLoginStatus(String username) {
        try {                                                       // Fisrt test is for Employee
            loginStatus.setString(1, username);
            resultSet = loginStatus.executeQuery();
            while(resultSet.next())
            {
                status = resultSet.getString("FirstLog");
            }
            if("Y".equals(status)) {
                System.out.println("three");
                return 0;
            }else if("N".equals(status)) {
                System.out.println("four");
                return 1;
            }else {                                                 // Not an Employee    
                cust_loginStatus.setString(1, username);
                resultSet = cust_loginStatus.executeQuery();
                while(resultSet.next())
                {
                    status = resultSet.getString("FirstLog");
                }
                if("Y".equals(status)) {                                
                    return 0;                                   // First login for a Customer
                }else {                          
                    return 1;                                   // Not first login for a Customer
                }
                
            }
        }catch(SQLException sqlE) {
            return sqlE.getErrorCode();
        }finally {
            try{
                if(resultSet != null)
                    resultSet.close();
            }catch(Exception e) {
                
            }
        }
    }
    
    
/******************************************************************************
 * Used by login2 to get the employee type
 * @param username
 * @returns integer empType or SQL error code number 
 ******************************************************************************/
    private int getEmpType(String username) throws SQLException {
        try {                                               // First check for Employee
            empType.setString(1, username);
            resultSet = empType.executeQuery();
            while(resultSet.next())
            {
                code = resultSet.getInt("EmpType");
            }
            if(code == 0) {                                 // Not an Employee
                try {                                       // Check for Customer
                    custType.setString(1, username);
                    resultSet = custType.executeQuery();
                    while(resultSet.next())
                    {
                        code = resultSet.getInt("CType");
                    }            
                }catch(SQLException sqlE) {
                    throw(new SQLException());
                }
            }
        }
        catch(SQLException sqlE) {
            throw(new SQLException());
        }
        return code;
    }
        
 /*****************************************************************************
  * Used to update the password for a given user in the system.
  * @param username
  * @param password
  * @returns 1 for success or throw errors 
  *****************************************************************************/    
    public int updatePassword(JCGlIO jcgLio) 
            throws InvalidUserException, BadConnectionException 
    {
        try {
            update_password.setString(1, jcgLio.getU());   // This will throw exception
            update_password.setString(2, jcgLio.getP());   // before testing for employee or customer
            update_password.execute();
            
            if(getEmpType(jcgLio.getU()) == 99) {
                update_cust_status.setString(1, jcgLio.getU());
                update_cust_status.execute();
            }
            else
            {
                update_status.setString(1, jcgLio.getU());  // update log status for Employee
                update_status.execute();
            }
            return 1; // successful update
        }
        catch(SQLException sqlE)
        {
            if(sqlE.getErrorCode() == 1044 || sqlE.getErrorCode() == 1045)
                throw(new InvalidUserException("InvalidUserNamePassword"));
            else
                throw(new BadConnectionException("BadConnection"));
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
