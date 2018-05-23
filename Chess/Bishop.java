import java.util.ArrayList;
public class Bishop extends ChessPiece
{
    
    public Bishop(boolean side, Point p)
    {
        setSide(side);
        setName("bishop");
        setPosition(p);
    }

    public ArrayList<Point> move(Point[][] whereTo)
    {
        //right down
        int y = myPosition.getY()+1;
        for(int x = myPosition.getX()+1; x<=7 &&y<=7; x++, y++)
        {
            if(whereTo[x][y].getPiece() != null)
            {
                if(whereTo[x][y].getPiece().darkSide == darkSide)
                {
                    break;
                }
                else
                {
                    possibleMoves.add(new Point(x, y));
                    break;
                }
            }
            else
            {
                possibleMoves.add(new Point(x, y));
            }
        }
        y = myPosition.getY()-1;
        //right up
        for(int x = myPosition.getX()+1; x<=7&&y >= 0; x++,y--)
        {

            if(whereTo[x][y].getPiece() != null)
            {
                if(whereTo[x][y].getPiece().darkSide == darkSide)
                {
                    break;
                }
                else
                {
                    possibleMoves.add(new Point(x, y));
                    break;
                }
            }
            else
            {
                possibleMoves.add(new Point(x, y));
            }

        }
        y = myPosition.getY() +1;
        //left down
        for(int x = myPosition.getX()-1; x>=0 && y <= 7; x--,y++)
        {

            if(whereTo[x][y].getPiece() != null)
            {
                if(whereTo[x][y].getPiece().darkSide == darkSide)
                {
                    break;
                }
                else
                {
                    possibleMoves.add(new Point(x, y));
                    break;
                }
            }
            else
            {
                possibleMoves.add(new Point(x,y));
            }

        }
        y = myPosition.getY()-1;
        //left up
        for(int x = myPosition.getX()-1; x>=0 && y>=0; x--,y--)
        {

            if(whereTo[x][y].getPiece() != null)
            {
                if(whereTo[x][y].getPiece().darkSide == darkSide)
                {
                    break;
                }
                else
                {
                    possibleMoves.add(new Point(x, y));
                    break;
                }
            }
            else
            {
                possibleMoves.add(new Point(x, y));

            }
        }
        return possibleMoves;
        
   }
}
