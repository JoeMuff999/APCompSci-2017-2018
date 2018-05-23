import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
public class Board extends JFrame implements WindowListener,ActionListener
{

    //setting the icons for pieces
    //naming system PieceName.PieceColor.TileColor, white knight on black = knightWB
    //rooks
    ImageIcon BrookW = new ImageIcon("BrookW.png");
    ImageIcon WrookW = new ImageIcon("WrookW.png");
    ImageIcon WrookB = new ImageIcon("WrookB.png");
    ImageIcon BrookB = new ImageIcon("BrookB.png");
    //pawns
    ImageIcon BpawnW = new ImageIcon("BpawnW.png");
    ImageIcon WpawnW = new ImageIcon("WpawnW.png");
    ImageIcon WpawnB = new ImageIcon("WpawnB.png");
    ImageIcon BpawnB = new ImageIcon("BpawnB.png");
    //queens
    ImageIcon BqueenW = new ImageIcon("BqueenW.png");
    ImageIcon WqueenW = new ImageIcon("WqueenW.png");
    ImageIcon WqueenB = new ImageIcon("WqueenB.png");
    ImageIcon BqueenB = new ImageIcon("BqueenB.png");
    //kings
    ImageIcon BkingW = new ImageIcon("BkingW.png");
    ImageIcon WkingW = new ImageIcon("WkingW.png");
    ImageIcon WkingB = new ImageIcon("WkingB.png");
    ImageIcon BkingB = new ImageIcon("BkingB.png");
    //bishops
    ImageIcon BbishopW = new ImageIcon("BbishopW.png");
    ImageIcon WbishopW = new ImageIcon("WbishopW.png");
    ImageIcon WbishopB = new ImageIcon("WbishopB.png");
    ImageIcon BbishopB = new ImageIcon("BbishopB.png");
    //knights
    ImageIcon BknightW = new ImageIcon("BknightW.png");
    ImageIcon WknightW = new ImageIcon("WknightW.png");
    ImageIcon WknightB = new ImageIcon("WknightB.png");
    ImageIcon BknightB = new ImageIcon("BknightB.png");

    ImageIcon black = new ImageIcon("black.png");
    ImageIcon white = new ImageIcon("white.png");

    ImageIcon possibleMoveBlack = new ImageIcon("blackP.png");
    ImageIcon possibleMoveWhite = new ImageIcon("whiteP.png");

    int originalX;
    int originalY;

    boolean gameover = false;

    ArrayList<Point> possibleMoves;
    //Point pointInfo = new Point();
    String whoTurn;
    Boolean alternate;

    Point[][]  spacesInfo;
    JToggleButton[][] spaces;
    JButton newGame;
    JButton turnColor;

    Panel north;
    int counter = 0;
    public static void main (String []args)
    {
        new Board();
    }

    public Board()
    {
        setTheBoard();
    }  

