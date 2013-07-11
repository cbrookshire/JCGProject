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
    //HACK#1 the following is Maurice's hack until he discovers how to 
    //convert an array of Franchise, Vehicle, Employee, Customer 
    //into a generic object
    public ArrayList <Franchise> DBfranchisorRouter (Object sysObject, 
             String action){
    
         ArrayList <Franchise> temp;
         
         try{            
                if ("VIEWALL".equals(action)){   
                    temp = queryDB.GetAllFranchises();
                    return temp;
                }
                if (sysObject instanceof Franchise){    
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
     }//end fovDBRouter method
        
    //HACK #2 
    public ArrayList <Vehicle> DBvehicleRouter (Object sysObject, 
             String action){
    
         ArrayList <Vehicle> temp;
         
         try{
                if ("VIEWALL".equals(action)){   
                    temp = queryDB.VehiclesForFranchise(((Vehicle)sysObject).getFranchiseNumber());
                    return temp;
                }
                if (sysObject instanceof Vehicle){    
                    temp = queryDB.GetOneVehicle(((Vehicle)sysObject).getVehicleID());
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
     }//end fovDBRouter method   
               
    //HACK #3
    public ArrayList <Employee> DBemployeeRouter (Object sysObject, 
             String action){
    
         ArrayList <Employee> temp;
         
         try{
                if ("VIEWALL".equals(action)){   
                    temp = queryDB.AllEmployeesInFranchise
                            (((Employee)sysObject).getFranchiseNumber());
                    return temp;
                }
                else{    
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
       
     }//end DBemployeeRouter method    
        
    
    //HACK #4 routes customer views
    public ArrayList <Customer> DBcustomerRouter (Object sysObject, 
             String action){
    
         ArrayList <Customer> temp;
         
         try{
                if ("VIEWALL".equals(action)){   
                    temp = queryDB.SingleCustomerData
                            (((Customer)sysObject).getCustomerID());
                    return temp;
                }
                else{    
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
    }//end DBcustomerRouter method   
    
    
   
    //HACK #4 routes reservation views      
    public ArrayList <Reservation> DBreservationRouter (Object sysObject, 
             String action){
    
         ArrayList <Reservation> temp;
         
         try{
                if ("VIEWALL".equals(action)){   
                    temp = queryDB.ReservationsForFranchise
                            (((Reservation)sysObject).getFranchiseNumber());
                    return temp;
                }
                else{    
                    temp = queryDB.ReservationsByCustomerForFranchise
                            (((Reservation)sysObject).getFranchiseNumber(), 
                            ((Reservation)sysObject).getCustomerID());
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
     }//end DBreservationRouter method */  
    
    
    
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
                        try{   //Cast object and attempt update
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
                
        case "LOGOUT":  try{
                                dbase.logOff();                                
                            }  
                            catch (Exception e){
                                return e.getMessage();
                            }
            
        case "ADD":     //determines object type and calls corresponding 
                        //insert methods   
                        /*try{
                            if (sysObject instanceof Franchise)
                                dbReturnCode = 
                                queryDB.insertFranchise((Franchise)sysObject);
                            if (sysObject instanceof Vehicle)
                                dbReturnCode =
                                queryDB.insertVehicle((Vehicle)sysObject); 
                            if (sysObject instanceof Employee)
                                dbReturnCode =
                                queryDB.insertEmployee((Employee)sysObject);
                            if (sysObject instanceof Reservation)
                                dbReturnCode =
                                queryDB.insertReservation((Reservation)sysObject);
                            if (sysObject instanceof Customer)
                                dbReturnCode =
                                queryDB.insertCustomer((Customer)sysObject);                               
                        }
                        catch(UnauthorizedUserException e){
                            return e.getMessage();
                        }  
                        catch(BadConnectionException e){
                            return e.getMessage();
                        }*/
                
                
            case "DELETE":  //determines object type and calls corresponding 
                            //insert methods   
                        /*try
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
                            if (sysObject instanceof Reservation){
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
                            }                               
                        }
                        catch(UnauthorizedUserException e){
                            return e.getMessage();
                        }  
                        catch(BadConnectionException e){
                            return e.getMessage();
                        }*/
                
                
            case "EDIT":
                          
            default:        
                            //default test String
                            return "-2";             
       
       }//end DB Switch
    }//end DBRouter method
    
   
    public static <T> ArrayList convertToGeneric (ArrayList <T> list){
    
        //local container
        ArrayList <Object> temp = new ArrayList();
        
        Iterator <T> iterator = list.iterator();
        while (iterator.hasNext()){            
            temp.add(iterator);
        }
        return temp;
    }//end dbSessionRouter   
    
    /*public static ArrayList <Class <T>> convertObjectInArrayList{
    
    
    }*/
    
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
