/* JCG Franchise Management System
 * CIST2391 Summer Session 2013 GTC
 * Author: #Maurice
 * Name: FranchiseManagerSession
 * Description: Describes a FranchisorManagerSession
 */
package bp;

import java.util.List;

public class FranchiseManagerSession {
    
    //class attributes
    private DBController dControl;
    
    
    //CONSTRUCTOR
    public FranchiseManagerSession(){
        dControl = DBController.getInstance();
    }
    
    //UTILITIES
    public List <Object> getItem(Object uiObject, String action){
        
        //local container
        List <Object> result;
        
        //send to dbSessionRouter      
        result = dControl.dbSessionRouter(uiObject, action);
        return result;
    }//end GetItem method
    
    public String AddItem(Object uiObject, String action){
        
        //local vars
        String dbCode;
        String sysCode;
        
        //send to dbSystemRouter      
        dbCode = dControl.dbSystemRouter(uiObject, action);
        return dbCode;
    }//end AddItem method
     
    public String DeleteItem(Object uiObject, String action){
        
        //local vars
        String dbCode;
        String sysCode;
        
        //send to dbSystemRouter      
        dbCode = dControl.dbSystemRouter(uiObject, action);
        return dbCode;
    }//end DeleteItem method
     
    public String EditItem(Object uiObject, String action){
        
        //local vars
        String dbCode;
        String sysCode;
        
        //send to dbSystemRouter      
        dbCode = dControl.dbSystemRouter(uiObject, action);
        return dbCode;
    }//end EditItem method
}//end FranchiseManagerSession class
