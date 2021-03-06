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
 * @author Corey
 */
public class CreateEmployeeJPanel extends javax.swing.JPanel {

    /**
     * Creates new form CreateFranchise
     */
    private int mode;
    private boolean isDriver;
    private ArrayList<Employee> list;
    private ArrayList<Franchise> list2;
    
    public CreateEmployeeJPanel(int m, boolean isDriver) {
        list = new ArrayList<Employee>();
        list2 = new ArrayList<Franchise>();
        mode = m;
        this.isDriver = isDriver;
        initComponents();
        
        
        if (mode ==0) //Add mode
        {   
            listSelection.setEnabled(false);
            if(isDriver)
                jLabel1.setText("Add a driver");
            else
                jLabel1.setText("Add an Employee");
            
            setList2();
        }
        if(mode == 1)  //Edit mode
        {
            getListSelection();
            if(isDriver)
                jLabel1.setText("Edit a driver");
            else
                jLabel1.setText("Edit an Employee");
            listSelection.setEnabled(true);
            jButton3.setText("Update");
            jButton1.setEnabled(false);
        }
        
        if(mode == 2)  //Delete mode
        {
            getListSelection();
            if(isDriver)
                jLabel1.setText("Delete a driver");
            else
                jLabel1.setText("Delete an Employee");
            listSelection.setEnabled(true);
            jButton3.setText("Delete");
            jButton1.setEnabled(false);
            
            //We're in delete mode, so here we disable all the text boxes and combo boxes.
            //Don't worry, we can still use setText-- this only disables the user from editing it.
            //In other words, the text boxes are only for display purposes.
            jButton1.setEnabled(false);
            newEmployeeFirstName.setEnabled(false);
            newEmployeeLastName.setEnabled(false);
            newEmployeeAddress.setEnabled(false);
            newEmployeeCity.setEnabled(false);
            newEmployeeState.setEnabled(false);
            newEmployeeZip.setEnabled(false);
            newEmployeePhone.setEnabled(false);
            newEmployeeEmail.setEnabled(false);
            newEmployeeType.setEnabled(false);
            newEmployeeNum.setEnabled(false);
            
        }
        
        if(isDriver)
        {
            //change code here
            newEmployeeType.setSelectedIndex(2);
            newEmployeeType.setEnabled(false);
        }
        
        listSelection.setSize(28, 20);  //Make sure it doesn't get too fat
    }
    
