/* JCG Franchise Management System
 * CIST2391 Summer Session 2013 GTC
 * Author: #Maurice
 * Name: UI Controller
 * Description: Controls communications 
 * between UI and BP 
 */
package bp;
import ui.*;

public class UIController {
    
   //attributes
    private JCGSystem jcgSys;
    private static UIController uicInstance;
    private ui.FranchisorOverviewPanel uiScreen;
    
    //CONSTRUCTOR
    //default
    public UIController(){}
    
    //UTILITIES
    public String UIRouter (Object UIObject, String action){
        
        String rCode;
        
        //call appropriate BP method
        switch(action){
            case "LOGIN":   rCode = jcgSys.Authentication(UIObject, action);
                            if (rCode.charAt(0) == 'P')
                            {   new uiScreen();                           }
                            return rCode;
                
            default:        return "-1";
        }
}
    //singleton method for UIController class
    public static UIController getInstance(){            
        if(uicInstance == null)
        {    uicInstance = new UIController();}
        return uicInstance;
       }
}
    
    
    
    
    
