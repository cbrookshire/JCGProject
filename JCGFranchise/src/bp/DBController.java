/* JCG Franchise Management System
 * CIST2391 Summer Session 2013 GTC
 * Author: #Maurice
 * Name: DB Controller
 * Description: Controls communications 
 * between UI and DB 
 */
package bp;
import db.*;

public class DBController {
    
    private String dbAction;
    private JCGDatabase dbase;
    int DBReturnCode;
        
    
    //CONSTRUCTOR
    //default
    public DBController(){    
        dbAction = "";
       
    }
    
    
    //SETS AND GETS
    public void setUiAction(String dbAction){
    
        this.dbAction = dbAction;
    }
    
    public String getdBAction(){
    
        return dbAction;
    }
    
    //UTILITIES
    public int dbRouter (Object sysObject, String action){
        
      
       switch(action){
            case "LOGIN":   DBReturnCode = 
                            dbase.JCGDatabase(sysObject);
                            return DBReturnCode;         
                
            default:        return 0;
            
              
       
       }
    
    
    
}
}
