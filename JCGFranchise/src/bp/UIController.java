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
    
    /*public ArrayList <Franchise> UIfranchisorRouter (String object, String action){
        
       
          
        
    }*/
   
    
    
    
    
    
    
    
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
                        returnCode = jcgSys.UpdatePassword(UIObject, action);
                        if ("InvalidUserNamePassword".equals(returnCode)){
                            return "901";  /*error prompt*/           }
                        if ("BadConnection".equals(returnCode)){
                            return "902";  /*error prompt*/           }
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


    
    
    
    
    
