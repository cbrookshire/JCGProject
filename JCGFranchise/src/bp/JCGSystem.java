/* JCG Franchise Management System
 * CIST2391 Summer Session 2013 GTC
 * Author: #Maurice
 * Name: JCGSystem 
 * Description: Runs system processes 
 */
package bp;

public class JCGSystem {
    
    private FranchisorOwnerSession foSession;
    private FranchiseManagerSession fmSession;
    private FranchiseEmployeeSession feSession;
    private CustomerSession cSession;
    private DBController dControl;
    private UIController uControl;
    
    
    public JCGSystem(){
        //uControl = UIController.getInstance();
        dControl = DBController.getInstance();
    }
    
    
    public String Authentication(Object sysObject, String action){
        
       //local vars
       String dbCode;
       String sysCode;
       
       dbCode = dControl.dbRouter(sysObject, action);
       
       switch (dbCode){
                        //Create session
           case "1":    foSession = new FranchisorOwnerSession();
                        sysCode = "FRANCHISOR";
                        return sysCode;               
           case "2":    fmSession = new FranchiseManagerSession();
                        sysCode = "OWNER";
                        return sysCode;
           case "3":    feSession = new FranchiseEmployeeSession();
                        sysCode = "EMPLOYEE";
                        return sysCode;
           case "4":    cSession = new CustomerSession();
                        sysCode = "CUSTOMER";
                        return sysCode;
           default:     return dbCode;
       }      
    }//end Authentication method
}//end class JCGSystem 
