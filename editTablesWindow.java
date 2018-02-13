import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import static java.awt.GraphicsDevice.WindowTranslucency.*;
public class editTablesWindow extends JFrame implements ActionListener, WindowListener
{
    static JFrame edit;
    JButton removeTable;
    JButton addTable;
    JPanel addOrRemoveTables;
    JPanel chairCount;
    JPanel chairCountFix;
    public static boolean location;
    JPanel layoutSize;
    Interface mainWindow = Interface.mainWindow;

    public static boolean firstTimeThrough;

    String rowNumber;
    String columnNumber;
    public editTablesWindow()
    {

        edit = new JFrame();
        edit.setTitle("Edit Table Layout");
        edit.setLayout(new BorderLayout());

        edit.setLocation(150,100);

        addOrRemoveTables = new JPanel();
        addOrRemoveTables.setLayout(new BorderLayout());

        addTable = new JButton("Add Tables +");
        addTable.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {

                    if(mainWindow.firstTimeEditingTables())
                    {
                        setGridSize();
                        firstTimeThrough = false;
                    }
                    else
                    {
                        addingTable();
                    }
                }
            });

        removeTable = new JButton("Remove Tables -");
        removeTable.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    removingTable();

                }
            });
        Dimension buttonSize = new Dimension(190,39);
        removeTable.setPreferredSize(buttonSize);
        addTable.setPreferredSize(buttonSize);

        addOrRemoveTables.add(addTable,BorderLayout.WEST);
        addOrRemoveTables.add(removeTable,BorderLayout.CENTER);

        //addOrRemoveTables.remove(addTable);

        edit.add(addOrRemoveTables,BorderLayout.NORTH);

        edit.setSize(400,200);
        edit.setVisible(true);
    }

    public void setGridSize()
    {

        edit.remove(addOrRemoveTables);
        edit.validate();
        edit.repaint();

        layoutSize = new JPanel();
        layoutSize.setLayout(new BorderLayout());

        Choice xChooser = new Choice();        
        Choice yChooser = new Choice();

        JButton youDoneBruv = new JButton("Okay");
        youDoneBruv.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    rowNumber = xChooser.getSelectedItem();
                    columnNumber = yChooser.getSelectedItem();

                    mainWindow.setGrid(rowNumber,columnNumber);
                    addingTable();

                }
            });

        JLabel rowSize = new JLabel("How many rows?");
        JLabel columnSize = new JLabel("How many columns?");

        JPanel labels = new JPanel();
        labels.setLayout(new BorderLayout());

        labels.add(rowSize,BorderLayout.WEST);
        labels.add(columnSize,BorderLayout.EAST);

        xChooser.add("1");
        xChooser.add("2");
        xChooser.add("3");
        xChooser.add("4");
        xChooser.add("5");
        xChooser.add("6");
        xChooser.add("7");

        yChooser.add("1");
        yChooser.add("2");
        yChooser.add("3");
        yChooser.add("4");
        yChooser.add("5");
        yChooser.add("6");
        yChooser.add("7");

        Dimension o = new Dimension(180,30);

        xChooser.setPreferredSize(o);
        yChooser.setPreferredSize(o);

        layoutSize.add(xChooser, BorderLayout.WEST);
        layoutSize.add(yChooser,BorderLayout.EAST);
        layoutSize.add(youDoneBruv,BorderLayout.SOUTH);
        layoutSize.add(labels,BorderLayout.NORTH);

        edit.add(layoutSize,BorderLayout.NORTH);

        edit.validate();
        edit.repaint();
    }

    public void addingTable()
    {
        if(mainWindow.firstTimeEditingTables())
        {
            edit.remove(layoutSize);
            edit.validate();
            edit.repaint();
        }
        else
        {
            edit.remove(addOrRemoveTables);
            edit.validate();
            edit.repaint();
        }

        edit.setTitle("Choose Parameters for table");
        JPanel chooseType = new JPanel();
        chooseType.setLayout(new BorderLayout());

        JLabel choose = new JLabel("What Table Type?");

        // JPanel howManySeats = new JPanel

        Choice tableType = new Choice();
        tableType.add("Round");
        tableType.add("Square");
        tableType.add("Rectangle");

        chooseType.add(choose,BorderLayout.NORTH);
        chooseType.add(tableType,BorderLayout.WEST);

        //panel used to put the howmanyseats north of center (but not fully north);
        chairCountFix = new JPanel();
        chairCountFix.setLayout(new BorderLayout());

        JLabel chairAmount = new JLabel("How many seats?");

        chairCount = new JPanel();
        chairCount.setLayout(new BorderLayout());

        JButton selectLocation = new JButton("Select Location for Table");
        selectLocation.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {

                    edit.setOpacity(0.6f);
                    new instructionsPopUp();
                    location = true;
                    mainWindow.setTableLocation();
                    //edit.dispose();
                    

                }
            });
        Choice howManySeats = new Choice();
        howManySeats.add("One");
        howManySeats.add("Two");
        howManySeats.add("Three");
        howManySeats.add("Four");
        howManySeats.add("Five");
        howManySeats.add("Six");

        chairCount.add(chairAmount,BorderLayout.NORTH);
        chairCount.add(howManySeats,BorderLayout.WEST);

        chairCountFix.add(chairCount,BorderLayout.NORTH);

        edit.add(chairCountFix,BorderLayout.WEST);        
        edit.add(chooseType,BorderLayout.NORTH);
        edit.add(selectLocation,BorderLayout.EAST);

        edit.validate();
        edit.repaint();

    }
    
    public static void disposeEdit()
    {
            edit.dispose();
    }

    public void removingTable()
    {

    }


    public void actionPerformed(ActionEvent e)
    {

    }

    public void     windowClosing(WindowEvent e)
    {
        System.exit(0);
    }

    public void     windowActivated(WindowEvent e){}

    public void     windowDeactivated(WindowEvent e){}

    public void     windowDeiconified(WindowEvent e){}

    public void     windowIconified(WindowEvent e){}

    public void     windowOpened(WindowEvent e){}

    public void     windowClosed(WindowEvent e){

    }

}