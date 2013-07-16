/* JCG Franchise Management System
 * CIST2931 Summer Session 2013 GTC
 * Author: #Maurice
 * Name: FranchiseManagerSession
 * Description: Describes a FranchisorManagerSession
 */
package bp;

import java.util.ArrayList;

public class FranchiseManagerSession {
    
    //class attributes
    private DBController dControl;
    private static FranchiseManagerSession fMS;
    
    
    //CONSTRUCTOR
    public FranchiseManagerSession(){
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
    
 /******************************************************
 * Singleton method for UIController class + Object 
 * clone override 
 ******************************************************/
    public static synchronized FranchiseManagerSession getInstance(){            
        if(fMS == null) {
            fMS = new FranchiseManagerSession();
        }
        return fMS;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException{    
        throw new CloneNotSupportedException();    
    }//end class UIController  
    
}//end FranchiseManagerSession class
