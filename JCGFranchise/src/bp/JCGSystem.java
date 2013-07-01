/* JCG Franchise Management System
 * CIST2391 Summer Session 2013 GTC
 * Author: #Maurice
 * Name: JCGSystem 
 * Description: Runs system processes 
 */
package bp;

public class JCGSystem {
    
    private DBController dControl;
    
    public JCGSystem(){}
    
    
    public void Authentication(String[] UIinput){
        
       dControl.JCGDBController(UIinput);
    
    }
    
    
    
}
