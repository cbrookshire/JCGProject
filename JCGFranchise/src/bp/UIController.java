/* JCG Franchise Management System
 * CIST2391 Summer Session 2013 GTC
 * Author: #Maurice
 * Name: UI Controller
 * Description: Controls communications 
 * between UI and BP 
 */
package bp;

public class UIController {
    
    private String uiAction;
    private JCGSystem jcg;
    private static UIController uicInstance;

    
    
    
    //CONSTRUCTOR
    //default
    public UIController(){    
        uiAction="";
    }
    
    //SETS AND GETS
    public void setUiAction(String uiAction){
    
        this.uiAction = uiAction;
    }
    
    public String getUiAction(){
    
        return uiAction;
    }
    
    //UTILITIES
    public int UIRouter (Object UIObject, String action){
        
        int dbrCode;
        
        //call appropriate BP method
        switch(action){
            case "LOGIN":   dbrCode = jcg.Authentication(UIObject, action);
                            return dbrCode;
                
            default:        return 0;
        }
}
    
    public static UIController getInstance(){            
        if(uicInstance == null)
        {    uicInstance = new UIController();}
        return uicInstance;
       }
}
    
    
    
    
    
