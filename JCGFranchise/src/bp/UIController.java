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
    
    private String uiAction;
    private JCGSystem jcg;
        
    
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
    public int JCGUIController (String[]UIInput){
        
        //last member of array is ui action
        uiAction = UIInput[UIInput.length-1];
    
        //call appropriate BP method
        switch(uiAction){
            case "LOGIN":   jcg.Authentication(UIInput);
                
            default:        uiAction = "";
                            return 0;
        }
}
}
    
    
    
    
    
