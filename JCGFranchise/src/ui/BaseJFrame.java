/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import gui.*;

/**
 *
 * @author Davidi
 */
public class BaseJFrame extends JFrame {
    private JPanel content = null;
    private static BaseJFrame instance;
    
    public BaseJFrame()
    {
        FlowLayout layout = new FlowLayout();
        this.setLayout(layout);
        this.setSize(800, 600);
        setContent(new OpeningJPanel(), "Main Page");
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void setContent(JPanel c, String title)
    {
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
        if(instance == null)
            instance = new BaseJFrame();
        
        return instance;
    }
}
