    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.*;
    import java.io.Serializable;
public class Interface extends Frame implements ActionListener,WindowListener,MouseListener
{
    JTabbedPane whichTab = new JTabbedPane();
    JButton addTable;
    public JFrame main;
    editTablesWindow editWindow = new editTablesWindow();
    ImageIcon rectFour = new ImageIcon("rectFour.png");

    public static void main(String [] args)
    {
        new Interface();
    }

    public  Interface()
    {
        main = new JFrame();
        main.setDefaultLookAndFeelDecorated(true);
        tabInterface();
        main.add(whichTab);
        
        main.addMouseListener(this);
        
        
        
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.addWindowListener(this);
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
        tab1.setBackground(Color.WHITE);
        tab1.addMouseListener(this);
        //first tab, adding stuff into the tab, adding tables is first priority!
        //adding tables (bottom buttons)        
        JPanel bottomButtons = new JPanel(); 
        bottomButtons.setLayout(new BorderLayout());
        JButton addTable = new JButton("Edit Table Layout +");
        addTable.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    new editTablesWindow();

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
    
    public void placeTable(int x, int y)
    {
        JLabel table = new JLabel(rectFour);
        
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

    public void mousePressed(MouseEvent e) 
    {
        
        boolean getter = editTablesWindow.location;
        if(getter)
        {
           // System.out.println("test");
            editTablesWindow.location = false;
            //System.out.println(editTablesWindow.location);
            getter = editTablesWindow.location;
            placeTable(getX(),getY());
            
        }

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseClicked(MouseEvent e) {

    }
}
