import java.util.ArrayList;
public abstract class ChessPiece
{
    public String pieceName;
    public boolean darkSide;
    public Point myPosition;
    
    protected ArrayList<Point> possibleMoves =
        new ArrayList<Point>();
        
    public abstract ArrayList<Point> move(Point[][] whereTo);
    
    public void setSide(boolean mySide)
    {
        darkSide = mySide;
    }
    
    public void setName(String myName)
    {
        pieceName = myName;
    }
    
    public void setPosition(Point myPos)
    {
        myPosition = myPos;
    }
    
    public void setPosition(int x, int y)
    {
        myPosition = new Point(x,y);
    }
    
    public String toString()
    {
        if(darkSide)
        {
            return "black " + pieceName;
        }
        else
        {
            return "white " + pieceName;
        }
    }
}
