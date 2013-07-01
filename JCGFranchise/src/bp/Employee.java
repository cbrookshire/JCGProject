/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bp;

/**
 *
 * @author MdMahmoodur
 */
public class Employee {
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
    private String empType;
    private String franchiseNumber;

  
   //default constructors
   public Employee(){
    
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
	empType = "";
	franchiseNumber = "";   }
    
    // twelve-argument constructor
    public Employee ( String userID, String password,String firstName,String lastName,
            String address,String cityt,String state,String zip,String phone,
            String email,String empType,String franchiseNumber )
    {
       setUserid(userID);
       setPassword(password);
       setFirstName(firstName);
       setLastName(lastName);
       setAddress(address);
       setCity(city);
       setState(state);
       setZip(zip);
       setPhone(phone);
       setEmail(email);
       setEmpType(empType);
       setFranchiseNumber(franchiseNumber);
              
    } // end twelve-argument constructor
	

//SETS AND GETS
    // attribute 1
    
    public void setUserid( String userID){
    this.userID = userID;
    } 
    
    public String getUserID( )
    {
    return userID;
    } 

    //attribute 2
   public void setPassword (String password){       
       this.password = password;
   }
   
   public String getPassword (){
       return password;
   }

    //attribute 3
   public void setFirstName (String firstName){       
       this.firstName = firstName;
   }
   
   public String getFirstName (){
       return firstName;
   }

//attribute 4
   public void setLastName (String lastName){       
       this.lastName = lastName;
   }
   
   public String getLastName (){
       return lastName;
   }

    //attribute 5
   public void setAddress (String address){       
       this.address = address;
   }
   
   public String getAddress (){
       return address;
   } 
   
   //attribute 6
   public void setCity (String city){       
       this.city = city;
   }
   
   public String getCity (){
       return city;
   } 
   
   //attribute 7
    public void setState (String state){       
       this.state = state;
   }
   
   public String getState (){
       return state;
   } 
   
   //attribute 8
    public void setZip (String zip){       
       this.zip = zip;
   }
   
   public String getZip (){
       return zip;
   } 
   
 //attribute 9
    public void setPhone (String phone){       
       this.phone = phone;
   }
   
   public String getPhone (){
       return phone;
   } 
   
 //attribute 10
    public void setEmail (String email){       
       this.email = email;
   }
   
   public String getEmail (){
       return email;
   } 
    
// attribute 11
    public void setEmpType( String empType){
    this.empType = empType;
    } 
    
     public String getEmpType( ){
    return empType;
    } 

    // attribute 12
     public void setFranchiseNumber( String franchiseNumber){
    this.franchiseNumber = franchiseNumber;
    } 
    
    public String getFranchiseNumber( ){
    return franchiseNumber;
    } 
    
} // end class Employee
