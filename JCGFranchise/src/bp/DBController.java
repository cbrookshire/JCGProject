/* JCG Franchise Management System
 * CIST2391 Summer Session 2013 GTC
 * Author: #Maurice
 * Name: DB Controller
 * Description: Controls communications 
 * between UI and DB 
 */
package bp;
import db.*;
import JCGExceptions.*;

public class DBController {
    
    //attributes
    private JCGDatabase dbase;
    private static DBController dbcInstance;
         
    
    //CONSTRUCTOR
    //default
    protected DBController(){}
    
    
    //UTILITIES
    public String dbRouter (Object sysObject, String action){
        
        int dbReturnCode;
        
                
        //DBSwitch
        switch(action){
            
            case "LOGIN":   try {   
                                //Attempt connection with JCGlIO object
                                JCGlIO temp = (JCGlIO)sysObject;
                                dbase = new JCGDatabase(temp); 
                                //Attempt login 
                                dbReturnCode = dbase.login(temp);
                                String convert = Integer.toString(dbReturnCode);
                                //Return user role
                                return convert;                                
                            }
                            catch(InvalidUserException e){
                                return e.toString();
                            }
                            catch(BadConnectionException e){
                                return e.toString();
                            }
                            catch(NewUserException e){
                                return e.toString();
                            }  

            case "UPDATE":             
            
                
                
                
            case "LOGOUT":  try{
                                dbase.logOff();                                
                            }  
                            catch (Exception e){
                                return e.toString();
                            }
                
            default:        return "0";
            
              
       
       }
    
    
    
}
    
    //singleton method for UIController class
    public static DBController getInstance(){            
        if(dbcInstance == null)
        {    dbcInstance = new DBController();}
        return dbcInstance;
       }
}
