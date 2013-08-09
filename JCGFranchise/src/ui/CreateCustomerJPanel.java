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
public class CreateCustomerJPanel extends javax.swing.JPanel {

    /**
     * Creates new form CreateFranchise
     */
    private int mode;
    private ArrayList<Customer> list;
    /**
     * Creates new form CreateFranchise
     */
    public CreateCustomerJPanel(int m) {
        list = new ArrayList<Customer>();
        mode = m;
        initComponents();
        
        
        
        if(mode == 0)  //Create mode
        {
            listSelection.setEnabled(false);
        }
        
        if(mode == 1)  //Edit mode
        {
            getListSelection();
            jLabel1.setText("Edit an Customer");
            listSelection.setEnabled(true);
            btnSave.setText("Update");
            btnClear.setEnabled(false);
        }
        
        if(mode == 2)  //Delete mode
        {
            getListSelection();
            jLabel1.setText("Delete an Customer");
            listSelection.setEnabled(true);
            btnSave.setText("Delete");
            btnClear.setEnabled(false);
            
            //We're in delete mode, so here we disable all the text boxes and combo boxes.
            //Don't worry, we can still use setText-- this only disables the user from editing it.
            //In other words, the text boxes are only for display purposes.
            btnClear.setEnabled(false);
            newCustomerName.setEnabled(false);
            newCustomerAddress.setEnabled(false);
            newCustomerCity.setEnabled(false);
            newCustomerState.setEnabled(false);
            newCustomerZip.setEnabled(false);
            newCustomerPhone.setEnabled(false);
            newCustomerEmail.setEnabled(false);
            resCount.setEnabled(false);
            memberID.setEnabled(false);
        }
    }
    
