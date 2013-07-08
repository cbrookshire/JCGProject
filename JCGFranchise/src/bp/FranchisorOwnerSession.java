/* JCG Franchise Management System
 * CIST2931 Summer Session 2013 GTC
 * Author: #Maurice
 * Name: FranchisorOwnerSession
 * Description: Describes a FranchisorOwnerSession
 */
package bp;

import java.util.List;

public class FranchisorOwnerSession {
   
    //local vars
    private DBController dControl;
    private UIController uControl;
    
    //CONSTRUCTOR
    public FranchisorOwnerSession(){        
        dControl = DBController.getInstance();
    }
    
    //UTILITIES
    /*public List <Object> getItem(Object uiObject, String action){
        
        //local container
        List <Object> result;
        
        //send to dbSessionRouter      
        result = dControl.dbSessionRouter(uiObject, action);
        return result;
    }//end GetItem method*/
    
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
}//end FranchiseOwnerSession class
    
    

