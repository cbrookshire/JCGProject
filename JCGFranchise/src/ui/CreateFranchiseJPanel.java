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
public class CreateFranchiseJPanel extends javax.swing.JPanel {

    /**
     * Creates new form CreateFranchiseJPanel
     */
    private int mode;
    private ArrayList<Franchise> list; 
    
    public CreateFranchiseJPanel(int m) {
        list = new ArrayList<Franchise>();
        mode = m;
        initComponents();
        
        if(mode == 0)  //Create mode
        {
            listSelection.setEnabled(false);
        }
        
        if(mode == 1)  //Edit mode
        {
            getListSelection();
            jLabel1.setText("Edit a Franchise");
            listSelection.setEnabled(true);
            newFranchiseButton.setText("Update");
            btnClear.setEnabled(false);
        }
        
        if(mode == 2)  //Delete mode
        {
            getListSelection();
            jLabel1.setText("Delete a Franchise");
            listSelection.setEnabled(true);
            newFranchiseButton.setText("Delete");
            btnClear.setEnabled(false);
            
            //We're in delete mode, so here we disable all the text boxes and combo boxes.
            //Don't worry, we can still use setText-- this only disables the user from editing it.
            //In other words, the text boxes are only for display purposes.
            btnClear.setEnabled(false);
            newFranchiseAddress.setEnabled(false);
            newFranchiseCity.setEnabled(false);
            newFranchiseState.setEnabled(false);
            newFranchiseZip.setEnabled(false);
            newFranchisePhone.setEnabled(false);
            newFranchiseEmail.setEnabled(false);
            AirportjComboBox.setEnabled(false);
            
        }
    }
    
