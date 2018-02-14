import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.*;
public class Interface extends Frame implements ActionListener,WindowListener,MouseListener
{
    JTabbedPane whichTab;
    JButton addTable;
    public JFrame main;
    // private editTablesWindow editWindow;
    ImageIcon rectFourEmpty = new ImageIcon("rectFourEmpty.png");
    ImageIcon rectFourFull = new ImageIcon("rectFourFull.png");
    ImageIcon rectFourDirty = new ImageIcon("rectFourDirty.png");
    ImageIcon rectFourCheck = new ImageIcon("rectFourCheck.png");
    //ImageIcon rectFour;
    JPanel grid;
    JPanel tab1;
    public static Interface mainWindow;
    int firstTimeThroughEditWindow = 0;
    JToggleButton[][] gridClickers;
    int rowNumber;
    int columnNumber;
    boolean getter;
    int gridButtonWidth;
    int gridButtonLength;
    public Tables[][] gridTables;
    
    
    public static void main(String [] args)
    {
        mainWindow = new Interface();
    }

    public  Interface()
    {
        //editWindow = new editTablesWindow();
        // editWindow.dispose();
        
        
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
        whichTab = new JTabbedPane();

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
                    System.out.println( x + " " + y);
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
        imageResizing();

    }
    //Set a table to full, timer to change tables to check, set a table to dirty etc...
    public void changeStateOfTables()
    {
        for(int x = 0; x < rowNumber; x++)
        {
            for(int y = 0; y < columnNumber; y++)
            {
                if(gridClickers[x][y].isSelected() && gridClickers[x][y].getIcon() != null)
                {
                    System.out.println("test");
                    gridClickers[x][y].setSelected(false);
                    new tableStateEditor(x,y);
                }
            }
        }
    }
    
    public void imageResizing()
    {
       /* gridButtonWidth = gridClickers[0][0].getWidth();
        gridButtonLength = gridClickers[0][0].getHeight();

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("rectFourEmpty.png"));
        } catch (IOException e) {
            
        }
       // System.out.println("test");


        BufferedImage resizedImage = new BufferedImage(gridButtonWidth, gridButtonLength,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(resizedImage, 0, 0, gridButtonWidth, gridButtonLength, null);
        g.dispose();
        
        rectFour = new ImageIcon(resizedImage);*/
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

    public void     windowClosing(WindowEvent e)
    {
        System.exit(0);
    }

    public void actionPerformed(ActionEvent e)
    {
        setTableLocation();
        changeStateOfTables();
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
