/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bp;

/**
 *
 * @author MdMahmoodur
 */
public class Customer {

	private String customerID;
	private String userID;
	private String password;
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
   //default
   public Customer(){
    
	customerID = "";
	userID = "";
	password = "";
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
   
   //13 arg constructor
   public Customer (String customerID, String userID, String password, String firstName, 
		String lastName, String address, String city, String state, String zip, 
		String phone, String email, String reservationCount, String memberID){
       
	setCustomerID(customerID);
	setUserID(userID);
	setPassword(password);
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
   
   //SETS AND GETS
   //attribute 1
   public void setCustomerID (String customerID){       
       this.customerID = customerID;
   }
   
   public String getCustomerID (){
       return customerID;
   }

//attribute 2
   public void setUserID (String userID){       
       this.userID = userID;
   }
   
   public String getUserID (){
       return userID;
   }
   
//attribute 3
   public void setPassword (String password){       
       this.password = password;
   }
   
   public String getPassword (){
       return password;
   }

//attribute 4
   public void setFirstName (String firstName){       
       this.firstName = firstName;
   }
   
   public String getFirstName (){
       return firstName;
   }

//attribute 5
   public void setLastName (String lastName){       
       this.lastName = lastName;
   }
   
   public String getLastName (){
       return lastName;
   }

   //attribute 6
   public void setAddress (String address){       
       this.address = address;
   }
   
   public String getAddress (){
       return address;
   } 
   
   //attribute 7
   public void setCity (String city){       
       this.city = city;
   }
   
   public String getCity (){
       return city;
   } 
   
   //attribute 8
    public void setState (String state){       
       this.state = state;
   }
   
   public String getState (){
       return state;
   } 
   
   //attribute 9
    public void setZip (String zip){       
       this.zip = zip;
   }
   
   public String getZip (){
       return zip;
   } 
   
 //attribute 10
    public void setPhone (String phone){       
       this.phone = phone;
   }
   
   public String getPhone (){
       return phone;
   } 
   
 //attribute 11
    public void setEmail (String email){       
       this.email = email;
   }
   
   public String getEmail (){
       return email;
   } 
   
 //attribute 12
    public void setReservationCount (String reservationCount){       
       this.reservationCount = reservationCount;
   }
   
   public String getReservationCount (){
       return reservationCount;

//attribute 13
   public void setMemberID (String memberID){     
       this.memberID = memberID;
   }
   
   public String getMemberID (){
       return memberID;

   }//end SETS and GETS
   
   
}//end class Customer
