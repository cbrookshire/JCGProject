/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import bp.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Davidi
 */
public class CreateVehicleJPanel extends javax.swing.JPanel {

    /**
     * Creates new form CreateVehicleJPanel
     */
    
    private int mode;
    private ArrayList<Vehicle> list;
    
    public CreateVehicleJPanel(int m) {
        list = new ArrayList<Vehicle>();
        initComponents();
        
        mode = m;
        if(mode == 0)  //Create mode
        {
            listSelection.setEnabled(false);
        }
        
        if(mode == 1)  //Edit mode
        {
            getListSelection();
            jLabel1.setText("Edit a Vehicle");
            listSelection.setEnabled(true);
            btnSubmit.setText("Update");
            btnClear.setEnabled(false);
        }
        
        if(mode == 2)  //Delete mode
        {
            getListSelection();
            jLabel1.setText("Delete a Vehicle");
            listSelection.setEnabled(true);
            btnSubmit.setText("Delete");
            btnClear.setEnabled(false);
            
            //We're in delete mode, so here we disable all the text boxes and combo boxes.
            //Don't worry, we can still use setText-- this only disables the user from editing it.
            //In other words, the text boxes are only for display purposes.
            btnClear.setEnabled(false);
            txtVIN.setEnabled(false);
            txtMake.setEnabled(false);
            txtModel.setEnabled(false);
            txtYear.setEnabled(false);
            txtMileage.setEnabled(false);
            txtCapacity.setEnabled(false);
            comboCondition.setEnabled(false);
            txtRentalPrice.setEnabled(false);
            
        }
    }
    
