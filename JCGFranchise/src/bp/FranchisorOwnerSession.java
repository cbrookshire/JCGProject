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
    private static FranchisorOwnerSession fOS;
    
    //CONSTRUCTOR
    private FranchisorOwnerSession(){        
        dControl = DBController.getInstance();
    }
    
    //UTILITIES
    //VIEW ALL - VIEW ITEM METHODS
    //HACK#1 the following is Maurice's hack until he figures out how to 
    //convert an array of Franchise, Vehicle, Employee, Customer into an  
    //an array of generic objects safely
    public ArrayList <Franchise> getFranchise(Object uiObject, String action){
        
        //local container
        ArrayList <Franchise> result = new ArrayList<>();
        
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
    //END VIEW ALL - VIEW ITEM METHODS
    
    //ADD, DELETE, EDIT METHODS
    public String AddItem(Object uiObject, String action){
        
        //local vars
        String dbCode;
               
        //send to dbSystemRouter      
        dbCode = dControl.dbSystemRouter(uiObject, action);
        return dbCode;
    }//end AddItem method
     
    public String DeleteItem(Object uiObject, String action){
        
        //local vars
        String dbCode;
                
        //send to dbSystemRouter      
        dbCode = dControl.dbSystemRouter(uiObject, action);
        return dbCode;
    }//end DeleteItem method
     
    public String EditItem(Object uiObject, String action){
        
        //local vars
        String dbCode;
                
        //send to dbSystemRouter      
        dbCode = dControl.dbSystemRouter(uiObject, action);
        return dbCode;
    }//end EditItem method
    //END ADD, DELETE, EDIT METHODS
    
 /******************************************************
 * Singleton method for UIController class + Object 
 * clone override 
 ******************************************************/
    public static synchronized FranchisorOwnerSession getInstance(){            
        if(fOS == null) {
            fOS = new FranchisorOwnerSession();
        }
        return fOS;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException{    
        throw new CloneNotSupportedException();    
    }//end class UIController  
}//end FranchiseOwnerSession class
    
    

