import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
public class checkersLOL extends JFrame implements WindowListener,ActionListener
{
    private JToggleButton[][] spaces;
    private boolean[][] clicked;
    private Button newGame;
    private JTextField playersTurn;
    private boolean xOrO;
    private char[][] letters = new char[8][8];
    private boolean gameover = false;
    //  private boolean[][] validMove = new boolean[8][8];
    private char[][] color = new char[8][8];
    private boolean[][] selected = new boolean[8][8];
    private boolean moveExists = false;
    private boolean moved = false;
    private boolean[][] king = new boolean[8][8];

    private boolean dontRunTwicePlease = false;

    JToggleButton ai = new JToggleButton();
    JToggleButton player = new JToggleButton();

    private boolean aiHasMove = false;

    public boolean aiC = false;

    public boolean chosen = false;
    ImageIcon redblack = new ImageIcon("blackR.png");
    ImageIcon greyblack = new ImageIcon("blackG.png");
    ImageIcon black = new ImageIcon("black.png");
    ImageIcon white = new ImageIcon("white.png");
    ImageIcon redblackSelected = new ImageIcon("blackRS.png");
    ImageIcon greyblackSelected = new ImageIcon("blackGS.png");
    ImageIcon validMove = new ImageIcon("blackGreen.png");
    ImageIcon validJump = new ImageIcon("blackGreen.png");
    ImageIcon redblackK = new ImageIcon("blackRK.png");
    ImageIcon greyblackK = new ImageIcon("blackGK.png");
    ImageIcon redblackKS = new ImageIcon("blackRKS.png");
    ImageIcon greyblackKS = new ImageIcon("blackGKS.png");

    ArrayList<int[]> moveVectors = new ArrayList<int[]>(); 
    ArrayList<int[]> jumpVectors = new ArrayList<int[]>();

    private boolean alt = false;
    private String whoTurn;

    public static void main(String [] args)
    {
        new checkersLOL(false, false);
    }

    public void setVectors()
    {
        int[] topLeft = {-1,-1};
        int[] topRight = {-1,1};
        int[] bottomLeft = {1,-1};
        int[] bottomRight = {1,1};

        // ArrayList<int[]> moveVectors = new ArrayList<int[]>();        
        moveVectors.add(topLeft);
        moveVectors.add(topRight);
        moveVectors.add(bottomLeft);
        moveVectors.add(bottomRight);

        int[] topLeftJump = {-2,-2};
        int[] topRightJump = {-2,2};
        int[] bottomLeftJump = {2,-2};
        int[] bottomRightJump = {2,2};       

        // ArrayList<int[]> jumpVectors = new ArrayList<int[]>();
        jumpVectors.add(topLeftJump);
        jumpVectors.add(topRightJump);
        jumpVectors.add(bottomLeftJump);
        jumpVectors.add(bottomRightJump);
    }

    public checkersLOL(boolean _chosen, boolean aiA)
    {
        chosen = _chosen;
        aiC = aiA;

        if(chosen)
        {
            whoTurn = "red";
            xOrO = true;
            clicked = new boolean[8][8];
            spaces = new JToggleButton[8][8];
            Panel main = new Panel(new BorderLayout());
            setVectors();

            for(int x = 0; x< clicked.length; x++){
                for( int y = 0; y <clicked[0].length; y++)
                {
                    clicked[x][y] = false;
                    king[x][y] = false;
                    //z validMove[x][y] = false;

                }
            }

            playersTurn = new JTextField("Red Player, select a piece");
            playersTurn.setEditable(false);
            Dimension lo = new Dimension(500,50);
            playersTurn.setSize(lo);
            Dimension lol = new Dimension(150,150);

            main.add(playersTurn,BorderLayout.CENTER);
            newGame = new Button("New Game");
            main.add(newGame,BorderLayout.EAST);

            newGame.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {

                        new checkersLOL(false,false);

                    }
                });