    private boolean checkFields()
    {
        boolean success = true;
        
        if(newFranchiseCity.isEnabled())
        {
            if(newFranchiseCity.getText().isEmpty())
            {
                success = false;
                JOptionPane.showMessageDialog(BaseJFrame.getInstance(), 
                    "City name is invalid.  Type something in there...",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(newFranchiseAddress.isEnabled())
        {
            if(newFranchiseAddress.getText().isEmpty())
            {
                success = false;
                JOptionPane.showMessageDialog(BaseJFrame.getInstance(), 
                    "Address is invalid.  Example: 123 Dragon drive",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(newFranchiseCity.isEnabled())
        {
            if(newFranchiseCity.getText().isEmpty())
            {
                success = false;
                JOptionPane.showMessageDialog(BaseJFrame.getInstance(), 
                    "City name is invalid.  Type something in there...",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(newFranchiseState.isEnabled())
        {
            if(newFranchiseState.getText().isEmpty())
            {
                success = false;
                JOptionPane.showMessageDialog(BaseJFrame.getInstance(), 
                    "City name is invalid.  Type something in there...",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(newFranchiseZip.isEnabled())
        {
            if(!newFranchiseZip.getText().matches("[1-9]{1}[0-9]{4}"))
            {
                success = false;
                JOptionPane.showMessageDialog(BaseJFrame.getInstance(), 
                    "Zip code is invalid (example: 30047)",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(newFranchisePhone.isEnabled())
        {
            if(!newFranchisePhone.getText().matches("[0-9]{3}-[0-9]{3}-[0-9]{4}"))
            {
                success = false;
                JOptionPane.showMessageDialog(BaseJFrame.getInstance(), 
                    "Phone number is invalid (example: 770-666-1234)",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(newFranchiseEmail.isEnabled())
        {
            if(!newFranchiseEmail.getText().matches("\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,6}"))
            {
                success = false;
                JOptionPane.showMessageDialog(BaseJFrame.getInstance(), 
                    "Email is invalid.  (Example: someuser@website.com)",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
       /* if(newFranchiseAirport.isEnabled())
        {
            if(!newFranchiseAirport.getText().matches("[0-9]+"))
            {
                success = false;
                JOptionPane.showMessageDialog(BaseJFrame.getInstance(), 
                    "Airport number is invalid.  Type a number in there...",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            }
        } */
        
        return success;
    }

    private void getListSelection()
    {
        //Gets a list of all the Employees (should display name)
        //list = LOLGETLIST();
        list = UIController.getInstance().UIfranchisorRouter(new String("FRANCHISOR"), "VIEWALL");
        for(int i = 0; i < list.size(); i++)
        {
            String t = list.get(i).getFranchiseID() + ":" + list.get(i).getAddress() + ";" + list.get(i).getCity() + "," + list.get(i).getState();
            listSelection.addItem(t);
        }
    }
    
    private void loadInfoFromList()
    {
        //Loads the information after an item from the combo box has been selected.
        //The data is put into the text fields
        Franchise me = list.get(listSelection.getSelectedIndex());
        newFranchiseAddress.setText(me.getAddress());
        newFranchiseCity.setText(me.getCity());
        newFranchiseState.setText(me.getState());
        newFranchiseZip.setText(String.valueOf(me.getZip()));
        newFranchisePhone.setText(me.getPhone());
        newFranchiseEmail.setText(me.getEmail());
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        newFranchiseAddress = new javax.swing.JTextField();
        newFranchiseCity = new javax.swing.JTextField();
        newFranchiseState = new javax.swing.JTextField();
        newFranchiseZip = new javax.swing.JTextField();
        newFranchisePhone = new javax.swing.JTextField();
        newFranchiseEmail = new javax.swing.JTextField();
        btnClear = new javax.swing.JButton();
        newFranchiseButton = new javax.swing.JButton();
        listSelection = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        AirportjComboBox = new javax.swing.JComboBox();

        setBackground(new java.awt.Color(204, 204, 204));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setPreferredSize(new java.awt.Dimension(545, 530));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Create a new Franchise");

        jLabel2.setText("Address:");

        jLabel3.setText("State:");

        jLabel4.setText("City:");

        jLabel5.setText("Email Address:");

        jLabel6.setText("Phone Number:");

        jLabel7.setText("Zip Code:");

        jLabel8.setText("Servicing Airport:");

        newFranchiseAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newFranchiseAddressActionPerformed(evt);
            }
        });

        newFranchiseEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newFranchiseEmailActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        newFranchiseButton.setText("Create");
        newFranchiseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newFranchiseButtonActionPerformed(evt);
            }
        });

        listSelection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listSelectionActionPerformed(evt);
            }
        });

        jLabel9.setText("Selection:");

        AirportjComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3" }));
        AirportjComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AirportjComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addComponent(newFranchiseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(listSelection, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(newFranchiseCity)
                                    .addComponent(newFranchiseState)
                                    .addComponent(newFranchiseZip)
                                    .addComponent(newFranchiseEmail)
                                    .addComponent(newFranchisePhone)
                                    .addComponent(newFranchiseAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                                    .addComponent(AirportjComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(listSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newFranchiseAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newFranchiseCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newFranchiseState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newFranchiseZip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newFranchisePhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newFranchiseEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AirportjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newFranchiseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, 500, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void newFranchiseAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newFranchiseAddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newFranchiseAddressActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        //yay clear button!  This button resets the form
        if(listSelection.getItemCount() > 0)
           listSelection.setSelectedIndex(0);
        newFranchiseAddress.setText("");
        newFranchiseCity.setText("");
        newFranchiseState.setText("");
        newFranchiseZip.setText("");
        newFranchisePhone.setText("");
        newFranchiseEmail.setText("");
        //newFranchiseAirport.setText("");
    }//GEN-LAST:event_btnClearActionPerformed

    private void newFranchiseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newFranchiseButtonActionPerformed
        if(!checkFields())
            return;
        
        Franchise fr = new Franchise();
        
        fr.setAddress(newFranchiseAddress.getText());
        fr.setCity(newFranchiseCity.getText());
        fr.setState(newFranchiseState.getText());
        fr.setZip(newFranchiseZip.getText());
        fr.setPhone(newFranchisePhone.getText());
        fr.setEmail(newFranchiseEmail.getText());
        
        fr.setAirportID(String.valueOf(AirportjComboBox.getSelectedIndex()+1));
        String sc = "100";
        if(mode == 0)  //Create one
        {
            sc = UIController.getInstance().UIRouter(fr, "ADD");
        }
        
        if(mode == 1)  //Update one
        {
            fr.setFranchiseID(list.get(listSelection.getSelectedIndex()).getFranchiseID());
            sc = UIController.getInstance().UIRouter(fr, "EDIT");
        }
        
        if(mode == 2)  //Delete one
        {
            fr.setFranchiseID(list.get(listSelection.getSelectedIndex()).getFranchiseID());
            sc = UIController.getInstance().UIRouter(fr, "DELETE");
        }
        System.out.println("YOU DIRTY CHEATER!  I HOPE YOU " + sc);
            if(sc == "1")
            {
                JOptionPane.showMessageDialog(BaseJFrame.getInstance(), 
                    "Success!",
                    "Success",
                    JOptionPane.PLAIN_MESSAGE);
                BaseJFrame.getInstance().setScreen("100");
            }
            else if(sc == "DoubleEntry")
            {
                JOptionPane.showMessageDialog(BaseJFrame.getInstance(), 
                    "Data already exists",
                    "Success",
                    JOptionPane.PLAIN_MESSAGE);
            }
            else
                BaseJFrame.getInstance().setScreen(sc);
    }//GEN-LAST:event_newFranchiseButtonActionPerformed

    private void newFranchiseEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newFranchiseEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newFranchiseEmailActionPerformed

    private void listSelectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listSelectionActionPerformed
        loadInfoFromList();
    }//GEN-LAST:event_listSelectionActionPerformed

    private void AirportjComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AirportjComboBoxActionPerformed
        // TODO add your handling code here:
        /*if(AirportjComboBox.getSelectedIndex() == 1)
        {
            
        }*/
    }//GEN-LAST:event_AirportjComboBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox AirportjComboBox;
    private javax.swing.JButton btnClear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox listSelection;
    private javax.swing.JTextField newFranchiseAddress;
    private javax.swing.JButton newFranchiseButton;
    private javax.swing.JTextField newFranchiseCity;
    private javax.swing.JTextField newFranchiseEmail;
    private javax.swing.JTextField newFranchisePhone;
    private javax.swing.JTextField newFranchiseState;
    private javax.swing.JTextField newFranchiseZip;
    // End of variables declaration//GEN-END:variables
}
