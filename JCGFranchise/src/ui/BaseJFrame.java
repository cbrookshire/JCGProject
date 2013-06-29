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
        setContent(new OpeningJPanel(), "Main Page");
        
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
}
