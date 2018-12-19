package tp.chinesecheckers.Server.GameBoard.GameBoardBuilder;

import tp.chinesecheckers.Server.GameBoard.GameBoard;

public class TenPawnsBoardBuilder extends BoardBulider {

  public TenPawnsBoardBuilder() {
    this.gameBoard = new GameBoard(17, 17);
  }

  @Override
  public void addPlayerOne() {
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j <= i; j++) {
        gameBoard.addPawn(4 + j, i, 1);
      }
    }
  }

  @Override
  public void addPlayerTwo() {
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j <= i; j++) {
        gameBoard.addPawn(i, 4 + j, 2);
      }
    }
  }

  @Override
  public void addPlayerThree() {
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j <= i; j++) {
        gameBoard.addPawn(4 + j, 9 + i, 3);
      }
    }
  }

  @Override
  public void addPlayerFour() {
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j <= i; j++) {
        gameBoard.addPawn(9 + i, 13 + j, 4);
      }
    }
  }

  @Override
  public void addPlayerFife() {
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j <= i; j++) {
        gameBoard.addPawn(13 + j, 9 + i, 5);
      }
    }
  }

  @Override
  public void addPlayerSix() {
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j <= i; j++) {
        gameBoard.addPawn(9 + i, 4 + j, 6);
      }
    }
  }
}
