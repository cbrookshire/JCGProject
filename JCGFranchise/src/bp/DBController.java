/* JCG Franchise Management System
 * CIST2391 Summer Session 2013 GTC
 * Author: #Maurice
 * Name: DB Controller
 * Description: Controls communications 
 * between UI and DB 
 */
package bp;

import JCGExceptions.*;
import db.*;
import java.util.List;

public class DBController {
    
    //attributes
    private JCGDatabase dbase;
    private Queries queryDB;
    private static DBController dbcInstance;
         
    
    //CONSTRUCTOR
    //default
    protected DBController(){ }
    
    
    //UTILITIES
    public String dbSystemRouter (Object sysObject, String action){
        
        //local var
        int dbReturnCode;        
         
        //DBSwitch
        switch(action){
            
            case "LOGIN":   
                        try {   
                                //Cast object and attempt connection
                                JCGlIO temp = (JCGlIO)sysObject;
                                dbase = new JCGDatabase(temp); 
                                //Attempt login 
                                dbReturnCode = dbase.login(temp);
                                String convert = Integer.toString(dbReturnCode);
                                //Return user role
                                return convert;                                
                            }
                            catch(InvalidUserException e){
                                return e.getMessage();
                            }
                            catch(BadConnectionException e){
                                return e.getMessage();
                            }
                            catch(NewUserException e){
                                return e.getMessage();
                            }  

            case "UPDATEPASSWORD":  
                            try
                            {   //Cast object and attempt update
                                JCGlIO temp = (JCGlIO)sysObject;
                                dbReturnCode = dbase.updatePassword(temp);
                                String convert = Integer.toString(dbReturnCode);
                                //return status
                                return convert;
                            }
                            catch(InvalidUserException e){
                                return e.getMessage();
                            }
                            catch(BadConnectionException e){
                                return e.getMessage();
                            }                         
                
            case "LOGOUT":  try{
                                dbase.logOff();                                
                            }  
                            catch (Exception e){
                                return e.getMessage();
                            }
            
            case "ADD":     /*try{
                                if (sysObject instanceof Franchise)
                                    queryDB.insertFranchise((Franchise)sysObject);
                            }
                             catch(UnauthorizedUserException e){
                                return e.getMessage();
                            }  
                             catch(BadConnectionException e){
                                return e.getMessage();
                            }*/
                
                
            case "DELETE":
                
            case "EDIT":
                          
            default:        
                            //default test String
                            return "-2";             
       
       }//end DB Switch
    }//end DBRouter method
    
    /*public List <Object> dbSessionRouter (Object sysObject, String action){
    
        //local container
        List <Object> temp;
        
        //DB object switch
        switch(action){
            
            case "VIEWALL":
                
            case "VIEWITEM":
                
            default:      return temp;
                           
                       
        }//end switch    
    }//end dbSessionRouter*/
       
    //singleton method for UIController class
    public static DBController getInstance(){            
        if(dbcInstance == null)
        {    dbcInstance = new DBController();}
        return dbcInstance;
       }
}
