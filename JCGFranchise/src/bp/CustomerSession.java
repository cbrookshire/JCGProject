/* JCG Franchise Management System
 * CIST2931 Summer Session 2013 GTC
 * Author: #Maurice
 * Name: CustomerSession
 * Description: Describes a CustomerSession
 */
package bp;

import java.util.ArrayList;

public class CustomerSession {
    
    //local vars
    private DBController dControl;
    
    //default constructor
    public CustomerSession(){
        dControl = DBController.getInstance();
    }
    
    //HACK#2
    public ArrayList <Vehicle> getVehicle(Object uiObject, String action){
        
        //local container
        ArrayList <Vehicle> result;
        
        //send to dbSessionRouter      
        result = dControl.DBvehicleRouter(uiObject, action);
        return result;       
    }//end getVehicle method
    
   
    //HACK#4
    public ArrayList <Reservation> getReservation(Object uiObject, String action){
        
        //local container
        ArrayList <Reservation> result;
        
        //send to dbSessionRouter      
        result = dControl.DBreservationRouter(uiObject, action);
        return result;       
    }//end getVehicle method  
    
    
    //HACK#5
    public ArrayList <Customer> getCustomer(Object uiObject, String action){
        
        //local container
        ArrayList <Customer> result;
        
        //send to dbSessionRouter      
        result = dControl.DBcustomerRouter(uiObject, action);
        return result;       
    }//end getVehicle method
    
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
}
