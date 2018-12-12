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

  public void setGameMode(int mode) {
    if (!isModeSet) {
      boardBuilderDirector.setGameMode(mode);
      setMoveController(mode);
      isModeSet = true;
    }
  }

  public void setPlayerNumber(int players) {
    if (!isBoardCreated) {
      gameBoard = boardBuilderDirector.buildGameBoard(players);
      isBoardCreated = true;
    }
  }

  public boolean movePawn(int begX, int begY, int endX, int endY) {
    if (!moveController.isMovePossible(gameBoard, begX, begY, endX, endY)) {
      return false;
    } else {
      try {
        gameBoard.movePawn(begX, begY, endX, endY);
        return true;
      } catch (CantRemovePawnException e) {
        return false;
      }
    }
  }

  private void setMoveController(int mode) {
    //Jak na razie jedyna opcja
    if (mode == 1) {
      moveController = new DefaultMoveController();
    }
  }

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
