import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import static java.awt.GraphicsDevice.WindowTranslucency.*;
public class transactionManager extends JFrame implements ActionListener,WindowListener
{
    JPanel manageTransactions;
    JButton addTransaction;
    JButton viewExistingTransactions;
    public static int row;
    public static int column;
    static  Interface mainWindow = Interface.mainWindow;
    static JLabel instructions;
    JList<String> listOfOpenTransactions;

    public transactionManager()
    {
        setLayout(new BorderLayout());

        manageTransactions = new JPanel();
        manageTransactions.setLayout(new BorderLayout());

        addTransaction = new JButton("Open New Tab +");
        addTransaction.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    selectTableForNewTransaction();
                }
            });

        viewExistingTransactions = new JButton("Manage Open Transactions");
        viewExistingTransactions.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    viewOpenTransactions();

                }
            });
        Dimension buttonSize = new Dimension(190,39);
        viewExistingTransactions.setPreferredSize(buttonSize);
        addTransaction.setPreferredSize(buttonSize);

        manageTransactions.add(addTransaction,BorderLayout.WEST);
        manageTransactions.add(viewExistingTransactions,BorderLayout.CENTER);
        addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent event) {
                    mainWindow.transactionMenuIsOpen = false;
                    dispose();
                }
            });

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        add(manageTransactions,BorderLayout.CENTER);

        setAlwaysOnTop(true);

        setTitle("Manage Transactions");        
        setSize(400,100);
        this.setLocation(150,100);
        setVisible(true);
    }

    public void selectTableForNewTransaction()
    {
        int row;
        int column;
        remove(manageTransactions);
        instructions = new JLabel("Click an existing FULL table to open a tab");
        add(instructions,BorderLayout.CENTER);
        JButton kill = new JButton("Okay");
        kill.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    mainWindow.transactionMenuIsOpen = false;
                    dispose();

                }
            });
        add(kill,BorderLayout.SOUTH);

        validate();
        repaint();

        //getting the information for the table from the main window
        mainWindow.transactionMenuAskingForTable = true;
        //after setting this to true, the next time i click it will return the correct x and y for the table selected 

    }

    public static void addTabForTable()
    {        
        //created an open tab object
        mainWindow.gridTables[row][column].setOpenTab(true);
        mainWindow.tabsForTables[row][column] = new openTab();

    }

    public void viewOpenTransactions()
    {        
        mainWindow.listManager.clear();

        for(int x = 0; x < mainWindow.rowNumber; x++)
        {
            for(int y = 0; y < mainWindow.columnNumber; y++)
            {
                if(mainWindow.gridTables[x][y]!=null)
                {
                    if(mainWindow.gridTables[x][y].openTab)
                    {
                        mainWindow.listManager.addElement("Table " + x + " " + y);
                        //System.out.println("Table " + x + " " + y + " currently has an open tab");
                    }
                }
            }
        }
        remove(manageTransactions);
        JLabel woot = new JLabel("List of tables with open tabs. Select then add transaction");
        listOfOpenTransactions = new JList<String>(mainWindow.listManager);
        JButton addTransactions = new JButton("Add transaction");
        addTransactions.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {                    
                    String selectedFullOpenTabTable = listOfOpenTransactions.getSelectedValue();
                    new transaction(selectedFullOpenTabTable);
                }
            });

        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());
        main.add(addTransactions,BorderLayout.SOUTH);
        main.add(woot,BorderLayout.NORTH);
        main.add(listOfOpenTransactions,BorderLayout.CENTER);
        add(main);

        validate();
        repaint();
        setSize(main.getWidth(), main.getHeight()+100);
        validate();
        repaint();

    }

    public void actionPerformed(ActionEvent e)
    {

    }

    public void     windowClosing(WindowEvent e)
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