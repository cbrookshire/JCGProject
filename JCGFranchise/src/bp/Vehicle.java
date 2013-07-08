/* JCG Franchise Management System
 * CIST2391 Summer Session 2013 GTC
 * Author: #Maurice
 * Name: Vehicle
 * Description: Describes a Vehicle 
 */
package bp;

public class Vehicle {
    
    //attributes - DB 
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
    private String vIndex;
    
    //CONTRUCTORS
    //default
    public Vehicle(){
        vIndex = "";    
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

    //11 args 
    public Vehicle(String franchiseNumber, String vehicleID,
        String vin, String make, String model, String year, 
        String mileage, String capacity, String condition, 
        String tablet, String rate, String vIndex){
        
        setFranchiseNumber(franchiseNumber);
        setVehicleID(vehicleID);
        setVin(vin);
        setMake(make);
        setModel(model);
        setYear(year);
        setMileage(mileage);
        setCapacity(capacity);
        setCondition(condition);
        setTablet(tablet);
        setRate(rate);  
        setvIndex(vIndex);
    }
    
    //SETS AND GETS
    //attrib 1
    public void setFranchiseNumber(String franchiseNumber){
       this.franchiseNumber = franchiseNumber;
    }
    
    public String getFranchiseNumber(){
        return franchiseNumber;
    }
    
    //attrib 2
    public void setVehicleID(String vehicleID){
       this.vehicleID = vehicleID;
    }
    
    //overloaded set for DB - converts int to string and sets
    public void setVehicleID(int vehicleID){
       this.vehicleID = String.valueOf(vehicleID);
    }
    
    public int getVehicleID(){
        return Integer.parseInt(vehicleID);
    }
    
    //attrib 3
    public void setVin(String vin){
       this.vin = vin;
    }
    
    public String getVin(){
        return vin;
    }
    
    //attrib 4
    public void setMake(String make){
       this.make = make;
    }
    
    public String getMake(){
        return make;
    }
    
    //attrib 5
    public void setModel(String model){
       this.model = model;
    }
    
    public String getModel(){
        return model;
    }
    
    //attrib 6
    public void setYear(String year){
       this.year = year;
    }
    
    public void setYear(int year){
       this.year = String.valueOf(year);
    }
    
    public String getYear(){
        return year;
    }
    
    //attrib 7
    public void setMileage(String mileage) {
        this.mileage = mileage;
    }
    
    //overloaded set for DB - converts int to string and sets
    public void setMileage(int mileage) {
        this.mileage = String.valueOf(mileage);
    }
    
    public int getMileage() {
        return Integer.parseInt(mileage);
    }
    
    //attrib 8
    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }
    
    //overloaded set for DB - converts int to string and sets
    public void setCapacity(int capacity) {
        this.capacity = String.valueOf(capacity);
    }
    
    public int getCapacity() {
        return Integer.parseInt(capacity);
    }

    //attrib 9
    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
    
    //attrib 10
    public void setTablet(String tablet) {
        this.tablet = tablet;
    }
    
    //overloaded set for DB - converts int to string and sets
    public void setTablet(int tablet) {
        this.tablet = String.valueOf(tablet);
    }
    public String getTablet() {
        return tablet;
    }

    //attrib 11
    public void setRate(String rate) {
        this.rate = rate;
    }
    
    //overloaded set for DB - converts int to string and sets
    public void setRate(double rate) {
        this.rate = String.valueOf(rate);
    }
    
    public double getRate() {
        return Double.parseDouble(rate);
    }
    
    //attrib 12
    public void setvIndex(String vIndex) {
        this.vIndex = vIndex;
    }
    
    //overloaded set for DB - converts int to string and sets
    public void setvIndex(int vIndex) {
        this.vIndex = String.valueOf(vIndex);
    }
    
     public int getvIndex() {
        return Integer.parseInt(vIndex);
    }
    
    
    //UTILITIES
    @Override
    public String toString(){
    
        return String.format
        ("%s%s\n%s%s  %s%s  %s%s/n%s%s  %s%s,  %s%s\n%s%s %s%s %s%s\n",
        "VIN Number: ", getVin(), 
        "Make: ", getMake(), "Model: ", getModel(), "Year: ", getYear(),
        "Mileage: ", getMileage(), "Capacity: ", getCapacity(), 
        "Condition: ", getCondition(), "Tablet", getTablet(), "Rate: ",
        getRate());
    }  
}//end class Vehicle
