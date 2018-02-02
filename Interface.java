import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
public class Interface extends Frame implements ActionListener,WindowListener
{
    JTabbedPane whichTab = new JTabbedPane();
    JButton addTable;

    public static void main(String [] args)
    {
        new Interface();
    }

    public  Interface()
    {
        JFrame main = new JFrame();
        tabInterface();
        main.add(whichTab);

        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setTitle("Muffoletto Restaurant Services: Version 1.0");
        main.setSize(700,700);
        main.setVisible(true);

    }

    public void tabInterface()
    {
        //first tab

        JPanel tab1 = new JPanel();
        tab1.setLayout(new BorderLayout());
        JLabel tab1Label = new JLabel("Restauraunt Layout");
        tab1Label.setHorizontalAlignment(SwingConstants.CENTER);
        tab1.add(tab1Label,BorderLayout.NORTH);
        //first tab, adding stuff into the tab, adding tables is first priority!
        //adding tables (bottom buttons)        
        JPanel bottomButtons = new JPanel(); 
        bottomButtons.setLayout(new BorderLayout());
        JButton addTable = new JButton("Edit Table Layout +");
        addTable.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {

                    System.out.println("test");

                }
            });

        bottomButtons.add(addTable,BorderLayout.WEST);
        //add the panel of bottom buttons to the first tab
        tab1.add(bottomButtons,BorderLayout.SOUTH);

        JPanel tab2 = new JPanel();
        JLabel tab2Label = new JLabel("Restauraunt Menu");
        tab2.add(tab2Label);

        whichTab.addTab("Floor Layout",tab1);
        whichTab.addTab("Menu",tab2);

    }

    public void     windowClosing(WindowEvent e)
    {
        System.exit(0);
    }

    public void actionPerformed(ActionEvent e)
    {

    }

    public void     windowActivated(WindowEvent e){}

    public void     windowDeactivated(WindowEvent e){}

    public void     windowDeiconified(WindowEvent e){}

    public void     windowIconified(WindowEvent e){}

    public void     windowOpened(WindowEvent e){}

    public void     windowClosed(WindowEvent e){

    }

}