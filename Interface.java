import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.TimerTask;
import java.util.Timer;
public class Interface extends JFrame implements ActionListener,WindowListener,MouseListener
{
    JTabbedPane whichTab;
    JButton addTable;
    public JFrame main;
    // private editTablesWindow editWindow;
    ImageIcon rectFourEmpty = new ImageIcon("rectFourEmpty.png");
    ImageIcon rectFourFull = new ImageIcon("rectFourFull.png");
    ImageIcon rectFourDirty = new ImageIcon("rectFourDirty.png");
    ImageIcon rectFourCheck = new ImageIcon("rectFourCheck.png");
    ImageIcon menu = new ImageIcon("menu.png");
    //ImageIcon rectFour;
    JPanel grid;
    JPanel tab1;
    JPanel tab2;
    public static Interface mainWindow;
    int firstTimeThroughEditWindow = 0;
    JToggleButton[][] gridClickers;
    static int rowNumber;
    static int columnNumber;
    boolean getter;
    int gridButtonWidth;
    int gridButtonLength;
    public Tables[][] gridTables;
    Timer[][] tableTimers;
    int[][] counter;
    int[][] dummyCounter;
    public static boolean transactionMenuIsOpen;
    public static boolean transactionMenuAskingForTable = false;
    static DefaultListModel<String> listManager;
    DefaultListModel<String> inventoryManager;
    JList<String> inventory;
    transactionManager transaction;
    openTab[][] tabsForTables;
    JPanel top;

    public static void main(String [] args)
    {
        mainWindow = new Interface();
    }

    public  Interface()
    {
        //editWindow = new editTablesWindow();
        // editWindow.dispose();z
        listManager = new DefaultListModel<String>();

        setDefaultLookAndFeelDecorated(true);
        main = new JFrame();
        main.setDefaultLookAndFeelDecorated(true);
        tabInterface();
        main.add(whichTab);

        main.addMouseListener(this);

        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.addWindowListener(this);
        main.setTitle("Muffoletto Restaurant Services: Version 0.5");
        main.setSize(700,700);
        main.setVisible(true);

    }

