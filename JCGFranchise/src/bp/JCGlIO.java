/* JCG Franchise Management System
 * CIST2931 Summer Session 2013 GTC
 * Author: #Maurice
 * Name: JCGlIO
 * Description: Login Object
 */
package bp;

public class JCGlIO {
    
    //attributes
    private String u;
    private String p;
    private String fN;
    private String eT;
    //private final String thisFranchise = "2";
    
    private static JCGlIO lIO;

    private JCGlIO(){
    
        u = "";
        p = "";
        fN = "";
        eT = "";
    }
        
    //CONSTRUCTOR
    //4 arg
    public JCGlIO(String u, String p, String eT, String fN) {
        this.u = u;
        this.p = p;
        this.fN = fN;
        this.eT = eT;
    }
    
    //SETS and GETS
    //attrib 1
    public String getU() {
        return u;
    }

    public void setU(String u) {
        this.u = u;
    }
    
    //attrib 2
    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }
    
    //attrib 3
    public int getfN() {
        return Integer.parseInt(fN);
    }

    public void setfN(String fN) {
        this.fN = fN;
        System.out.println(fN);
    }
    
    public void setfN(int fN) {
        this.fN = String.valueOf(fN);
        System.out.println(fN);
    }
    //attrib 4
    public String geteT() {
        return eT;
    }

    public void seteT(String eT) {
        this.eT = eT;
    }   
    
 /******************************************************
 * Singleton method for JCGSystem class + Object 
 * clone override 
 ******************************************************/
    public static synchronized JCGlIO getInstance(){            
        if(lIO == null)
        {    lIO = new JCGlIO();}
        return lIO;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException{    
        throw new CloneNotSupportedException();
    }
    
}//end class JCGlIO
    
    
    
    