            Panel grid = new Panel(new GridLayout(8,8));
            for(int x = 0; x < spaces.length; x++)
            {

                for (int y =0; y< spaces[0].length; y++)
                {
                    spaces[x][y] = new JToggleButton();
                    setBoard();
                    if(x%2== y%2 )
                    {
                        spaces[x][y].setIcon(white);
                        color[x][y] = 'w';

                    }

                    else
                    {
                        spaces[x][y].setIcon(black);
                        color[x][y] = 'b';
                    }
                    // spaces[x][y].setSize(lol);

                    grid.add(spaces[x][y]);
                    spaces[x][y].addActionListener(this);
                }
            }    

            setLayout(new BorderLayout());

            add(grid,BorderLayout.CENTER);
            add(main,BorderLayout.NORTH);

            setSize(900,900);
            setVisible(true);
            setTitle("Tic Tac Toe");
        }
        else
        {
            Panel aiOrNot = new Panel(new BorderLayout());

            Panel gridButtons = new Panel(new GridLayout(1,2));

            ai.addActionListener(this);
            player.addActionListener(this);

            ai.setText("AI");
            player.setText("ANOTHER PLAYER");

            gridButtons.add(ai);
            gridButtons.add(player);

            aiOrNot.add(gridButtons);

            add(aiOrNot);

            setSize(300,100);
            setVisible(true);
            setTitle("Player or AI?");

            ai.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {

                        new checkersLOL(true,true);
                        setVisible(false);

                    }

                });

            player.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {

                        new checkersLOL(true,false);
                        setVisible(false);

                    }
                });

        }
    }

    public void setBoard()
    {
        //set red side 
        for(int x = 5; x < spaces.length; x++)
        {
            for (int y =0; y< spaces[0].length; y++)
            {
                if(color[x][y] == 'b')
                {
                    spaces[x][y].setIcon(redblack);
                    // clicked[x][y] = true;

                }
            }
        }
        for(int x = 0; x < 3; x++)
        {
            for (int y =0; y< spaces[0].length; y++)
            {
                if(color[x][y] == 'b')
                {
                    spaces[x][y].setIcon(greyblack);
                    // clicked[x][y] = true;

                }
            }
        }
    }

    public void checkAround(int x, int y, String whoTurn)
    {
        int posX = x;
        int posY = y;
        String turn = whoTurn;
        for(int[] z: moveVectors)
        {
            //xAdd = x vector to add, yAdd = y vector to add to selected point
            //xPosAfterMove is the row number after they move
            int xAdd = z[0];
            int yAdd = z[1];
            int xPosAfterMove = posX + xAdd; 
            int yPosAfterMove = posY +yAdd;

            if(xPosAfterMove >= 0 && xPosAfterMove <= 7 && yPosAfterMove>= 0 && yPosAfterMove <= 7)
            {
                if(king[x][y])
                {
                    if(spaces[xPosAfterMove][yPosAfterMove].getIcon() == black)
                    {                       
                        spaces[xPosAfterMove][yPosAfterMove].setIcon(validMove);
                    }
                }
                else{
                    //System.out.println(xPosAfterMove + "," + yPosAfterMove);
                    if(spaces[xPosAfterMove][yPosAfterMove].getIcon() == black )
                    {
                        if(turn.equals("red"))
                        {
                            if(xPosAfterMove < posX)
                            {
                                spaces[xPosAfterMove][yPosAfterMove].setIcon(validMove);
                            }
                        }
                        else
                        {
                            if(xPosAfterMove > posX)
                            {
                                spaces[xPosAfterMove][yPosAfterMove].setIcon(validMove);
                            }

                        }
                    }
                }
            }

        }

        for(int[] z : jumpVectors)
        {
            int xAdd = z[0];
            int yAdd = z[1];
            int xPosAfterJump = posX + xAdd;
            int yPosAfterJump = posY +yAdd;

            if(xPosAfterJump  >= 0 && xPosAfterJump  <= 7 && yPosAfterJump >= 0 && yPosAfterJump  <= 7)
            {
                //System.out.println(xPosAfterJump  + "," + yPosAfterJump );
                if(king[x][y])
                {
                    if(spaces[xPosAfterJump ][yPosAfterJump].getIcon() == black)
                    {
                        if(turn.equals("red"))
                        {
                            jump(posX,posY,xPosAfterJump, yPosAfterJump,greyblack,greyblackK,false,true);
                        }
                        else

                        {
                            jump(posX,posY,xPosAfterJump, yPosAfterJump,redblack,redblackK,false,true);
                        }
                    }
                }
                else
                {
                    if(spaces[xPosAfterJump ][yPosAfterJump].getIcon() == black)
                    {
                        if(turn.equals("red"))
                        {
                            jump(posX,posY,xPosAfterJump, yPosAfterJump,greyblack,greyblackK,false,false);

                        }
                        else
                        {
                            jump(posX,posY,xPosAfterJump, yPosAfterJump,redblack,redblackK,true,false);

                        }
                    }
                }
            }
        }

    }

    public void computerMoves()
    {
        double xd =0;
        double yd = 0;
        int x =0;
        int y = 0;
        if(aiC)
        {
            if(whoTurn.equals("grey"))
            {
                while(!aiHasMove)
                {
                    xd= Math.random() *7;
                    yd = Math.random() *7;
                    x = (int)xd;
                    y = (int)yd;
                    //System.out.println(x + "," + y);

                    if(spaces[x][y].getIcon() == greyblack || spaces[x][y].getIcon() == greyblackK)
                    {
                        checkAround(x,y,whoTurn);

                        for( int z = 0; z < spaces.length;z++)
                        {
                            for ( int a =0; a< spaces[0].length; a++)
                            {
                                if(spaces[z][a].getIcon() == validMove || spaces[z][a].getIcon() == validJump)
                                {
                                    aiHasMove = true;
                                    selected[x][y] = true;
                                    spaces[z][a].setSelected(true);

                                }
                            }
                        }

                    }
                }

            }
        }

    }

    public void jump(int x, int y, int jumpX, int jumpY, ImageIcon whichColorJumpOver, ImageIcon kingJump, boolean greaterOrLess,boolean isKing)
    {
        ImageIcon dummy = whichColorJumpOver;
        int posX =x;
        int posY = y;
        int xPosAfterJump = jumpX;
        int yPosAfterJump = jumpY;
        boolean lol = greaterOrLess;
        boolean areKing = isKing;

        for(int[] l: moveVectors)
        {
            //xAdd = x vector to add, yAdd = y vector to add to selected point
            //xPosAfterMove is the row number after they move
            int xAdd = l[0];
            int yAdd = l[1];
            int xPosAfterMove = posX + xAdd;
            int yPosAfterMove = posY +yAdd; 
            if(xPosAfterMove >= 0 && xPosAfterMove <= 7 && yPosAfterMove>= 0 && yPosAfterMove <= 7)
            {
                if((yPosAfterJump - yPosAfterMove == 1) || (yPosAfterJump - yPosAfterMove == -1))
                {
                    if(lol && isKing == false)
                    {
                        if(posX < xPosAfterJump&&  xPosAfterMove > posX && (spaces[xPosAfterMove][yPosAfterMove].getIcon() == dummy || spaces[xPosAfterMove][yPosAfterMove].getIcon() == kingJump))
                        {
                            spaces[xPosAfterJump ][yPosAfterJump ].setIcon(validJump);
                        }
                    }
                    else if (lol == false  && isKing == false)
                    {
                        if(posX > xPosAfterJump && xPosAfterMove < posX && (spaces[xPosAfterMove][yPosAfterMove].getIcon() == dummy  || spaces[xPosAfterMove][yPosAfterMove].getIcon() == kingJump))
                        {
                            spaces[xPosAfterJump ][yPosAfterJump ].setIcon(validJump);
                        }
                    }
                    if((xPosAfterJump - xPosAfterMove == 1) || (xPosAfterJump - xPosAfterMove == -1))
                    {

                        if(lol == false && isKing == true)
                        {
                            if(spaces[xPosAfterMove][yPosAfterMove].getIcon() == dummy || spaces[xPosAfterMove][yPosAfterMove].getIcon() == kingJump)
                            {
                                spaces[xPosAfterJump ][yPosAfterJump].setIcon(validJump);
                            }
                        }
                    }

                }
            }
        }
    }
    //erase the jumped piece

    public void eraseJumped(int x, int y, int originalX, int originalY, String whoTurn)
    {
        int coordX = originalX;
        int coordY = originalY;

        int jumpCoordY = y;
        if(king[coordX][coordY])
        { 

            //going right and up
            if((coordY - jumpCoordY) < 0 && (coordX - x) > 0)
            {
                spaces[coordX-1][coordY +1].setIcon(black);
            }
            //going left and up
            else if((coordY - jumpCoordY) > 0 && (coordX - x) > 0)
            {
                spaces[coordX-1][coordY -1].setIcon(black);
            }
            //going left and going down
            else if((coordY - jumpCoordY) > 0 && (coordX - x) < 0)
            {
                spaces[coordX+1][coordY -1].setIcon(black);
            }
            //going right and going down
            else if((coordY - jumpCoordY) < 0 && (coordX - x) < 0)
            {
                spaces[coordX+1][coordY +1].setIcon(black);
            }

        }

        else
        {
            if(whoTurn.equals("red"))
            {
                if((coordY - jumpCoordY) < 0)
                {

                    spaces[coordX-1][coordY +1].setIcon(black);

                }
                else if((coordY - y) > 0)
                {
                    spaces[coordX-1][coordY -1].setIcon(black);

                }

            }
            else
            {
                if((coordY - jumpCoordY) < 0)
                {

                    spaces[coordX+1][coordY +1].setIcon(black);

                }
                else if((coordY - y) > 0)
                {
                    spaces[coordX+1][coordY -1].setIcon(black);

                }
            }
        }
        //System.out.println(coordX-1 + "," + coordY+1);

    }

    public void king()
    {
        for(int y =0; y < 8; y++)
        {
            //king the red pieces
            if(spaces[0][y].getIcon() == redblack)
            {
                spaces[0][y].setIcon(redblackK);
                king[0][y] = true;
            }
            if(spaces[7][y].getIcon() == greyblack)
            {
                spaces[7][y].setIcon(greyblackK);
                king[7][y] = true;
            }
        }

    }

    public void checkIfMoveExists()
    {
        int dummy = 0;
        for(int x = 0; x < spaces.length; x++)
        {
            for (int y =0; y< spaces[0].length; y++)
            {
                if(spaces[x][y].getIcon() != validMove && spaces[x][y].getIcon() != validJump)                
                { 
                    dummy++;
                }
                if(dummy == 64)
                {
                    moveExists = false;
                }
                else
                {
                    moveExists = true;
                }

            }
        }
        //reset all pieces

        //System.out.println(moveExists);
        if(moveExists == false)
        {
            for(int x = 0; x < spaces.length; x++)
            {
                for (int y =0; y< spaces[0].length; y++)
                {
                    if(selected[x][y] == true && whoTurn.equals("red"))
                    {
                        if(king[x][y])
                        {
                            spaces[x][y].setIcon(redblackK);
                        }
                        else
                        {spaces[x][y].setIcon(redblack);
                        }

                    }
                    if(selected[x][y] == true && whoTurn.equals("grey"))
                    {
                        if(king[x][y])
                        {
                            spaces[x][y].setIcon(greyblackK);
                        }
                        else
                        {spaces[x][y].setIcon(greyblack);
                        }
                    }
                    selected[x][y] = false;

                }
            }
            alt = false;
            selectPiece();
            if(whoTurn.equals("red"))
            {
                playersTurn.setText("Red player, your selected piece has no available move. Select a new piece");
            }
            else
            {
                playersTurn.setText("Grey player, your selected piece has no available move. Select a new piece");
            }

        }   
    }

    public void issaMess(int x, int y)
    {
        selected[x][y] = false;
        whoTurn = "grey";
        playersTurn.setText("Grey Player, Select a Piece to move");
        alt = false;
        dontRunTwicePlease = true;
        spaces[x][y].setSelected(false);
    }

    public void issaMessG(int x, int y)
    {
        whoTurn = "red";
        playersTurn.setText("Red Player, Select a Piece to move");
        alt = false;
        selected[x][y] = false;
        dontRunTwicePlease = true;
        spaces[x][y].setSelected(false);
    }

    public void selectPiece()   
    {

        for(int x = 0; x < spaces.length; x++)
        {
            for (int y =0; y< spaces[0].length; y++)
            {
                if(aiC)
                {

                    if(spaces[x][y].isSelected() && (spaces[x][y].getIcon() == redblack || spaces[x][y].getIcon() == redblackK) && whoTurn.equals("red"))
                    {
                        selected[x][y] = true;
                        alt = true;
                        playersTurn.setText("Red Player, Select a Diagonal Spot");

                        checkAround(x,y,whoTurn);
                        if(king[x][y])
                        {
                            spaces[x][y].setIcon(redblackKS);
                        }
                        else
                        {
                            spaces[x][y].setIcon(redblackSelected);
                        }

                        spaces[x][y].setSelected(false);
                        checkIfMoveExists();
                    }
                    else if (whoTurn.equals("grey"))
                    {
                        computerMoves();
                        alt = true;
                        playersTurn.setText("Grey Player, Select a Diagonal Spot");

                    }
                }
                else
                {
                    if(spaces[x][y].isSelected() && (spaces[x][y].getIcon() == redblack || spaces[x][y].getIcon() == redblackK) && whoTurn.equals("red"))
                    {
                        selected[x][y] = true;
                        alt = true;
                        playersTurn.setText("Red Player, Select a Diagonal Spot");

                        checkAround(x,y,whoTurn);
                        if(king[x][y])
                        {
                            spaces[x][y].setIcon(redblackKS);
                        }
                        else
                        {
                            spaces[x][y].setIcon(redblackSelected);
                        }

                        spaces[x][y].setSelected(false);
                        checkIfMoveExists();
                    }
                    else if(spaces[x][y].isSelected() && (spaces[x][y].getIcon() == greyblack || spaces[x][y].getIcon() == greyblackK) && whoTurn.equals("grey"))
                    {
                        selected[x][y] = true;
                        alt = true;
                        playersTurn.setText("Grey Player, Select a Diagonal Spot");

                        checkAround(x,y,whoTurn);
                        if(king[x][y])
                        {
                            spaces[x][y].setIcon(greyblackKS);
                        }
                        else
                        {
                            spaces[x][y].setIcon(greyblackSelected);
                        }
                        spaces[x][y].setSelected(false);
                        checkIfMoveExists();

                    }

                    spaces[x][y].setSelected(false);
                }
            }
        }

    }

    public void movePiece2()
    {
        int sX = 0;
        int sY = 0;

        for(int x = 0; x < spaces.length; x++)
        {
            for (int y =0; y< spaces[0].length; y++)
            {
                if(selected[x][y] == true)
                {
                    sX = x;
                    sY = y;
                }
            }
        }

        for(int x = 0; x < spaces.length; x++)
        {
            for (int y =0; y< spaces[0].length; y++)
            {

                if(spaces[x][y].isSelected() && spaces[x][y].getIcon() == validMove && !dontRunTwicePlease)
                {

                    if(whoTurn.equals("red"))
                    {

                        if(king[sX][sY])
                        {
                            king[x][y] = true;
                            eraseJumped(x,y,sX,sY,whoTurn);
                            king[sX][sY] = false;
                            spaces[x][y].setIcon(redblackK);
                        }
                        else
                        {
                            eraseJumped(x,y,sX,sY,whoTurn); 

                            spaces[x][y].setIcon(redblack);
                        }

                        issaMess(x,y);
                    }
                    else
                    {
                        if(king[sX][sY])
                        {
                            king[x][y] = true;
                            eraseJumped(x,y,sX,sY,whoTurn);
                            king[sX][sY] = false;
                            spaces[x][y].setIcon(greyblackK);
                        }

                        else 
                        {

                            eraseJumped(x,y,sX,sY,whoTurn);
                            spaces[x][y].setIcon(greyblack);
                        }
                        issaMessG(x,y);
                    }

                    moved = true;
                } 
                else if(spaces[x][y].isSelected() && spaces[x][y].getIcon() == validJump && !dontRunTwicePlease)
                {

                    if(whoTurn.equals("red"))
                    {
                        if(king[sX][sY])
                        {
                            king[x][y] = true;
                            eraseJumped(x,y,sX,sY,whoTurn);
                            king[sX][sY] = false;
                            spaces[x][y].setIcon(redblackK);
                        }
                        else
                        {
                            eraseJumped(x,y,sX,sY,whoTurn);
                            spaces[x][y].setIcon(redblack);
                        }

                        issaMess(x,y);
                    }
                    else
                    {
                        if(king[sX][sY])
                        {
                            king[x][y] = true;
                            eraseJumped(x,y,sX,sY,whoTurn);
                            king[sX][sY] = false;
                            spaces[x][y].setIcon(greyblackK);
                        }
                        else
                        {
                            eraseJumped(x,y,sX,sY,whoTurn);
                            spaces[x][y].setIcon(greyblack);
                        }

                        issaMessG(x,y);
                    }
                    moved = true;

                }
            }
        }

        if(moved == true)
        {
            {
                for(int x = 0; x < spaces.length; x++)
                {
                    for (int y =0; y< spaces[0].length; y++)
                    {
                        if(spaces[x][y].getIcon() == validMove || spaces[x][y].getIcon() == validJump)
                        {
                            spaces[x][y].setIcon(black);

                        }
                        if(selected[x][y]== true)
                        {
                            spaces[x][y].setIcon(black);
                            selected[x][y] = false;
                        }
                    }
                }

                moved = false;
            }

            king(); 
            aiHasMove = false;
            dontRunTwicePlease = false;
        }
    }

    public void win()
    {
        int winCounterRed =0;
        int winCounterGrey =0;
        for(int x = 0; x < spaces.length; x++)
        {
            for (int y =0; y< spaces[0].length; y++)
            {
                if(spaces[x][y].getIcon() == greyblack || spaces[x][y].getIcon() == greyblackK || spaces[x][y].getIcon() == greyblackKS ||  spaces[x][y].getIcon() == greyblackSelected)
                {
                    winCounterRed++;

                }

                if(spaces[x][y].getIcon() == redblack || spaces[x][y].getIcon() == redblackK || spaces[x][y].getIcon() == redblackKS ||  spaces[x][y].getIcon() == redblackSelected )
                {
                    winCounterGrey++;

                }

            }
        }
        if(winCounterRed == 0)
        {
            playersTurn.setText("Red wins!");
        }
        if(winCounterGrey == 0)
        {
            playersTurn.setText("Grey wins!");
        }
    }

    @Override public void actionPerformed(ActionEvent e)
    {
        //alt starts false, once they click it becomes true, signalling that they have clicked a valid button,
        //and the next click should select diagonal and set it to be false

        if(!chosen)
        {

        }
        else
        {
            if(aiC)
            {
                if (whoTurn.equals("grey"))
                {

                    selectPiece();
                    win();

                    movePiece2();
                    win();

                    // System.out.println(whoTurn);
                }
                else if(whoTurn.equals("red"))
                {
                    if (alt == false)
                    {

                        selectPiece();
                        win();
                        // System.out.println(whoTurn);
                    }
                    else
                    {
                        movePiece2();
                        win();

                    }   
                }

            }
            else
            {

                if (alt == false)
                {

                    selectPiece();
                    win();
                    // System.out.println(whoTurn);
                }
                else
                {
                    movePiece2();
                    win();

                }
            }
        }

    }

    public void     windowClosing(WindowEvent e)
    {
        System.exit(0);
    }

    public void     windowActivated(WindowEvent e){}

    public void     windowDeactivated(WindowEvent e){}

    public void     windowDeiconified(WindowEvent e){}

    public void     windowIconified(WindowEvent e){}

    public void     windowOpened(WindowEvent e){}

    public void     windowClosed(WindowEvent e){

    }
}