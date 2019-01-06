package tp.chinesecheckers.Server.GameBoard;

public class GameMaster {

  private static volatile GameMaster instance;

  private boolean isModeSet;
  private boolean isBoardCreated;
  private BoardBuilderDirector boardBuilderDirector;
  private GameBoard gameBoard;
  private MoveController moveController;

  private GameMaster() {
    boardBuilderDirector = new BoardBuilderDirector();
    isModeSet = false;
    isBoardCreated = false;
  }

  /**
   *
   * @param mode
   */
  public void setGameMode(int mode) {
    if (!isModeSet) {
      boardBuilderDirector.setGameMode(mode);
      setMoveController(mode);
      isModeSet = true;
    }
  }

  /**
   *
   * @param players
   */
  public void setPlayerNumber(int players) {
    if (!isBoardCreated) {
      gameBoard = boardBuilderDirector.buildGameBoard(players);
      isBoardCreated = true;
    }
  }

  /**
   * Metoda zwraca informację o prawidłowości jednocześnie dokonując go na planszy serverowej.
   * Zwraca 2 gdy ruch jest niedozwolony.
   * Zwraca 1 gdy ruch jest dozwolony i gracz może kontynuować ruch.
   * Zwraca 0 gdy ruch jest dozwolony i gracz ma zakończyć ruch.
   * @param jump Informaca czy poprzedni ruch tego gracza był skokiem
   * @param begX
   * @param begY
   * @param endX
   * @param endY
   * @return
   */
  public int movePawn(boolean jump, int begX, int begY, int endX, int endY) {
    if (jump) {
      if (!moveController.isJump(begX, begY, endX, endY)) {
        return 2;
      }
    }
    if (!moveController.isMovePossible(gameBoard, begX, begY, endX, endY)) {
      return 2;
    } else {
      try {
        gameBoard.movePawn(begX, begY, endX, endY);
        return moveController.isJump(begX, begY, endX, endY) ? 1 : 0;
      } catch (CantRemovePawnException e) {
        return 2;
      }
    }
  }

  public boolean isWinner(int playerID) {
    return moveController.isWiner(gameBoard, playerID);
  }

  /**
   *
   * @param mode
   */
  private void setMoveController(int mode) {
    //Jak na razie jedyna opcja
    if (mode == 1) {
      moveController = new DefaultMoveController();
    }
  }

  /**
   *
   * @return
   */
  public static GameMaster getInstance() {
    if (instance == null) {
      synchronized (GameMaster.class) {
        if (instance == null) {
          instance = new GameMaster();
        }
      }
    }
    return instance;
  }


}
