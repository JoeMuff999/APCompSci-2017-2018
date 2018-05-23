import java.util.ArrayList;
public class Pawn extends ChessPiece
{
    public Pawn(boolean side, Point p)
    {
        setSide(side);
        setName("pawn");
        setPosition(p);
    }

    public ArrayList<Point> move(Point[][] whereTo)
    {
        possibleMoves.clear();
        //depending on side:
        //can either move up/down one space, up/down two spaces,
        //or diagonally 'forward' when attacking
        //attacking to be handled separately
        if(darkSide) //black on top or bottom
        {
            if(myPosition.getY() < 7) //dark pieces start at top of board facing down
            {
                if(whereTo[myPosition.getX()][myPosition.getY() + 1].getPiece() == null)
                { //is anything directly in front of me?
                    possibleMoves.add(new Point(myPosition.getX(),myPosition.getY() + 1));
                }
            }
            if(myPosition.getY() == 1)
            { //am I in my starting position
                if(whereTo[myPosition.getX()][myPosition.getY() + 2].getPiece() == null)
                { //is anything in front of me
                    possibleMoves.add(new Point(myPosition.getX(), myPosition.getY() + 2));
                }
            }
            if(myPosition.getX() > 0 && myPosition.getY() < 7)
            { //getting ready to move diagonally
                if(whereTo[myPosition.getX() - 1][myPosition.getY() + 1].getPiece() != null)
                { //is there a piece at my diagonal? if not I can't move there
                    if(whereTo[myPosition.getX() - 1][myPosition.getY() + 1].getPiece().darkSide != darkSide)
                    { //there's a piece there! is it on my own team? if not I shouldn't attack
                        possibleMoves.add(new Point(myPosition.getX() - 1, myPosition.getY() + 1));
                    }
                }
            }
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
        }
        else //same thing other side
        {
            if(myPosition.getY() > 0) //dark pieces start at top of board facing down
            {
                if(whereTo[myPosition.getX()][myPosition.getY() - 1].getPiece() == null)
                {
                    possibleMoves.add(new Point(myPosition.getX(),myPosition.getY() - 1));
                }
            }
            if(myPosition.getY() == 6)
            {
                if(whereTo[myPosition.getX()][myPosition.getY() - 2].getPiece() == null)
                {
                    possibleMoves.add(new Point(myPosition.getX(), myPosition.getY() - 2));
                }
            }
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
        }
        return possibleMoves;
    }

}
