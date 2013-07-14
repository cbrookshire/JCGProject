/* JCG Franchise Management System
 * CIST2931 Summer Session 2013 GTC
 * Author: #Maurice
 * Name: Franchise
 * Description: Describes a franchise 
 */
package bp;


public class Franchise {
    
   //attributes - DB 
   private final String name = "JCG Franchise #";
   private String franchiseID;
   private String address;
   private String city;
   private String state;
   private String zip;
   private String phone;
   private String email; 
   private String airport;
   private String airportID;

      
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
       airport = "";  
       airportID = "";
   }
   
   //8 args 
   public Franchise (String franchiseID, String address, String city, 
     String state, String zip, String phone, String email, String airport,
     String airportID){
       
       setFranchiseID(franchiseID);
       setAddress(address);
       setCity(city);
       setState(state);
       setZip(zip);
       setPhone(phone);
       setEmail(email);
       setAirport(airport); 
       setAirportID(airportID);
   }
   
   //SETS AND GETS
   //attrib 1
   public void setFranchiseID (String franchiseID){       
       this.franchiseID = franchiseID;
   }
   
   //overloaded set for DB - converts int to string and sets
   public void setFranchiseID (int franchiseID){
   
       this.franchiseID = String.valueOf(franchiseID);
   }
   
   public int getFranchiseID (){
       return Integer.parseInt(franchiseID);
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
   
   //overloaded set for DB - converts int to string and sets 
   public void setZip (int zip){       
       this.zip = String.valueOf(zip);
   }
   
   public int getZip (){
       return Integer.parseInt(zip);
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
   }
   
   
   //attrib 9
   public void setAirportID(String airportID) {
        this.airportID = airportID;
    }
  
   //overloaded set for DB - converts int to string and sets 
   public void setAirportID(int airportID) {
        this.airportID = String.valueOf(airportID);
    }
    
   public int getAirportID() {
        return Integer.parseInt(airportID);
    }
   
   
   
   
   //UTILITIES
   //Class Print Method
   @Override
   public String toString(){
    
    return String.format("%s%s%s  %s%s/n%s%s %s, %s %s\n%s%s\n%s%s\n",
        "Franchise: ", name, getFranchiseID(), 
        "Airport:   ", getAirportID(),
        "Address:   ", getAddress(), getCity(), 
        "           ", getState(), getZip(),
        "Phone:     ", getPhone(), 
        "Email:     ", getEmail());
    } 
}//end class Franchise
