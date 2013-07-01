/* JCG Franchise Management System
 * CIST2391 Summer Session 2013 GTC
 * Author: #Maurice
 * Name: DB Controller
 * Description: Controls communications 
 * between UI and DB 
 */
package bp;
import db.Login;

public class DBController {
    
    private String dbAction;
    private db.Login dbase;
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
    public int JCGDBController (String[] bpInput){
        
       dbAction = bpInput[bpInput.length-1];
        
       switch(dbAction){
            case "LOGIN":   DBReturnCode = 
                            dbase.Login(bpInput[0], bpInput[1]);
                            return DBReturnCode;         
                
            default:        return 0;
            
              
        }
    }
    
    
}
