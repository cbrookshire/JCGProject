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
    private static UIController uicInstance;
  
    
    //CONSTRUCTOR
    //default
    protected UIController(){}
    
    //UTILITIES
    public String UIRouter (Object UIObject, String action){
        
        String returnCode = "";
                
        //call appropriate BP method
        switch(action){
            case "LOGIN":   returnCode = jcgSys.Authentication(UIObject, action);
                            if (returnCode == "UserNotFound"){
                                return "900";  /*error prompt*/           }        
                            if (returnCode == "InvalidUserNamePassword"){
                                return "901";  /*error prompt*/           }
                            if (returnCode == "BadConnection"){
                                return "901";  /*error prompt*/           }
                            if (returnCode == "FRANCHISOR"){
                                return "100";  /*Franchisor Main"*/       }        
                            if (returnCode == "MANAGER"){
                                return "200";  /*error prompt*/           }
                            if (returnCode == "EMPLOYEE"){
                                return "220";    /*error prompt*/         } 
                            if (returnCode == "CUSTOMER"){
                                return "300";    /*error prompt*/         }     
            
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
        if(uicInstance == null)
            uicInstance = new UIController();
        return uicInstance;
    }
}
    
    
    
    
    
