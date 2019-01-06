package boot;

import com.sun.xml.internal.bind.v2.TODO;
import tp.chinesecheckers.Server.GameBoard.Game;
import tp.chinesecheckers.Server.GameBoard.Player;

import java.util.ArrayList;

public class Boot implements Player {
  private boolean correctMove;
  private boolean myTurn;
  private int opponents;
  private int myId;
  private int myRealId;
  private int[] playersIds;
  private int size;
  private int pawns;
  private int[][] gameBoard;
  private Game game;
  private int[][] achievedGoals;


  public Boot(int boardSize, Game game, int id) {
    myId = 1;
    this.size = boardSize;
    this.myRealId = id;
    this.game = game;
    gameBoard = new int[boardSize][boardSize];
    pawns = (size - 1) / 4;
    playersIds = new int[6];
    this.game = game;
    configureGameBoard();

    achievedGoals = new int[size][size];
    configureGoals();
  }
  private void configureGoals() {
    int i, j, x, y;
    for ( i = 0; i < size; i++) {
      for (j = 0; j < size; j++) {
        achievedGoals[i][j] = -1;
      }
    }
    for (y = size - pawns, i = 0; i < pawns; i++) {
      for (x = size - pawns - 1, j = 0; j < pawns - i; j++) {
        achievedGoals[x - j][y + i] = 0;
      }
    }

    if(gameBoard[12][16] == myId) {
      achievedGoals[12][16]=1;
      if (gameBoard[12][15]==myId) achievedGoals[12][15]=1;
      if (gameBoard[11][15]==myId) achievedGoals[11][15]=1;

      if (gameBoard[12][15]==myId&&gameBoard[11][15]==myId) {
        if (gameBoard[10][14]==myId) achievedGoals[10][14]=1;
        if (gameBoard[11][14]==myId) achievedGoals[11][14]=1;
        if (gameBoard[12][14]==myId) achievedGoals[12][14]=1;

        if (gameBoard[10][14]==myId&&gameBoard[11][14]==myId&&gameBoard[12][14]==myId) {
          if (gameBoard[9][13]==myId) achievedGoals[9][13]=1;
          if (gameBoard[10][13]==myId) achievedGoals[10][13]=1;
          if (gameBoard[11][13]==myId) achievedGoals[1][13]=1;
          if (gameBoard[12][13]==myId) achievedGoals[12][13]=1;
        }
      }
    }
  }


  //todo
  //for test
  public static void main(String args[]) {
    Boot boot = new Boot(17, new Game(), 1);
    //boot.setId(2);
    boot.setOpponetsNum(5);
    boot.start();
    boot.otherPlayerMoved(3,4,10,8,8);
    try {
      boot.movePawn(5,3,5,4);
     // boot.movePawn(4,0,5,6);
    } catch (MovingPawnBootException e) {
      System.out.println(e.getMessage());
    }
    for (int j = 16; j >= 0; j--) {
      for (int i = 0; i < 17; i++) {
        if (boot.getGameBoard()[i][j] != -1) {
          System.out.print(" ");
        }
        System.out.print(boot.getGameBoard()[i][j] + " ");
      }
      System.out.println();
    }

    BestMove bestMove = new BestMove(boot.getGameBoard(), 17, 1, null);
    Path bestPath = bestMove.chooseBestPath();
    int fromX = bestPath.getFromX();
    int fromY = bestPath.getFromY();
    int toX = bestPath.getToX();
    int toY = bestPath.getToY();
    try {
      boot.movePawn(fromX,fromY,toX,toY);
    } catch (MovingPawnBootException e) {
      System.out.println(e.getMessage());
    }
    for (int j = 16; j >= 0; j--) {
      for (int i = 0; i < 17; i++) {
        if (boot.getGameBoard()[i][j] != -1) {
          System.out.print(" ");
        }
        System.out.print(boot.getGameBoard()[i][j] + " ");
      }
      System.out.println();
    }

  }


  @Override
  public void start() {
    for (int i = 0; i < opponents + 1;i++) {
      addPlayerToBoard(playersIds[i]);
    }
  }

  @Override
  public void otherPlayerMoved(int opponent, int begX, int begY, int endX, int endY) {
    //TODO
    //System.out.println(begX+ " " + begY+ " " + endX+ " " + endY);
    try {
      movePawn(begX,begY,endX,endY);
    } catch (MovingPawnBootException e) {
      System.out.println(e.getMessage());
    }
  }

