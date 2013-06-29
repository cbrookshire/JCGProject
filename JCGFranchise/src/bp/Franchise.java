/* JCG Franchise Management System
 * CIST2391 Summer Session 2013 GTC
 * @author Maurice
 * Name: Franchise
 * Description: Describes a franchise 
 */
package bp;


public class Franchise {
    
   //franchise attributes - DB 
   private final String name = "JCG Franchise #";
   private String franchiseID;
   private String address;
   private String city;
   private String state;
   private String zip;
   private String phone;
   private String email; 
   private String airport;
   
   
   //CONTRUCTORS
   //default
   public Franchise(){
    
       franchiseID = "";
       address = "";
       city = "";
       state = "";
       zip = "";
       phone = "";
       email = "";
       airport = "";   }
   
   //8 arg constructor
   public Franchise (String fID, String addr, String cty, 
           String ste, String zp, String ph, String em, String airpt){
   }
   
   //SETS AND GETS
   //attrib 1
   public void setFranchiseID (String fID){       
       franchiseID = fID;
   }
   
   public String getFranchiseID (){
       return franchiseID;
   }
   
   //attrib 2
   public void setAddress (String addr){       
       address = addr;
   }
   
   public String getAddress (){
       return address;
   } 
   
   //attrib 3
   public void setCity (String cty){       
       city = cty;
   }
   
   public String getCity (){
       return city;
   } 
   
   //attrib 4
    public void setState (String ste){       
       state = ste;
   }
   
   public String getState (){
       return state;
   } 
   
   //attrib 5
    public void setZip (String zp){       
       zip = zp;
   }
   
   public String getZip (){
       return zip;
   } 
   
   //attrib 6
    public void setPhone (String ph){       
       phone = ph;
   }
   
   public String getPhone (){
       return phone;
   } 
   
   //attrib 7
    public void setEmail (String em){       
       email = em;
   }
   
   public String getEmail (){
       return email;
   } 
   
   //attrib 8
    public void setAirport (String airpt){       
       airport = airpt;
   }
   
   public String getAirport (){
       return airport;
   }
   
   //UTILITIES
   @Override
   public String toString(){
    
    return String.format("%s%s%s  %s%s/n%s%s %s, %s %s\n%s%s\n%s%s\n",
        "Franchise: ", name, getFranchiseID(), 
        "Airport: ", getAirport(),
        "Address: ", getAddress(), getCity(), 
        "         ", getState(), getZip(),
        "Phone: ", getPhone(), 
        "Email: ", getEmail());
    }
  
}
