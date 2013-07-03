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
    public UIController(){}
    
    //UTILITIES
    public int UIRouter (Object UIObject, String action){
        
        int rCode = 0;
        
        //call appropriate BP method
        switch(action){
            case "LOGIN":   rCode = jcgSys.Authentication(UIObject, action);
                            return rCode;
                
            default:        return 0;
        }
}
    
    public static UIController getInstance(){            
        if(uicInstance == null)
        {    uicInstance = new UIController();}
        return uicInstance;
       }
}
    
    
    
    
    
