import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
public class tableStateEditor extends JFrame implements ActionListener, WindowListener
{
    Interface mainWindow = Interface.mainWindow;
    public tableStateEditor(int row, int column)
    {
        setLayout(new BorderLayout());
        choices(row,column);
        this.setLocation(300,200);
        setTitle("Change State of Table");        
        setSize(200,200);
        setVisible(true);
    }

    public void choices(int row, int column)
    {
        JPanel main = new JPanel();

        JLabel currentState = new JLabel("Current state of Table :: ");
        JLabel isTheTableEmpty = new JLabel("Empty? " + mainWindow.gridTables[row][column].empty 
                + "| Dirty? " + mainWindow.gridTables[row][column].dirty);
        JPanel consolidationPanel = new JPanel();

        JButton confirm = new JButton("Confirm Changes");

        consolidationPanel.setLayout(new BorderLayout());
        JPanel labelContainer = new JPanel();
        labelContainer.setLayout(new BorderLayout());
        JLabel dirtyChooserLabel = new JLabel("Dirty or Clean?");
        JLabel emptyChooserLabel = new JLabel("Empty or Full?");

        labelContainer.add(dirtyChooserLabel,BorderLayout.WEST);
        labelContainer.add(emptyChooserLabel,BorderLayout.EAST);

        JPanel choicesContainer = new JPanel();
        choicesContainer.setLayout(new BorderLayout());

        Choice dirtyOrNot = new Choice();
        Choice emptyOrNot = new Choice();

        emptyOrNot.add("Empty");
        emptyOrNot.add("Full");

        dirtyOrNot.add("Clean");
        dirtyOrNot.add("Dirty");

        choicesContainer.add(emptyOrNot,BorderLayout.EAST);
        choicesContainer.add(dirtyOrNot,BorderLayout.WEST);

        confirm.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    mainWindow.changeStateOfTables(row, column,emptyOrNot.getSelectedItem(),dirtyOrNot.getSelectedItem()); 
                    dispose();

                }
            });

        consolidationPanel.add(labelContainer,BorderLayout.NORTH);
        consolidationPanel.add(choicesContainer,BorderLayout.CENTER);
        consolidationPanel.add(confirm,BorderLayout.SOUTH);

        main.setLayout(new BorderLayout());
        main.add(currentState,BorderLayout.NORTH);
        main.add(isTheTableEmpty, BorderLayout.SOUTH);
        add(consolidationPanel,BorderLayout.CENTER);

        add(main,BorderLayout.NORTH);

    }

    public void     windowClosing(WindowEvent e)
    {
        System.exit(0);
    }

    public void actionPerformed(ActionEvent e){}

    public void     windowActivated(WindowEvent e){}

    public void     windowDeactivated(WindowEvent e){}

    public void     windowDeiconified(WindowEvent e){}

    public void     windowIconified(WindowEvent e){}

    public void     windowOpened(WindowEvent e){}

    public void     windowClosed(WindowEvent e){}
}