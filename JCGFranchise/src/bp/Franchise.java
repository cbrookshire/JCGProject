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
   public Franchise (String franchiseID, String address, String city, 
     String state, String zip, String phone, String email, String airport){
       
       setFranchiseID(franchiseID);
       setAddress(address);
       setCity(city);
       setState(state);
       setZip(zip);
       setPhone(phone);
       setEmail(email);
       setAirport(airport);       
   }
   
   //SETS AND GETS
   //attrib 1
   public void setFranchiseID (String franchiseID){       
       this.franchiseID = franchiseID;
   }
   
   public String getFranchiseID (){
       return franchiseID;
   }
   
   //attrib 2
   public void setAddress (String address){       
       this.address = address;
   }
   
   public String getAddress (){
       return address;
   } 
   
   //attrib 3
   public void setCity (String city){       
       this.city = city;
   }
   
   public String getCity (){
       return city;
   } 
   
   //attrib 4
    public void setState (String state){       
       this.state = state;
   }
   
   public String getState (){
       return state;
   } 
   
   //attrib 5
    public void setZip (String zip){       
       this.zip = zip;
   }
   
   public String getZip (){
       return zip;
   } 
   
   //attrib 6
    public void setPhone (String phone){       
       this.phone = phone;
   }
   
   public String getPhone (){
       return phone;
   } 
   
   //attrib 7
    public void setEmail (String email){       
       this.email = email;
   }
   
   public String getEmail (){
       return email;
   } 
   
   //attrib 8
    public void setAirport (String airport){       
       this.airport = airport;
   }
   
   public String getAirport (){
       return airport;
   }//end SETS and GETS
   
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
    }//end UTILS  
}//end class Franchise
