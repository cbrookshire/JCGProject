/* JCG Franchise Management System
 * CIST2931 Summer Session 2013 GTC
 * Author: #Maurice
 * Name: UI Controller
 * Description: Controls communications 
 * between UI and BP 
 */
package bp;

import java.util.ArrayList;

public class UIController {
    
   //attributes
    private JCGSystem jcgSys;
    private FranchisorOwnerSession foSession;
    private FranchiseManagerSession fmSession;
    private FranchiseEmployeeSession empSession;
    private CustomerSession custSession;
    private JCGlIO role;
    private static UIController uicInstance;
  
    
    //CONSTRUCTOR
    //default
    public UIController()    {
        jcgSys = JCGSystem.getInstance();
    }
    
    //UTILITIES
    public String UIRouter (Object UIObject, String action){
        
        String returnCode;
                
        //call appropriate BP method
        switch(action){
            case "LOGIN":   
                        returnCode = jcgSys.Authentication(UIObject, action);
                        if ("UserNotFound".equals(returnCode)){
                            return "900";  /*error prompt*/           }        
                        if ("InvalidUserNamePassword".equals(returnCode)){
                            return "901";  /*error prompt*/           }
                        if ("BadConnection".equals(returnCode)){
                            return "901";  /*error prompt*/           }
                        if ("FRANCHISOR".equals(returnCode)){
                            return "100";  /*Franchisor Main*/        }        
                        if ("MANAGER".equals(returnCode)){
                            return "200";  /*Manager Main*/           }
                        if ("EMPLOYEE".equals(returnCode)){
                            return "220";   /*Employee Main*/         } 
                        if ("CUSTOMER".equals(returnCode)){
                            return "300";   /*Customer Main*/         }     
            
            case "UPDATEPASSWORD": 
                        returnCode = jcgSys.UpdatePassword(UIObject, action);
                        if ("InvalidUserNamePassword".equals(returnCode)){
                            return "901";  /*error prompt*/           }
                        if ("BadConnection".equals(returnCode)){
                            return "901";  /*error prompt*/           }
                        if ("OK".equals(returnCode)) {
                            return "001";                             }                           
                     
            case "ADD":     
                        //determine object passed and call method in the 
                        //appropriate session class
                        //Franchisor 
                        if ((UIObject instanceof Franchise || 
                              UIObject instanceof Employee)&&
                                "1".equals(role.geteT())) {
                            foSession.AddItem(UIObject, action);      }
                        //Franchise                        
                        if ((UIObject instanceof Employee || 
                             UIObject instanceof Vehicle ||
                             UIObject instanceof Reservation ||
                             UIObject instanceof Customer)&&
                                "2".equals(role.geteT())) {
                            fmSession.AddItem(UIObject, action);      }
                        /*//Employee
                        if (UIObject instanceof Reservation &&
                                "3".equals(role.geteT())) {
                            empSession.AddItem(UIObject, action);     }
                        //CUSTOMER
                        if ((UIObject instanceof Customer ||
                             UIObject instanceof Reservation)&&
                                "4".equals(role.geteT())) {
                            custSession.AddItem(UIObject, action);     } */             
                
            case "DELETE":
                
            case "EDIT":
                
                
            default:        return "000";
        }
}
    public ArrayList <Object> UIRouter (String object, String action){
        
        //holds list of objects received 
        //from session classes
        ArrayList <Object> temp;
        
        
        switch(action){
            
            case "VIEWALL":     if ("1".equals(role.geteT())){
                                    temp = foSession.getItem(object, action);
                                    return temp;                        }
                                /*if ("2".equals(role.geteN())){
                                    temp = fmSession.getItem(object, action);
                                    return temp;                        }
                                if ("3".equals(role.geteN())){
                                    temp = empSession.getItem(object, action);
                                    return temp;                        }        
                                if ("4".equals(role.geteN())){
                                    temp = custSession.getItem(object, action);
                                    return temp;                        }*/    
                
                
                
            case "VIEWITEM":    
            
            default:            temp = new ArrayList();
                                return temp;
            
        }
        
            }
    
    
    
    
    //singleton method for UIController class
    public static UIController getInstance()
    {            
        if(uicInstance == null) {
            uicInstance = new UIController();
        }
        return uicInstance;
    }
    
    
}


    
    
    
    
    
