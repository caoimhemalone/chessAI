import java.util.*;

public class AIAgent{
  Random rand;
  boolean possibleAttack = false;

  public AIAgent(){
    rand = new Random();
  }

/*
  The method randomMove takes as input a stack of potential moves that the AI agent
  can make. The agent uses a rondom number generator to randomly select a move from
  the inputted Stack and returns this to the calling agent.
*/

// ****** RANDOM MOVE ******
  public Move randomMove(Stack possibilities){

    int moveID = rand.nextInt(possibilities.size());
    System.out.println("Agent randomly selected move : "+moveID);
    for(int i=1;i < (possibilities.size()-(moveID));i++){
      possibilities.pop();
    }
    Move selectedMove = (Move)possibilities.pop();
    return selectedMove;
  }


// ****** NEXT BEST MOVE ******
  public Move nextBestMove(Stack whitePossibilities, Stack blackPossibilities){
    Stack backup = (Stack) whitePossibilities.clone();
    Stack black = (Stack) blackPossibilities.clone();
    Move whiteMove, move, attackMove;
    Square blackPosition;
    int Point = 0;
    int pieceChosen = 0;
    attackMove = null;

    while (!whitePossibilities.empty()) {
      whiteMove = (Move) whitePossibilities.pop();
      move = whiteMove;

      // 1 point given for centre position on the board
      if ((move.getStart().getYC() < move.getLanding().getYC())
        && (move.getLanding().getXC()== 3) && (move.getLanding().getYC() == 3) //Check if the x and y landing coordinates are equal to 3
        || (move.getLanding().getXC() == 4) && (move.getLanding().getYC() == 3) // OR ......
        || (move.getLanding().getXC() == 3) && (move.getLanding().getYC() == 4)
        || (move.getLanding().getXC() == 4) && (move.getLanding().getYC() == 4)) {
          Point = 0;
          //assign the best moves
          if (Point > pieceChosen) {
            pieceChosen = Point;
            attackMove = move;
          }
        }
        // If the piece has a higher point value than the centre position then return an attackMove
        // The BlackPawn has 2 points for this demonstration so that the centre position is priotised over the random move

        while (!black.isEmpty()){
          Point = 0;
          blackPosition = (Square) black.pop();
          if ((move.getLanding().getXC() == blackPosition.getXC())
          && (move.getLanding().getYC() == blackPosition.getYC())) {
            //Check for black pawn and assign points
             if (blackPosition.getName().equals("BlackPawn")) {
                 Point = 2;
             }
             //Check for black bishop/knight and assign points
             else if (blackPosition.getName().equals("BlackBishop")
            || blackPosition.getName().equals("BlackKnight")) {
                 Point = 3;
             }
             //Check for black rook and assign points
             else if (blackPosition.getName().equals("BlackRook")) {
                 Point = 5;
             }
             //Check for black queen and assign points
             else if (blackPosition.getName().equals("BlackQueen")) {
                 Point = 9;
             }
          }

          //Now we need to update the best moves
          if (Point > pieceChosen) {
            pieceChosen = Point;
            attackMove = move;
          }
        }
        // Now the black squares need to load again
        black = (Stack) blackPossibilities.clone();
    }

    //Now we see if we should use the next best move or just use the random move
    if (pieceChosen > 0) {
      System.out.println("Agent selected best move : " + pieceChosen);
      return attackMove;
    }
    return randomMove(backup); // if the piece chosen is less than zero than as a backup we return the random move
  }


// ****** TWO LEVELS DEEP ******
  public Move twoLevelsDeep(Stack whitePossibilities, Stack blackPossibilities){
    Stack backup = (Stack) whitePossibilities.clone();
    Stack black = (Stack) blackPossibilities.clone();
    Move whiteMove, move, attackMove;
    Square blackPosition;
       int Point = 0;
       int pieceChosen = 0;
       attackMove = null;

       while (!whitePossibilities.empty()) {
           whiteMove = (Move) whitePossibilities.pop();
           move = whiteMove;

    //The same as next best move we assign 1 point to the centre of the board
    if ((move.getStart().getYC() < move.getLanding().getYC())
      && (move.getLanding().getXC()== 3) && (move.getLanding().getYC() == 3) //Check if the x and y landing coordinates are equal to 3
      || (move.getLanding().getXC() == 4) && (move.getLanding().getYC() == 3) // OR ......
      || (move.getLanding().getXC() == 3) && (move.getLanding().getYC() == 4)
      || (move.getLanding().getXC() == 4) && (move.getLanding().getYC() == 4)) {
        Point = 0;

    //Again the same as before we assign the best move
    if (Point > pieceChosen) {
      pieceChosen = Point;
      attackMove = move;
    }
  }
  // We do the same as above when it comes to the attacking move having a higher point than the centre of the chess board
  while (!black.isEmpty()){
    Point = 0;
    blackPosition = (Square) black.pop();
    if ((move.getLanding().getXC() == blackPosition.getXC())
    && (move.getLanding().getYC() == blackPosition.getYC())) {
      //Check for black pawn and assign points
       if (blackPosition.getName().equals("BlackPawn")) {
           Point = 2;
       }
       //Check for black bishop/knight and assign points
       else if (blackPosition.getName().equals("BlackBishop")
      || blackPosition.getName().equals("BlackKnight")) {
           Point = 3;
       }
       //Check for black rook and assign points
       else if (blackPosition.getName().equals("BlackRook")) {
           Point = 5;
       }
       //Check for black queen and assign points
       else if (blackPosition.getName().equals("BlackQueen")) {
           Point = 9;
       }
    }

    //Now we need to update the best moves
    if (Point > pieceChosen) {
      pieceChosen = Point;
      attackMove = move;
    }
  }

  //Reload the black squares
  black = (Stack) blackPossibilities.clone();
}
//Check if we can use the best move available or do we need to use a random move
if (pieceChosen > 0) {
            System.out.println("The two level deep move selected by the AI agent:" + pieceChosen);
            return attackMove;
        }
        return randomMove(backup);
      }
}
