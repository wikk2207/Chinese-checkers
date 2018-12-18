package tp.chinesecheckers.Server.GameBoard.GameBoardBuilder;

import tp.chinesecheckers.Server.GameBoard.GameBoard;

public abstract class BoardBulider {

  protected GameBoard gameBoard;

  void createFrame() {
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        gameBoard.addPawn(i, j, -1);
        gameBoard.addPawn(15 - i, j, -1);
        gameBoard.addPawn(i, 15 - j, -1);
        gameBoard.addPawn(15 - i, 15 - j, -1);
      }
    }
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 4; j++) {
        gameBoard.addPawn(9 + i, j, -1);
        gameBoard.addPawn(4 +  i, 14 + j, -1);
        gameBoard.addPawn(14 + j, 4 + i, -1);
        gameBoard.addPawn(j, 9 + i, -1);
      }
    }
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j <= i; j++) {
        gameBoard.addPawn(6 + i, j, -1);
        gameBoard.addPawn(15 +  i, 10 + j, -1);
        gameBoard.addPawn(j, 6 + i, -1);
        gameBoard.addPawn(10 + j, 15 +  i, -1);
      }
    }
  }

  public abstract void addPlayerOne();

  public abstract void addPlayerTwo();

  public abstract void addPlayerThree();

  public abstract void addPlayerFour();

  public abstract void addPlayerFife();

  public abstract void addPlayerSix();

  public GameBoard getBoard() {
    return this.gameBoard;
  }

}