    private boolean checkFields()
    {
        boolean success = true;
        
        if(newEmployeeFirstName.isEnabled())
        {
            if(newEmployeeFirstName.getText().isEmpty())
            {
                success = false;
                JOptionPane.showMessageDialog(BaseJFrame.getInstance(), 
                    "First name is invalid.  Type something in there...",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(newEmployeeLastName.isEnabled())
        {
            if(newEmployeeLastName.getText().isEmpty())
            {
                success = false;
                JOptionPane.showMessageDialog(BaseJFrame.getInstance(), 
                    "Last name is invalid.  Type something in there...",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(newEmployeeAddress.isEnabled())
        {
            if(newEmployeeAddress.getText().isEmpty())
            {
                success = false;
                JOptionPane.showMessageDialog(BaseJFrame.getInstance(), 
                    "Address is invalid.  Example: 123 Dragon drive",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(newEmployeeCity.isEnabled())
        {
            if(newEmployeeCity.getText().isEmpty())
            {
                success = false;
                JOptionPane.showMessageDialog(BaseJFrame.getInstance(), 
                    "City name is invalid.  Type something in there...",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(newEmployeeState.isEnabled())
        {
            if(newEmployeeState.getText().isEmpty())
            {
                success = false;
                JOptionPane.showMessageDialog(BaseJFrame.getInstance(), 
                    "State is invalid.  Type something in there...",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(newEmployeeZip.isEnabled())
        {
            if(!newEmployeeZip.getText().matches("[1-9]{1}[0-9]{4}"))
            {
                success = false;
                JOptionPane.showMessageDialog(BaseJFrame.getInstance(), 
                    "Zip code is invalid (example: 30047)",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(newEmployeePhone.isEnabled())
        {
            if(!newEmployeePhone.getText().matches("[0-9]{3}-[0-9]{3}-[0-9]{4}"))
            {
                success = false;
                JOptionPane.showMessageDialog(BaseJFrame.getInstance(), 
                    "Phone number is invalid (example: 770-666-1234)",
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
        list = UIController.getInstance().UIemployeeRouter(new String("FRANCHISOR"), "VIEWALL");
        for(int i = 0; i < list.size(); i++)
        {
            String t = list.get(i).toString();
            listSelection.addItem(t);
        }
        
        /*
        //list2 = UIController.getInstance().UIfranchisorRouter(new String("FRANCHISOR"), "VIEWALL");
        
        for(int i = 0; i < list2.size(); i++)
        {
            String t = list2.get(i).toString();
            newEmployeeNum.addItem(t);
        }
        */
        
        
        
        
        setList2();
        
        
        
        
        
        
            
    }
    
    private void setList2()
    {
        list2 = UIController.getInstance().UIfranchisorRouter(new String("FRANCHISOR"), "VIEWALL");
        
        int myID = JCGlIO.getInstance().getfN();
        
        while(list2.size() > 1)
        {
            if(list2.get(0).getFranchiseID() != myID)
                list2.remove(0);
            else if(list2.get(1).getFranchiseID() != myID)
                list2.remove(1);
        }
        
        newEmployeeNum.addItem(list2.get(0).toString());
    }
    
    private void loadInfoFromList()
    {
        //Loads the information after an item from the combo box has been selected.
        //The data is put into the text fields
        Employee e = list.get(listSelection.getSelectedIndex());
        
        newEmployeeFirstName.setText(e.getFirstName());
        newEmployeeLastName.setText(e.getLastName());
        newEmployeeAddress.setText(e.getAddress());
        newEmployeeCity.setText(e.getCity());
        newEmployeeState.setText(e.getState());
        newEmployeeZip.setText(String.valueOf(e.getZip()));
        newEmployeePhone.setText(e.getPhone());
        newEmployeeEmail.setText(e.getEmail());
        System.out.println(e.getEmpType());
        int type = e.getEmpType();
        if(type == 98)
            newEmployeeType.setSelectedIndex(3);
        else
            newEmployeeType.setSelectedIndex(type-1);
        
        int searchInt = e.getEmployeeID();
        int i = 0;
        for(i = 0; i < list2.size(); i++)
        {
            if(list2.get(i).getFranchiseID() == searchInt)
                break;
        }
        
        if(i < list2.size())
            newEmployeeNum.setSelectedIndex(i);
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        newEmployeeAddress = new javax.swing.JTextField();
        newEmployeeCity = new javax.swing.JTextField();
        newEmployeeState = new javax.swing.JTextField();
        newEmployeeZip = new javax.swing.JTextField();
        newEmployeePhone = new javax.swing.JTextField();
        newEmployeeEmail = new javax.swing.JTextField();
        newEmployeeFirstName = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        newEmployeeType = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        newEmployeeNum = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        listSelection = new javax.swing.JComboBox();
        newEmployeeLastName = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Create a new Employee");

        jLabel2.setText("Address:");

        jLabel3.setText("State:");

        jLabel4.setText("City:");

        jLabel5.setText("Email Address:");

        jLabel6.setText("Phone Number:");

        jLabel7.setText("Zip Code:");

        jLabel8.setText("First Name:");

        newEmployeeAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newEmployeeAddressActionPerformed(evt);
            }
        });

        jButton1.setText("Clear");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Create");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        newEmployeeType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Franchisor", "Franchisee", "Employee", "Administrator" }));

        jLabel9.setText("Employee Type:");

        jLabel10.setText("Franchise Number:");

        jLabel11.setText("Selection:");

        listSelection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listSelectionActionPerformed(evt);
            }
        });

        jLabel12.setText("Last Name:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel12))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(23, 23, 23)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(newEmployeeAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(newEmployeeCity, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(newEmployeeState, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(newEmployeeZip, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(newEmployeePhone, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(newEmployeeEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(newEmployeeFirstName, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(newEmployeeLastName, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(listSelection, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(newEmployeeType, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(32, 32, 32)
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(newEmployeeNum, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(listSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newEmployeeFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newEmployeeLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newEmployeeAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newEmployeeCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newEmployeeState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newEmployeeZip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newEmployeePhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newEmployeeEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newEmployeeType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newEmployeeNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void newEmployeeAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newEmployeeAddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newEmployeeAddressActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //Clear button
        newEmployeeFirstName.setText("");
        newEmployeeAddress.setText("");
        newEmployeeCity.setText("");
        newEmployeeState.setText("");
        newEmployeeZip.setText("");
        newEmployeePhone.setText("");
        newEmployeeEmail.setText("");
        if(newEmployeeType.getItemCount() > 0)
            newEmployeeType.setSelectedIndex(0);
        if(newEmployeeNum.getItemCount() > 0)
            newEmployeeNum.setSelectedIndex(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //Submit button
        if(checkFields() == false)
            return;
        
        Employee emp = new Employee();
        emp.setFirstName(newEmployeeFirstName.getText());
        emp.setLastName(newEmployeeLastName.getText());
        emp.setAddress(newEmployeeAddress.getText());
        emp.setCity(newEmployeeCity.getText());
        emp.setState(newEmployeeState.getText());
        emp.setZip(newEmployeeZip.getText());
        emp.setPhone(newEmployeePhone.getText());
        emp.setEmail(newEmployeeEmail.getText());
        emp.setFranchiseNumber(list2.get(0).getFranchiseID());
        emp.setEmpType(newEmployeeType.getSelectedIndex() + 1);
        String code = "200";
        if(mode == 0)  //Create one
        {
            //send new Employee to DB
            code = UIController.getInstance().UIRouter(emp, "ADD");
        }
        
        if(mode == 1)  //Update one
        {
            //Send list.get(listSelection.getSelectedIndex()) to DB for update
            emp.setEmployeeID(list.get(listSelection.getSelectedIndex()).getEmployeeID());
            code = UIController.getInstance().UIRouter(emp, "EDIT");
        }
        
        if(mode == 2)  //Delete one
        {
            emp.setEmployeeID(list.get(listSelection.getSelectedIndex()).getEmployeeID());
            code = UIController.getInstance().UIRouter(emp, "DELETE");
        }
        
        BaseJFrame.getInstance().setScreen(code);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void listSelectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listSelectionActionPerformed
        loadInfoFromList();
    }//GEN-LAST:event_listSelectionActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JComboBox listSelection;
    private javax.swing.JTextField newEmployeeAddress;
    private javax.swing.JTextField newEmployeeCity;
    private javax.swing.JTextField newEmployeeEmail;
    private javax.swing.JTextField newEmployeeFirstName;
    private javax.swing.JTextField newEmployeeLastName;
    private javax.swing.JComboBox newEmployeeNum;
    private javax.swing.JTextField newEmployeePhone;
    private javax.swing.JTextField newEmployeeState;
    private javax.swing.JComboBox newEmployeeType;
    private javax.swing.JTextField newEmployeeZip;
    // End of variables declaration//GEN-END:variables
}
