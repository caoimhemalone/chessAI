import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/*
	This class can be used as a starting point for creating your Chess game project. The only piece that
	has been coded is a white pawn...a lot done, more to do!
*/

public class ChessProject extends JFrame implements MouseListener, MouseMotionListener {
    JLayeredPane layeredPane;
    JPanel chessBoard;
    JLabel chessPiece;
    int xAdjustment;
    int yAdjustment;
	int startX;
	int startY;
	int initialX;
	int initialY;
	JPanel panels;
	JLabel pieces;


    public ChessProject(){
        Dimension boardSize = new Dimension(600, 600);

        //  Use a Layered Pane for this application
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        //Add a chess board to the Layered Pane
        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout( new GridLayout(8, 8) );
        chessBoard.setPreferredSize( boardSize );
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel( new BorderLayout() );
            chessBoard.add( square );

            int row = (i / 8) % 2;
            if (row == 0)
                square.setBackground( i % 2 == 0 ? Color.white : Color.gray );
            else
                square.setBackground( i % 2 == 0 ? Color.gray : Color.white );
        }

        // Setting up the Initial Chess board.
		for(int i=8;i < 16; i++){
       		pieces = new JLabel( new ImageIcon("WhitePawn.png") );
			panels = (JPanel)chessBoard.getComponent(i);
	        panels.add(pieces);
		}
		pieces = new JLabel( new ImageIcon("WhiteRook.png") );
		panels = (JPanel)chessBoard.getComponent(0);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKnight.png") );
		panels = (JPanel)chessBoard.getComponent(1);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKnight.png") );
		panels = (JPanel)chessBoard.getComponent(6);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteBishop.png") );
		panels = (JPanel)chessBoard.getComponent(2);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteBishop.png") );
		panels = (JPanel)chessBoard.getComponent(5);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKing.png") );
		panels = (JPanel)chessBoard.getComponent(3);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
		panels = (JPanel)chessBoard.getComponent(4);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteRook.png") );
		panels = (JPanel)chessBoard.getComponent(7);
	    panels.add(pieces);
		for(int i=48;i < 56; i++){
       		pieces = new JLabel( new ImageIcon("BlackPawn.png") );
			panels = (JPanel)chessBoard.getComponent(i);
	        panels.add(pieces);
		}
		pieces = new JLabel( new ImageIcon("BlackRook.png") );
		panels = (JPanel)chessBoard.getComponent(56);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKnight.png") );
		panels = (JPanel)chessBoard.getComponent(57);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKnight.png") );
		panels = (JPanel)chessBoard.getComponent(62);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackBishop.png") );
		panels = (JPanel)chessBoard.getComponent(58);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackBishop.png") );
		panels = (JPanel)chessBoard.getComponent(61);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKing.png") );
		panels = (JPanel)chessBoard.getComponent(59);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackQueen.png") );
		panels = (JPanel)chessBoard.getComponent(60);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackRook.png") );
		panels = (JPanel)chessBoard.getComponent(63);
	    panels.add(pieces);
    }

	/*
		This method checks if there is a piece present on a particular square.
	*/
	private Boolean piecePresent(int x, int y){
		Component c = chessBoard.findComponentAt(x, y);
		if(c instanceof JPanel){
			return false;
		}
		else{
			return true;
		}
	}

	/*
		This is a method to check if a piece is a Black piece.
	*/
	private Boolean checkWhiteOpponent(int newX, int newY){
		Boolean opponent;
		Component c1 = chessBoard.findComponentAt(newX, newY);
		JLabel awaitingPiece = (JLabel)c1;
		String tmp1 = awaitingPiece.getIcon().toString();
		if(((tmp1.contains("Black")))){
			opponent = true;
		}
		else{
			opponent = false;
		}
		return opponent;
	}

  /*
    This is a method to check if a piece is a king
  */
  private Boolean checkKingOpponent(int newX, int newY){
    Boolean opponent;
    Component c1 = chessBoard.findComponentAt(newX,newY);
    JLabel awaitingPiece = (JLabel)c1;
    String tmp1 = awaitingPiece.getIcon().toString();
    if(((tmp1.contains("King")))){
      opponent = true;
    }
    else {
      opponent = false;
    }
    return opponent;
  }



  /*
    This is a method to check if a piece is a White piece.
  */
  private Boolean checkBlackOpponent(int newX, int newY){
    Boolean opponent;
    Component c1 = chessBoard.findComponentAt(newX, newY);
    JLabel awaitingPiece = (JLabel)c1;
    String tmp1 = awaitingPiece.getIcon().toString();
    if(((tmp1.contains("White")))){
      opponent = true;
    }
    else{
      opponent = false;
    }
    return opponent;
  }

	/*
		This method is called when we press the Mouse. So we need to find out what piece we have
		selected. We may also not have selected a piece!
	*/
    public void mousePressed(MouseEvent e){
        chessPiece = null;
        Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
        if (c instanceof JPanel)
			return;

        Point parentLocation = c.getParent().getLocation();
        xAdjustment = parentLocation.x - e.getX();
        yAdjustment = parentLocation.y - e.getY();
        chessPiece = (JLabel)c;
		initialX = e.getX();
		initialY = e.getY();
		startX = (e.getX()/75);
		startY = (e.getY()/75);
        chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
        chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
        layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
    }

    public void mouseDragged(MouseEvent me) {
        if (chessPiece == null) return;
         chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
     }

 	/*
		This method is used when the Mouse is released...we need to make sure the move was valid before
		putting the piece back on the board.
	*/
    public void mouseReleased(MouseEvent e) {
        if(chessPiece == null) return;

        chessPiece.setVisible(false);
		Boolean whiteSuccess =false;
    Boolean blackSuccess = false;
        Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
		String tmp = chessPiece.getIcon().toString();
		String pieceName = tmp.substring(0, (tmp.length()-4));
		Boolean validMove = false;


    /*

    this code helps us to understand when a user starts to move pieces around the board. We are using the standard output to show
    - the name of the piece that is being moved
    - the starting square of a piece that is clicked
    - the distance in the x-direction that a player is trying to move the piece
    - the distance in the y-direction that a player is trying to move the piece
    - the landing square of where a player is returning the piece to the board

    Having this information printed out to the standard output as we test and construct the solution allows us to understand the consctructs of the game..... This code snippet below should be pasted into the following place:

      public void mouseReleased(MouseEvent e){
      ...
      ...
      ...
      Boolean validMove = false;
    }
    */

    int landingX = (e.getX()/75);
    int landingY = (e.getY()/75);
    int xMovement = Math.abs((e.getX()/75)-startX);
    int yMovement = Math.abs((e.getY()/75)-startY);
    System.out.println("-----------------------------");
    System.out.println("The piece that is being moved is : "+pieceName);
    System.out.println("The starting coordinates are : "+"( "+startX+","+startY+")");
    System.out.println("The xMovement is : "+xMovement);
    System.out.println("The yMovement is : "+yMovement);
    System.out.println("The landing coordinates are : "+"( "+landingX+","+landingY+")");
    System.out.println("-----------------------------");


	//Queen Movements

    if(pieceName.contains("Queen")){  //Allows queen to move
      Boolean inTheWay = false;
      int distance = Math.abs(startX-landingX);
      if(((landingX < 0) || (landingX > 7))||((landingY < 0)|| (landingY> 7))){
        validMove = false;
      }
      else {
        validMove = true;
        //bishop movements
        // To determine the direction that the Bishop can move along and that there is no piece in the way
        if(Math.abs(startX-landingX)==Math.abs(startY-landingY)){
          if ((startX-landingX < 0)&& (startY-landingY < 0)){
            for (int i = 0; i < distance; i++){
              if(piecePresent((initialX+(i*75)), (initialY+(i*75)))){
                inTheWay = true;
              }
            }
          } //difference in this one: startY-landingY < 0 and +(i*75)

          else if ((startX-landingX < 0)&&(startY-landingY > 0)){
            for (int i = 0; i < distance; i++){
              if(piecePresent((initialX+(i*75)), (initialY-(i*75)))){
                inTheWay = true;
              }
            }
          }//difference in this one: startY-landingY > 0 and -(i*75)

          else if ((startX-landingX > 0)&&(startY-landingY > 0)){
            for (int i = 0; i < distance; i++){
              if(piecePresent((initialX+(i*75)), (initialY-(i*75)))){
                inTheWay = true;
              }
            }
          } //difference in this one: startX-landingX > 0 and -(i*75)

          else if ((startX-landingX > 0)&& (startY-landingY < 0)){
            for (int i = 0; i < distance; i++){
              if(piecePresent((initialX-(i*75)), (initialY=(i*75)))){
                inTheWay = true;
              }
            }
          } //difference in this one: startX-landingX > 0

          /*
          The variable inTheWay checks if there is another piece in the way. If there is then the move is
          considered an invalid move. If the Boolean is false then this means that you can move the piece there
          */

          if(inTheWay){
            validMove = false;
          }
          else{
            if (piecePresent(e.getX(), (e.getY()))){
              if(pieceName.contains("White")){
                if(checkWhiteOpponent(e.getX(), e.getY())){
                  validMove = true;
                }
                else {
                  validMove = false;
                }
              }
              else {
                if(checkBlackOpponent(e.getX(), e.getY())){
                  validMove = true;
                }
                else {
                  validMove = false;
                }
              }
            }
            else{
              validMove = true;
            }
          }
        }


      //rook movements
        //Checks that the piece has been put back on the board
       else if(((Math.abs(startX-landingX)!=0)&&(Math.abs(startY-landingY)==0))||
        ((Math.abs(startX-landingX)==0)&&(Math.abs(landingY-startY)!=0))){
          //For horizontal Movement - along the x-axis
          if(Math.abs(startX-landingX)!=0){
            int xDifference = Math.abs(startX-landingX);
            if(startX-landingX > 0){
              for(int i = 0; i < xDifference; i++){
                if(piecePresent(initialX-(i*75), e.getY())){
                  inTheWay = true;
                  break;
                }
                else {
                  inTheWay = false;
                }
              }
            }
            else {
              for(int i = 0; i < xDifference; i++){
                if(piecePresent(initialX+(i*75), e.getY())){
                  inTheWay = true;
                  break;
                }
                else {
                  inTheWay = false;
                }
              }
            }
          }//close if for horizontal movement
          else {
            //For vertical movement - y axis
            int yDifference = Math.abs(startY-landingY);
            if(startY-landingY > 0){
              for(int i = 0; i < yDifference; i++){
                if(piecePresent(e.getX(), initialY-(i*75))){
                  inTheWay = true;
                  break;
                }
                else {
                  inTheWay = false;
                }
              }
            }
            else {
              for (int i =0; i <yDifference; i++){
                if(piecePresent(e.getX(), initialY+(i*75))){
                  inTheWay = true;
                  break;
                }
                else {
                  inTheWay = false;
                }
              }
            }
          }//close else for vertical movement

          // to check if theres a piece in the way
          if(inTheWay){
            validMove = false;
          }
          else{
            if (piecePresent(e.getX(), (e.getY()))){
              if(pieceName.contains("White")){
                if(checkWhiteOpponent(e.getX(), e.getY())){
                  validMove = true;
                }
                else {
                  validMove = false;
                }
              }
              else {
                if(checkBlackOpponent(e.getX(), e.getY())){
                  validMove = true;
                }
                else {
                  validMove = false;
                }
              }
            }//close if to see if there's a piece in the way
            else{
              validMove = true;
            }
          }//close else to see if there's a piece in the way
        }//close rook if

      else {
       validMove = false;
      }
      }//close else
  }//close queen

      /*
      We want to say

      if(the queen is being placed back onto the board){

        if (the movement is like the bishop){
         test for a valid move
          we had a valid bishop movement if the xMovement == yMovement
          Check
            if there is something in the way
            if there is a piece on the expected landing square, if so
            make sure that it's an enemy piece....
        }

        else if (the movement is like a rook){
        test for a valid rook movement
        if there is an xMovement there can't be a yMovement
        if there is a yMovement there can't be an xMovement

        if one of the above conditions are true we simply
        make sure that there is nothing in the way and that we can't take our own piece.
        }
    }
      */



//Pawn Movements

    /*
      The only piece that has been enabled to move is a White Pawn...but we should really have this is a separate
      method somewhere...how would this work.

      So a Pawn is able to move two squares forward one its first go but only one square after that.
      The Pawn is the only piece that cannot move backwards in chess...so be careful when committing
      a pawn forward. A Pawn is able to take any of the opponent’s pieces but they have to be one
      square forward and one square over, i.e. in a diagonal direction from the Pawns original position.
      If a Pawn makes it to the top of the other side, the Pawn can turn into any other piece, for
      demonstration purposes the Pawn here turns into a Queen.
    */

    //Check if its a black pawn
    else if(pieceName.equals("BlackPawn")){
      if(startY == 6){        //startY is the coordinates where it is initialy starting, Y value of 6 means it's in the second row from the bottom

/*
  If the pawn is making it's first move....
  the pawn can either move one square or two in the Y direction
  as long as we are moving up the board
  And also there is no movement in the X direction
  */

//For the first move for pawn
    if(((yMovement==1)||(yMovement == 2))&&(startY > landingY)&&(xMovement ==0)){  /* start Y > landing Y means we are moving up the board*/
      if(yMovement == 2){ //Pawn shouldn't be able to move 2 spaces if there is an opponents pawn in that space as pawns can only take over opponents pieces diagonally
        if((!piecePresent(e.getX(), e.getY()))&&(!piecePresent(e.getX(), (e.getY()+75)))){
          validMove = true;
        }
      }
      else{
        if(!piecePresent(e.getX(), e.getY())){
          validMove = true;
        }
      }
    }
      else if((yMovement == 1)&&(startY > landingY)&&(xMovement == 1)){ //Moving up the board, one in the Y and one in the X so diagonal
        if(piecePresent(e.getX(), e.getY())){ //Allows you to go diagonally and take a piece
          if(checkBlackOpponent(e.getX(), e.getY())){ //stops a black piece from taking a black piece
            validMove = true;
          }
        }
      }
    }
    //end of code for when pawn is in it's initial state
    // We can move up 1, 2 and diagonally but not any more than 2

      else { // this is where the pawn is making all other moves apart from it's initial move
        if(((yMovement==1))&&(startY > landingY)&&(xMovement == 0)){
          if(!piecePresent(e.getX(), e.getY())){
            validMove = true;
            }
          }

          else if((yMovement == 1)&&(startY > landingY)&&(xMovement == 1)){ //Moving up the board, one in the Y and one in the X so diagonal
            if(piecePresent(e.getX(), e.getY())){ //Allows you to go diagonally and take a piece
              if(checkBlackOpponent(e.getX(), e.getY())){ //stops a black piece from taking a black piece
                if (landingY == 0){
                  blackSuccess = true;
                }
                validMove = true;
              }
            }
            else if(!piecePresent(e.getX(), e.getY())){
              if((xMovement == landingX) && (landingY - startY) == 1){
                if(landingY == 0){
                  blackSuccess = true;
                }
                validMove = true;
              }
              else{
                validMove = false;
              }
            }
          }
        }
      }


		else if(pieceName.equals("WhitePawn")){
      /*Think about the logic...
      The pawn moves the same as the white pawn but in the other direction*/
			if(startY == 1)
			{
				if((startX == (e.getX()/75))&&((((e.getY()/75)-startY)==1)||((e.getY()/75)-startY)==2))
				{
					if((((e.getY()/75)-startY)==2)){
						if((!piecePresent(e.getX(), (e.getY())))&&(!piecePresent(e.getX(), (e.getY()+75)))){
							validMove = true;
						}
						else{
							validMove = false;
						}
					}
					else{
						if((!piecePresent(e.getX(), (e.getY()))))
						{
							validMove = true;
						}
						else{
							validMove = false;
						}
					}
				}
				else{
					validMove = false;
				}
			}
			else{
				int newY = e.getY()/75;
				int newX = e.getX()/75;
				if((startX-1 >=0)||(startX +1 <=7))
				{
					if((piecePresent(e.getX(), (e.getY())))&&((((newX == (startX+1)&&(startX+1<=7)))||((newX == (startX-1))&&(startX-1 >=0)))))
					{
						if(checkWhiteOpponent(e.getX(), e.getY())){
							validMove = true;
							if(startY == 6){
								whiteSuccess = true;
							}
						}
						else{
							validMove = false;
						}
					}
					else{
						if(!piecePresent(e.getX(), (e.getY()))){
							if((startX == (e.getX()/75))&&((e.getY()/75)-startY)==1){
								if(startY == 6){
									whiteSuccess = true;
								}
								validMove = true;
							}
							else{
								validMove = false;
							}
						}
						else{
							validMove = false;
						}
					}
				}
				else{
					validMove = false;
				}
			}
		}


// Knight movements
else if(pieceName.contains("Knight")){

/*
  We know that the Knight can move in an L direction.
  It means that if xMovement == 1 than the yMovement
  must equal 2 and also the other way around

  We need to check the square that we are moving to and make sure that if there's a piece
  present that it's not our own piece
*/
  if(((xMovement == 1)&&(yMovement ==2))||((xMovement == 2)&& (yMovement ==1))){ //this means if the knight is moving in an L shape that's okay
    if(!piecePresent(e.getX(), e.getY())){ //If there's no piece present that's okay
      validMove = true;
    }

    else { //If there is a piece present check that...

      if(pieceName.contains("White")){ //If it's a white opponent
        if(checkWhiteOpponent(e.getX(), e.getY())){
          validMove = true;
        }
      }
      else {
        if(checkBlackOpponent(e.getX(), e.getY())){ //If it's a black opponent
          validMove = true;
        }
      }
    }
  }
}

// King Movements
else if(pieceName.contains("King")){  //Allows King to move
  Boolean kingPresent = false;
    if(((landingX < 0) || (landingX > 7))||((landingY < 0)|| (landingY > 7))){
      validMove = false;
    }
    else {
      if(((xMovement==1)&&(yMovement == 0)) || ((xMovement == 0 )&&(yMovement == 1)) || ((xMovement == 1)&&(yMovement == 1))){
        if(!piecePresent(e.getX(),e.getY())){
          validMove = true;
          }
        else { //If there is a piece present check that...

         if(pieceName.contains("White")){ //If it's a white opponent
           if(checkWhiteOpponent(e.getX(), e.getY())){



              /* need to check that the pieces in the surrounding 8 squares after the move are
              not a king
              check 1,0 0,1 -1,0 0,-1 1,1 -1,1 -1,-1 1,-1
              If theres the opposite king present then kingPresent=true

              need to check if it is at the edge of the board
               */

             validMove = true;
           }
         }

           /*
            Look at surrounding pieces
            if one of the surrounding pieces is a king validMove = false
           */



        // else if(((landingX(+1)))&& (landingY())){
        //   if(pieceName.contains("King")){
        //     validMove = false;
        //   }
        // }
        // else if(((landingX) && (landingY+1))){
        //   if(pieceName.contains("King")){
        //     validMove = false;
        //   }
        // }
        // else if(((landingX-1)&& (landingY+0))){
        //   if(pieceName.contains("King")){
        //     validMove = false;
        //   }
        // }
        // else if(((landingX)&& ( landingY-1))){
        //   if(pieceName.contains("King")){
        //     validMove = false;
        //   }
        // }
        // else if(((landingX+1) && (landingY+1))){
        //   if(pieceName.contains("King")){
        //     validMove = false;
        //   }
        // }
        // else if(((landingX-1) && (landingY+1))){
        //   if(pieceName.contains("King")){
        //     validMove = false;
        //   }
        // }
        // else if(((landingX-1) && (landingY-1))){
        //   if(pieceName.contains("King")){
        //     validMove = false;
        //   }
        // }
        // else if(((landingX+1) && (landingY-1))){
        //   if(pieceName.contains("King")){
        //     validMove = false;
        //   }
        // }

         else {
           if(checkBlackOpponent(e.getX(), e.getY())){ //If it's a black opponent
             validMove = true;
           }
         }
       }

     }
   }





   // Things I've tried

   //  if(checkKingOpponent((e.getX()+1), (e.getY()+1))){
   //    validMove = false;
   //  }
   //  else {
   //    validMove = true;
   //  }



   // if (piecePresent((initialX+1), (initialY+0))){
   //   kingPresent = true;
   // }
   // else if (piecePresent((initialX+0), (initialY+1))){
   //   kingPresent = true;
   // }
   // else if (piecePresent((initialX-1), (initialY+0))){
   //   kingPresent = true;
   // }
   // else if (piecePresent((initialX+0), (initialY-1))){
   //   kingPresent = true;
   // }
   // else if (piecePresent((initialX+1), (initialY+1))){
   //   kingPresent = true;
   // }
   // else if (piecePresent((initialX-1), (initialY+1))){
   //   kingPresent = true;
   // }
   // else if (piecePresent((initialX-1), (initialY-1))){
   //   kingPresent = true;
   // }
   // else if (piecePresent((initialX+1), (initialY-1))){
   //   kingPresent = true;
   // }
   // else {
   //   kingPresent = false;
   // }
   //
   // if (kingPresent = true){
   //   validMove = false;
   // }
   // else {
   //   validMove = true;
   // }





   /* else if(pieceName.contains("King")){
      for (int i = 0; i < 7; i++){
        if(piecePresent(e.getX(), initialY+1)){ //0,1
          kingPresent = true;
          break;
        }
        else if(piecePresent(e.getX(), initialY-1)){//0,-1
          kingPresent = true;
          break;
        }
        else if(piecePresent(e.getY(), initialX+1)){//1,0
          kingPresent = true;
          break;
        }
        else if(piecePresent(e.getY(), initialX-1)){//-1,0
          kingPresent = true;
          break;
        }
        else if(piecePresent(initialX-1, initialY-1)){//-1,-1
          kingPresent = true;
          break;
        }
        else if(piecePresent(initialX+1, initialY+1)){//1,1
          kingPresent = true;
          break;
        }
        else if(piecePresent(initialX-1, initialY+1)){//-1,1
          kingPresent = true;
          break;
        }
        else if(piecePresent(initialX+1, initialY-1)){//1,-1
          kingPresent = true;
          break;
        }
      }
    }
    */

}//close King



  /*
    the king can move in any direction as long as its being placed back onto the board
    and its only moving one square at a time...

    if( the king is being placed back onto the board){
      if(xMovement == 1) || (yMovement == 1) ..... and all other combinations of moving
      only one square at a time... essentially just like a restricted Queen.

      We can't take our own piece and there must be one square between both Kings after completing the move
  }
  */



//Bishop Movement - can go diagonally

else if(pieceName.contains("Bishop")){  //Allows queen to use
  //Checks if there is a piece in the way


  Boolean inTheWay = false;
  int distance = Math.abs(startX-landingX);
  //If the piece isn't on the board to start
  if(((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))){
    validMove = false;
  }
  else {
    validMove = true;


    // To determine the direction that the Bishop can move along and that there is no piece in the way
    if(Math.abs(startX-landingX)==Math.abs(startY-landingY)){
      if ((startX-landingX < 0)&& (startY-landingY < 0)){
        for (int i = 0; i < distance; i++){
          if(piecePresent((initialX+(i*75)), (initialY+(i*75)))){
            inTheWay = true;
          }
        }
      } //difference in this one: startY-landingY < 0 and +(i*75)

      else if ((startX-landingX < 0)&&(startY-landingY > 0)){
        for (int i = 0; i < distance; i++){
          if(piecePresent((initialX+(i*75)), (initialY-(i*75)))){
            inTheWay = true;
          }
        }
      }//difference in this one: startY-landingY > 0 and -(i*75)

      else if ((startX-landingX > 0)&&(startY-landingY > 0)){
        for (int i = 0; i < distance; i++){
          if(piecePresent((initialX+(i*75)), (initialY-(i*75)))){
            inTheWay = true;
          }
        }
      } //difference in this one: startX-landingX > 0 and -(i*75)

      else if ((startX-landingX > 0)&& (startY-landingY < 0)){
        for (int i = 0; i < distance; i++){
          if(piecePresent((initialX-(i*75)), (initialY=(i*75)))){
            inTheWay = true;
          }
        }
      } //difference in this one: startX-landingX > 0

      /*
      The variable inTheWay checks if there is another piece in the way. If there is then the move is
      considered an invalid move. If the Boolean is false then this means that you can move the piece there
      */

      if(inTheWay){
        validMove = false;
      }
      else{
        if (piecePresent(e.getX(), (e.getY()))){
          if(pieceName.contains("White")){
            if(checkWhiteOpponent(e.getX(), e.getY())){
              validMove = true;
            }
            else {
              validMove = false;
            }
          }
          else {
            if(checkBlackOpponent(e.getX(), e.getY())){
              validMove = true;
            }
            else {
              validMove = false;
            }
          }
        }
        else{
          validMove = true;
        }
      }
    }
   else {
      validMove = false;
    }
  }
}


//Rook Movement - can move up or down

else if(pieceName.contains("Rook")){
  Boolean inTheWay = false;
  if(((landingX < 0) || (landingX > 7))||((landingY < 0)||(landingY > 7))){
    validMove = false;
  }
  else {
    //Checks that the piece has been put back on the board
    if(((Math.abs(startX-landingX)!=0)&&(Math.abs(startY-landingY)==0))||
    ((Math.abs(startX-landingX)==0)&&(Math.abs(landingY-startY)!=0))){
      //For horizontal Movement - along the x-axis
      if(Math.abs(startX-landingX)!=0){
        int xDifference = Math.abs(startX-landingX);
        if(startX-landingX > 0){
          for(int i = 0; i < xDifference; i++){
            if(piecePresent(initialX-(i*75), e.getY())){
              inTheWay = true;
              break;
            }
            else {
              inTheWay = false;
            }
          }
        }
        else {
          for(int i = 0; i < xDifference; i++){
            if(piecePresent(initialX+(i*75), e.getY())){
              inTheWay = true;
              break;
            }
            else {
              inTheWay = false;
            }
          }
        }
      }
      else {
        //For vertical movement - y axis
        int yDifference = Math.abs(startY-landingY);
        if(startY-landingY > 0){
          for(int i = 0; i < yDifference; i++){
            if(piecePresent(e.getX(), initialY-(i*75))){
              inTheWay = true;
              break;
            }
            else {
              inTheWay = false;
            }
          }
        }
        else {
          for (int i =0; i <yDifference; i++){
            if(piecePresent(e.getX(), initialY+(i*75))){
              inTheWay = true;
              break;
            }
            else {
              inTheWay = false;
            }
          }
        }
      }

      // to check if theres a piece in the way
      if(inTheWay){
        validMove = false;
      }
      else{
        if (piecePresent(e.getX(), (e.getY()))){
          if(pieceName.contains("White")){
            if(checkWhiteOpponent(e.getX(), e.getY())){
              validMove = true;
            }
            else {
              validMove = false;
            }
          }
          else {
            if(checkBlackOpponent(e.getX(), e.getY())){
              validMove = true;
            }
            else {
              validMove = false;
            }
          }
        }
        else{
          validMove = true;
        }
      }
    }
    else {
      validMove = false;
    }
  }
}


if(!validMove){
  int location=0;
  if(startY ==0){
    location = startX;
  }
  else{
    location  = (startY*8)+startX;
  }
  String pieceLocation = pieceName+".png";
  pieces = new JLabel( new ImageIcon(pieceLocation) );
  panels = (JPanel)chessBoard.getComponent(location);
    panels.add(pieces);
}

else{
  if(whiteSuccess){
    int whiteLocation = 56 + (e.getX()/75);
    if (c instanceof JLabel){
            Container parent = c.getParent();
            parent.remove(0);
      pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
      parent = (JPanel)chessBoard.getComponent(whiteLocation);
        parent.add(pieces);
    }
    else{
      Container parent = (Container)c;
            pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
      parent = (JPanel)chessBoard.getComponent(whiteLocation);
        parent.add(pieces);
    }
  }

  if(!validMove){
    int location=0;
    if(startY ==0){
      location = startX;
    }
    else{
      location  = (startY*8)+startX;
    }
    String pieceLocation = pieceName+".png";
    pieces = new JLabel( new ImageIcon(pieceLocation) );
    panels = (JPanel)chessBoard.getComponent(location);
      panels.add(pieces);
  }

  else if(blackSuccess){
    int blackLocation = 0 + (e.getX()/75);
    if (c instanceof JLabel){
            Container parent = c.getParent();
            parent.remove(0);
      pieces = new JLabel( new ImageIcon("BlackQueen.png") );
      parent = (JPanel)chessBoard.getComponent(blackLocation);
        parent.add(pieces);
    }
    else{
      Container parent = (Container)c;
            pieces = new JLabel( new ImageIcon("BlackQueen.png") );
      parent = (JPanel)chessBoard.getComponent(blackLocation);
        parent.add(pieces);
    }
  }

  else{
    if (c instanceof JLabel){
            Container parent = c.getParent();
            parent.remove(0);
            parent.add( chessPiece );
        }
        else {
            Container parent = (Container)c;
            parent.add( chessPiece );
        }
      chessPiece.setVisible(true);
  }
}
    }

    public void mouseClicked(MouseEvent e) {

    }
    public void mouseMoved(MouseEvent e) {
   }
    public void mouseEntered(MouseEvent e){

    }
    public void mouseExited(MouseEvent e) {

    }

	/*
		Main method that gets the ball moving.
	*/
    public static void main(String[] args) {
        JFrame frame = new ChessProject();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo( null );
        frame.setVisible(true);
     }
}
