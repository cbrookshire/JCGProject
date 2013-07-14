/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import bp.*;

/**
 *
 * @author Davidi
 */
public class CreateReservationsJPanel extends javax.swing.JPanel {

    private int mode;
    private ArrayList<Reservation> list;
    
    /**
     * Creates new form CreateReservationsJPanel
     */
    public CreateReservationsJPanel(int m) {
        initComponents();
        list = new ArrayList<Reservation>();
        
        mode = m;
        if(mode == 0)  //Create mode
        {
            listSelection.setEnabled(false);
        }
        
        if(mode == 1)  //Edit mode
        {
            getListSelection();
            jLabel1.setText("Edit an Reservation");
            listSelection.setEnabled(true);
            btnSubmit.setText("Update");
            btnClear.setEnabled(false);
        }
        
        if(mode == 2)  //Delete mode
        {
            getListSelection();
            jLabel1.setText("Delete an Reservation");
            listSelection.setEnabled(true);
            btnSubmit.setText("Delete");
            btnClear.setEnabled(false);
            
            //We're in delete mode, so here we disable all the text boxes and combo boxes.
            //Don't worry, we can still use setText-- this only disables the user from editing it.
            //In other words, the text boxes are only for display purposes.
            btnClear.setEnabled(false);
            franchiseNumList.setEnabled(false);
            vehicleIDList.setEnabled(false);
            listCustomerID.setEnabled(false);
            txtPrice.setEnabled(false);
            txtStatus.setEnabled(false);
            txtComment.setEnabled(false);
            txtFlightNum.setEnabled(false);
            txtAirline.setEnabled(false);
            txtDate.setEnabled(false);
            txtFlightTime.setEnabled(false);
            txtPickupTime.setEnabled(false);
            txtDropoffTime.setEnabled(false);
            
        }
        
    }
    