    private boolean checkFields()
    {
        boolean success = true;
        
        if(newCustomerName.isEnabled())
        {
            if(newCustomerName.getText().isEmpty())
            {
                success = false;
                JOptionPane.showMessageDialog(BaseJFrame.getInstance(), 
                    "Customer name is invalid.  Type something in there...",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(newCustomerAddress.isEnabled())
        {
            if(newCustomerAddress.getText().isEmpty())
            {
                success = false;
                JOptionPane.showMessageDialog(BaseJFrame.getInstance(), 
                    "Address is invalid.  Example: 123 Dragon drive",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(newCustomerCity.isEnabled())
        {
            if(newCustomerCity.getText().isEmpty())
            {
                success = false;
                JOptionPane.showMessageDialog(BaseJFrame.getInstance(), 
                    "City name is invalid.  Type something in there...",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(newCustomerState.isEnabled())
        {
            if(newCustomerState.getText().isEmpty())
            {
                success = false;
                JOptionPane.showMessageDialog(BaseJFrame.getInstance(), 
                    "State name is invalid.  Type something in there...",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(newCustomerZip.isEnabled())
        {
            if(!newCustomerZip.getText().matches("[1-9]{1}[0-9]{4}"))
            {
                success = false;
                JOptionPane.showMessageDialog(BaseJFrame.getInstance(), 
                    "Zip code is invalid (example: 30047)",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(newCustomerPhone.isEnabled())
        {
            if(!newCustomerPhone.getText().matches("[0-9]{3}-[0-9]{3}-[0-9]{4}"))
            {
                success = false;
                JOptionPane.showMessageDialog(BaseJFrame.getInstance(), 
                    "Phone number is invalid (example: 770-666-1234)",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(newCustomerEmail.isEnabled())
        {
            if(!newCustomerEmail.getText().matches("\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,6}"))
            {
                success = false;
                JOptionPane.showMessageDialog(BaseJFrame.getInstance(), 
                    "Email is invalid.  (Example: someuser@website.com)",
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
        list = UIController.getInstance().UIcustomerRouter(new String("CUSTOMER"), "VIEWALL");
        for(int i = 0; i < list.size(); i++)
        {
            String t = list.get(i).getLastName() + "," + list.get(i).getFirstName();
            listSelection.addItem(t);
        }
    }
    
    private void loadInfoFromList()
    {
        //Loads the information after an item from the combo box has been selected.
        //The data is put into the text fields
        Customer c = list.get(listSelection.getSelectedIndex());
        
        
        newCustomerName.setText(c.getFirstName() + " " + c.getLastName());
        newCustomerAddress.setText(c.getAddress());
        newCustomerCity.setText(c.getCity());
        newCustomerState.setText(c.getState());
        newCustomerZip.setText(String.valueOf(c.getZip()));
        newCustomerPhone.setText(c.getPhone());
        newCustomerEmail.setText(c.getEmail());
        
                
        
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
        newCustomerAddress = new javax.swing.JTextField();
        newCustomerCity = new javax.swing.JTextField();
        newCustomerState = new javax.swing.JTextField();
        newCustomerZip = new javax.swing.JTextField();
        newCustomerPhone = new javax.swing.JTextField();
        newCustomerEmail = new javax.swing.JTextField();
        newCustomerName = new javax.swing.JTextField();
        btnClear = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        listSelection = new javax.swing.JComboBox();
        resCount = new javax.swing.JLabel();
        memberID = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setPreferredSize(new java.awt.Dimension(545, 500));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Create a new Customer");

        jLabel2.setText("Address:");

        jLabel3.setText("State:");

        jLabel4.setText("City:");

        jLabel5.setText("Email Address:");

        jLabel6.setText("Phone Number:");

        jLabel7.setText("Zip Code:");

        jLabel8.setText("Name:");

        newCustomerAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newCustomerAddressActionPerformed(evt);
            }
        });

        newCustomerEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newCustomerEmailActionPerformed(evt);
            }
        });

        btnClear.setText("Clear Form");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnSave.setText("Submit");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jLabel9.setText("Reservation Count:");

        jLabel10.setText("Member ID:");

        listSelection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listSelectionActionPerformed(evt);
            }
        });

        resCount.setText("resCount");

        memberID.setText("memberID");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel8))
                            .addGap(23, 23, 23)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(newCustomerName, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                                .addComponent(newCustomerAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                                .addComponent(newCustomerCity, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                                .addComponent(newCustomerState, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                                .addComponent(newCustomerZip, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                                .addComponent(newCustomerPhone, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                                .addComponent(newCustomerEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                                .addComponent(listSelection, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel9)
                                .addComponent(jLabel10))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(resCount, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(memberID, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(listSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(newCustomerAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(newCustomerCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(newCustomerState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(newCustomerZip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(56, 56, 56))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(newCustomerPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(newCustomerEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resCount, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(memberID, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void newCustomerAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newCustomerAddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newCustomerAddressActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        //OMG CLEAR BUTTON SO MUCH FUN!  :O
        newCustomerName.setText("");
        newCustomerAddress.setText("");
        newCustomerCity.setText("");
        newCustomerState.setText("");
        newCustomerZip.setText("");
        newCustomerPhone.setText("");
        newCustomerEmail.setText("");
        resCount.setText("1");
        memberID.setText("00000");
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if(checkFields() == false)
            return;
        Customer cust = new Customer();
        
        String[] t = newCustomerName.getText().split(" ");
        cust.setFirstName(t[0]);
        if(t.length > 1)
            cust.setLastName(t[1]);
        cust.setAddress(newCustomerAddress.getText());
        cust.setCity(newCustomerCity.getText());
        cust.setState(newCustomerState.getText());
        cust.setZip(newCustomerZip.getText());
        cust.setPhone(newCustomerPhone.getText());
        cust.setEmail(newCustomerEmail.getText());
        
        String code = "200";
        if(mode == 0)  //Create one
        {
            code = UIController.getInstance().UIRouter(cust, "ADD");
            //send new Customer to DB
        }
        
        if(mode == 1)  //Update one
        {
            code = UIController.getInstance().UIRouter(cust, "EDIT");
            //Send list.get(listSelection.getSelectedIndex()) to DB for update
        }
        
        if(mode == 2)  //Delete one
        {
            code = UIController.getInstance().UIRouter(cust, "DELETE");
        }
        
        BaseJFrame.getInstance().setScreen(code);
    }//GEN-LAST:event_btnSaveActionPerformed

    private void newCustomerEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newCustomerEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newCustomerEmailActionPerformed

    private void listSelectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listSelectionActionPerformed
        loadInfoFromList();
    }//GEN-LAST:event_listSelectionActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnSave;
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
    private javax.swing.JLabel memberID;
    private javax.swing.JTextField newCustomerAddress;
    private javax.swing.JTextField newCustomerCity;
    private javax.swing.JTextField newCustomerEmail;
    private javax.swing.JTextField newCustomerName;
    private javax.swing.JTextField newCustomerPhone;
    private javax.swing.JTextField newCustomerState;
    private javax.swing.JTextField newCustomerZip;
    private javax.swing.JLabel resCount;
    // End of variables declaration//GEN-END:variables
}
