import java.util.ArrayList;
public class Knight extends ChessPiece
{
    ArrayList<int[]> listOfMoves = new ArrayList<>();
    public Knight(boolean side, Point p)
    {
        setSide(side);
        setName("knight");
        setPosition(p);
    }
    
    public void setVectors()
    {
        
        
        int[] downRight = {1,2};
        int[] downLeft = {-1,2};
        int[] upRight = {1,-2};
        int[] upLeft = {-1,-2};
        listOfMoves.add(downRight);
        listOfMoves.add(downLeft);
        listOfMoves.add(upRight);
        listOfMoves.add(upLeft);
        
        
        int[] downSlideRight = {2,1};
        int[] downSlideLeft = {-2,1};
        int[] upSlideRight = {2,-1};
        int[] upSlideLeft = {-2,-1};
        listOfMoves.add(downSlideRight);
        listOfMoves.add(downSlideLeft);
        listOfMoves.add(upSlideRight);
        listOfMoves.add(upSlideLeft);
    }

    public ArrayList<Point> move(Point[][] whereTo)
    {
        setVectors();
        possibleMoves.clear();
        int originalX = myPosition.getX();
        int originalY = myPosition.getY();
        for(int[]z : listOfMoves)
        {
            int newX = z[0] + originalX;
            int newY = z[1] + originalY;
            if(newX < 8 && newX >0 && newY >0 && newY <8)
            {
                if(whereTo[newX][newY].getPiece() == null || whereTo[newX][newY].getPiece().darkSide != darkSide)
                {
                     possibleMoves.add(new Point(newX, newY));
                }
            }
        }

        
        
        return possibleMoves;
    }
}
