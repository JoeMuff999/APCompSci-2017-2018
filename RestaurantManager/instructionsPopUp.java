import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
public class instructionsPopUp extends JFrame
{
    public instructionsPopUp()
    {
        super("Instructions :) ");
        this.setLayout(new BorderLayout());
        
        JLabel help = new JLabel("Click the Main Window to");
        JLabel bah = new JLabel ("select the location of the table");
        
        JButton close = new JButton("Okay");
        close.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    dispose();

                }
            });
        this.add(help,BorderLayout.NORTH);
        this.add(bah,BorderLayout.CENTER);
        this.add(close,BorderLayout.SOUTH);
        this.setVisible(true);
        this.setSize(200,100);
        this.setLocation(300,300);
        
    }
}