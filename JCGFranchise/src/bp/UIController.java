/* JCG Franchise Management System
 * CIST2931 Summer Session 2013 GTC
 * Author: #Maurice
 * Name: UI Controller
 * Description: Controls communications 
 * between UI and BP 
 */
package bp;

import java.util.ArrayList;
import ui.BaseJFrame;

public class UIController {
    
   //attributes
    private JCGSystem jcgSys;
    private JCGlIO io;
    private FranchisorOwnerSession foSession;
    private FranchiseManagerSession fmSession;
    private FranchiseEmployeeSession empSession;
    private CustomerSession custSession;
    private JCGlIO role;
    private static UIController uicInstance;
  
    
    //CONSTRUCTOR
    //default
    private UIController()    {
        jcgSys = JCGSystem.getInstance();
    }
    
    //UTILITIES
    //Used by JCGSystem to start UI 
    public void StartUI(){    
        BaseJFrame inst = BaseJFrame.getInstance();
        inst.setVisible(true);
    }
    
    //HACK#1: consider throwing an UnauthorizedUserException
    public ArrayList <Franchise> UIfranchisorRouter (Object uiObject, 
            String action){
    
        ArrayList<Franchise> temp;
        
        //only franchisor allowed to see franchises
        temp = foSession.getFranchise(uiObject, action);
        return temp;
    }//end UIfranchisorRouter
   
   
    //HACK#2: consider throwing an UnauthorizedUserException
    public ArrayList <Vehicle> UIvehicleRouter (Object uiObject, 
            String action){
       
        ArrayList<Vehicle> temp;
        
        //if employee type is manager call BP appropriate method
        if ("2".equals(io.geteT())) {
            temp = fmSession.getVehicle(uiObject, action);
            return temp;
        }
        if ("99".equals(io.geteT())) {
            temp = custSession.getVehicle(uiObject, action);
            return temp;          
        }
        return null;
   }
    
   //HACK#3: consider throwing an UnauthorizedUserException
   public ArrayList <Employee> UIemployeeRouter (Object object, 
            String action){
    
        //local container
        ArrayList<Employee> temp;
        
        //if employee type is franchisor call BP appropriate method
        if ("1".equals(io.geteT())) {
            temp = foSession.getEmployee(object, action);
            return temp;
        }            
        //if employee type is manager call BP appropriate method
        if ("99".equals(io.geteT())) {
            temp = fmSession.getEmployee(object, action);
            return temp;
        }
        return null;
   }
    
   //HACK#4: consider throwing an UnauthorizedUserException
   public ArrayList <Customer> UIcustomerRouter (Object object, 
            String action){
       //local container
       ArrayList<Customer> temp;
              
       //if employee type is manager call BP appropriate method
       if ("2".equals(io.geteT())) {
            temp = fmSession.getCustomer(object, action);
            return temp;
       }            
       //if employee type is customer call BP appropriate method
       if ("99".equals(io.geteT())) {
            temp = custSession.getCustomer(object, action);
            return temp;
       }
       return null;
   } 
    
