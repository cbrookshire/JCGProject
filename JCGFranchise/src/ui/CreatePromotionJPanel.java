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
public class CreatePromotionJPanel extends javax.swing.JPanel {

    /**
     * Creates new form CreatePromotionJPanel
     */
    
    int mode = 1;
    private ArrayList<Membership> list; 
    
    public CreatePromotionJPanel(int m) {
        mode = m;
        list = new ArrayList<Membership>();
        
        
        initComponents();
        
        if(mode == 0)  //Create mode
        {
            //Nothing here!
            jLabel2.setText("Add a Promotion (disabled)");
            JOptionPane.showMessageDialog(BaseJFrame.getInstance(), 
                    "Cannot create any promotions (can only edit and delete)",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            
            listSelection.setEnabled(false);
            txtMemberID.setEnabled(false);
            txtDiscount.setEnabled(false);
            txtMinAmount.setEnabled(false);
        }
        
        if(mode == 1)  //Edit
        {
            getListSelection();
        }
        
        if(mode == 2) //Delete
        {
            getListSelection();
            jLabel2.setText("Delete a Promotion");
            txtMemberID.setEnabled(false);
            txtDiscount.setEnabled(false);
            txtMinAmount.setEnabled(false);
        }
        
        
    }
    
    private boolean checkFields()
    {
        boolean success = true;
        
        if(txtMemberID.isEnabled())
        {
            if(txtMemberID.getText().isEmpty())
            {
                success = false;
                JOptionPane.showMessageDialog(BaseJFrame.getInstance(), 
                    "Member ID is invalid.  Type something in there...",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(txtDiscount.isEnabled())
        {
            if(txtDiscount.getText().isEmpty())
            {
                success = false;
                JOptionPane.showMessageDialog(BaseJFrame.getInstance(), 
                    "Discount is invalid.  Type something in there...",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(txtMinAmount.isEnabled())
        {
            if(txtMinAmount.getText().isEmpty())
            {
                success = false;
                JOptionPane.showMessageDialog(BaseJFrame.getInstance(), 
                    "Min amount is invalid.  Type something in there...",
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
        list = UIController.getInstance().UImembershipRouter(new String("FRANCHISOR"), "VIEWALL");
        for(int i = 0; i < list.size(); i++)
        {
            String t = list.get(i).toString();
            listSelection.addItem(t);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtMemberID = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtDiscount = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        txtMinAmount = new javax.swing.JFormattedTextField();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        listSelection = new javax.swing.JComboBox();

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Edit a Promotion");

        jLabel1.setText("Member ID:");

        jLabel3.setText("Discount");

        txtDiscount.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        jLabel4.setText("Minimum Amount");

        txtMinAmount.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));

        jButton1.setText("Submit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Select");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMemberID, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtMinAmount, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtDiscount, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(listSelection, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(233, 233, 233)
                                .addComponent(jButton1)))
                        .addGap(0, 237, Short.MAX_VALUE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(listSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMemberID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtMinAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(152, 152, 152))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(!checkFields())
            return;
        
        Membership mr = new Membership();
        mr.setMemberID(txtMemberID.getText());
        mr.setDiscount(txtDiscount.getText());
        mr.setMinAmount(txtMinAmount.getText());
        
        String sc = "1";
        if(mode == 0)  //Create one
        {
            sc = UIController.getInstance().UIRouter(mr, "ADD");
        }
        
        if(mode == 1)  //Update one
        {
            sc = UIController.getInstance().UIRouter(mr, "EDIT");
        }
        
        if(mode == 2)  //Delete one
        {
            sc = UIController.getInstance().UIRouter(mr, "DELETE");
        }
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
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JComboBox listSelection;
    private javax.swing.JFormattedTextField txtDiscount;
    private javax.swing.JTextField txtMemberID;
    private javax.swing.JFormattedTextField txtMinAmount;
    // End of variables declaration//GEN-END:variables
}
