/*
 * 
 */
package db;

import JCGExceptions.BadConnectionException;
import JCGExceptions.UnauthorizedUserException;
import bp.Franchise;
import bp.Vehicle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author taylor
 */
public class Queries {
    
    private Connection con = null;
    private QStrings qs;
    
    public Queries(Connection con) {
        
        setConnection(con);
        
    }
    
    private void setConnection(Connection con) {
        this.con = con;
    }
      
/******************************************************************************
 *          All Queries to the Franchise table.                               * 
 *          Inserts, Updates authored by Taylor                               * 
 *          Selects, Deletes authored by Miles                                *
 ******************************************************************************/
    /*
    public void updateFranchise(Franchise fran) 
            throws UnauthorizedUserException, BadConnectionException {
        try
        {
            PreparedStatement pStmnt = con.prepareStatement(qs.update_fran);
            pStmnt.setString(1, fran.getAddress());
            pStmnt.setString(2, fran.getCity());
            pStmnt.setString(3, fran.getState());
            pStmnt.setInt(4, fran.getZip());
            pStmnt.setString(5, fran.getPhone());
            pStmnt.setString(6, fran.getEmail());
            pStmnt.setInt(7, fran.getAirport());
            pStmnt.setInt(8, fran.getFranchiseID());
            pStmnt.executeUpdate();             
        }
        catch(SQLException sqlE) {
            if(sqlE.getErrorCode() == 1142)
                throw(new UnauthorizedUserException("AccessDenied"));
            else 
                throw(new BadConnectionException("BadConnection"));
        }
    }
    
    public void insertFranchise(Franchise fran) 
            throws UnauthorizedUserException, BadConnectionException {
        try {
            PreparedStatement pStmnt = con.prepareStatement(qs.insert_fran);
            pStmnt.setString(1, fran.getAddress());
            pStmnt.setString(2, fran.getCity());
            pStmnt.setString(3, fran.getState());
            pStmnt.setInt(4, fran.getZip());
            pStmnt.setString(5, fran.getPhone());
            pStmnt.setString(6, fran.getEmail());
            pStmnt.setInt(7, fran.getAirport());
            pStmnt.executeUpdate();
            
        } catch(SQLException sqlE) {
            if(sqlE.getErrorCode() == 1142)
                throw(new UnauthorizedUserException("AccessDenied"));
            else 
                throw(new BadConnectionException("BadConnection"));
        }
    }
    */
/******************************************************************************
 *          All Queries for the Membership table                             *
 ******************************************************************************/
    //public void updateMemeberShip()
    
/******************************************************************************
 *          All Queries for the Vehicle table                                 *
 ******************************************************************************/
    /*
    public void insertVehicle(Vehicle car) 
            throws UnauthorizedUserException, BadConnectionException {
        try
        {
            PreparedStatement pStmnt = con.prepareStatement(qs.insert_vehicle);
            pStmnt.setInt(1, car.getVehicleID());
            pStmnt.setString(2, car.getVin());
            pStmnt.setString(3, car.getMake());
            pStmnt.setString(4, car.getModel());
            pStmnt.setString(5, car.getYear());
            pStmnt.setInt(6, car.getMileage());
            pStmnt.setInt(7, car.getCapacity());
            pStmnt.setString(8, car.getCondition());
            pStmnt.setString(9, car.getTablet());
            pStmnt.setDouble(10, car.getRate());
            pStmnt.setInt(11, car.getFranchiseNumber());
            pStmnt.executeUpdate();             
        }
        catch(SQLException sqlE) {
            if(sqlE.getErrorCode() == 1142)
                throw(new UnauthorizedUserException("AccessDenied"));
            else 
                throw(new BadConnectionException("BadConnection"));
        }
    }
    
    public void updateVehicle(Vehicle car) 
            throws UnauthorizedUserException, BadConnectionException {
        try {
            PreparedStatement pStmnt = con.prepareStatement(qs.update_vehicle);
            pStmnt.setInt(1, car.getMileage());
            pStmnt.setString(2, car.getCondition());
            pStmnt.setString(3, car.getTablet());
            pStmnt.setDouble(4, car.getRate());
            pStmnt.setInt(5, car.getFranchiseNumber());
            pStmnt.setInt(6, car.getVehicleID());
            pStmnt.executeUpdate();
            
        } catch(SQLException sqlE) {
            if(sqlE.getErrorCode() == 1142)
                throw(new UnauthorizedUserException("AccessDenied"));
            else 
                throw(new BadConnectionException("BadConnection"));
        }
    }
    */
/******************************************************************************
 *          All Queries for the Employee table                                *
 *          includes Create and Grant Queries                                 *
 ******************************************************************************/
    
    
/******************************************************************************
 *          All Queries for the Maintenance table                             *
 ******************************************************************************/
    /*
    public void insertMaintenance(Maintenance fixit) 
            throws UnauthorizedUserException, BadConnectionException {
    
        try {
           PreparedStatement pStmnt = 
                   con.prepareStatement(qs.insert_maintenance);
           pStmnt.setString(1, fixit.getServiceNumber());
           pStmnt.setInt(2, fixit.getVehicleID());
           pStmnt.setString(3, fixit.getServiceType());
           pStmnt.setDouble(4, fixit.getServiceCost());
           pStmnt.executeQuery();
        }
        catch(SQLException sqlE) {
            if(sqlE.getErrorCode() == 1142)
                throw(new UnauthorizedUserException("AccessDenied"));
            else 
                throw(new BadConnectionException("BadConnection"));
        }
    }
    */
    
}
