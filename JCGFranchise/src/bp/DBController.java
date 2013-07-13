/* JCG Franchise Management System
 * CIST2931 Summer Session 2013 GTC
 * Author: #Maurice
 * Name: DB Controller
 * Description: Controls communications 
 * between UI and DB 
 */
package bp;

import JCGExceptions.*;
import db.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;


public class DBController {
    
    //attributes
    private JCGDatabase dbase;
    private Queries queryDB;
    private static DBController dbcInstance;
         
    
    //CONSTRUCTOR
    //default
    private DBController(){ }
    
    
    //UTILITIES
    //HACK#1 the following is Maurice's hack until he figures out how to 
    //convert an array of Franchise, Vehicle, Employee, Customer into an  
    //an array of generic objects safely
    public ArrayList <Franchise> DBfranchisorRouter (Object sysObject, 
             String action){
    
        //local container 
        ArrayList <Franchise> temp;
         
        try{            
                if (sysObject instanceof String && "VIEWALL".equals(action)){   
                    temp = queryDB.GetAllFranchises();
                    return temp;
                }
                if (sysObject instanceof Franchise && "VIEWITEM".equals(action)){    
                    temp = queryDB.GetOneFranchise(((Franchise)sysObject).getFranchiseID());
                    return temp;
                }                
        }
        catch(UnauthorizedUserException e){
                Franchise error = new Franchise(e.getMessage(), 
                        "", "", "", "", "", "", "", "" );
                temp = new ArrayList();
                temp.add(error);
                return temp; 
        }
        catch(BadConnectionException e){
                Franchise error = new Franchise(e.getMessage(), "", "", "",
                        "", "", "", "", "" );
                temp = new ArrayList();
                temp.add(error);
                return temp;
        }          
        catch(DoubleEntryException e){
                Franchise error = new Franchise(e.getMessage(), "", 
                        "", "", "", "", "", "", "" );
                temp = new ArrayList();
                temp.add(error);
                return temp;             
        }
        return null;
    }//end DBfranchisorRouter method
    
        
    //HACK #2 
    public ArrayList <Vehicle> DBvehicleRouter (Object sysObject, 
             String action){
        
        //local container
        ArrayList <Vehicle> temp;
         
        try{
            if (sysObject instanceof String && "VIEWALL".equals(action)){   
                temp = queryDB.VehiclesForFranchise
                        (((Vehicle)sysObject).getFranchiseNumber());
                return temp;
            }
            if (sysObject instanceof Vehicle && "VIEWITEM".equals(action)){    
                temp = queryDB.GetOneVehicle
                        (((Vehicle)sysObject).getVehicleID());
                return temp;
            }               
        }
        catch(UnauthorizedUserException e){
                Vehicle error = new Vehicle("", e.getMessage(), 
                        "", "", "", "", "", "", "", "", "", "" );
                temp = new ArrayList();
                temp.add(error);
                return temp; 
        }
        catch(BadConnectionException e){
                Vehicle error = new Vehicle("", e.getMessage(), 
                        "", "", "", "", "", "", "", "", "", "" );
                temp = new ArrayList();
                temp.add(error);
                return temp;
        }          
        catch(DoubleEntryException e){
                Vehicle error = new Vehicle("", e.getMessage(), 
                        "", "", "", "", "", "", "", "", "", "" );
                temp = new ArrayList();
                temp.add(error);
                return temp;             
        }         
        return null;
     }//end DBvehicleRouter method   
               
