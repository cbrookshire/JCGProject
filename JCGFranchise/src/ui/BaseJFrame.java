/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import ui.*;

/**
 *
 * @author Davidi
 */
public class BaseJFrame extends JFrame {
    private JPanel content = null;
    private static BaseJFrame instance;
    
    public BaseJFrame()
    {
        //Constructor.  I use the flow layout because there's 
        //only one thing added to the frame anyway.
        FlowLayout layout = new FlowLayout();
        this.setLayout(layout);
        this.setSize(1000, 800);
        setScreen("000");
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void setContent(JPanel c, String title)
    {
        //Change the content of the JFrame to c, and set the top title bar to title
        if(content != null)
        {
            content.setVisible(false);
            this.remove(content);
        }
        
        content = null;
        content = c;
        
        this.add(content);
        content.setVisible(true);
        
        this.setTitle(title);
    }
    
    public static BaseJFrame getInstance()
    {
        //There should only be one BaseJFrame.  This ensures singleton method is used.
        if(instance == null)
            instance = new BaseJFrame();
        
        return instance;
    }
    
    public void setScreen(String code)
    {
        FranchisorJPanel pane1 = new FranchisorJPanel();
        FranchiseeJPanel pane2 = new FranchiseeJPanel();
        switch(code)
        {
            case "000":  //Main page
                setContent(new OpeningJPanel(), "Main Page");
                break;
            case "001": //Login page
                setContent(new LoginJPanel(), "Login screen");  
                break;
            case "002":  //Create account page
                //Create account
                break;
            case "003":  //Logout page
                //Logout page
                break;
            
            
            //Franchisor
            case "100":  //Franchisor main page
                setContent(pane1, "Franchisor Main");
                pane1.setContent(new FranchisorOverviewPanel());
                break;
            case "101":    //Overview page
                setContent(pane1, "Franchisor Main");
                pane1.setContent(new FranchisorOverviewPanel());
                break;
            case "102": //Create franchisee page
                setContent(pane1, "Franchisor Main");
                pane1.setContent(new CreateFranchiseJPanel(0));
                break;
            case "103": //Edit franchisee page
                setContent(pane1, "Franchisor Main");
                pane1.setContent(new CreateFranchiseJPanel(1));
                break;
            case "104": //Delete franchisee page
                setContent(pane1, "Franchisor Main");
                pane1.setContent(new CreateFranchiseJPanel(2));
                break;
          
           //Franchisee
           case "200": //Franchisee Main page
                setContent(pane2, "Franchisee Main");
                pane2.setContent(new CreateFranchiseJPanel(0));
                break;
           case "201": //Reservation overview
                setContent(pane2, "Franchisee Main");
                pane2.setContent(new ReservationsOverviewPanel());
                break;
           case "202": //Drivers overview
                setContent(pane2, "Franchisee Main");
                pane2.setContent(new DriversOverviewPanel());
                break;
           case "203": //Vehicles overview
                setContent(pane2, "Franchisee Main");
                pane2.setContent(new VehiclesOverviewPanel());
                break;
               
           case "210": //Reservation Main
                setContent(pane2, "Franchisee Main");
                pane2.setContent(new ReservationsOverviewPanel());
                break;
           case "211": //Reservation add
                setContent(pane2, "Franchisee Main");
                pane2.setContent(new CreateReservationsJPanel(0));
                break;
           case "212": //Reservation edit
                setContent(pane2, "Franchisee Main");
                pane2.setContent(new CreateReservationsJPanel(1));
                break;
           case "213": //Reservation delete
                setContent(pane2, "Franchisee Main");
                pane2.setContent(new CreateReservationsJPanel(2));
                break;
           case "220": //Driver Main
                setContent(pane2, "Franchisee Main");
                pane2.setContent(new DriversOverviewPanel());
                break;
           case "221": //Driver add
                setContent(pane2, "Franchisee Main");
                pane2.setContent(new CreateEmployeeJPanel(0, true));
                break;
           case "222": //Driver edit
                setContent(pane2, "Franchisee Main");
                pane2.setContent(new CreateEmployeeJPanel(1, true));
                break;
           case "223": //Driver delete
                setContent(pane2, "Franchisee Main");
                pane2.setContent(new CreateEmployeeJPanel(2, true));
                break;
           case "230": //Vehicles Main
                setContent(pane2, "Franchisee Main");
                pane2.setContent(new VehiclesOverviewPanel());
                break;
           case "231": //Vehicles add
                setContent(pane2, "Franchisee Main");
                //pane2.setContent(new CreateVehiclesJPanel(0));
                break;
           case "232": //Vehicles edit
                setContent(pane2, "Franchisee Main");
                //pane2.setContent(new CreateVehiclesJPanel(1));
                break;
           case "233": //Vehicles delete
                setContent(pane2, "Franchisee Main");
                //pane2.setContent(new CreateVehiclesJPanel(2));
                break;
           
           case "240":  //Other main
               setContent(pane2, "Franchisee Main");
               //pane2.setContent(new MemberRewardsJPanel());
               break;
           case "241":  //Member rewards
               setContent(pane2, "Franchisee Main");
               //pane2.setContent(new MemberRewardsJPanel());
               break;
           case "242":  //Vehicle Loans
               setContent(pane2, "Franchisee Main");
               //pane2.setContent(new VehicleLoansJPanel());
               break;
               
           
           //Error prompts
           case "900": //Unable to login
               setContent(new errorJPanel("Unable to login.", "001"), "Error!");  //call the error and return to login
               break;
           case "901": //Unable to register
               setContent(new errorJPanel("Unable to create account.", "001"), "Error!");  //call the error code and return to registration
               break;
        }
    }
}
