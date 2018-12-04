package tp.chinesecheckers.Server.GameBoard.GameBoardBuilder;

import tp.chinesecheckers.Server.GameBoard.GameBoard;

public class TenPawnsBoardBuilder extends BoardBulider {

  public TenPawnsBoardBuilder() {
    this.gameBoard = new GameBoard();
  }

  @Override
  public void addPlayerOne() {
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < i; j++) {
        gameBoard.addPawn(5 + j, i, 1);
      }
    }
  }

  @Override
  public void addPlayerTwo() {
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < i; j++) {
        gameBoard.addPawn(i, 5 + j, 2);
      }
    }
  }

  @Override
  public void addPlayerThree() {
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < i; j++) {
        gameBoard.addPawn(5 + j, 10 + i, 3);
      }
    }
  }

  @Override
  public void addPlayerFour() {
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < i; j++) {
        gameBoard.addPawn(10 + i, 14 + j, 4);
      }
    }
  }

  @Override
  public void addPlayerFife() {
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < i; j++) {
        gameBoard.addPawn(14 + j, 10 + i, 5);
      }
    }
  }

  @Override
  public void addPlayerSix() {
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < i; j++) {
        gameBoard.addPawn(10 + i, 5 + j, 6);
      }
    }
  }
}
