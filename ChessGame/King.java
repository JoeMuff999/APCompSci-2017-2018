import java.util.ArrayList;
public class King extends ChessPiece
{
    public King(boolean side, Point p)
    {
        setSide(side);
        setName("king");
        setPosition(p);
    }

    public ArrayList<Point> move(Point[][] whereTo)
    {
        possibleMoves.clear();

        //down
        if(myPosition.getY() < 7) 
        {
            if(whereTo[myPosition.getX()][myPosition.getY() + 1].getPiece() == null)
            { 
                if(whereTo[myPosition.getX()][myPosition.getY() + 1].getPiece().darkSide !=darkSide)
                {
                    possibleMoves.add(new Point(myPosition.getX(),myPosition.getY() + 1));
                }
            }
        }
        //up
        if(myPosition.getY() > 0) 
        {
            if(whereTo[myPosition.getX()][myPosition.getY() - 1].getPiece() == null)
            { 
                if(whereTo[myPosition.getX()][myPosition.getY() - 1].getPiece().darkSide !=darkSide)
                {
                    possibleMoves.add(new Point(myPosition.getX(),myPosition.getY() - 1));
                }
            }
        }
        //right
        if(myPosition.getX() < 7) 
        {
            if(whereTo[myPosition.getX() + 1][myPosition.getY()].getPiece() == null)
            { 
                if(whereTo[myPosition.getX() + 1][myPosition.getY()].getPiece().darkSide !=darkSide)
                {
                    possibleMoves.add(new Point(myPosition.getX() + 1,myPosition.getY()));
                }
            }
        }
        //left
        if(myPosition.getX() > 0) 
        {
            if(whereTo[myPosition.getX() - 1][myPosition.getY()].getPiece() == null)
            { 
                if(whereTo[myPosition.getX() - 1][myPosition.getY()].getPiece().darkSide !=darkSide)
                {
                    possibleMoves.add(new Point(myPosition.getX() - 1,myPosition.getY()));
                }
            }
        }

        //left down
        if(myPosition.getX() > 0 && myPosition.getY() < 7)
        { 
            if(whereTo[myPosition.getX() - 1][myPosition.getY() + 1].getPiece() != null)
            { //is there a piece at my diagonal? if not I can't move there
                if(whereTo[myPosition.getX() - 1][myPosition.getY() + 1].getPiece().darkSide != darkSide)
                { //there's a piece there! is it on my own team? if not I shouldn't attack
                    possibleMoves.add(new Point(myPosition.getX() - 1, myPosition.getY() + 1));
                }
            }
        }
        //right down
        if(myPosition.getX() < 7 && myPosition.getY() < 7)
        { //same thing other direction
            if(whereTo[myPosition.getX() + 1][myPosition.getY() + 1].getPiece() != null)
            {
                if(whereTo[myPosition.getX() + 1][myPosition.getY() + 1].getPiece().darkSide != darkSide)
                {
                    possibleMoves.add(new Point(myPosition.getX() + 1, myPosition.getY() + 1));
                }
            }
        }
        //up left
        if(myPosition.getX() > 0 && myPosition.getY() > 0)
        {
            if(whereTo[myPosition.getX() - 1][myPosition.getY() - 1].getPiece() != null)
            {
                if(whereTo[myPosition.getX() - 1][myPosition.getY() - 1].getPiece().darkSide != darkSide)
                {
                    possibleMoves.add(new Point(myPosition.getX() - 1, myPosition.getY() - 1));
                }
            }
        }
        //up right
        if(myPosition.getX() < 7 && myPosition.getY() > 0)
        {
            if(whereTo[myPosition.getX() + 1][myPosition.getY() - 1].getPiece() != null)
            {
                if(whereTo[myPosition.getX() + 1][myPosition.getY() - 1].getPiece().darkSide != darkSide)
                {
                    possibleMoves.add(new Point(myPosition.getX() + 1, myPosition.getY() - 1));
                }
            }
        }

        return possibleMoves;
    }

}
