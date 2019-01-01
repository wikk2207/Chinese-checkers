package tp.chinesecheckers.Server.GameBoard;

import java.lang.Math;

public class DefaultMoveController implements MoveController {

  @Override
  public boolean isMovePossible(GameBoard gameBoard, int begX, int begY, int endX, int endY) {
    int x = endX - begX;
    int y = endY - begY;

    if (!gameBoard.isFree(endX, endY)) {
      return false;
    }
    if (!moveDirection(x, y)) {
      return false;
    }
    if (Math.abs(x) == 2 || Math.abs(y) == 2) {
      return !gameBoard.isFree(begX + (x / 2), begY + (y / 2));
    }
    return true;
  }

  @Override
  public boolean isJump(int begX, int begY, int endX, int endY) {
    int x = endX - begX;
    int y = endY - begY;
    return ((Math.abs(x) == 2 && Math.abs(y) == 0) || (Math.abs(x) == 0 && Math.abs(y) == 2)
        || (x == 2 && y == 2) || (x == -2 && y == -2));

  }

  private boolean moveDirection(int x, int y) {
    for (int i = -2; i < 3; i++) {
      if (x == 0 && y == i) {
        return true;
      } else if (x == i && y == 0) {
        return true;
      } else if (x == i && y == i) {
        return true;
      }
    }
    return false;
  }
}
