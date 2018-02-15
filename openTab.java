import java.util.Arrays;
public class openTab
{
    //array of types of food they order
    //array of how much of that food they ordered
    public String[] foodType  = new String[20];
    public int[] amountOfFood = new int[20];
    int counter = 0;
    public openTab()
    {        
    }

    public void addFoodItems(String foodItem, int howManyFood)
    {
        foodType[counter] = foodItem;
        amountOfFood[counter] = howManyFood;
        counter++;
    }

    public void closeTab()
    {
        //reseting the tab on the table, might want to add the values to the stock list
        
        
        foodType = null;
        amountOfFood = null;
        counter = 0;
    }
    public String toString()
    {
        return Arrays.toString(foodType) + " " + Arrays.toString(amountOfFood);
    }
}