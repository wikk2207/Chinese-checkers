package tp.chinesecheckers.Server.GameBoard;

import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


//TODO napisac test do planszy
public class GameBordTest {
  GameBoard gameBoard;

  @Test
  public void addPawnTest() {
    gameBoard = new GameBoard(5, 5);
    gameBoard.addPawn(0, 0, 1);
    gameBoard.addPawn(3, 1, -1);
    gameBoard.addPawn(2, 4, 3);
    gameBoard.addPawn(2, 2, 4);
    assertFalse(gameBoard.isFree(0, 0));
    assertFalse(gameBoard.isFree(3, 1));
    assertFalse(gameBoard.isFree(2, 4));
    assertFalse(gameBoard.isFree(2, 2));
    assertTrue(gameBoard.isFree(1, 1));
    assertTrue(gameBoard.isFree(4, 0));
    assertTrue(gameBoard.isFree(0, 4));
    assertTrue(gameBoard.isFree(3, 2));
  }

  @Test
  public void movePawnTest() {
    gameBoard = new GameBoard(5, 5);
    gameBoard.addPawn(0, 0, 1);
    gameBoard.addPawn(3, 1, 2);
    try {
      gameBoard.movePawn(0, 0, 2, 1);
      gameBoard.movePawn(3, 1, 1, 3);

    } catch (CantRemovePawnException e) {
      assertTrue(false);
    }
    assertTrue(gameBoard.isFree(0, 0));
    assertTrue(gameBoard.isFree(3, 1));
    assertFalse(gameBoard.isFree(2, 1));
    assertFalse(gameBoard.isFree(1, 3));

  }

  @Test
  public void CantRemovePawnExceptionTest() {
    gameBoard = new GameBoard(5, 5);
    gameBoard.addPawn(3, 1, -1);
    try {
      gameBoard.movePawn(3, 1, 1, 3);
      fail();

    } catch (CantRemovePawnException e) {}
  }
}
