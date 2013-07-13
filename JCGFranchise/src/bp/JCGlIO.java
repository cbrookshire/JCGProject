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

    //CONSTRUCTOR
    //4 arg
    public JCGlIO(String u, String p, String fN, String eT) {
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
    public String getfN() {
        return fN;
    }

    public void setfN(String fN) {
        this.fN = fN;
    }
    
    //attrib 4
    public String geteT() {
        return eT;
    }

    public void seteT(String eT) {
        this.eT = eT;
    }   
    
    
    
}//end class JCGlIO
    
    
    
    
