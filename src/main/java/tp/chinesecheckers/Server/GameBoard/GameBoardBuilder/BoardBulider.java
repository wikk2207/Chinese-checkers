package tp.chinesecheckers.Server.GameBoard.GameBoardBuilder;

import tp.chinesecheckers.Server.GameBoard.GameBoard;

public abstract class BoardBulider {

  protected GameBoard gameBoard;

  public void createFrame() {
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        gameBoard.addPawn(i, j, -1);
        gameBoard.addPawn(16 - i, j, -1);
        gameBoard.addPawn(i, 16 - j, -1);
        gameBoard.addPawn(16 - i, 16 - j, -1);
      }
    }
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 4; j++) {
        gameBoard.addPawn(8 + i, j, -1);
        gameBoard.addPawn(4 +  i, 13 + j, -1);
        gameBoard.addPawn(13 + j, 4 + i, -1);
        gameBoard.addPawn(j, 8 + i, -1);
      }
    }
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j <= i; j++) {
        gameBoard.addPawn(5 + i, j, -1);
        gameBoard.addPawn(14 +  i, 9 + j, -1);
        gameBoard.addPawn(j, 5 + i, -1);
        gameBoard.addPawn(9 + j, 14 +  i, -1);
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
