/* JCG Franchise Management System
 * CIST2931 Summer Session 2013 GTC
 * Author: #Maurice
 * Name: JCGSystem 
 * Description: Runs system processes 
 */
package bp;

public class JCGSystem {
    
    private static JCGSystem jcgSysInstance;
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
       
       dbCode = dControl.dbSystemRouter(sysObject, action);
       
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
    
    
    public String UpdatePassword (Object sysObject, String action){
    
        //local vars
       String dbCode;
       String sysCode;
       
       //send to DB Controller
       dbCode = dControl.dbSystemRouter(sysObject, action);
       if ("1".equals(dbCode)){
           sysCode = "OK";
           return sysCode;    }
       else {
            return dbCode;    }     
    }//end UpdatePassword method
    
    
    //singleton method for UIController class
    public static JCGSystem getInstance()
    {            
        if(jcgSysInstance == null) {
            jcgSysInstance = new JCGSystem();
        }
        return jcgSysInstance;
    }//end singleton method   
    
}//end class JCGSystem 
