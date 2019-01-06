package boot;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BestMove {
  private int[][] gameBoard;
  private int size;
  private int myId;
  private int pawns;
  public boolean[][] goals; //todo private
  private ArrayList<Path> possiblePaths;
  //private boolean previousWasJump;
  private boolean firstStep;
  private Random random;
  int[][] achieved;


  BestMove(int[][] gameBoard, int size, int myId, int[][] achieved) {
    this.gameBoard = gameBoard;
    this.size = size;
    this.myId = myId;
    this.achieved = achieved;
    pawns = (size - 1) / 4;
    random = new Random();

    goals = new boolean[size][size];
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        goals[i][j] = false;
      }
    }
    setGoals();//todo first set id
    possiblePaths = new ArrayList<>();
    //previousWasJump = false;
    firstStep = true;

    checkPawnsToFindBestMove();

  }

  //TODO for test
  public static void main(String args[]) {
    int[][] gameBoard = new int[17][17];
    int size = 17;
    int pawns = 4;

    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        gameBoard[i][j] = -1;
      }
    }
    for (int y = 0; y < size - pawns; y++) {
      for (int x = pawns; x < pawns + y + 1; x++) {
        gameBoard[x][y] = 0;
      }
    }
    for (int y = pawns; y < size; y++) {
      for (int x = size - pawns - 1; x > y - pawns - 1; x--) {
        gameBoard[x][y] = 0;
      }
    }

    for (int i = 0,y = pawns - 1; i < pawns; i++) {
      for (int j = 0, x = pawns; j < pawns - i; j++) {
        gameBoard[x + j][y - i] = 1;
      }
    }
