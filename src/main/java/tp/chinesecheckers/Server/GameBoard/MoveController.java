package tp.chinesecheckers.Server.GameBoard;

public interface MoveController {
  boolean isMovePossible(GameBoard gameBoard, int begX, int begY, int endX, int endY);
  boolean isJump(int begX, int begY, int endX, int endY);
}
