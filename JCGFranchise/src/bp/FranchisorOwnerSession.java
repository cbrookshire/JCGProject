/* JCG Franchise Management System
 * CIST2931 Summer Session 2013 GTC
 * Author: #Maurice
 * Name: FranchisorOwnerSession
 * Description: Describes a FranchisorOwnerSession
 */
package bp;

import java.util.ArrayList;

public class FranchisorOwnerSession {
   
    //local vars
    private DBController dControl;
    private UIController uControl;
    
    //CONSTRUCTOR
    public FranchisorOwnerSession(){        
        dControl = DBController.getInstance();
    }
    
    //UTILITIES
    //HACK#1 the following is Maurice's hack until he discovers how to 
    //convert an array of Franchise, Vehicle, Employee, Customer 
    //into a generic object
    public ArrayList <Franchise> getFranchise(Object uiObject, String action){
        
        //local container
        ArrayList <Franchise> result;
        
        //send to dbSessionRouter      
        result = dControl.DBfranchisorRouter(uiObject, action);
        return result;       
    }//end getFranchise method
    
    //HACK#2
    public ArrayList <Vehicle> getVehicle(Object uiObject, String action){
        
        //local container
        ArrayList <Vehicle> result;
        
        //send to dbSessionRouter      
        result = dControl.DBvehicleRouter(uiObject, action);
        return result;       
    }//end getVehicle method
    
    //HACK#3
    public ArrayList <Employee> getEmployee(Object uiObject, String action){
        
        //local container
        ArrayList <Employee> result;
        
        //send to dbSessionRouter      
        result = dControl.DBemployeeRouter(uiObject, action);
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
}//end FranchiseOwnerSession class
    
    