    //HACK #3
    public ArrayList <Employee> DBemployeeRouter (Object sysObject, 
             String action){
    
        //local container 
        ArrayList <Employee> temp;
         
         try{
                if (sysObject instanceof String && "VIEWALL".equals(action)){   
                    temp = queryDB.AllEmployeesInFranchise
                            (((Employee)sysObject).getFranchiseNumber());
                    return temp;
                }
                if (sysObject instanceof Employee && "VIEWITEM".equals(action)){    
                    temp = queryDB.SingleEmployeeData
                            (((Employee)sysObject).getEmployeeID());
                    return temp;
            }               
        }
        catch(UnauthorizedUserException e){
                Employee error = new Employee(e.getMessage(), 
                        "", "", "", "", "", "", "", "", "","");
                temp = new ArrayList();
                temp.add(error);
                return temp; 
        }
        catch(BadConnectionException e){
                Employee error = new Employee(e.getMessage(), 
                        "", "", "", "", "", "", "", "", "","");
                temp = new ArrayList();
                temp.add(error);
                return temp;
        }          
        catch(DoubleEntryException e){
                Employee error = new Employee(e.getMessage(), 
                        "", "", "", "", "", "", "", "", "","");
                temp = new ArrayList();
                temp.add(error);
                return temp;             
        }         
        return null;
     }//end DBemployeeRouter method    
        
    
    //HACK #4 routes customer views
    public ArrayList <Customer> DBcustomerRouter (Object sysObject, 
             String action){
        
        //local container
        ArrayList <Customer> temp;
         
        try{
            if (sysObject instanceof String && "VIEWALL".equals(action)){   
                temp = queryDB.SingleCustomerData
                        (((Customer)sysObject).getCustomerID());
                return temp;
            }
            if (sysObject instanceof Customer && "VIEWITEM".equals(action)){    
                temp = queryDB.SingleCustomerData
                        (((Customer)sysObject).getCustomerID());
                return temp;
            }               
        }
        catch(UnauthorizedUserException e){
            Customer error = new Customer(e.getMessage(), 
                    "", "", "", "", "", "", "", "", "","");
            temp = new ArrayList();
            temp.add(error);
            return temp; 
        }
        catch(BadConnectionException e){
            Customer error = new Customer(e.getMessage(), 
                    "", "", "", "", "", "", "", "", "","");
            temp = new ArrayList();
            temp.add(error);
            return temp;
        }          
        catch(DoubleEntryException e){
            Customer error = new Customer(e.getMessage(), 
                    "", "", "", "", "", "", "", "", "","");
            temp = new ArrayList();
            temp.add(error);
            return temp;             
        } 
        return null;
    }//end DBcustomerRouter method   
    
    
    //HACK #4 routes reservation views      
    public ArrayList <Reservation> DBreservationRouter (Object sysObject, 
             String action){
    
        //local container
        ArrayList <Reservation> temp;
         
        try{
            if (sysObject instanceof String && "VIEWALL".equals(action)){   
                temp = queryDB.ReservationsForFranchise
                        (((Reservation)sysObject).getFranchiseNumber());
                return temp;
            }
            if (sysObject instanceof Reservation && "VIEWITEM".equals(action)) {    
                temp = queryDB.ReservationsByCustomerForFranchise
                        (((Reservation)sysObject).getFranchiseNumber(), 
                        (((Reservation)sysObject)).getCustomerID());
                return temp;
            }               
        }
        catch(UnauthorizedUserException e){
            Reservation error = new Reservation(e.getMessage(), 
                    "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "");
            temp = new ArrayList();
            temp.add(error);
            return temp; 
        }
        catch(BadConnectionException e){
            Reservation error = new Reservation(e.getMessage(), 
                    "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "");
            temp = new ArrayList();
            temp.add(error);
            return temp;
        }          
        catch(DoubleEntryException e){
            Reservation error = new Reservation(e.getMessage(), 
                    "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "");
            temp = new ArrayList();
            temp.add(error);
            return temp;             
        }
        return null;
    }//end DBreservationRouter method  
    
    
    
    public String dbSystemRouter (Object sysObject, String action){
        
        //local var
        int dbReturnCode;
        Boolean milesReturnCode;
         
        //DBSwitch
        switch(action){
            
        case "LOGIN":   
            try{   
                     //Cast object and attempt connection
                     JCGlIO temp = (JCGlIO)sysObject;
                     dbase = new JCGDatabase(temp);
                     //System.out.println(temp.getU() + temp.getP());
                     //Attempt login 
                     dbReturnCode = dbase.login(temp);
                     String convert = Integer.toString(dbReturnCode);
                     //Return user role
                     return convert;                                
             }
             catch(InvalidUserException e){
                 return e.getMessage();
             }
             catch(BadConnectionException e){
                 return e.getMessage();
             }
             catch(NewUserException e){
                 return e.getMessage();
             }  

        case "UPDATEPASSWORD":
            
            try{ 
                //Cast object and attempt update
                JCGlIO temp = (JCGlIO)sysObject;
                dbReturnCode = dbase.updatePassword(temp);
                String convert = Integer.toString(dbReturnCode);
                //return status
                return convert;
            }
            catch(InvalidUserException e){
                return e.getMessage();
            }
            catch(BadConnectionException e){
                return e.getMessage();
            }                         
                
        case "RESET": 
            
            try{
                    dbReturnCode = queryDB.resetDatabase();
                    String convert = Integer.toString(dbReturnCode);
            }
            catch(FileNotFoundException e){
                return e.getMessage();
            }
            catch(BadConnectionException e){
                return e.getMessage();
            }        
        
        
        case "LOGOUT":  
            
            dbase.logOff();                                
                
            
        case "ADD": 
            
            //determines object type and calls corresponding 
            //insert methods   
            try{
                if (sysObject instanceof Franchise) {
                    dbReturnCode = queryDB.insertFranchise(((Franchise)sysObject));
                    return String.valueOf(dbReturnCode);
                }
                if (sysObject instanceof Vehicle) {
                    dbReturnCode = queryDB.insertVehicle(((Vehicle)sysObject));
                    return String.valueOf(dbReturnCode);
                } 
                if (sysObject instanceof Employee) {
                    dbReturnCode = queryDB.insertEmployee(((Employee)sysObject));
                    return String.valueOf(dbReturnCode);
                }
                /*if (sysObject instanceof Reservation) {
                    dbReturnCode = queryDB.insertReservation((Reservation)sysObject);
                    return String.valueOf(dbReturnCode);
                }*/
                if (sysObject instanceof Customer) {
                    dbReturnCode = queryDB.insertCustomer(((Customer)sysObject));
                    return String.valueOf(dbReturnCode);
                }                               
            }
            catch(UnauthorizedUserException e){
                return e.getMessage();
            }  
            catch(BadConnectionException e){
                return e.getMessage();
            }
            catch(DoubleEntryException e){
                return e.getMessage();
            }
                
        case "DELETE":  
                    
            //determines object type and calls corresponding 
            //insert methods   
            try
            {
                if (sysObject instanceof Franchise){
                    milesReturnCode = 
                    queryDB.RemoveFranchise(((Franchise)sysObject).getFranchiseID());
                    if (milesReturnCode == true) 
                        return "1";
                    else
                        return "0";                                                                                      
                }
                else if (sysObject instanceof Vehicle){
                    milesReturnCode =
                    queryDB.RemoveLimo(((Vehicle)sysObject).getVehicleID());
                    if (milesReturnCode == true) 
                        return "1";                             
                    else
                        return "0";                                                        
                }
                else if (sysObject instanceof Employee){
                    milesReturnCode =
                    queryDB.RemoveEmployee(((Employee)sysObject).getEmployeeID());
                    if (milesReturnCode == true) 
                        return "1";
                    else
                        return "0";
                }
                /*if (sysObject instanceof Reservation){
                    milesReturnCode = 
                    queryDB.RemoveReservation(((Reservation)sysObject).getReservationNumber());
                    if (milesReturnCode == true) 
                        return "1";                             
                    else
                        return "0";
                }
                if (sysObject instanceof Customer){
                    milesReturnCode =
                    queryDB.RemoveCustomer(((Customer)sysObject).getCustomerID()); 
                     if (milesReturnCode == true) 
                        return "1";                             
                    else
                        return "0";
                }*/                               
            }
            catch(UnauthorizedUserException e){
                return e.getMessage();
            }  
            catch(BadConnectionException e){
                return e.getMessage();
            }
             catch(DoubleEntryException e){
                return e.getMessage();
            }   
                
        case "EDIT":

            //determines object type and calls corresponding 
            //insert methods   
            try{
                if (sysObject instanceof Franchise) {
                    dbReturnCode = queryDB.updateFranchise((Franchise)sysObject);
                    return String.valueOf(dbReturnCode);
                }
                if (sysObject instanceof Vehicle) {
                    dbReturnCode = queryDB.updateVehicle((Vehicle)sysObject);
                    return String.valueOf(dbReturnCode);
                } 
                if (sysObject instanceof Employee) {
                    dbReturnCode = queryDB.updateEmployee((Employee)sysObject);
                    return String.valueOf(dbReturnCode);
                }
                /*if (sysObject instanceof Reservation) {
                    dbReturnCode = queryDB.updateReservation((Reservation)sysObject);
                    return String.valueOf(dbReturnCode);
                }*/
                if (sysObject instanceof Customer) {
                    dbReturnCode = queryDB.updateCustomer((Customer)sysObject);
                    return String.valueOf(dbReturnCode);
                }                               
            }
            catch(UnauthorizedUserException e){
                return e.getMessage();
            }  
            catch(BadConnectionException e){
                return e.getMessage();
            }
            catch(DoubleEntryException e){
                return e.getMessage();
            } 
                
        default:    return null;      
       }//end DB Switch
    }//end DBRouter method
    
   
   /* public static <T> ArrayList convertToGenericList (ArrayList <T> list){
    
        //local container
        ArrayList <Object> temp = new ArrayList();
        
        Iterator <T> iterator = list.iterator();
        while (iterator.hasNext()){            
            temp.add(iterator);
        }
        return temp;
    }//end dbSessionRouter */  
    
    
    
 /******************************************************
 * Singleton method for JCGSystem class + Object 
 * clone override 
 ******************************************************/
    public static synchronized DBController getInstance(){            
        if(dbcInstance == null)
        {    dbcInstance = new DBController();}
        return dbcInstance;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException{    
        throw new CloneNotSupportedException();
    }
}//end DBController class