  @Override
  public void yourTurn() {
    myTurn=true;
    configureGoals();
    BestMove bestMove=new BestMove(gameBoard, size, myId, achievedGoals);
    //TODO
    Path bestPath=bestMove.chooseBestPath();
    //Path bestPath=bestMove.chooseBestPath4();
    try {
      Thread.sleep(1000);
      for (int i=0; i<bestPath.size(); i++) {
        move(bestPath.getMove(i));
      }
    } catch (InterruptedException e) {
      System.out.println(e.getMessage());
    }

    //move(new Move(4,3,4,4,true,false,false));
    game.endMove(myRealId);
  }
  @Override
  public void turnEnd() {
    myTurn = false;
  }

  @Override
  public void setOpponetsNum(int players) {
    //TODO
    this.opponents = players - 1;
    //System.out.println("oopponents " +opponents);
    switch (opponents) {
      case 1:
        playersIds[0] = 1;
        playersIds[1] = 4;
        break;

      case 2:
        playersIds[0] = 1;
        playersIds[1] = 3;
        playersIds[2] = 5;
        break;

      case 3:
        playersIds[0] = 2;
        playersIds[1] = 3;
        playersIds[2] = 5;
        playersIds[3] = 6;
        break;

      case 5:
        playersIds[0] = 1;
        playersIds[1] = 2;
        playersIds[2] = 3;
        playersIds[3] = 4;
        playersIds[4] = 5;
        playersIds[5] = 6;
        break;
    }
  }

  @Override
  public void wrongMove() {
    this.correctMove = false;
  }

  @Override
  public void correctMove() {
    this.correctMove = true;
  }

  public void setId(int id) {
    this.myId = id;
  }

  private void addPlayerToBoard(int playerId)  {
    int x,y; //coordinates
    int i,j;

    switch (playerId) {
      case 1: {
        for (i = 0,y = pawns - 1; i < pawns; i++) {
          for (j = 0, x = pawns; j < pawns - i; j++) {
            gameBoard[x + j][y - i] = playerId;
          }
        }
        break;
      }
      case 2: {
        for (i = 0, y = pawns; i < pawns; i++) {
          for (x = pawns - 1, j = 0; j < pawns - i; j++) {
            gameBoard[x - j][y + i] = playerId;
          }
        }
        break;
      }
      case 3: {
        for (y = size - pawns - 1, i = 0; i < pawns; i++) {
          for (x = pawns, j = 0; j < pawns - i; j++) {
            gameBoard[x + j][y - i] = playerId;
          }
        }
        break;
      }
      case 4: {
        for (y = size - pawns, i = 0; i < pawns; i++) {
          for (x = size - pawns - 1, j = 0; j < pawns - i; j++) {
            gameBoard[x - j][y + i] = playerId;
          }
        }


        break;
      }
      case 5: {

        for (y = size - pawns - 1, i = 0; i < pawns; i++) {
          for (x = size - pawns, j = 0; j < pawns - i; j++) {
            gameBoard[x + j][y - i] = playerId;
          }
        }

        break;
      }
      case 6: {
        for (y = pawns, i = 0; i < pawns; i++) {
          for (x = size - pawns - 1, j = 0; j < pawns - i; j++) {
            gameBoard[x - j][y + i] = playerId;
          }
        }
        break;
      }
    }
  }

  //todo method for tests
  public int[][] getGameBoard() {
    return this.gameBoard;
  }

  private void configureGameBoard() {
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
  }

  public void movePawn(int fromX, int fromY, int toX, int toY) throws MovingPawnBootException {
    if (fromX > size - 1 || fromX < 0 || fromY > size - 1 || fromY < 0 || toX > size - 1 || toX < 0 || toY > size - 1 || toY < 0) {
      throw new MovingPawnBootException("Out of array");
    }
    if (gameBoard[fromX][fromY] == -1 || gameBoard[toX][toY] == -1) {
      throw new MovingPawnBootException("Out of the board");
    }
    if (gameBoard[fromX][fromY] == 0) {
      throw  new MovingPawnBootException("Field where you begin movement is empty.");
    }
    if (gameBoard[toX][toY] != 0) {
      throw new MovingPawnBootException("Field where you end movement isn't empty.");
    }
    gameBoard[toX][toY] = gameBoard[fromX][fromY];
    gameBoard[fromX][fromY] = 0;
  }

  private void move(Move move) {
    try {
      movePawn(move.getFromX(), move.getFromY(), move.getToX(), move.getToY());
      game.move(myRealId, move.getFromX(), move.getFromY(), move.getToX(), move.getToY());
    } catch (MovingPawnBootException e) {
      System.out.println(myRealId + e.getMessage());
    }
    //TODO
    //System.out.println("Send Move: "+ myRealId +" " + move.getFromX() + " " + move.getFromY() + " " + move.getToX() + " " + move.getToY());

  }

}
