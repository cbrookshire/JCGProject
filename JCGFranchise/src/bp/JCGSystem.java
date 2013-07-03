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
    
    
    public int Authentication(Object sysObject, String action){
        
        int dbCode;
        dbCode = dControl.dbRouter(sysObject, action);
        return dbCode;
        
    
    }
    
    
    
}
