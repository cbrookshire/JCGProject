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
    private final String OWNER = "1";
    private final String MANAGER = "2";
    private final String EMPLOYEE = "3";
    private final String CUSTOMER = "99";
    private final String SYSADMIN = "98";
    
    
    
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
    
    //VIEW ALL - VIEW ITEMS CONTROLLER METHODS
    //HACK#1: consider throwing an UnauthorizedUserException
    public ArrayList <Franchise> UIfranchisorRouter (Object uiObject, 
            String action){
    
        //local container
        ArrayList<Franchise> temp;
        
        //only franchisor allowed to see franchises
        temp = foSession.getFranchise(uiObject, action);
        return temp;
    }//end UIfranchisorRouter
   
   
    //HACK#2: consider throwing an UnauthorizedUserException
    public ArrayList <Vehicle> UIvehicleRouter (Object uiObject, 
            String action){
       //local container
       ArrayList<Vehicle> temp;
        
       //managers and customers can see vehicles
       //if type is manager call to appropriate session
       if (MANAGER.equals(io.geteT())) {
            temp = fmSession.getVehicle(uiObject, action);
            return temp;
       }
       //if type is customer make call to appropriate session
       if (CUSTOMER.equals(io.geteT())) {
            temp = custSession.getVehicle(uiObject, action);
            return temp;          
       }
       return null;
   }//end UIvehicleRouter
    
   
   //HACK#3: consider throwing an UnauthorizedUserException
   public ArrayList <Employee> UIemployeeRouter (Object object, 
            String action){
    
        //local container
        ArrayList<Employee> temp;
        
        //owners and managers can see employees
        //if type is owner make call to appropriate session
        if (OWNER.equals(io.geteT())) {
            temp = foSession.getEmployee(object, action);
            return temp;
        }            
        //if type is manager make call to appropriate session
        if (MANAGER.equals(io.geteT())) {
            temp = fmSession.getEmployee(object, action);
            return temp;
        }
        return null;
   }//end UIemployeeRouter
    
   
   //HACK#4: consider throwing an UnauthorizedUserException
   public ArrayList <Customer> UIcustomerRouter (Object object, 
            String action){
       
       //local container
       ArrayList<Customer> temp;
       
       //managers and customers can see customers
       //if type is manager make call to appropriate session
       if (MANAGER.equals(io.geteT())) {
            temp = fmSession.getCustomer(object, action);
            return temp;
       }            
       //if type is customer make call to appropriate session
       if (CUSTOMER.equals(io.geteT())) {
            temp = custSession.getCustomer(object, action);
            return temp;
       }
       return null;
   }//end UIcustomerRouter 
    
   
   //HACK#5: consider throwing an UnauthorizedUserException
   public ArrayList <Reservation> UIreservationRouter (Object object, 
            String action){
       
       //local container
       ArrayList<Reservation> temp;
        
       //managers, employees, and customers can see reservations
       //if type is manager make call to appropriate session
       if (MANAGER.equals(io.geteT())) {
            temp = fmSession.getReservation(object, action);
            return temp;
       }            
       //if type is employee make call to appropriate session
       if (EMPLOYEE.equals(io.geteT())) {
            temp = empSession.getReservation(object, action);
            return temp;
       }
       //if type is customer make call to appropriate session
       if (CUSTOMER.equals(io.geteT())) {
            temp = custSession.getReservation(object, action);
            return temp;
       }       
       return null;
   }//end UIreservationRouter method
   //END VIEW ALL - VIEW ITEM ROUTER METHODS
   
   
   //LOGIN, UPDATE, LOGOUT, ADD, DELETE, and EDIT CONTROLLER
   public String UIRouter (Object UIObject, String action){
                            
       //local var 
       String returnCode = null;
                
       //call appropriate BP method
       switch(action){
        case "LOGIN":   
                    
                //make call to JCGSystem authentication method, check
                //returnCode and if error return appropriate error 
                //screen prompt or open appropriate start screen
                returnCode = jcgSys.Authentication(UIObject, action);
                if ("UserNotFound".equals(returnCode)){
                    return "900";                                   }        
                if ("InvalidUserNamePassword".equals(returnCode)){
                    return "900";                                   }
                if ("BadConnection".equals(returnCode)){
                    return "902";                                   }
                if ("OWNER".equals(returnCode)){
                    return "100";  /*Franchisor Main Menu*/         }        
                if ("MANAGER".equals(returnCode)){
                    return "200";  /*Manager Main Menu*/            }
                if ("EMPLOYEE".equals(returnCode)){
                    return "220";   /*Employee Main Menu*/          } 
                if ("CUSTOMER".equals(returnCode)){
                    return "300";   /*Customer Main Menu*/          }     
            
        case "UPDATEPASSWORD": 
                
                //make call to JCGSystem authentication method, check                
                //returnCode and if error return appropriate error 
                //screen prompt or open appropriate start screen
                returnCode = jcgSys.UpdatePassword(UIObject, action);                
                if ("InvalidUserNamePassword".equals(returnCode)){
                    return "901";                                   }
                if ("BadConnection".equals(returnCode)){
                    return "902";                                   }
                if ("0".equals(returnCode)) {
                    return "902";                                   }
                if ("1".equals(returnCode)) {
                    return "001";   /*LogInScreen*/                 }
            
        case "RESET": 
                
                returnCode = jcgSys.ResetDatabase(UIObject, action);
                if ("BadConnection".equals(returnCode)){
                    return "902";                                   }
                if ("FileNotFoundException".equals(returnCode)){
                    return "902";                                   }           
                                     
        case "LOGOUT":
            
                jcgSys.Logout(UIObject, action);
                        
        
        case "ADD":     
                
                //determine session, make call to appropriate session 
                //only owners, managers, and customers can ADD
                //Franchise Owner Session
                if ("1".equals(role.geteT())) {
                    returnCode = foSession.AddItem(UIObject, action);                    
                }
                 //Franchise Manager Session                      
                if ("2".equals(role.geteT())) {
                    returnCode = fmSession.AddItem(UIObject, action);
                }
                //Customer Session
                if ("99".equals(role.geteT())) {
                    returnCode = custSession.AddItem(UIObject, action);                    
                } 
                
                               
                //check returnCode and if error return appropriate error 
                //screen prompt or notify UI action success "1" or action
                //failed "0"
                if (returnCode != null){ //check if returnCode still null
                    
                    if ("1".equals(returnCode) || "0".equals(returnCode)){
                        return returnCode;                                  }  
                    if ("BadConnection".equals(returnCode)){
                        return "902";                                       }
                    if ("UnauthorizedUserException".equals(returnCode)){
                        return "902";                                       }
                    if ("DoubleEntryException".equals(returnCode)){
                        return "902";                                       }                    
                }
                else                 
                {   //add and return local error code
                    returnCode = "Missing user type or session not found";
                    return returnCode;
                }
                
        case "DELETE":
                
                //determine session, make call to appropriate session 
                //only owners, managers, and customers can DELETE
                //Franchisor Session
                if (OWNER.equals(role.geteT())) {
                    returnCode = foSession.DeleteItem(UIObject, action);                    
                }
                //Franchise Manager Session                       
                if (MANAGER.equals(role.geteT())) {
                    returnCode = fmSession.DeleteItem(UIObject, action);
                }
                //Customer Session
                if (CUSTOMER.equals(role.geteT())) {
                    returnCode = custSession.DeleteItem(UIObject, action);                    
                } 
                
                //if returnCode not null, determine results. if error 
                //return appropriate error screen prompt or notify UI 
                //action success "1" or action failed "0"
                if (returnCode != null){ //check if returnCode still null
                    
                    if ("1".equals(returnCode) || "0".equals(returnCode)){
                        return returnCode;                          }  
                    if ("BadConnection".equals(returnCode)){
                        return "902";                               }
                    if ("UnauthorizedUserException".equals(returnCode)){
                        return "902";                               }
                    if ("DoubleEntryException".equals(returnCode)){
                        return "902";                               }                    
                }
                else                 
                {   //send back error local prompt 
                    returnCode = "Missing user type or session not found";
                    return returnCode;
                }
            
            
        case "EDIT":
                
                //determine session, make call to appropriate session 
                //owners, managers, employees and customers can EDIT
                //Franchisor Session
                if (OWNER.equals(role.geteT())) {
                    returnCode = foSession.EditItem(UIObject, action);                    
                }
                //Franchise Manager Session                       
                if (MANAGER.equals(role.geteT())) {
                    returnCode = fmSession.EditItem(UIObject, action);
                }
                //Employee Session
                if (EMPLOYEE.equals(role.geteT())) {
                    returnCode = custSession.EditItem(UIObject, action);                    
                } 
                //Customer Session
                if (CUSTOMER.equals(role.geteT())) {
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
                {   returnCode = "Missing user type or session not found";
                    return returnCode;
                }
                
            default:        if(returnCode == null){
                                return "000";}                           
       }//end switch
       return returnCode;
}//end LOGIN, UPDATE, LOGOUT, ADD, DELETE, and EDIT CONTROLLER

   
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


    
    
    
    
    
