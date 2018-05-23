import java.util.ArrayList;
public class Knight extends ChessPiece
{
    public Knight(boolean side, Point p)
    {
        setSide(side);
        setName("knight");
        setPosition(p);
    }

    public ArrayList<Point> move(Point[][] whereTo)
    {
        possibleMoves.clear();

        //down one, right two
        if(myPosition.getY() < 7 && myPosition.getX() < 6)
        {
            if(whereTo[myPosition.getX()+2][myPosition.getY() +1]==null)
            {
                possibleMoves.add(new Point(myPosition.getX()+2,myPosition.getY() +1));
            }
        }
        //down two, right one
        if(myPosition.getY() < 6 && myPosition.getX() < 7)
        {
            if(whereTo[myPosition.getX()+1][myPosition.getY() +2]==null)
            {
                possibleMoves.add(new Point(myPosition.getX()+1,myPosition.getY() +2));
            }
        }
        //up one, right two
        if(myPosition.getY() > 0 && myPosition.getX() < 6)
        {
            if(whereTo[myPosition.getX()+2][myPosition.getY() -1]==null)
            {
                possibleMoves.add(new Point(myPosition.getX() + 2,myPosition.getY() -1));
            }
        }
        //up two, right one
        if(myPosition.getY()>1 && myPosition.getX() <7)
        {
            if(whereTo[myPosition.getX()+1][myPosition.getY() -2]==null)
            {
                possibleMoves.add(new Point(myPosition.getX()+1,myPosition.getY()-2));
            }
        }
        //down one, left two
        if(myPosition.getY() < 7 && myPosition.getX() > 1)
        {
            if(whereTo[myPosition.getX()-2][myPosition.getY() +1]==null)
            {
                possibleMoves.add(new Point(myPosition.getX()-2,myPosition.getY() +1));
            }
        }
        //down two, left one
        if(myPosition.getY() < 6 && myPosition.getX() < 0)
        {
            if(whereTo[myPosition.getX()-1][myPosition.getY() +2]==null)
            {
                possibleMoves.add(new Point(myPosition.getX()-1,myPosition.getY() +2));
            }
        }
        //up one, left two
        if(myPosition.getY() >0 && myPosition.getX() >1)
        {
            if(whereTo[myPosition.getX()-2][myPosition.getY() +1]==null)
            {
                possibleMoves.add(new Point(myPosition.getX()-2,myPosition.getY() +1));
            }
        }
        //up two, left one
        if(myPosition.getY() > 1 && myPosition.getX() > 0)
        {
            if(whereTo[myPosition.getX()-1][myPosition.getY() +2]==null)
            {
                possibleMoves.add(new Point(myPosition.getX()-1,myPosition.getY() +2));
            }
        }
        return possibleMoves;
    }
}
