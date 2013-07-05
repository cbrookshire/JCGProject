/* JCG Franchise Management System
 * CIST2391 Summer Session 2013 GTC
 * Author: #Maurice
 * Name: UI Controller
 * Description: Controls communications 
 * between UI and BP 
 */
package bp;

public class UIController {
    
   //attributes
    private JCGSystem jcgSys;
    private FranchisorOwnerSession foSession;
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
                     
            case "VIEWALL": 
                
            case "VIEWITEM":
                
            case "ADD":
                
            case "DELETE":
                
            case "EDIT":
                
                
            default:        return "-1";
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
    
    
    
    
    
