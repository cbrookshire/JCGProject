/* JCG Franchise Management System
 * CIST2931 Summer Session 2013 GTC
 * Author: #Maurice
 * Name: JCGSystem 
 * Description: Runs system processes 
 */
package bp;


public class JCGSystem {
    
    private static JCGSystem jcgSysInstance;
    private JCGlIO lIO;
    private CustomerSession cSession;
    private FranchisorOwnerSession foSession;
    private FranchiseManagerSession fmSession;
    private FranchiseEmployeeSession feSession;
    private DBController dControl;
    private UIController uControl;
    
    //default constructor private singleton
    private JCGSystem(){
        //uControl = UIController.getInstance();
        dControl = DBController.getInstance();
        lIO = JCGlIO.getInstance();
    }
    
    //used to start UI
    public void startSystem(){    
        uControl.StartUI();
    }
        
    //passes JCGlIO object to DBController for processing
    public String Authentication(Object sysObject, String action){
        
       String dbCode;   //holds database return code
       String sysCode;  //holds JCGSystem code
       
       //call to DBController method
       dbCode = dControl.dbSystemRouter(sysObject, action);
       
       //determine action based on database return code
       switch (dbCode){
                        //set employee type in JCGlIO and create sessions
           case "1":    lIO.seteT(dbCode);
                        foSession = FranchisorOwnerSession.getInstance();
                        sysCode = "OWNER"; //session name
                        return sysCode;               
           case "2":    lIO.seteT(dbCode);
                        fmSession = new FranchiseManagerSession();
                        sysCode = "MANAGER";      //session name
                        return sysCode;
           case "3":    lIO.seteT(dbCode);
                        feSession = new FranchiseEmployeeSession();
                        sysCode = "EMPLOYEE";   //session name
                        return sysCode;
           case "99":    lIO.seteT(dbCode);
                        cSession = new CustomerSession();
                        sysCode = "CUSTOMER";   //session name
                        return sysCode;
           default:     return dbCode;          //no session return dbCode
       }//end switch      
    }//end Authentication method
    
    //Passes JCGlIO object to DBController for processing
    public String UpdatePassword (Object sysObject, String action){
    
       String dbCode;   //holds database return code
              
       //call to DBController method
       dbCode = dControl.dbSystemRouter(sysObject, action);
       return dbCode;         
    }//end UpdatePassword method
    
    
    public String ResetDatabase (Object sysObject, String action){
        
        String dbCode;
        
        dbCode = dControl.dbSystemRouter(sysObject, action);
        return dbCode;          
    }
    
    public String Logout (Object sysObject, String action){    
        
        String dbCode;
        dbCode = dControl.dbSystemRouter(sysObject, action); 
        return dbCode;
    }
    
             
/******************************************************
 * Singleton method for JCGSystem class + Object 
 * clone override 
 ******************************************************/
    public static synchronized JCGSystem getInstance()
    {            
        if(jcgSysInstance == null) {
            jcgSysInstance = new JCGSystem();
        }
        return jcgSysInstance;
    } 
    
    @Override
    public Object clone()throws CloneNotSupportedException{    
        throw new CloneNotSupportedException();    
    }
}//end class JCGSystem 
