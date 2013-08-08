/* JCG Franchise Management System
 * CIST 2931 Summer Session 2013 GTC
 * Author: #Mahmoodur
 * Name: Customer
 * Description: Describes a customer 
 */
package bp;

public class Customer {

	//attributes - DB
        private String customerID;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String phone;
	private String email;
	private String reservationCount;
	private String memberID;
   
   
   //CONTRUCTORS
   public Customer(){
    
	customerID = "";
	firstName = "";
	lastName = "";
	address = "";
	city = "";
	state = "";
	zip = "";
	phone = "";
	email = "";
	reservationCount = "";
	memberID = "";   }
   
   //11 args
   public Customer (String customerID, String firstName, String lastName, 
           String address, String city, String state, String zip, String phone, 
           String email, String reservationCount, String memberID){
       
	setCustomerID(customerID);
	setFirstName(firstName);
	setLastName(lastName);
	setAddress(address);
	setCity(city);
    	setState(state);
     	setZip(zip);
     	setPhone(phone);
    	setEmail(email);
	setReservationCount(reservationCount);
     	setMemberID(memberID);       
   }
   
    //SETS AND GETS methods
    //attribute 1
    public void setCustomerID (String customerID){       
       this.customerID = customerID;
    }
    
    public void setCustomerID (int customerID){       
       this.customerID = String.valueOf(customerID);
    }
       
    public int getCustomerID (){
       return Integer.parseInt(customerID);
    }
    
    //attribute 2
    public void setFirstName (String firstName){       
       this.firstName = firstName;
    }
   
    public String getFirstName (){
       return firstName;
    }

    //attribute 3
    public void setLastName (String lastName){       
       this.lastName = lastName;
    }
   
    public String getLastName (){
       return lastName;
    }

    //attribute 4
    public void setAddress (String address){       
       this.address = address;
    }
   
     public String getAddress (){
       return address;
    } 
   
    //attribute 5
    public void setCity (String city){       
       this.city = city;
    }
   
    public String getCity (){
       return city;
    } 
   
    //attribute 6
    public void setState (String state){       
       this.state = state;
    }
   
    public String getState (){
       return state;
    } 
   
    //attribute 7
    public void setZip (String zip){       
       this.zip = zip;
    }
    
    public void setZip (int zip){       
       this.zip = String.valueOf(zip);
    }
   
    public int getZip (){
       return Integer.parseInt(zip);
   } 
   
 //attribute 8
    public void setPhone (String phone){       
       this.phone = phone;
    }
   
    public String getPhone (){
       return phone;
    } 
   
    //attribute 9
    public void setEmail (String email){       
       this.email = email;
    }
   
    public String getEmail (){
       return email;
    } 
   
    //attribute 10
    public void setReservationCount (String reservationCount){       
       this.reservationCount = reservationCount;
    }
   
    public void setReservationCount (int reservationCount){       
       this.reservationCount = String.valueOf(reservationCount);
    }
        
    public int getReservationCount (){
       return Integer.parseInt(reservationCount);
    }
    
    //attribute 11
    public void setMemberID (String memberID){     
       this.memberID = memberID;
    }
   
    public void setMemberID (int memberID){     
       this.memberID = String.valueOf(memberID);
    }
    public String getMemberID (){
       return memberID;
    } 
    
  //UTILITIES
  @Override
   public String toString(){
    
    return String.format("%s%s\n%s%s\n%s\n%s%s%s\n%s\n%s\n%s\n%s%s\n\n",
        "CustomerID: ", getCustomerID(), 
        getFirstName(), getLastName(),
        getAddress(), 
        getCity(), getState(), getZip(),
        getPhone(), 
        getEmail(),
	getReservationCount(),
	"MemberID: ", getMemberID());
	
    }//end UTILS  
}//end class Customer
