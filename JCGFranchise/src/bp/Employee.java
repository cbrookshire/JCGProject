/* JCG Franchise Management System
 * CIST2391 Summer Session 2013 GTC
 * Author: #Mahmood
 * Name: Employee
 * Description: Describes an employee 
 */
package bp;

public class Employee {
    
    private String franchiseNumber;
    private String userid;
    private String passWord;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phone;
    private String email;
    private String empType;
    

    //CONSTRUCTORS
    //default 
    public Employee(){
    
	userid = "";
	passWord = "";
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
    
    //12 args
    public Employee ( String uid,String pword,String fName,String lName,
            String adrs,String ct,String st,String zp,String ph,
            String eml,String empTp,String fNumber )
    {
       userid = uid;
       passWord = pword;
       firstName = fName;
       lastName = lName;
       address = adrs;
       city = ct;
       state = st;
       zip = zp;
       phone = ph;
       email = eml;
       empType = empTp;
       franchiseNumber = fNumber;              
    } 

    //SETS AND GETS
    // attribute 1    
    public void setUserid( String uid)
    {   userid = uid;
    } 
    
    public String getUserid( )
    {   return userid;
    }

    // attribute 2   
    public void setPassWord( String pword)
    {   passWord = pword;
    } 
    
    
    public String getPassWord( )
    {   return passWord;
    } 

    // attribute 3   
    public void setFirstName( String fName)
    {   firstName = fName;
    } 
    
    
    public String getFirstName( )
    {   return firstName;
    } 

    // attribute 4    
    public void setLastName( String lName)
    {   lastName = lName;
    } 
    
    
    public String getLastName( )
    {   return lastName;
    } 

    // attribute 5    
    public void setAddress( String adrs)
    {   address = adrs;
    } 
    
   
    public String getAddress( )
    {   return address;
    } 

    // attribute 6    
    public void setCity( String ct)
    {   city = ct;
    } 
    
    
    public String getCity( )
    {   return city;
    } 

    // attribute 7    
    public void setState( String st)
    {   state = st;
    } 
       
    public String getState( )
    {   return state;
    } 

    // attribute 8    
    public void setZip( String zp)
    {   zip = zp;
    } 
    
    public String getZip( )
    {   return zip;
    } 

    // attribute 9    
    public void setPhone( String ph)
    {   phone = ph;
    } 
    
    public String getPhone( )
    {   return phone;
    } 

    // attribute 10    
    public void setEmail( String eml)
    {   email = eml;
    } 
        
    public String getEmail( )
    {   return email;
    } 

    // attribute 11    
    public void setEmpType( String empTp)
    {   empType = empTp;
    } 
    
    public String getEmpType( )
    {   return empType;
    } 

    // attribute 12    
    public void setFranchiseNumber( String fNumber)
    {   franchiseNumber = fNumber;
    } 
    
   
    public String getFranchiseNumber( )
    {   return franchiseNumber;
    }     
} // end class Employee

