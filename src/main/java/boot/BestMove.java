package boot;

import java.util.ArrayList;

public class BestMove {
  private int[][] gameBoard;
  private int size;
  private int myId;
  private int pawns;
  public boolean[][] goals; //todo private
  private ArrayList<ArrayList<Move>> possibleMoves;
  private boolean previousWasJump;
  private boolean firstStep;


  BestMove(int[][] gameBoard, int size, int myId) {
    this.gameBoard=gameBoard;
    this.size=size;
    this.myId=myId;
    pawns=(size-1)/4;

    goals=new boolean[size][size];
    for (int i=0; i<size; i++) {
      for (int j=0; j<size; j++) {
        goals[i][j]=false;
      }
    }
    setGoals();//todo first set id
    possibleMoves=new ArrayList<>();
    previousWasJump=false;
    firstStep=true;

  }


  private void checkPawnsToFindBestMove() {
    for (int i=0; i<size; i++) {
      for (int j=0; j<size; j++) {
        if (gameBoard[i][j]!=myId) continue;
        checkWays(i, j);
      }
    }
  }

  private void setGoals() {
    int i, j, x, y;
    switch (myId) {
      case 1: {
        for (y=size-pawns, i=0; i<pawns; i++) {
          for (x=size-pawns-1, j=0; j<pawns-i; j++) {
            goals[x-j][y+i]=true;
          }
        }
        break;
      }
      case 2: {
        for (y=size-pawns-1, i=0; i<pawns; i++) {
          for (x=size-pawns, j=0; j<pawns-i; j++) {
            goals[x+j][y-i]=true;
          }
        }

        break;
      }
      case 3: {
        for (y=pawns, i=0; i<pawns; i++) {
          for (x=size-pawns-1, j=0; j<pawns-i; j++) {
            goals[x-j][y+i]=true;
          }
        }

        break;
      }
      case 4: {
        for (i=0, y=pawns-1; i<pawns; i++) {
          for (j=0, x=pawns; j<pawns-i; j++) {
            goals[x+j][y-i]=true;
          }
        }
        break;
      }
      case 5: {
        for (i=0, y=pawns; i<pawns; i++) {
          for (x=pawns-1, j=0; j<pawns-i; j++) {
            goals[x-j][y+i]=true;
          }
        }


        break;
      }
      case 6: {
        for (y=size-pawns-1, i=0; i<pawns; i++) {
          for (x=pawns, j=0; j<pawns-i; j++) {
            goals[x+j][y-i]=true;
          }
        }
        break;
      }
    }
  }

  private void checkWays(int fromX, int fromY) {
    Move[] moves=arrayOfPossibleMoves(fromX, fromY);

  }

  private boolean checkJump(int fromX, int fromY, int toX, int toY) {
    boolean result;

    if (toX>fromX) {
      if (toY>fromY) {
        if (gameBoard[toX-1][toY-1]!=0&&gameBoard[toX-1][toY-1]!=-1) {
          result = true;
        } else result = false;
      } else {
        if (gameBoard[toX-1][toY]!=0&&gameBoard[toX-1][toY]!=-1) {
          result = true;
        } else result = false;
      }
    } else if (toX==fromX) {
      if (toY>fromY) {
        if (gameBoard[toX][toY-1]!=0&&gameBoard[toX][toY-1]!=-1) {
          result = true;
        } else result = false;
      } else {
        if (gameBoard[toX][toY+1]!=0&&gameBoard[toX][toY+1]!=-1) {
          result = true;
        } else result = false;
      }
    } else {
        if (toY<fromY) {
          if (gameBoard[toX+1][toY+1]!=0&&gameBoard[toX+1][toY+1]!=-1) {
            result = true;
          } else result = false;
        } else {
          if (gameBoard[toX+1][toY]!=0&&gameBoard[toX+1][toY]!=-1) {
            result = true;
          } else result = false;
        }
      }
    previousWasJump = true;
    firstStep = false;
    return result;
  }

  public boolean isMovePossible( int fromX, int fromY, int toX, int toY, boolean isJump) {
    boolean result;

    if (fromX > size - 1 || fromX < 0 || fromY > size - 1 || fromY < 0
      || toX > size - 1 || toX < 0 || toY > size - 1 || toY < 0) return false; //out of the array
    if (gameBoard[fromX][fromY] == -1 || gameBoard[toX][toY] == -1)  return false; //out of the board
    if (gameBoard[fromX][fromY] == 0) return false; //empty 'fromField'
    if (gameBoard[toX][toY] != 0) return false; //not empty 'toField'


   if (firstStep) {
     if (isJump) {
       result = checkJump(fromX,fromY,toX,toY);
     } else {
       result = true;
     }
   } else {
     if (isJump) {
       if (previousWasJump) {
         result = checkJump(fromX,fromY,toX,toY);
       } else {
         result = false;
       }
     } else {
       if (previousWasJump) {
         result = false;
       } else {
         result = true;
       }
     }
   }

   firstStep = false;
   return result;
  }

  private Move[] arrayOfPossibleMoves(int x, int y) {
    Move[] fields = new Move[12];
    fields[0] = new Move(x, y, x, y + 1, false);
    fields[1] = new Move(x, y, x + 1, y + 1, false);
    fields[2] = new Move(x, y, x + 1, y, false);
    fields[3] = new Move(x, y, x, y - 1, false);
    fields[4] = new Move(x, y, x - 1, y - 1, false);
    fields[5] = new Move(x, y, x - 1, y, false);
    fields[6] = new Move(x, y, x, y + 2, true);
    fields[7] = new Move(x, y, x + 2, y + 2, true);
    fields[8] = new Move(x, y, x + 2, y, true);
    fields[9] = new Move(x, y, x, y - 2, true);
    fields[10] = new Move(x, y, x - 2, y - 2, true);
    fields[11] = new Move(x, y, x - 2, y, true);

    for (int i = 0; i < 12; i++) {
      if(!isMovePossible(fields[i].getFromX(), fields[i].getFromY(), fields[i].getToX(), fields[i].getToY(), fields[i].isJump())) {
        fields[i] = null;
      }
    }
    return fields;
  }
}
