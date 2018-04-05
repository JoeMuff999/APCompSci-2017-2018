import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
public class transaction extends JFrame 
{
    Interface mainWindow = Interface.mainWindow;
    String table;
    public transaction(String whatTable)
    {
        super("Add Transaction To Tab");
        table = whatTable;

        this.setLayout(new BorderLayout());

        Choice chooseFood = new Choice();
        chooseFood.add("Burger");

        Choice chooseAmount = new Choice();
        chooseAmount.add("1");

        JButton confirm = new JButton("Confirm");
        confirm.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    addItemsToTab(chooseFood.getSelectedItem(),Integer.parseInt(chooseAmount.getSelectedItem()));
                    dispose();

                }
            });
            
        add(chooseFood,BorderLayout.WEST);
        add(chooseAmount,BorderLayout.EAST);
        this.add(confirm,BorderLayout.SOUTH);
        this.setVisible(true);
        this.setSize(200,100);
        this.setLocation(300,300);

    }

    public void addItemsToTab(String food,int amount )
    {
        String x = Character.toString(table.charAt(6));
        String y = Character.toString(table.charAt(8));
        mainWindow.tabsForTables[Integer.parseInt(x)][Integer.parseInt(y)].addFoodItems(food, amount);
        //System.out.println(mainWindow.tabsForTables[Integer.parseInt(x)][Integer.parseInt(y)]);
        //System.out.println(Integer.parseInt(x) + " " + Integer.parseInt(y));
    }
}