//moves

    gameBoard[5][3] = 0;
    gameBoard[5][4] = 1;
    gameBoard[4][0] = 0;
    gameBoard[12][16] = 1;

    int[][] tab = new int[17][17];
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        gameBoard[i][j] = 0;
      }
    }



    BestMove bestMove = new BestMove(gameBoard, 17, 1,tab);

    ArrayList<Path> possiblePaths = bestMove.getPossibleMoves();
    Path bestPath = bestMove.chooseBestPath();
    System.out.print("Best move: ");
    System.out.println(bestPath.getFromX() +" "+ bestPath.getFromY() + " " +bestPath.getToX() + " " + bestPath.getToY());

    for (int i = 0; i < possiblePaths.size(); i++) {
      System.out.println(i + ". New way... " + possiblePaths.get(i).getLength() + " " +possiblePaths.get(i).getDistanceToGoal() );
      for (int j = 0; j < possiblePaths.get(i).size(); j++) {
        System.out.println("  " + possiblePaths.get(i).get(j).getFromX()
            + " " + possiblePaths.get(i).get(j).getFromY()
            + " " + possiblePaths.get(i).get(j).getToX()
            + " " + possiblePaths.get(i).get(j).getToY());
      }
    }

    for (int j = 16; j >= 0; j--) {
      for (int i = 0; i < 17; i++) {
        if (gameBoard[i][j] != -1) {
          System.out.print(" ");
        }
        System.out.print(gameBoard[i][j] + " ");
      }
      System.out.println();
    }
    for (int j = 16; j >= 0; j--) {
      for (int i = 0; i < 17; i++) {
        if (bestMove.getGoals()[i][j]) {
          System.out.print("1 ");
        } else {
          System.out.print("0 ");
        }
      }
      System.out.println();
    }



  }


  private void checkPawnsToFindBestMove() {
    int pawnNumber = 1;
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if (gameBoard[i][j] != myId) {
          continue;
        }
        if(gameBoard[i][j] == myId && achieved[i][j] == 1) {
          continue;
        }
        checkWays(pawnNumber,i, j, true,false);
        pawnNumber++;
      }
    }
  }

  private void setGoals() {
    int i;
    int j;
    int x;
    int y;

    if(gameBoard[12][16] != myId) {
      goals[12][16] = true;
    } else if(gameBoard[12][15] != myId || gameBoard[11][15] != myId) {
        goals[12][15] = true;
        goals[11][15] = true;
    } else if (gameBoard[10][14] != myId || gameBoard[11][14] != myId || gameBoard[12][14] != myId) {
      goals[10][14] = true;
      goals[11][14] = true;
      goals[12][14] = true;
    } else {
      goals[9][13] = true;
      goals[10][13] = true;
      goals[11][13] = true;
      goals[12][13] = true;
    }

    for (y = size - pawns, i = 0; i < pawns; i++) {
      for (x = size - pawns - 1, j = 0; j < pawns - i; j++) {
        if (goals[x - j][y + i] && gameBoard[x - j][y + i] == myId) {
          goals[x - j][y + i] = false;
        }
      }
    }

  }

  private void checkWays(int pawnNumber, int fromX, int fromY, boolean isFirst, boolean previousWasJump) {
    Move[] moves = arrayOfPossibleMoves(fromX, fromY, isFirst, previousWasJump);

    if(moves == null) return;

    int sizeWhileCreatingNewBranch;
    int lastX;
    int lastY;

    for (int i = 0; i < 12; i++) {
      if(moves[i] != null) {
        break;
      }
    }

    for (int i = 0; i < 12; i++) {
      if(moves[i] == null) continue;

      if (moves[i].isFirst()) {
        possiblePaths.add(new Path(pawnNumber,moves[i].getFromX(), moves[i].getFromY())); //dodaj nową ścieżkę dla tej możliwości
        possiblePaths.get(possiblePaths.size() - 1).add(moves[i]); //dodaj do ścieżki ten ruch
        possiblePaths.get(possiblePaths.size() -1).setToX(moves[i].getToX());
        possiblePaths.get(possiblePaths.size() -1).setToY(moves[i].getToY());


        if(moves[i].isJump()) { //jeśli to był skok
          checkWays(pawnNumber, moves[i].getToX(),moves[i].getToY(),false,true);
        }
      } else {
        if (wereIHere(moves[i],possiblePaths.size()-1)) continue;
        else {
          sizeWhileCreatingNewBranch = possiblePaths.get(possiblePaths.size() - 1).size();
              // rozgałęziamy

          int startX = possiblePaths.get(possiblePaths.size()-1).getFromX();
          int startY = possiblePaths.get(possiblePaths.size()-1).getFromY();
          possiblePaths.add(new Path(pawnNumber, startX, startY));
          //przepisz pierwsze ruchy z poprzedniej ścieżki
          for (int k = 0; k < sizeWhileCreatingNewBranch; k++) {
            possiblePaths.get(possiblePaths.size() - 1).add(possiblePaths.get(possiblePaths.size()-2).get(k));
          }
          possiblePaths.get(possiblePaths.size() - 1).add(moves[i]);
          lastX = possiblePaths.get(possiblePaths.size() - 1).get(possiblePaths.get(possiblePaths.size() - 1).size() - 1).getToX();
          lastY = possiblePaths.get(possiblePaths.size() - 1).get(possiblePaths.get(possiblePaths.size() - 1).size() - 1).getToY();
          possiblePaths.get(possiblePaths.size() -1).setToX(lastX);
          possiblePaths.get(possiblePaths.size() -1).setToY(lastY);
          checkWays(pawnNumber, lastX, lastY, false,true);
        }
      }
    }

  }

  private boolean wereIHere(Move move, int indexOfPath) {
    int j = indexOfPath;
    //sprawdź wszystkie ruchy danej ścieżki
    for (int k = 0; k < possiblePaths.get(j).size(); k++) {
      //sprawdż ruchy w tej ścieżce
      int fromXX=possiblePaths.get(j).get(k).getFromX();
      int fromYY=possiblePaths.get(j).get(k).getFromY();
      int toXX=possiblePaths.get(j).get(k).getToX();
      int toYY=possiblePaths.get(j).get(k).getToY();

      if (fromXX==move.getToX()&&fromYY==move.getToY()) {
        return true;
      }
    }
    return false;
  }

  private boolean checkJump(int fromX, int fromY, int toX, int toY) {
    boolean result;

    if (toX > fromX) {
      if (toY > fromY) {
        result = (gameBoard[toX - 1][toY - 1] != 0 && gameBoard[toX - 1][toY - 1] != -1);
      } else {
        result = (gameBoard[toX - 1][toY] != 0 && gameBoard[toX - 1][toY] != -1);
      }
    } else if (toX == fromX) {
      if (toY > fromY) {
        result = (gameBoard[toX][toY - 1] != 0 && gameBoard[toX][toY - 1] != -1);
      } else {
        result = (gameBoard[toX][toY + 1] != 0 && gameBoard[toX][toY + 1] != -1);
      }
    } else {
      if (toY < fromY) {
        result = (gameBoard[toX + 1][toY + 1] != 0 && gameBoard[toX + 1][toY + 1] != -1);
      } else {
        result = (gameBoard[toX + 1][toY] != 0 && gameBoard[toX + 1][toY] != -1);
      }
    }
    firstStep = false;
    return result;
  }

  /**
   * The method checks if move is possible according to game rules,
   * locations of pawns and construction of the board.
   * @return True if movement is possible and false if is not.
   */
  public boolean isMovePossible(Move move) {
    int fromX = move.getFromX();
    int fromY = move.getFromY();
    int toX = move.getToX();
    int toY = move.getToY();
    boolean isJump = move.isJump();
    boolean isFirst = move.isFirst();

    boolean result;
    if (fromX > size - 1 || fromX < 0 || fromY > size - 1 || fromY < 0
        || toX > size - 1 || toX < 0 || toY > size - 1 || toY < 0) {
      return false; //out of the array
    }
    if (gameBoard[fromX][fromY] == -1 || gameBoard[toX][toY] == -1)  {
      return false; //out of the board
    }
    if (gameBoard[fromX][fromY] == 0) {
      if(move.isFirst()) {
        return false; //empty 'fromField'
      }
    }
    if (gameBoard[toX][toY] != 0) {
      return false; //not empty 'toField'
    }

    if(goals[move.getFromX()][move.getFromY()] == true) return false;


    if (isFirst) {
      if (isJump) {
        result = checkJump(fromX,fromY,toX,toY);
      } else {
        result = true;
      }
    } else {
      if (isJump) {
        if (move.isPreviousWasJump()) {
          result = checkJump(fromX,fromY,toX,toY);
        } else {
          result = false;
        }
      } else {
        result = false;
      }
    }

    return result;
  }

  private Move[] arrayOfPossibleMoves( int x, int y, boolean isFirst, boolean previousWasJump) {
    if(goals[x][y] == true) return null;

    Move[] fields = new Move[12];

    if(isFirst) {
      fields[0] = new Move( x, y, x, y + 1,  isFirst,false, previousWasJump);
      fields[1] = new Move(x, y, x + 1, y + 1,  isFirst,false, previousWasJump);
      fields[2] = new Move(x, y, x + 1, y,  isFirst,false, previousWasJump);
      fields[3] = new Move(x, y, x, y - 1,  isFirst,false, previousWasJump);
      fields[4] = new Move(x, y, x - 1, y - 1,  isFirst,false, previousWasJump);
      fields[5] = new Move(x, y, x - 1, y,  isFirst,false, previousWasJump);
    } else {
      for (int j = 0; j < 6; j++) {
        fields[j] = null;
      }
    }
    fields[6] = new Move(x, y, x, y + 2,  isFirst,true, previousWasJump);
    fields[7] = new Move(x, y, x + 2, y + 2,  isFirst,true, previousWasJump);
    fields[8] = new Move(x, y, x + 2, y,  isFirst,true, previousWasJump);
    fields[9] = new Move(x, y, x, y - 2,  isFirst,true, previousWasJump);
    fields[10] = new Move(x, y, x - 2, y - 2,  isFirst,true, previousWasJump);
    fields[11] = new Move(x, y, x - 2, y,  isFirst,true, previousWasJump);


    int i;
    if (isFirst) i = 0;
    else i=6;
    for (; i < 12; i++) {
      if (!isMovePossible(fields[i])) {
        fields[i] = null;
      }
    }
    return fields;
  }

  private void calculateLength(int indexOfPath) {
    int firstX = possiblePaths.get(indexOfPath).getFromX();
    int firstY = possiblePaths.get(indexOfPath).getFromY();
    int lastX = possiblePaths.get(indexOfPath).getToX();
    int lastY = possiblePaths.get(indexOfPath).getToY();

    int length = Math.abs(lastX -firstX) + Math.abs(lastY-firstY);
    possiblePaths.get(indexOfPath).setLength(length);
  }

  private int calculatePathToGoal(int fromX, int fromY) {
    int bestDistance = 25;
    int distance;
    for(int i = 0; i < size ; i++) {
      for (int j = 0 ; j < size; j++) {
        if(goals[i][j] == false) continue;
        distance = Math.abs(fromX -i) + Math.abs(fromY-j);
        if (distance < bestDistance) bestDistance = distance;
      }
    }
    return bestDistance;
  }

  private void calculateDistanceToGoal(int indexOfPath) {
    int bestDistance = 0;
    int distance;
    int lastX = possiblePaths.get(indexOfPath).getToX();
    int lastY = possiblePaths.get(indexOfPath).getToY();

    for(int i = 0; i < size ; i++) {
      for (int j = 0 ; j < size; j++) {
        if(goals[i][j] == false) continue;
        distance = Math.abs(lastX -i) + Math.abs(lastY-j);
        if (distance > bestDistance) bestDistance = distance;
      }
    }

    possiblePaths.get(indexOfPath).setDistanceToGoal(bestDistance);
  }

  // TODO test
  public ArrayList<Path> getPossibleMoves() {
    return possiblePaths;
  }

  public Path chooseBestPath() {
    for (int j = 0; j < possiblePaths.size(); j++) {
      calculateDistanceToGoal(j);
      calculateLength(j);
    }

    ArrayList<Path> closestToDestination = new ArrayList<>();
    int goalDist=24;
    int pathLength=0;
    int index=0;
    for(int i = 0; i < possiblePaths.size(); i++) {
      if(possiblePaths.get(i).getDistanceToGoal() < goalDist) {
        goalDist = possiblePaths.get(i).getDistanceToGoal();
      }
    }


    for(int i = 0; i < possiblePaths.size(); i++) {
      if(possiblePaths.get(i).getDistanceToGoal() == goalDist) {
        closestToDestination.add(possiblePaths.get(i));
      }
    }
    index = random.nextInt(closestToDestination.size());

    /*for(int i = 0; i < closestToDestination.size(); i++) {
      if(closestToDestination.get(i).getLength() > pathLength) {
        pathLength = closestToDestination.get(i).getLength();
        index=i;
      }
    }*/
    //LOSOWO


    return closestToDestination.get(index);
  }

  //TODO testy
  public boolean[][] getGoals() {
    return goals;
  }
}
