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
    private String franchiseNumber;

  
   //default constructors
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
    
    // twelve-argument constructor
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
              
    } // end twelve-argument constructor

    // attribute 1
    // set userid
    public void setUserid( String uid)
    {
    userid = uid;
    } // end method setUserid
    
    // get userid
    public String getUserid( )
    {
    return userid;
    } // end method getUserid

    // attribute 2
    // set password
    public void setPassWord( String pword)
    {
    passWord = pword;
    } // end method setPassWord
    
    // get userid
    public String getPassWord( )
    {
    return passWord;
    } // end method getPassWord

    // attribute 3
    // set firstName
    public void setFirstName( String fName)
    {
    firstName = fName;
    } // end method setfirstName
    
    // get firstName
    public String getFirstName( )
    {
    return firstName;
    } // end method getfirstName

    // attribute 4
    // set lastName
    public void setLastName( String lName)
    {
    lastName = lName;
    } // end method setlastName
    
    // get lastName
    public String getLastName( )
    {
    return lastName;
    } // end method getlastName

    // attribute 5
    // set address
    public void setAddress( String adrs)
    {
    address = adrs;
    } // end method setAddress
    
    // get address
    public String getAddress( )
    {
    return address;
    } // end method getAddress

    // attribute 6
    // set city
    public void setCity( String ct)
    {
    city = ct;
    } // end method setCity
    
    // get address
    public String getCity( )
    {
    return city;
    } // end method getCity

    // attribute 7
    // set state
    public void setState( String st)
    {
    state = st;
    } // end method setState
    
    // get state
    public String getState( )
    {
    return state;
    } // end method getState

    // attribute 8
    // set zip
    public void setZip( String zp)
    {
    zip = zp;
    } // end method setZip
    
    // get zip
    public String getZip( )
    {
    return zip;
    } // end method getZip

    // attribute 9
    // set phone
    public void setPhone( String ph)
    {
    phone = ph;
    } // end method setPhone
    
    // get phone
    public String getPhone( )
    {
    return phone;
    } // end method getPhone

    // attribute 10
    // set email
    public void setEmail( String eml)
    {
    email = eml;
    } // end method setEmail
    
    // get email
    public String getEmail( )
    {
    return email;
    } // end method getEmail

    // attribute 11
    // set empType
    public void setEmpType( String empTp)
    {
    empType = empTp;
    } // end method setEmpType
    
    // get empType
    public String getEmpType( )
    {
    return empType;
    } // end method getEmpType

    // attribute 12
    // set franchiseNumber
    public void setFranchiseNumber( String fNumber)
    {
    franchiseNumber = fNumber;
    } // end method setFranchiseNumber
    
    // get franchiseNumber
    public String getFranchiseNumber( )
    {
    return franchiseNumber;
    } // end method getFranchiseNumber
    
} // end class Employee

