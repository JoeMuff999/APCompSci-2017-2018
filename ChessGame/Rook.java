import java.util.ArrayList;
public class Rook extends ChessPiece
{
    public Rook(boolean side, Point p)
    {
        setSide(side);
        setName("rook");
        setPosition(p);
    }

    public ArrayList<Point> move(Point[][] whereTo)
    {
        possibleMoves.clear();
        //independent of side
        //travels in a straight line 
        //can be blocked by pieces of own color
        //can be blocked by pieces of opposite color, but can take them
        for(int x = myPosition.getX()+1; x <= 7; x++)
        {
            if(whereTo[x][myPosition.getY()].getPiece() != null)
            {
                if(whereTo[x][myPosition.getY()].getPiece().darkSide == darkSide)
                {
                    break;
                }
                else
                {
                    possibleMoves.add(new Point(x, myPosition.getY()));
                    break;
                }
            }
            else
            {
                possibleMoves.add(new Point(x, myPosition.getY()));
            }            
        }
        for(int x = myPosition.getX()-1; x >= 0; x--)
        {
            if(whereTo[x][myPosition.getY()].getPiece() != null)
            {
                if(whereTo[x][myPosition.getY()].getPiece().darkSide == darkSide)
                {
                    break;
                }
                else
                {
                    possibleMoves.add(new Point(x, myPosition.getY()));
                    break;
                }
            }
            else
            {
                possibleMoves.add(new Point(x, myPosition.getY()));
            } 
        }
        for(int y = myPosition.getY()-1; y >= 0; y--)
        {
            if(whereTo[myPosition.getX()][y].getPiece() != null)
            {
                if(whereTo[myPosition.getX()][y].getPiece().darkSide == darkSide)
                {
                    break;
                }
                else
                {
                    possibleMoves.add(new Point(myPosition.getX(), y));
                    break;
                }
            }
            else
            {
                possibleMoves.add(new Point(myPosition.getX(), y));
            } 
        }
        for(int y = myPosition.getY()+1; y <= 7; y++)
        {
            if(whereTo[myPosition.getX()][y].getPiece() != null)
            {
                if(whereTo[myPosition.getX()][y].getPiece().darkSide == darkSide)
                {
                    break;
                }
                else
                {
                    possibleMoves.add(new Point(myPosition.getX(), y));
                    break;
                }
            }
            else
            {
                possibleMoves.add(new Point(myPosition.getX(), y));
            } 
        }
        return possibleMoves;
    }

}
