
public class Point
{
    private int x;
    private int y;
    
    private ChessPiece piece;
    
    public Point()
    {
        x = 0;
        y = 0;
        piece = null;
    }
    
    public Point(int _x, int _y)
    {
        x = _x;
        y = _y;
        piece = null;
    }
    
    public Point(int _x, int _y, ChessPiece _piece)
    {
        x = _x;
        y = _y;
        piece = _piece;
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public ChessPiece getPiece()
    {
        return piece;
    }
    
    public void setPiece(ChessPiece _piece)
    {
        piece = _piece;
    }
    
    public Point getPoint()
    {
        return new Point(x,y);
    }
    
    public boolean equals(Object other)
    {
        Point dummy = (Point) other;
        return x == dummy.getX() && y == dummy.getY() && piece == dummy.getPiece();
    }
    
    public String toString()
    {
        return x + ", " + y + ", " + piece;
    }
}