    public void tabInterface()
    {
        //first tab
        whichTab = new JTabbedPane();

        tab2 = new JPanel();
        tab2.setLayout(new BorderLayout());
        JLabel tab2Label = new JLabel("Used Inventory");
        tab2Label.setHorizontalAlignment(SwingConstants.CENTER);
         top = new JPanel();
         tab2   .setBackground(Color.WHITE);
        top.setLayout(new BorderLayout());
        top.add(tab2Label,BorderLayout.NORTH);
        tab2.add(top,BorderLayout.NORTH);
        

        tab1 = new JPanel();
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
        JButton openTransaction = new JButton("Manage and Open New Tabs $");
        openTransaction.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    transaction = new transactionManager();
                    transactionMenuIsOpen = true;

                }
            });

        JButton addTable = new JButton("Edit Table Layout +");
        addTable.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    new editTablesWindow();

                }
            });

        bottomButtons.add(addTable,BorderLayout.WEST);
        bottomButtons.add(openTransaction,BorderLayout.CENTER);
        //add the panel of bottom buttons to the first tab
        tab1.add(bottomButtons,BorderLayout.SOUTH);

        JPanel tab3 = new JPanel();
        tab3.setLayout(new BorderLayout());
        JLabel tab3Label = new JLabel("Restauraunt Menu");
        JLabel lol = new JLabel(menu);
        tab3.add(tab3Label,BorderLayout.NORTH);
        tab3.add(lol,BorderLayout.CENTER);

        whichTab.addTab("Floor Layout",tab1);
        whichTab.addTab("Inventory",tab2);
        whichTab.addTab("Menu",tab3);

    }

    public void placeTable()
    {
        for(int x = 0; x < rowNumber; x++)
        {
            for(int y = 0; y < columnNumber; y++)
            {
                gridClickers[x][y].setEnabled(true);
            }
        }
        for(int x = 0; x < rowNumber; x++)
        {
            for(int y = 0; y < columnNumber; y++)
            {
                if(gridClickers[x][y].isSelected())
                {
                    //System.out.println( x + " " + y);
                    placeTableIcon(x,y);
                    editTablesWindow.location = false;
                    getter = editTablesWindow.location;
                    gridClickers[x][y].setSelected(false);
                    editTablesWindow.disposeEdit();
                    //disabling all the tables again
                    for(int z = 0; z < rowNumber; z++)
                    {
                        for(int p = 0; p < columnNumber; p++)
                        {
                            if(gridClickers[z][p].getIcon() == null)
                            {
                                gridClickers[z][p].setEnabled(false);
                            }
                        }
                    }

                }
            }
        }

    }

    public void placeTableIcon(int row, int column)
    {
        gridClickers[row][column].setIcon(rectFourEmpty);
        gridTables[row][column] = new Tables(true,false);
        //System.out.println(gridTables[row][column]);
    }

    public void setGrid(String rows, String columns)
    {
        rowNumber = Integer.parseInt(rows);
        columnNumber = Integer.parseInt(columns);
        grid = new JPanel();
        grid.setLayout(new GridLayout(rowNumber, columnNumber));
        gridClickers = new JToggleButton[rowNumber][columnNumber];

        gridTables = new Tables[rowNumber][columnNumber];
        tableTimers = new Timer[rowNumber][columnNumber];
        dummyCounter = new int[rowNumber][columnNumber];
        counter = new int[rowNumber][columnNumber];
        tabsForTables = new openTab[rowNumber][columnNumber];

        for(int x = 0; x < rowNumber; x++)
        {
            for(int y = 0; y < columnNumber; y++)
            {

                gridClickers[x][y] = new JToggleButton(x + " " + y);

                if( x%2 ==y%2 )
                {

                    gridClickers[x][y].setBackground(Color.WHITE);
                    gridClickers[x][y].setEnabled(false);
                    gridClickers[x][y].addActionListener(this);

                }
                else
                {

                    gridClickers[x][y].setBackground(Color.BLACK);
                    gridClickers[x][y].setEnabled(false);
                    gridClickers[x][y].addActionListener(this);

                }
                grid.add(gridClickers[x][y]);
            }            
        }

        tab1.add(grid,BorderLayout.CENTER);
        tab1.validate();
        tab1.repaint();
        //imageResizing();

    }
    //Set a table to full, timer to change tables to check, set a table to dirty etc...
    public void openTableStateEditor()
    {
        for(int x = 0; x < rowNumber; x++)
        {
            for(int y = 0; y < columnNumber; y++)
            {
                if(gridClickers[x][y].isSelected() && gridClickers[x][y].getIcon() != null)
                {
                    //System.out.println("test");
                    gridClickers[x][y].setSelected(false);
                    new tableStateEditor(x,y);
                }
            }
        }
    }

    public void changeStateOfTables(int row, int column, String empty,String dirty)
    {
        //full takes priority over dirty!
        if(empty.equals("Empty"))
        {
            //needs to be empty and dirty for it to show as dirty!
            gridTables[row][column].setEmpty(true);
            gridClickers[row][column].setIcon(rectFourEmpty);
            if(gridTables[row][column].openTab)
            {
                gridTables[row][column].setOpenTab(false);
                gridClickers[row][column].setIcon(rectFourDirty);
                tabsForTables[row][column].closeTab();
                //System.out.println("test");
            }

            if(dirty.equals("Dirty"))
            {
                gridClickers[row][column].setIcon(rectFourDirty);
                
            }
        }
        //if its a full table
        else
        {

            gridTables[row][column].setEmpty(false);
            gridClickers[row][column].setIcon(rectFourFull);
            startTimer(row, column);
            //reset dummy counter for this table so i can set it to full again and it will work
            dummyCounter[row][column] = 0;
        }
        if(dirty.equals("Dirty"))
        {
            gridTables[row][column].setDirty(true);

        }
        else
        {
            gridTables[row][column].setDirty(false);

        }
    }

    public void returnTableForTabManagement()
    {
        if(transactionMenuAskingForTable)
        {
            for(int x = 0; x < rowNumber; x++)
            {
                for(int y = 0; y < columnNumber; y++)
                {
                    //table selected and its full
                    if(gridClickers[x][y].isSelected() && !gridTables[x][y].empty)
                    {

                        transactionMenuAskingForTable = false;
                        //sending x and y values back to transaction menu
                        transactionManager.column = y;
                        transactionManager.row = x;
                        transactionManager.addTabForTable();

                        //System.out.println("test");

                    }
                }
            }

        }
    }
    //pretty crappy code, use get(x) and getY() to return the row and column of the selected button

    public void startTimer(int row, int column)
    {
        tableTimers[row][column] = new Timer();
        TimerTask duckingCount = new TimerTask(){
                public void run()
                {
                    dummyCounter[row][column]++;
                    if(dummyCounter[row][column] == 10)
                    {

                        timersduckingSuck(row, column);

                    }
                }
            };

        tableTimers[row][column].scheduleAtFixedRate(duckingCount,100,50);
    }

    public void timersduckingSuck(int row, int column)
    {
        long yup = 1000;

        TimerTask flipColors = new TimerTask(){
                public void run()
                {
                    if(!gridTables[row][column].empty)
                    {
                        if(counter[row][column] == 0)
                        {
                            gridClickers[row][column].setIcon(rectFourCheck);
                            counter[row][column]++;
                        }
                        else
                        {
                            gridClickers[row][column].setIcon(rectFourFull);
                            counter[row][column]--;
                        }
                    }
                }
            };
        tableTimers[row][column].scheduleAtFixedRate(flipColors, yup, 600);
    }

    public boolean firstTimeEditingTables()
    {
        if(firstTimeThroughEditWindow == 0)
        {
            firstTimeThroughEditWindow++;
            return true;

        }
        else if (firstTimeThroughEditWindow ==1)
        {
            firstTimeThroughEditWindow++;
            return true;
        }
        else
        {
            return false;
        }
    }

    public void setTableLocation()
    {
        getter =  editTablesWindow.location;
        if(getter)
        {

            placeTable();
        }
    }

    public void updateInventory(String[] addFoodToUsedInventory, int[] addAmountFoodUsedToInventory)
    {
        inventoryManager = new DefaultListModel<String>();
        int safeCounter = 0;
        int cost = 0;
        for(int x = 1; x < addFoodToUsedInventory.length; x++)
        {
            if(addFoodToUsedInventory[x]!= null)
            {
                if(addFoodToUsedInventory[x].equals(addFoodToUsedInventory[x-1]))
                {
                    int first = addAmountFoodUsedToInventory[x];
                    int second = addAmountFoodUsedToInventory[x-1];
                    int third = second+first;
                    
                    if(addFoodToUsedInventory[x].equals("Burger"))
                    {
                        cost = 5;
                    }
                    inventoryManager.addElement("Food Type :: " + addFoodToUsedInventory[x] + "         Amount :: " + 
                        third + "       Cost " + cost);
                    
                }
                else
                {
                    cost = 5;
                    inventoryManager.addElement("Food Type :: " + addFoodToUsedInventory[x] + " Amount :: " +  addAmountFoodUsedToInventory[x]+ 
                    "       Cost " + cost);
                }
            }

        }
        inventory = new JList<String>(inventoryManager);
        top.add(inventory, BorderLayout.NORTH);

        tab2.validate();
        tab2.repaint();
    }

    public void     windowClosing(WindowEvent e)
    {
        System.exit(0);
    }

    public void actionPerformed(ActionEvent e)
    {
        setTableLocation();
        returnTableForTabManagement();
        if(!transactionMenuIsOpen)
        {
            openTableStateEditor();
        }
        //after i do all actionPerformed, make sure every button is deselected;
        for(int x = 0; x < rowNumber; x++)
        {
            for(int y = 0; y < columnNumber; y++)
            {
                gridClickers[x][y].setSelected(false);
            }
        }

        //System.out.println("test");

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
