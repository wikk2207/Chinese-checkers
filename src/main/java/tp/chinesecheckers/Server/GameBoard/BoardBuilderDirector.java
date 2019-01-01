package tp.chinesecheckers.Server.GameBoard;

import tp.chinesecheckers.Server.GameBoard.GameBoardBuilder.BoardBulider;
import tp.chinesecheckers.Server.GameBoard.GameBoardBuilder.TenPawnsBoardBuilder;

public class BoardBuilderDirector {
  private BoardBulider boardBulider;

  /**
   *
   * @param gameMode
   */
  public void setGameMode(int gameMode) {
    if (gameMode == 1) {
      boardBulider = new TenPawnsBoardBuilder();
    } else {
      //Jak na razie to jedyny tryb
      boardBulider = null;
    }
  }

  /**
   *
   * @param players
   * @return
   */
  public GameBoard buildGameBoard(int players) {
    switch (players) {
      case 2:
        boardBulider.addPlayerOne();
        boardBulider.addPlayerFour();
        break;

      case 3:
        boardBulider.addPlayerOne();
        boardBulider.addPlayerThree();
        boardBulider.addPlayerFife();
        break;

      case 4:
        boardBulider.addPlayerTwo();
        boardBulider.addPlayerThree();
        boardBulider.addPlayerFife();
        boardBulider.addPlayerSix();
        break;

      case 6:
        boardBulider.addPlayerOne();
        boardBulider.addPlayerTwo();
        boardBulider.addPlayerThree();
        boardBulider.addPlayerFour();
        boardBulider.addPlayerFife();
        boardBulider.addPlayerSix();
        break;
    }
    return boardBulider.getBoard();
  }
}
