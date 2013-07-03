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
    
    
    public String Authentication(Object sysObject, String action){
        
       String dbCode;
       dbCode = dControl.dbRouter(sysObject, action);
       
       return dbCode;
        
    
    }
    
    
    
}
