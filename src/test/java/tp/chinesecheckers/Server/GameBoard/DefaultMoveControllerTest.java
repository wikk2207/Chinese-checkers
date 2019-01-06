package tp.chinesecheckers.Server.GameBoard;
//TODO napisac testy

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DefaultMoveControllerTest {
  BoardBuilderDirector director;
  GameBoard gameBoard;
  DefaultMoveController controller;

  @Test
  public void isMovePossibleTest() {
    director = new BoardBuilderDirector();
    director.setGameMode(1);
    gameBoard = director.buildGameBoard(6);
    controller = new DefaultMoveController();

    //single move
    assertTrue(controller.isMovePossible(gameBoard, 4, 3, 4, 4));
    assertTrue(controller.isMovePossible(gameBoard, 5, 3, 6, 4));
    assertTrue(controller.isMovePossible(gameBoard, 10, 13, 9, 12));
    assertTrue(controller.isMovePossible(gameBoard, 11, 13, 11, 12));
    assertTrue(controller.isMovePossible(gameBoard, 6, 11, 7, 11));
    assertTrue(controller.isMovePossible(gameBoard, 13, 11, 12, 11));

    //jump
    assertTrue(controller.isMovePossible(gameBoard, 4, 2, 4, 4));
    assertTrue(controller.isMovePossible(gameBoard, 4, 2, 6, 4));
    assertTrue(controller.isMovePossible(gameBoard, 11, 14, 9, 12));
    assertTrue(controller.isMovePossible(gameBoard, 11, 14, 11, 12));
    assertTrue(controller.isMovePossible(gameBoard, 5, 11, 7, 11));
    assertTrue(controller.isMovePossible(gameBoard, 14, 11, 12, 11));

    assertTrue(controller.isJump(4, 2, 4, 4));
    assertTrue(controller.isJump( 4, 2, 6, 4));
    assertTrue(controller.isJump( 11, 14, 9, 12));
    assertTrue(controller.isJump( 11, 14, 11, 12));
    assertTrue(controller.isJump( 5, 11, 7, 11));
    assertTrue(controller.isJump( 14, 11, 12, 11));

    //random place
    assertFalse(controller.isMovePossible(gameBoard, 4, 3, 11, 2));
    assertFalse(controller.isMovePossible(gameBoard, 5, 3, 0, 0));
    assertFalse(controller.isMovePossible(gameBoard, 10, 13, 7, 7));
    assertFalse(controller.isMovePossible(gameBoard, 11, 13, 4, 12));

    //correct in full place
    assertFalse(controller.isMovePossible(gameBoard, 6, 11, 5, 11));
    assertFalse(controller.isMovePossible(gameBoard, 13, 11, 14, 11));
    assertFalse(controller.isMovePossible(gameBoard, 4, 2, 4, 3));
    assertFalse(controller.isMovePossible(gameBoard, 4, 2, 5, 3));

    //jump threw empty space
    assertFalse(controller.isMovePossible(gameBoard, 4, 3, 4, 5));
    assertFalse(controller.isMovePossible(gameBoard, 5, 3, 7, 5));
    assertFalse(controller.isMovePossible(gameBoard, 5, 10, 7, 10));
    assertFalse(controller.isMovePossible(gameBoard, 13, 11, 11, 11));
  }

  @Test
  public void isWinerTest() {
    director = new BoardBuilderDirector();
    director.setGameMode(1);
    gameBoard = director.buildGameBoard(6);
    controller = new DefaultMoveController();


    assertFalse(controller.isWiner(gameBoard, 1));
    assertFalse(controller.isWiner(gameBoard, 2));
    assertFalse(controller.isWiner(gameBoard, 3));
    assertFalse(controller.isWiner(gameBoard, 4));
    assertFalse(controller.isWiner(gameBoard, 5));
    assertFalse(controller.isWiner(gameBoard, 6));

    for (int i = 0; i < 4; i++) {
      for (int j = 0; j <= i; j++) {
        gameBoard.addPawn(4 + j, i, 4);
        gameBoard.addPawn(i, 4 + j, 5);
        gameBoard.addPawn(4 + j, 9 + i, 6);
        gameBoard.addPawn(9 + i, 13 + j, 1);
        gameBoard.addPawn(13 + j, 9 + i, 2);
        gameBoard.addPawn(9 + i, 4 + j, 3);
      }
    }

    assertTrue(controller.isWiner(gameBoard, 1));
    assertTrue(controller.isWiner(gameBoard, 2));
    assertTrue(controller.isWiner(gameBoard, 3));
    assertTrue(controller.isWiner(gameBoard, 4));
    assertTrue(controller.isWiner(gameBoard, 5));
    assertTrue(controller.isWiner(gameBoard, 6));

  }
}
