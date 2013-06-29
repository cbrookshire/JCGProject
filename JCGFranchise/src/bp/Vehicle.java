/* JCG Franchise Management System
 * CIST2391 Summer Session 2013 GTC
 * @author Maurice
 * Name: Vehicle
 * Description: Describes a Vehicle 
 */
package bp;

public class Vehicle {
    
    //vehicle attributes - DB 
    private String franchiseNumber;
    private String vehicleID; 
    private String vin;
    private String make;
    private String model;
    private String year;
    private String mileage;
    private String capacity;
    private String condition;
    private String tablet;
    private String rate;
    
    
    //CONTRUCTORS
    //default
    public Vehicle(){
    
        franchiseNumber = "";
        vehicleID = "";
        vin = "";
        make = "";
        model = "";
        year = "";
        mileage = "";
        capacity = "";
        condition = "";
        tablet = "";
        rate = "";        
    }
    
    //12 arg constructor
    public Vehicle(String franchiseNumber, String VehicleID,
        String vin, String make, String model, String year, 
        String mileage, String capacity, String condition, 
        String tablet, String rate){
    
    }
    
}
