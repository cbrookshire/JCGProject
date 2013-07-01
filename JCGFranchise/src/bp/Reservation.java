/* JCG Franchise Management System
 * CIST2391 Summer Session 2013 GTC
 * Author: #Maurice
 * Name: Reservation
 * Description: Describes a reservation 
 */
package bp;

public class Reservation {
    
    
    private Customer customer;
    
    //attributes - DB        
    private final String name = "JCG Franchise #";
    private String reservationNumber;
    private String franchiseNumber;
    private String vehicleID;
    private String customerID;
    private String price;
    private String status;
    private String comment;
    private String flightNumber;
    private String airline;
    private String flightTime;
    private String pickUpTime;
    private String dropOffTime;
    private String date;
    private String altAddress;
    private String altCity;
    private String altState;
    private String altZip;

    //CONSTRUCTORS
    //default
    public Reservation(){
    
        reservationNumber = "";
        franchiseNumber = "";
        vehicleID = "";
        customerID = "";
        price = "";
        status = "";
        comment = "";
        flightNumber = "";
        airline = "";
        flightTime = "";
        pickUpTime = "";
        dropOffTime = "";
        date = "";
        altAddress = "";
        altCity = "";
        altState = "";
        altZip = "";     
    }
    
    //17 args 
    public Reservation(String reservationNumber, String franchiseNumber, 
            String vehicleID, String customerID, String price, 
            String status, String comment, String flightNumber, 
            String airline, String flightTime, String pickUpTime, 
            String dropOffTime, String date, String altAddress, 
            String altCity, String altState, String altZip) {
        
        setReservationNumber(reservationNumber);
        setFranchiseNumber(franchiseNumber);
        setVehicleID(vehicleID);
        setCustomerID(customerID);
        setPrice(price);
        setStatus(status);
        setComment(comment);
        setFlightNumber(flightNumber);
        setAirline(airline);
        setFlightTime(flightTime);
        setPickUpTime(pickUpTime);
        setDropOffTime(dropOffTime);
        setDate(date);
        setAltAddress(altAddress);
        setAltCity(altCity);
        setAltState(altState);
        setAltZip(altZip);       
    }
        
    //SETS AND GETS
    //attrib 1
    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }
    
    //attrib 2
    public String getFranchiseNumber() {
        return franchiseNumber;
    }

    public void setFranchiseNumber(String franchiseNumber) {
        this.franchiseNumber = franchiseNumber;
    }
    //attrib 3
    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }
    //attrib 4
    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }
    //attrib 5
    public String getPrice() {
        return price;
    }
   
    public void setPrice(String price) {
        this.price = price;
    }
    //attrib 6
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    //attrib 7
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    //attrib 8
    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
    //attrib 9
    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }
    //attrib 10
    public String getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(String flightTime) {
        this.flightTime = flightTime;
    }
    //attrib 11
    public String getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(String pickUpTime) {
        this.pickUpTime = pickUpTime;
    }
    //attrib 12
    public String getDropOffTime() {
        return dropOffTime;
    }

    public void setDropOffTime(String dropOffTime) {
        this.dropOffTime = dropOffTime;
    }
    //attrib 13
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    //attrib 14
    public String getAltAddress() {
        return altAddress;
    }

    public void setAltAddress(String altAddress) {
        this.altAddress = altAddress;
    }
    //attrib 15
    public String getAltCity() {
        return altCity;
    }

    public void setAltCity(String altCity) {
        this.altCity = altCity;
    }
    //attrib 16
    public String getAltState() {
        return altState;
    }

    public void setAltState(String altState) {
        this.altState = altState;
    }
    //attrib 17
    public String getAltZip() {
        return altZip;
    }

    public void setAltZip(String altZip) {
        this.altZip = altZip;
    }
    
    //UTILITIES
    //Class Print Method
    @Override
    public String toString(){
    
        return String.format("%s%s  %s%s\n%s%s  %s  %s/n%s%s  %s%s  %s%s  %s%s\n"
                + "%s%s  %s%s\n%s%s, %s\n%s%s\n%s, %s %s\n%s%s\n%s, %s %s\n%s\n",
        name, getFranchiseNumber(), "Date: ", getDate(), 
        "Reservation ID: ", getReservationNumber(), "Customer ID: ", 
        getCustomerID(), "Vehicle ID: ", getVehicleID(), "Airline: ", 
        getAirline(), "Flight Time: ", getFlightTime(), "Pickup Time: ", 
        getPickUpTime(), "Drop Off Time: ", getDropOffTime(), "Price: ",
        getPrice(), "Status: ", getStatus(), "Customer Name", 
        customer.getFirstName(), customer.getLastName(), 
        "Address: ", customer.getAddress(), customer.getCity(),
        customer.getState(), customer.getZip(), "Alt Address: ", 
        getAltAddress(), getAltCity(), getAltState(), getAltZip(),
        getComment());    
    }   
}//end Reservation class
