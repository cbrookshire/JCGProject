/* JCG Franchise Management System
 * CIST2931 Summer Session 2013 GTC
 * Author: #Maurice
 * Name: FranchiseEmployeeSession
 * Description: Describes a FranchiseEmployeeSession
 */
package bp;

import java.util.ArrayList;

public class FranchiseEmployeeSession {
    
    //local vars
    private DBController dControl;
    
    //default constructor
    public FranchiseEmployeeSession(){    
        dControl = DBController.getInstance();    
    }
    
    //HACK#4
    public ArrayList <Reservation> getReservation(Object uiObject, String action){
        
        //local container
        ArrayList <Reservation> result = new ArrayList<>();
        
        //send to dbSessionRouter      
        result = dControl.DBreservationRouter(uiObject, action);
        return result;       
    }//end getVehicle method  
    
}