    public void setTheBoard()
    {
        //y = up and down direction
        //x = left and right direction board is opposite so just watch up for that.
        /*for Rook, x moves along the columns = x x1 x2 x3 x4
         *  y counts the rows                  y
         *                                     y1
         *                                     y2
         *                                     y3
         *                                     y4 
         */
        whoTurn = "white";
        alternate = false;

        Panel main = new Panel(new BorderLayout());
        Panel grid = new Panel(new GridLayout(8,8));
        spaces = new JToggleButton[8][8];
        spacesInfo = new Point[8][8];
        setPieces();
        //setting colors;
        for(int x = 0; x < 8; x++)
        {
            for( int y = 0; y < 8; y++)
            { 
                spaces[x][y] = new JToggleButton();

                if(x%2 == y%2)
                {//black squares
                    spaces[x][y].setIcon(black);
                    //setpawns 
                    if(x == 6)
                    {
                        spaces[x][y].setIcon(WpawnB);                                                       
                    }
                    if(x ==1)
                    {
                        spaces[x][y].setIcon(BpawnB);  
                    }
                    else if (x == 0)
                    {
                        if(y == 0)
                        {
                            spaces[x][y].setIcon(BrookB);  

                        }
                        if(y == 2)
                        {
                            spaces[x][y].setIcon(BbishopB);  
                        }if(y == 4)
                        {
                            spaces[x][y].setIcon(BqueenB);  
                        }
                        if(y == 6)
                        {
                            spaces[x][y].setIcon(BknightB);  
                        }

                    }
                    else if (x == 7)
                    {
                        if(y == 1)
                        {
                            spaces[x][y].setIcon(WknightB);  
                        }
                        if(y == 3)
                        {
                            spaces[x][y].setIcon(WkingB);  
                        }
                        if(y == 5)
                        {
                            spaces[x][y].setIcon(WbishopB);  
                        }
                        if(y == 7)
                        {
                            spaces[x][y].setIcon(WrookB);  
                        }
                    }

                }
                else
                {

                    spaces[x][y].setIcon(white);
                    //set pawns, white on bottom
                    if(x == 6)
                    {
                        spaces[x][y].setIcon(WpawnW);                                                       
                    }
                    if(x ==1)
                    {
                        spaces[x][y].setIcon(BpawnW);  
                    }
                    else if (x == 7)
                    {
                        if(y == 0)
                        {
                            spaces[x][y].setIcon(WrookW);  
                        }
                        if(y == 2)
                        {
                            spaces[x][y].setIcon(WbishopW);  
                        }if(y == 4)
                        {
                            spaces[x][y].setIcon(WqueenW);  
                        }
                        if(y == 6)
                        {
                            spaces[x][y].setIcon(WknightW);  
                        }

                    }
                    else if (x == 0)
                    {
                        if(y == 1)
                        {
                            spaces[x][y].setIcon(BknightW);  
                        }
                        if(y == 3)
                        {
                            spaces[x][y].setIcon(BkingW);  
                        }
                        if(y == 5)
                        {
                            spaces[x][y].setIcon(BbishopW);  
                        }
                        if(y == 7)
                        {
                            spaces[x][y].setIcon(BrookW);  
                        }
                    }
                }
                spaces[x][y].addActionListener(this);

                grid.add(spaces[x][y]);
            }
        }
        Panel north = new Panel(new BorderLayout());

        newGame = new JButton("New Game");
        north.add(newGame,BorderLayout.CENTER);
        turnColor = new JButton();
        turnColor.setIcon(new ImageIcon("whiteLOL.png"));
        //turnColor.setEnabled(false);
        turnColor.setText("WHITE'S TURN");

        north.setPreferredSize(new Dimension(800,20));

        north.add(turnColor,BorderLayout.EAST);

        main.add(north,BorderLayout.NORTH);

        newGame.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e)
                {

                    new Board();

                }
            });

        main.add(grid,BorderLayout.CENTER);        
        setLayout(new BorderLayout());
        add(main);
        setVisible(true);
        setSize(800,800);
        setTitle("Chess");

    }

    public void setPieces()
    {

        for(int x = 0; x < 8; x++)
        {
            for(int y = 0; y < 8; y++)
            {
                spacesInfo[x][y] = new Point(x,y,null);
            }
        }

        Pawn blackPawn0 = new Pawn(true, spacesInfo[0][1]);
        Pawn blackPawn1 = new Pawn(true, spacesInfo[1][1]);
        Pawn blackPawn2 = new Pawn(true, spacesInfo[2][1]);
        Pawn blackPawn3 = new Pawn(true, spacesInfo[3][1]);
        Pawn blackPawn4 = new Pawn(true, spacesInfo[4][1]);
        Pawn blackPawn5 = new Pawn(true, spacesInfo[5][1]);
        Pawn blackPawn6 = new Pawn(true, spacesInfo[6][1]);
        Pawn blackPawn7 = new Pawn(true, spacesInfo[7][1]);       

        spacesInfo[0][1] = new Point(0,1,blackPawn0);
        spacesInfo[1][1] = new Point(1,1,blackPawn1);
        spacesInfo[2][1] = new Point(2,1,blackPawn2);
        spacesInfo[3][1] = new Point(3,1,blackPawn3);
        spacesInfo[4][1] = new Point(4,1,blackPawn4);
        spacesInfo[5][1] = new Point(5,1,blackPawn5);
        spacesInfo[6][1] = new Point(6,1,blackPawn6);
        spacesInfo[7][1] = new Point(7,1,blackPawn7);

        Pawn whitePawn0 = new Pawn(false, spacesInfo[0][6]);
        Pawn whitePawn1 = new Pawn(false, spacesInfo[1][6]);
        Pawn whitePawn2 = new Pawn(false, spacesInfo[2][6]);
        Pawn whitePawn3 = new Pawn(false, spacesInfo[3][6]);
        Pawn whitePawn4 = new Pawn(false, spacesInfo[4][6]);
        Pawn whitePawn5 = new Pawn(false, spacesInfo[5][6]);
        Pawn whitePawn6 = new Pawn(false, spacesInfo[6][6]);
        Pawn whitePawn7 = new Pawn(false, spacesInfo[7][6]); 

        spacesInfo[0][6] = new Point(0,6,whitePawn0);
        spacesInfo[1][6] = new Point(1,6,whitePawn1);
        spacesInfo[2][6] = new Point(2,6,whitePawn2);
        spacesInfo[3][6] = new Point(3,6,whitePawn3);
        spacesInfo[4][6] = new Point(4,6,whitePawn4);
        spacesInfo[5][6] = new Point(5,6,whitePawn5);
        spacesInfo[6][6] = new Point(6,6,whitePawn6);
        spacesInfo[7][6] = new Point(7,6,whitePawn7);

        Rook blackRook1 = new Rook(true, spacesInfo[0][0]);
        Rook blackRook2 = new Rook(true, spacesInfo[7][0]);
        Rook whiteRook1 = new Rook(false, spacesInfo[0][7]);
        Rook whiteRook2 = new Rook(false, spacesInfo[7][7]);

        spacesInfo[0][0] = new Point(0,0,blackRook1);
        spacesInfo[7][0] = new Point(7,0,blackRook2);
        spacesInfo[0][7] = new Point(0,7,whiteRook1);
        spacesInfo[7][7] = new Point(7,7,whiteRook2);

        Knight blackKnight1 = new Knight(true, spacesInfo[1][0]);
        Knight blackKnight2 = new Knight(true, spacesInfo[6][0]);
        Knight whiteKnight1 = new Knight(false, spacesInfo[1][7]);
        Knight whiteKnight2 = new Knight(false, spacesInfo[6][7]);

        spacesInfo[1][0] = new Point(1,0,blackKnight1);
        spacesInfo[6][0] = new Point(6,0,blackKnight2);
        spacesInfo[1][7] = new Point(1,7,whiteKnight1);
        spacesInfo[6][7] = new Point(6,7,whiteKnight2);

        Bishop blackBishop1 = new Bishop(true, spacesInfo[2][0]);
        Bishop blackBishop2 = new Bishop(true, spacesInfo[5][0]);
        Bishop whiteBishop1 = new Bishop(false, spacesInfo[2][7]);
        Bishop whiteBishop2 = new Bishop(false, spacesInfo[5][7]);

        spacesInfo[2][0] = new Point(2,0,blackBishop1);
        spacesInfo[5][0] = new Point(5,0,blackBishop2);
        spacesInfo[2][7] = new Point(2,7,whiteBishop1);
        spacesInfo[5][7] = new Point(5,7,whiteBishop2);

        Queen blackQueen = new Queen(true, spacesInfo[4][0]);
        Queen whiteQueen = new Queen(false, spacesInfo[4][7]);

        spacesInfo[4][0] = new Point(4,0,blackQueen);
        spacesInfo[4][7] = new Point(4,7,whiteQueen);

        King blackKing = new King(true, spacesInfo[3][0]);
        King whiteKing = new King(false, spacesInfo[3][7]);

        spacesInfo[3][0] = new Point(3,0,blackKing);
        spacesInfo[3][7] = new Point(3,7,whiteKing);

    }

    public void setSpaces()
    {

    }

    public void getPiece()
    {
        //detect the selected piece and get its info

        for(int x = 0; x < 8; x++)
        {
            for(int y = 0; y < 8; y++)
            {

                if(spaces[x][y].isSelected())
                {
                    System.out.println(spacesInfo[y][x].getPiece());
                    if(spacesInfo[y][x].getPiece() != null)
                    {
                        if(whoTurn.equals(spacesInfo[y][x].getPiece().toString().substring(0,5)))
                        {
                            //System.out.println(x + "," +y);
                            //possibleMoves = spacesInfo[x][y].getPiece().move(spacesInfo);
                            possibleMoves = spacesInfo[y][x].getPiece().move(spacesInfo);
                            originalX = x;
                            originalY = y;
                            System.out.println(spacesInfo[y][x].getPiece());
                            if(spacesInfo[y][x].getPiece().toString().substring(0,5).equals("white"))
                            {
                                whoTurn = "black";

                            }
                            else if(spacesInfo[y][x].getPiece().toString().substring(0,5).equals("black"))
                            {
                                whoTurn = "white";

                            }
                            for(Point z: possibleMoves)
                            {

                                //black spaces
                                //System.out.println(z.getX() + "," +z.getY());
                                if(z.getX() %2 == z.getY()%2)
                                {
                                    spaces[z.getY()][z.getX()].setIcon(possibleMoveBlack);
                                }
                                else
                                {
                                    spaces[z.getY()][z.getX()].setIcon(possibleMoveWhite);
                                }

                            }

                        }
                        else
                        {
                            spaces[x][y].setSelected(false);
                            getPiece();
                        }

                    }
                    spaces[x][y].setSelected(false);
                }
            }
        }   

        for(int x = 0; x < 8; x++)
        {
            for(int y = 0; y < 8; y++)
            {
                if(spaces[x][y].getIcon() != possibleMoveBlack && spaces[x][y].getIcon() != possibleMoveWhite)
                {
                    counter++;
                }
            }
        }
        //System.out.println(counter);
        if(counter == 64)
        {
            //System.out.println("test");
            if(whoTurn.equals("white"))
            {
                whoTurn = "black";

            }
            else
            {
                whoTurn = "white";

            }

        }
        else
        {
            for(int z = 0; z < 8; z++)
            {
                for(int a = 0; a < 8; a++)
                {
                    if(spaces[z][a].getIcon() != possibleMoveBlack && spaces[z][a].getIcon() != possibleMoveWhite)
                    {
                        spaces[z][a].setEnabled(false); 
                    }
                }
            }
            alternate = true;
        }

        counter = 0;

    }

    public void movePiece()
    {

        for(int x = 0; x < 8; x++)
        {
            for(int y = 0; y < 8; y++)
            {
                if(spaces[x][y].isSelected())
                {  
                    if(spaces[x][y].getIcon() == possibleMoveBlack || spaces[x][y].getIcon() == possibleMoveWhite)
                    {
                        //get the original point's information
                        setPieceImage(originalX, originalY, x,y);
                        if(whoTurn.equals("black") &&alternate)
                        {
                            turnColor.setIcon(new ImageIcon("blackLOL.png"));

                            turnColor.setText("BLACK'S TURN");            

                        }
                        else if(alternate && whoTurn.equals("white"))
                        {

                            turnColor.setIcon(new ImageIcon("whiteLOL.png"));
                            turnColor.setText("WHITE'S TURN");
                        }
                        alternate = false;
                        //System.out.println("test");

                    }

                }

                spaces[x][y].setSelected(false);
            }
        }
        for(int x = 0; x < 8; x++)
        {
            for(int y = 0; y < 8; y++)
            {
                spaces[x][y].setEnabled(true);
                if(spaces[x][y].getIcon() == possibleMoveBlack || spaces[x][y].getIcon() == possibleMoveWhite)
                {                    
                    restoreImages(y,x);    
                    //System.out.println("test2");      

                }

            }
        }

    }

    public void setPieceImage(int originalx, int originaly, int newX, int newY)
    {
        int x = originalx;
        int y = originaly;
        String originalPieceName = spacesInfo[y][x].getPiece().toString();
        ChessPiece originalPiece = spacesInfo[y][x].getPiece();

        if(originalPieceName.substring(0,5).equals("black"))
        {

            if(newX%2 == newY%2)
            {//black spaces with black pieces
                if(originalPiece.pieceName.equals("pawn"))
                {
                    spaces[newX][newY].setIcon(BpawnB);
                    clearOriginalSpace(x,y,newX,newY,originalPiece);
                }
                if(originalPiece.pieceName.equals("rook"))
                {
                    spaces[newX][newY].setIcon(BrookB);
                    clearOriginalSpace(x,y,newX,newY,originalPiece);
                }
                if(originalPiece.pieceName.equals("bishop"))
                {
                    spaces[newX][newY].setIcon(BbishopB);
                    clearOriginalSpace(x,y,newX,newY,originalPiece);
                }
                if(originalPiece.pieceName.equals("queen"))
                {
                    spaces[newX][newY].setIcon(BqueenB);
                    clearOriginalSpace(x,y,newX,newY,originalPiece);
                }
                if(originalPiece.pieceName.equals("king"))
                {
                    spaces[newX][newY].setIcon(BkingB);
                    clearOriginalSpace(x,y,newX,newY,originalPiece);
                }
                if(originalPiece.pieceName.equals("knight"))
                {
                    spaces[newX][newY].setIcon(BknightB);
                    clearOriginalSpace(x,y,newX,newY,originalPiece);
                }
            }
            else
            {//black pieces on white squares
                if(originalPiece.pieceName.equals("pawn"))
                {
                    spaces[newX][newY].setIcon(BpawnW);
                    clearOriginalSpace(x,y,newX,newY,originalPiece);
                }
                if(originalPiece.pieceName.equals("rook"))
                {
                    spaces[newX][newY].setIcon(BrookW);
                    clearOriginalSpace(x,y,newX,newY,originalPiece);
                }
                if(originalPiece.pieceName.equals("bishop"))
                {
                    spaces[newX][newY].setIcon(BbishopW);
                    clearOriginalSpace(x,y,newX,newY,originalPiece);
                }
                if(originalPiece.pieceName.equals("queen"))
                {
                    spaces[newX][newY].setIcon(BqueenW);
                    clearOriginalSpace(x,y,newX,newY,originalPiece);
                }
                if(originalPiece.pieceName.equals("king"))
                {
                    spaces[newX][newY].setIcon(BkingW);
                    clearOriginalSpace(x,y,newX,newY,originalPiece);
                }
                if(originalPiece.pieceName.equals("knight"))
                {
                    spaces[newX][newY].setIcon(BknightW);
                    clearOriginalSpace(x,y,newX,newY,originalPiece);
                }
            }
        }
        else if(originalPieceName.substring(0,5).equals("white"))
        {
            if(newX%2 == newY%2)
            {//white spaces with black pieces
                if(originalPiece.pieceName.equals("pawn"))
                {
                    spaces[newX][newY].setIcon(WpawnB);
                    clearOriginalSpace(x,y,newX,newY,originalPiece);
                }
                if(originalPiece.pieceName.equals("rook"))
                {
                    spaces[newX][newY].setIcon(WrookB);
                    clearOriginalSpace(x,y,newX,newY,originalPiece);
                }
                if(originalPiece.pieceName.equals("bishop"))
                {
                    spaces[newX][newY].setIcon(WbishopB);
                    clearOriginalSpace(x,y,newX,newY,originalPiece);
                }
                if(originalPiece.pieceName.equals("queen"))
                {
                    spaces[newX][newY].setIcon(WqueenB);
                    clearOriginalSpace(x,y,newX,newY,originalPiece);
                }
                if(originalPiece.pieceName.equals("king"))
                {
                    spaces[newX][newY].setIcon(WkingB);
                    clearOriginalSpace(x,y,newX,newY,originalPiece);
                }
                if(originalPiece.pieceName.equals("knight"))
                {
                    spaces[newX][newY].setIcon(WknightB);
                    clearOriginalSpace(x,y,newX,newY,originalPiece);
                }
            }
            else
            {//black pieces on white squares
                if(originalPiece.pieceName.equals("pawn"))
                {
                    spaces[newX][newY].setIcon(WpawnW);
                    clearOriginalSpace(x,y,newX,newY,originalPiece);
                }
                if(originalPiece.pieceName.equals("rook"))
                {
                    spaces[newX][newY].setIcon(WrookW);
                    clearOriginalSpace(x,y,newX,newY,originalPiece);
                }
                if(originalPiece.pieceName.equals("bishop"))
                {
                    spaces[newX][newY].setIcon(WbishopW);
                    clearOriginalSpace(x,y,newX,newY,originalPiece);
                }
                if(originalPiece.pieceName.equals("queen"))
                {
                    spaces[newX][newY].setIcon(WqueenW);
                    clearOriginalSpace(x,y,newX,newY,originalPiece);
                }
                if(originalPiece.pieceName.equals("king"))
                {
                    spaces[newX][newY].setIcon(WkingW);
                    clearOriginalSpace(x,y,newX,newY,originalPiece);
                }
                if(originalPiece.pieceName.equals("knight"))
                {
                    spaces[newX][newY].setIcon(WknightW);
                    clearOriginalSpace(x,y,newX,newY,originalPiece);
                }
            }

        }
    }

    public void restoreImages(int newX, int newY)
    {
        //not actually newX or newY im just lazy

        String originalPieceName;
        int y = newY;
        int x = newX;
        ChessPiece originalPiece = spacesInfo[x][y].getPiece();
        if(originalPiece != null)
        {
            // System.out.println(originalPiece);

            originalPieceName = spacesInfo[x][y].getPiece().toString();
            if(originalPieceName.substring(0,5).equals("black"))
            {

                if(newX%2 == newY%2)
                {//black spaces with black pieces
                    System.out.println("testxd");
                    if(originalPiece.pieceName.equals("Pawn"))
                    {
                        spaces[newY][newX].setIcon(BpawnB);

                    }
                    if(originalPiece.pieceName.equals("rook"))
                    {
                        spaces[newY][newX].setIcon(BrookB);

                    }
                    if(originalPiece.pieceName.equals("bishop"))
                    {
                        spaces[newY][newX].setIcon(BbishopB);

                    }
                    if(originalPiece.pieceName.equals("queen"))
                    {
                        spaces[newY][newX].setIcon(BqueenB);

                    }
                    if(originalPiece.pieceName.equals("king"))
                    {
                        spaces[newY][newX].setIcon(BkingB);

                    }
                    if(originalPiece.pieceName.equals("knight"))
                    {
                        spaces[newY][newX].setIcon(BknightB);

                    }

                }
                else
                {//black pieces on white squares
                    if(originalPiece.pieceName.equals("pawn"))
                    {
                        spaces[newY][newX].setIcon(BpawnW);

                    }
                    if(originalPiece.pieceName.equals("rook"))
                    {
                        spaces[newY][newX].setIcon(BrookW);

                    }
                    if(originalPiece.pieceName.equals("bishop"))
                    {
                        spaces[newY][newX].setIcon(BbishopW);

                    }
                    if(originalPiece.pieceName.equals("queen"))
                    {
                        spaces[newY][newX].setIcon(BqueenW);

                    }
                    if(originalPiece.pieceName.equals("king"))
                    {
                        spaces[newY][newX].setIcon(BkingW);

                    }
                    if(originalPiece.pieceName.equals("knight"))
                    {
                        spaces[newY][newX].setIcon(BknightW);

                    }

                }
            }
            else if(originalPieceName.substring(0,5).equals("white"))
            {
                if(newX%2 == newY%2)
                {//white spaces with black pieces
                    if(originalPiece.pieceName.equals("pawn"))
                    {
                        spaces[newY][newX].setIcon(WpawnB);

                    }
                    if(originalPiece.pieceName.equals("rook"))
                    {
                        spaces[newY][newX].setIcon(WrookB);

                    }
                    if(originalPiece.pieceName.equals("bishop"))
                    {
                        spaces[newY][newX].setIcon(WbishopB);

                    }
                    if(originalPiece.pieceName.equals("queen"))
                    {
                        spaces[newY][newX].setIcon(WqueenB);

                    }
                    if(originalPiece.pieceName.equals("king"))
                    {
                        spaces[newY][newX].setIcon(WkingB);

                    }
                    if(originalPiece.pieceName.equals("knight"))
                    {
                        spaces[newY][newX].setIcon(WknightB);

                    }

                }
                else
                {//black pieces on white squares
                    if(originalPiece.pieceName.equals("pawn"))
                    {
                        spaces[newY][newX].setIcon(WpawnW);

                    }
                    if(originalPiece.pieceName.equals("rook"))
                    {
                        spaces[newY][newX].setIcon(WrookW);

                    }
                    if(originalPiece.pieceName.equals("bishop"))
                    {
                        spaces[newY][newX].setIcon(WbishopW);

                    }
                    if(originalPiece.pieceName.equals("queen"))
                    {
                        spaces[newY][newX].setIcon(WqueenW);

                    }
                    if(originalPiece.pieceName.equals("king"))
                    {
                        spaces[newY][newX].setIcon(WkingW);

                    }
                    if(originalPiece.pieceName.equals("knight"))
                    {
                        spaces[newY][newX].setIcon(WknightW);

                    }

                }

            }
        }
        else
        {
            originalPieceName = null;
            if(newX%2 != newY %2)
            {
                spaces[newY][newX].setIcon(white);
                //System.out.println("testW");
            }
            else
            {
                spaces[newY][newX].setIcon(black); 

                //System.out.println(newX + "," + newY);
            }

        }
    }

    public void clearOriginalSpace(int x, int y,int newX, int newY, ChessPiece piece)
    {
        //clear original picture and set the spacesInfo
        if(x%2 == y%2)
        {
            spaces[x][y].setIcon(black);
        }
        else
        {
            spaces[x][y].setIcon(white);
        }
        //set old space to be null, new = original piece
        spacesInfo[y][x].getPiece().setPosition(spacesInfo[newY][newX]);
        spacesInfo[y][x] = new Point(y,x,null);
        spacesInfo[newY][newX] = new Point(newY,newX,piece);  

    }

    public void getPawn()
    {

    }

    public void     windowClosing(WindowEvent e)
    {
        System.exit(0);
    }

    @Override public void actionPerformed(ActionEvent e){
        //if alt = true, then its time to move the piece
        if(!alternate && !gameover)
        {
            getPiece();

        }
        else if(!gameover && alternate)
        {
            movePiece();
            gameover();
        }
    }

    public void changeColor()
    {

    }

    public void gameover()
    {
        int asdf = 0;
        for(int x = 0; x < 8; x++)
        {
            for( int y = 0; y < 8; y++)
            { 
                if(spaces[x][y].getIcon() == BkingW || spaces[x][y].getIcon() == BkingB|| spaces[x][y].getIcon() == WkingW || spaces[x][y].getIcon() == WkingB)
                {
                    asdf++;

                }
            }
        }
        if(asdf != 2)
        {
            gameover = true;
            newGame.setText("Game over, new game?");
        }
        asdf = 0;
    }

    public void     windowActivated(WindowEvent e){}

    public void     windowDeactivated(WindowEvent e){}

    public void     windowDeiconified(WindowEvent e){}

    public void     windowIconified(WindowEvent e){}

    public void     windowOpened(WindowEvent e){}

    public void     windowClosed(WindowEvent e){
    }
}