    private boolean checkFields()
    {
        boolean success = true;
        
        if(txtFlightNum.isEnabled())
        {
            if(!txtFlightNum.getText().matches("[0-9]+"))
            {
                success = false;
                JOptionPane.showMessageDialog(BaseJFrame.getInstance(), 
                    "Flight number is invalid.  Numbers only",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(txtAirline.isEnabled())
        {
            if(txtAirline.getText().isEmpty())
            {
                success = false;
                JOptionPane.showMessageDialog(BaseJFrame.getInstance(), 
                    "Airline name is invalid.  Type something in there...",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(txtDate.isEnabled())
        {
            if(!txtDate.getText().matches("[0-9]{1,2}/[0-9]{1,2}/[0-9]{1,2}"))
            {
                success = false;
                JOptionPane.showMessageDialog(BaseJFrame.getInstance(), 
                    "Invalid date.  Example (1/1/10)",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(txtFlightTime.isEnabled())
        {
            if(!txtFlightTime.getText().matches("^((([0]?[1-9]|1[0-2])(:|\\.)[0-5][0-9]((:|\\.)[0-5][0-9])?( )?(AM|am|aM|Am|PM|pm|pM|Pm))|(([0]?[0-9]|1[0-9]|2[0-3])(:|\\.)[0-5][0-9]((:|\\.)[0-5][0-9])?))$"))
            {
                success = false;
                JOptionPane.showMessageDialog(BaseJFrame.getInstance(), 
                    "Invalid flight time.  Example (1:15 AM)",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(txtPickupTime.isEnabled())
        {
            if(!txtPickupTime.getText().matches("^((([0]?[1-9]|1[0-2])(:|\\.)[0-5][0-9]((:|\\.)[0-5][0-9])?( )?(AM|am|aM|Am|PM|pm|pM|Pm))|(([0]?[0-9]|1[0-9]|2[0-3])(:|\\.)[0-5][0-9]((:|\\.)[0-5][0-9])?))$"))
            {
                success = false;
                JOptionPane.showMessageDialog(BaseJFrame.getInstance(), 
                    "Invalid pickup time.  Example (1:15 AM)",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(txtDropoffTime.isEnabled())
        {
            if(!txtDropoffTime.getText().matches("^((([0]?[1-9]|1[0-2])(:|\\.)[0-5][0-9]((:|\\.)[0-5][0-9])?( )?(AM|am|aM|Am|PM|pm|pM|Pm))|(([0]?[0-9]|1[0-9]|2[0-3])(:|\\.)[0-5][0-9]((:|\\.)[0-5][0-9])?))$"))
            {
                success = false;
                JOptionPane.showMessageDialog(BaseJFrame.getInstance(), 
                    "Invalid pickup time.  Example (1:15 AM)",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
        return success;
    }
    
    private void getListSelection()
    {
        //Gets a list of all the Employees (should display name)
        //list = LOLGETLIST();
        list = UIController.getInstance().UIreservationRouter(new String("RESERVATION"), "VIEWALL");
        for(int i = 0; i < list.size(); i++)
        {
            String t = list.get(i).toString();
            listSelection.addItem(t);
        }
        
        ArrayList<Franchise> list2 = new ArrayList<Franchise>();
        list2 = UIController.getInstance().UIfranchisorRouter(new String("FRANCHISE"), "VIEWALL");
        
        for(int i = 0; i < list2.size(); i++)
        {
            String t = list2.get(i).toString();
            franchiseNumList.addItem(t);
        }
        
        ArrayList<Vehicle> list3 = new ArrayList<Vehicle>();
        list3 = UIController.getInstance().UIvehicleRouter(new String("VEHICLE"), "VIEWALL");
        
        for(int i = 0; i < list3.size(); i++)
        {
            String t = list3.get(i).toString();
            vehicleIDList.addItem(t);
        }
        
        ArrayList<Customer> list4 = new ArrayList<Customer>();
        list4 = UIController.getInstance().UIcustomerRouter(new String("CUSTOMER"), "VIEWALL");
        
        for(int i = 0; i < list4.size(); i++)
        {
            String t = list4.get(i).toString();
            listCustomerID.addItem(t);
        }
    }
    
    private void loadInfoFromList()
    {
        //Loads the information after an item from the combo box has been selected.
        //The data is put into the text fields
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        listSelection = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        franchiseNumList = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        vehicleIDList = new javax.swing.JComboBox();
        listCustomerID = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtStatus = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtComment = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        txtFlightNum = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtAirline = new javax.swing.JTextField();
        txtDate = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtPickupTime = new javax.swing.JFormattedTextField();
        jLabel14 = new javax.swing.JLabel();
        txtFlightTime = new javax.swing.JFormattedTextField();
        jLabel15 = new javax.swing.JLabel();
        txtDropoffTime = new javax.swing.JFormattedTextField();
        btnClear = new javax.swing.JButton();
        btnSubmit = new javax.swing.JButton();
        txtPrice = new javax.swing.JFormattedTextField();

        setPreferredSize(new java.awt.Dimension(545, 500));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Add a Reservation");

        jLabel3.setText("List");

        jLabel4.setText("Franchise No:");

        jLabel5.setText("Vehicle ID:");

        jLabel6.setText("Customer ID:");

        jLabel7.setText("Price");

        jLabel9.setText("Status");

        txtStatus.setText("Pending");

        jLabel10.setText("Comment");

        txtComment.setColumns(20);
        txtComment.setRows(5);
        jScrollPane1.setViewportView(txtComment);

        jLabel2.setText("Flight Num:");

        txtFlightNum.setText("0");

        jLabel11.setText("Airline:");

        txtDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));
        txtDate.setText("1/1/10");

        jLabel12.setText("Date:");

        jLabel13.setText("Flight Time:");

        txtPickupTime.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT))));

        jLabel14.setText("Pickup time");

        txtFlightTime.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT))));
        txtFlightTime.setText("12:00 AM");

        jLabel15.setText("Dropoff Time");

        txtDropoffTime.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT))));

        btnClear.setText("Clear form");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnSubmit.setText("Submit");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        txtPrice.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        txtPrice.setText("$0.00");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnClear)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(29, 29, 29)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(listSelection, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(btnSubmit)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(listCustomerID, javax.swing.GroupLayout.Alignment.LEADING, 0, 95, Short.MAX_VALUE)
                                                    .addComponent(vehicleIDList, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(franchiseNumList, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                            .addGap(93, 93, 93)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel2)
                                                .addComponent(jLabel11)
                                                .addComponent(jLabel12)
                                                .addComponent(jLabel13)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel15)
                                                    .addComponent(jLabel14)))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtDate)
                                                .addComponent(txtAirline)
                                                .addComponent(txtFlightNum)
                                                .addComponent(txtPickupTime)
                                                .addComponent(txtFlightTime, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                                                .addComponent(txtDropoffTime, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(listSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(franchiseNumList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)
                                .addComponent(txtFlightNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(vehicleIDList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(txtAirline, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(listCustomerID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel13)
                            .addComponent(txtFlightTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPickupTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15))
                    .addComponent(txtDropoffTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClear)
                    .addComponent(btnSubmit))
                .addGap(53, 53, 53))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        //Button- clear form
        if(franchiseNumList.getItemCount() > 0)
            franchiseNumList.setSelectedIndex(0);
        if(vehicleIDList.getItemCount() > 0)
            vehicleIDList.setSelectedIndex(0);
        if(listCustomerID.getItemCount() > 0)
            listCustomerID.setSelectedIndex(0);
        txtPrice.setText("$0.00");
        txtStatus.setText("Pending");
        txtComment.setText("");
        txtFlightNum.setText("0");
        txtAirline.setText("");
        txtDate.setText("1/1/10");
        txtFlightTime.setText("12:00 AM");
        txtPickupTime.setText("12:00 AM");
        txtDropoffTime.setText("12:00 AM");
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        if(!checkFields())
            return;
        
        Reservation res = new Reservation();
        res.setReservationNumber(franchiseNumList.getSelectedIndex());
        res.setVehicleID(vehicleIDList.getSelectedIndex());
        res.setCustomerID(listCustomerID.getSelectedIndex());
        res.setPrice(txtPrice.getText());
        res.setStatus(txtStatus.getText());
        res.setComment(txtComment.getText());
        res.setFlightNumber(txtFlightNum.getText());
        res.setAirline(txtAirline.getText());
        res.setDate(txtDate.getText());
        res.setFlightTime(txtFlightTime.getText());
        res.setPickUpTime(txtPickupTime.getText());
        res.setDropOffTime(txtDropoffTime.getText());
        
        if(mode == 0)  //Create one
        {
            //send new Franchise to DB
            UIController.getInstance().UIRouter(res, "ADD");
        }
        
        if(mode == 1)  //Update one
        {
            //Send list.get(listSelection.getSelectedIndex()) to DB for update
            UIController.getInstance().UIRouter(res, "EDIT");
        }
        
        if(mode == 2)  //Delete one
        {
            //Send list.get(listSelection.getSelectedIndex()) to DB for update
            UIController.getInstance().UIRouter(res, "DELETE");
        }
    }//GEN-LAST:event_btnSubmitActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JComboBox franchiseNumList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox listCustomerID;
    private javax.swing.JComboBox listSelection;
    private javax.swing.JTextField txtAirline;
    private javax.swing.JTextArea txtComment;
    private javax.swing.JFormattedTextField txtDate;
    private javax.swing.JFormattedTextField txtDropoffTime;
    private javax.swing.JTextField txtFlightNum;
    private javax.swing.JFormattedTextField txtFlightTime;
    private javax.swing.JFormattedTextField txtPickupTime;
    private javax.swing.JFormattedTextField txtPrice;
    private javax.swing.JTextField txtStatus;
    private javax.swing.JComboBox vehicleIDList;
    // End of variables declaration//GEN-END:variables
}