   //HACK#5: consider throwing an UnauthorizedUserException
   public ArrayList <Reservation> UIreservationRouter (Object object, 
            String action){
       
       //local container
       ArrayList<Reservation> temp;
        
       //if employee type is manager call appropriate session
       if ("2".equals(io.geteT())) {
            temp = fmSession.getReservation(object, action);
            return temp;
       }            
       //if employee type is driver call appropriate session
       if ("3".equals(io.geteT())) {
            temp = empSession.getReservation(object, action);
            return temp;
       }
       //if employee type is customer call appropriate session
       if ("99".equals(io.geteT())) {
            temp = custSession.getReservation(object, action);
            return temp;
       }       
       return null;
   }//end UIreservationRouter method
   
   
   public String UIRouter (Object UIObject, String action){
        
        String returnCode = null;
                
        //call appropriate BP method
        switch(action){
            case "LOGIN":   
                
                //call JCGSystem authentication method
                returnCode = jcgSys.Authentication(UIObject, action);
                //check returnCode and return appropriate error prompt
                //session start screen
                if ("UserNotFound".equals(returnCode)){
                    return "900";                             }        
                if ("InvalidUserNamePassword".equals(returnCode)){
                    return "901";  /*error prompt*/           }
                if ("BadConnection".equals(returnCode)){
                    return "902";  /*error prompt*/           }
                if ("FRANCHISOR".equals(returnCode)){
                    return "100";  /*Franchisor Main*/        }        
                if ("MANAGER".equals(returnCode)){
                    return "200";  /*Manager Main*/           }
                if ("EMPLOYEE".equals(returnCode)){
                    return "220";   /*Employee Main*/         } 
                if ("CUSTOMER".equals(returnCode)){
                    return "300";   /*Customer Main*/         }     
            
            case "UPDATEPASSWORD": 
                
                //call JCGSystem updatePassword method                
                returnCode = jcgSys.UpdatePassword(UIObject, action);
                //check returnCode and return appropriate error prompt
                //session start screen
                if ("InvalidUserNamePassword".equals(returnCode)){
                    return "901";  /*error prompt*/           }
                if ("BadConnection".equals(returnCode)){
                    return "902";  /*error prompt*/           }
                if ("OK".equals(returnCode)) {
                    return "001";                             }                           
                     
            case "ADD":     
                
                //determine object / session, all method in 
                //the appropriate session  
                //Franchisor Session
                if ((UIObject instanceof Franchise || 
                     UIObject instanceof Employee)&&
                        "1".equals(role.geteT())) {
                    returnCode = foSession.AddItem(UIObject, action);                    
                }
                //Franchise Manager Session                       
                if ((UIObject instanceof Employee || 
                     UIObject instanceof Vehicle ||
                     UIObject instanceof Reservation ||
                     UIObject instanceof Customer)&&
                        "2".equals(role.geteT())) {
                    returnCode = fmSession.AddItem(UIObject, action);
                }
                //Customer Session
                if ((UIObject instanceof Customer ||
                     UIObject instanceof Reservation)&&
                        "99".equals(role.geteT())) {
                    returnCode = custSession.AddItem(UIObject, action);                    
                } 
                
                //if returnCode not null, determine results
                if (returnCode != null){
                    
                    if ("1".equals(returnCode) || "0".equals(returnCode)){
                        return returnCode;                          }  
                    if ("BadConnection".equals(returnCode)){
                        return "902";                               }
                    if ("UnauthorizedUserException".equals(returnCode)){
                        return "902";                               }
                    if ("DoubleEntryException".equals(returnCode)){
                        return "902";  /*error prompt*/             }
                    
                }
                else                 
                {   returnCode = "Unauthorized Class Access";
                    return returnCode;
                }
                
            case "DELETE":
                
                //determine object / session, call method in 
                //the appropriate session  
                //Franchisor Session
                if ((UIObject instanceof Franchise || 
                     UIObject instanceof Employee)&&
                        "1".equals(role.geteT())) {
                    returnCode = foSession.DeleteItem(UIObject, action);                    
                }
                //Franchise Manager Session                       
                if ((UIObject instanceof Employee || 
                     UIObject instanceof Vehicle ||
                     UIObject instanceof Reservation ||
                     UIObject instanceof Customer)&&
                        "2".equals(role.geteT())) {
                    returnCode = fmSession.DeleteItem(UIObject, action);
                }
                //Customer Session
                if ((UIObject instanceof Customer ||
                     UIObject instanceof Reservation)&&
                        "99".equals(role.geteT())) {
                    returnCode = custSession.DeleteItem(UIObject, action);                    
                } 
                
                //if returnCode not null, determine results
                if (returnCode != null){
                    
                    if ("1".equals(returnCode) || "0".equals(returnCode)){
                        return returnCode;                          }  
                    if ("BadConnection".equals(returnCode)){
                        return "902";                               }
                    if ("UnauthorizedUserException".equals(returnCode)){
                        return "902";                               }
                    if ("DoubleEntryException".equals(returnCode)){
                        return "902";  /*error prompt*/             }
                    
                }
                else                 
                {   returnCode = "Unauthorized Class Access";
                    return returnCode;
                }
            case "EDIT":
                
                //determine object / session, call method in 
                //the appropriate session  
                //Franchisor Session
                if ((UIObject instanceof Franchise || 
                     UIObject instanceof Employee)&&
                        "1".equals(role.geteT())) {
                    returnCode = foSession.EditItem(UIObject, action);                    
                }
                //Franchise Manager Session                       
                if ((UIObject instanceof Employee || 
                     UIObject instanceof Vehicle ||
                     UIObject instanceof Reservation ||
                     UIObject instanceof Customer)&&
                        "2".equals(role.geteT())) {
                    returnCode = fmSession.EditItem(UIObject, action);
                }
                //Employee Session
                if (UIObject instanceof Reservation&&
                        "3".equals(role.geteT())) {
                    returnCode = custSession.EditItem(UIObject, action);                    
                } 
                //Customer Session
                if ((UIObject instanceof Customer ||
                     UIObject instanceof Reservation)&&
                        "99".equals(role.geteT())) {
                    returnCode = custSession.EditItem(UIObject, action);                    
                } 
                
                //if returnCode not null, determine results
                if (returnCode != null){
                    
                    if ("1".equals(returnCode) || "0".equals(returnCode)){
                        return returnCode;                          }  
                    if ("BadConnection".equals(returnCode)){
                        return "902";                               }
                    if ("UnauthorizedUserException".equals(returnCode)){
                        return "902";                               }
                    if ("DoubleEntryException".equals(returnCode)){
                        return "902";  /*error prompt*/             }
                    
                }
                else                 
                {   returnCode = "Unauthorized Class Access";
                    return returnCode;
                }
                
            default:        return null;
        }
}

    
    
 /******************************************************
 * Singleton method for UIController class + Object 
 * clone override 
 ******************************************************/
    public static synchronized UIController getInstance()
    {            
        if(uicInstance == null) {
            uicInstance = new UIController();
        }
        return uicInstance;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException{    
        throw new CloneNotSupportedException();    
    }   
}//end class UIController


    
    
    
    
    