    private boolean checkFields()
    {
        boolean success = true;
        
        if(txtVIN.isEnabled())
        {
            if(txtVIN.getText().isEmpty())
            {
                success = false;
                JOptionPane.showMessageDialog(BaseJFrame.getInstance(), 
                    "VIN is invalid.  Type something in there",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(txtMake.isEnabled())
        {
            if(txtMake.getText().isEmpty())
            {
                success = false;
                JOptionPane.showMessageDialog(BaseJFrame.getInstance(), 
                    "Make is invalid.  Type something in there",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(txtModel.isEnabled())
        {
            if(txtModel.getText().isEmpty())
            {
                success = false;
                JOptionPane.showMessageDialog(BaseJFrame.getInstance(), 
                    "Model is invalid.  Type something in there",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(txtYear.isEnabled())
        {
            if(txtYear.getText().isEmpty())
            {
                success = false;
                JOptionPane.showMessageDialog(BaseJFrame.getInstance(), 
                    "Year is invalid.  Type something in there",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            }
            
            if(Integer.parseInt(txtYear.getText()) < 1800)
            {
                success = false;
                JOptionPane.showMessageDialog(BaseJFrame.getInstance(), 
                    "Year is invalid.  There were no cars before the 1800s!",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(txtMileage.isEnabled())
        {
            if(txtMileage.getText().isEmpty())
            {
                success = false;
                JOptionPane.showMessageDialog(BaseJFrame.getInstance(), 
                    "Mileage is invalid.  Type something in there",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            }
            
            if(Integer.parseInt(txtMileage.getText()) <= 0)
            {
                success = false;
                JOptionPane.showMessageDialog(BaseJFrame.getInstance(), 
                    "Mileage is invalid.  Must be greater than 0.",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(txtCapacity.isEnabled())
        {
            if(txtCapacity.getText().isEmpty())
            {
                success = false;
                JOptionPane.showMessageDialog(BaseJFrame.getInstance(), 
                    "Capacity is invalid.  Type something in there",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            }
            
            if(Integer.parseInt(txtCapacity.getText()) <= 0)
            {
                success = false;
                JOptionPane.showMessageDialog(BaseJFrame.getInstance(), 
                    "Capacity is invalid.  Must be greater than 0.",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
        
        if(txtRentalPrice.isEnabled())
        {
            if(txtRentalPrice.getText().isEmpty())
            {
                success = false;
                JOptionPane.showMessageDialog(BaseJFrame.getInstance(), 
                    "Rental is invalid.  Type something in there",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            }
            
            if(Double.parseDouble(txtRentalPrice.getText().substring(1)) <= 0.00)
            {
                success = false;
                JOptionPane.showMessageDialog(BaseJFrame.getInstance(), 
                    "Rental is invalid.  Must be greater than 0.",
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
        list = UIController.getInstance().UIvehicleRouter(new String("MANAGER"), "VIEWALL");
        for(int i = 0; i < list.size(); i++)
        {
            String t = list.get(i).toString();
            listSelection.addItem(t);
        }
        
    }
    
    private void loadInfoFromList()
    {
        Vehicle v = list.get(listSelection.getSelectedIndex());
        txtVIN.setText(v.getVin());
        txtMake.setText(v.getMake());
        txtModel.setText(v.getModel());
        txtYear.setText(v.getYear());
        txtMileage.setText(String.valueOf(v.getMileage()));
        txtCapacity.setText(String.valueOf(v.getCapacity()));
        comboCondition.setSelectedIndex(0);
        if(v.getCondition() == "Good")
            comboCondition.setSelectedIndex(1);
        if(v.getCondition() == "Poor")
            comboCondition.setSelectedIndex(2);
        txtRentalPrice.setText(String.valueOf(v.getRate()));
        
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
        jLabel2 = new javax.swing.JLabel();
        listSelection = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        txtVIN = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMake = new javax.swing.JTextField();
        txtModel = new javax.swing.JTextField();
        txtYear = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        txtMileage = new javax.swing.JFormattedTextField();
        txtCapacity = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtRentalPrice = new javax.swing.JFormattedTextField();
        btnClear = new javax.swing.JButton();
        btnSubmit = new javax.swing.JButton();
        comboCondition = new javax.swing.JComboBox();

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Create a new Vehicle");

        jLabel2.setText("Selection:");

        listSelection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listSelectionActionPerformed(evt);
            }
        });

        jLabel3.setText("VIN Number");

        jLabel4.setText("Make:");

        jLabel5.setText("Model:");

        jLabel6.setText("Year:");

        txtYear.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0000"))));
        txtYear.setText("1999");

        jLabel7.setText("Mileage");

        txtMileage.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtMileage.setText("0");

        txtCapacity.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtCapacity.setText("0");

        jLabel8.setText("Capacity:");

        jLabel9.setText("Condition:");

        jLabel10.setText("Rental Price:");

        txtRentalPrice.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        txtRentalPrice.setText("$0.00");

        btnClear.setText("Clear");
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

        comboCondition.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Excellent", "Good", "Poor" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(122, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(113, 113, 113))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(18, 18, 18)
                                    .addComponent(listSelection, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtVIN))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtMake)
                                        .addComponent(txtModel)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtMileage, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtCapacity, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(0, 0, Short.MAX_VALUE))))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(comboCondition, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtRentalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnClear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSubmit)
                        .addGap(159, 159, 159))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(listSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtVIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtMake, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtMileage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCapacity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(comboCondition, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtRentalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClear)
                    .addComponent(btnSubmit))
                .addContainerGap(62, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        //Clear button
        txtVIN.setText("0");
        txtMake.setText("");
        txtModel.setText("");
        txtYear.setText("1999"); //first car in the 1800s
        txtMileage.setText("0");
        txtCapacity.setText("0");
        comboCondition.setSelectedIndex(0);
        txtRentalPrice.setText("$0.00");
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        // I WILL NOT SUBMIT TO YOUR SLAVERY!
        // Er, submit button
        
        if(!checkFields())
            return;
        
        Vehicle v = new Vehicle();
        v.setVin(txtVIN.getText());
        v.setMake(txtMake.getText());
        v.setModel(txtModel.getText());
        v.setYear(txtYear.getText());
        v.setMileage(txtMileage.getText());
        v.setCapacity(txtCapacity.getText());
        if(comboCondition.getSelectedIndex() == 0)
            v.setCondition("Excellent");
        if(comboCondition.getSelectedIndex() == 1)
            v.setCondition("Good");
        if(comboCondition.getSelectedIndex() == 2)
            v.setCondition("Poor");
        v.setRate(txtRentalPrice.getText().substring(1));  //Ignore the first character ($)
        v.setFranchiseNumber(JCGlIO.getInstance().getfN());
        
        String code = "200";
        if(mode == 0)  //Create one
        {
            //send new Franchise to DB
            v.setVehicleID("00");  //Temporary.  CHANGE THIS LATER
            code = UIController.getInstance().UIRouter(v, "ADD");
        }
        
        if(mode == 1)  //Update one
        {
            //Send list.get(listSelection.getSelectedIndex()) to DB for update
            v.setVehicleID(list.get(listSelection.getSelectedIndex()).getVehicleID());
            code = UIController.getInstance().UIRouter(v, "EDIT");
        }
        
        if(mode == 2)  //Delete one
        {
            //Send list.get(listSelection.getSelectedIndex()) to DB for update
            code = UIController.getInstance().UIRouter(v, "DELETE");
        }
        BaseJFrame.getInstance().setScreen(code);
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void listSelectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listSelectionActionPerformed
        // Change edit
        loadInfoFromList();
    }//GEN-LAST:event_listSelectionActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JComboBox comboCondition;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JComboBox listSelection;
    private javax.swing.JFormattedTextField txtCapacity;
    private javax.swing.JTextField txtMake;
    private javax.swing.JFormattedTextField txtMileage;
    private javax.swing.JTextField txtModel;
    private javax.swing.JFormattedTextField txtRentalPrice;
    private javax.swing.JTextField txtVIN;
    private javax.swing.JFormattedTextField txtYear;
    // End of variables declaration//GEN-END:variables
}
