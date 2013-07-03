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
    public DBController(){}
    
    
    //UTILITIES
    public String dbRouter (Object sysObject, String action){
        
        String dbReturnCode;
        
        //DBSwitch
        switch(action){
            case "LOGIN":  try{
                            JCGlIO temp = (JCGlIO)sysObject;
                            dbase = new JCGDatabase(temp);
                            dbReturnCode = dbase.login(temp);
                            return dbReturnCode;
                            }
                            catch(InvalidUserException e){
                                return e.toString();
                            }
                            catch(BadConnectionException e)
                            {   return e.toString();
                            }
                            catch(NewUserException e)
                            {   return e.toString();
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
