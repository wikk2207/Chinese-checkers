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

  @Override
  public boolean isWiner(GameBoard gameBoard, int playerID) {
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j <= i; j++) {
        switch (playerID) {
          case 1:
            if (!gameBoard.isPlayer(9 + i, 13 + j, 1)) {
              return false;
            }
            break;

          case 2:
            if (!gameBoard.isPlayer(13 + j, 9 + i, 2)) {
              return false;
            }
            break;

          case 3:
            if (!gameBoard.isPlayer(9 + i, 4 + j, 3)) {
              return false;
            }
            break;

          case 4:
            if (!gameBoard.isPlayer(4 + j, i, 4)) {
              return false;
            }
            break;

          case 5:
            if (!gameBoard.isPlayer(i, 4 + j, 5)) {
              return false;
            }
            break;

          case 6:
            if (!gameBoard.isPlayer(4 + j, 9 + i, 6)) {
              return false;
            }
            break;

          default:
            return false;
        }
      }
    }
    return true;
  }

}
