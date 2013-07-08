/*
 * JCG Franchise Management System
 * CIST 2931 Summer Session 2013 GTC
 * Author: #Mahmoodur
 * Name: Employee
 * Description: Describes an employee 
 */
 
package bp;


public class Employee {
    
    //attributes - DB
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
    
    // 10 argument constructor
    public Employee ( String firstName,String lastName,
            String address,String city,String state,String zip,String phone,
            String email,String empType,String franchiseNumber )
    {
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
    
    public void setFirstName (String firstName){       
       this.firstName = firstName;
    }
   
    public String getFirstName (){
       return firstName;
    }

    //attribute 2
    public void setLastName (String lastName){       
       this.lastName = lastName;
    }
   
    public String getLastName (){
        return lastName;
    }

     //attribute 3
    public void setAddress (String address){       
        this.address = address;
    }

    public String getAddress (){
        return address;
    } 
   
    //attribute 4
    public void setCity (String city){       
        this.city = city;
    }

    public String getCity (){
        return city;
    } 

    //attribute 5
     public void setState (String state){       
        this.state = state;
    }

    public String getState (){
        return state;
    } 

    //attribute 6
    public void setZip (String zip){       
        this.zip = zip;
    }
    
    public void setZip (int zip){       
        this.zip = String.valueOf(zip);
    }
   
    public int getZip (){
        return Integer.parseInt(zip);
    } 
   
    //attribute 7
    public void setPhone (String phone){       
        this.phone = phone;
    }
   
    public String getPhone (){
        return phone;
    } 
   
    //attribute 8
    public void setEmail (String email){       
        this.email = email;
    }
   
    public String getEmail (){
        return email;
    } 
    
    // attribute 9
    public void setEmpType( String empType){
        this.empType = empType;
    } 
    
    public void setEmpType( int empType){
    this.empType = String.valueOf(empType);
    } 
    
    public int getEmpType( ){
        return Integer.parseInt(empType);
    } 

    // attribute 10
    public void setFranchiseNumber( String franchiseNumber){
        this.franchiseNumber = franchiseNumber;
    } 
    
    public void setFranchiseNumber( int franchiseNumber){
        this.franchiseNumber = String.valueOf(franchiseNumber);
    } 
     
    public int getFranchiseNumber( ){
        return Integer.parseInt(franchiseNumber);
    } 
   
    //UTILITIES
    @Override
    public String toString(){
    
        return String.format("%s%s  %s%s/n%s%s %s, %s %s\n%s%s\n%s%s\n",
        "First Name: ", getFirstName(),
	"Last Name: ", getLastName(),
        "Address: ", getAddress(), getCity(), 
        "         ", getState(), getZip(),
        "Phone: ", getPhone(), 
        "Email: ", getEmail(),
	"Emp Type: ", getEmpType(),
	"Franchise Number: ", getFranchiseNumber());
	
    }//end UTILS    
} // end class Employee
