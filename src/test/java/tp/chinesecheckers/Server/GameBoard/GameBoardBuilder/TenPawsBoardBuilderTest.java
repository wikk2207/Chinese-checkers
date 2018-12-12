package tp.chinesecheckers.Server.GameBoard.GameBoardBuilder;

import org.junit.Test;
import tp.chinesecheckers.Server.GameBoard.BoardBuilderDirector;
import tp.chinesecheckers.Server.GameBoard.GameBoard;

import static org.junit.Assert.assertFalse;

//TODO dodac testy
public class TenPawsBoardBuilderTest {
  GameBoard gameBoard;
  BoardBuilderDirector director;

  @Test
  public void addPlayerOneTest() {
    director = new BoardBuilderDirector();
    director.setGameMode(1);
    gameBoard = director.buildGameBoard(6);

    assertFalse(gameBoard.isFree(7, 3));
    assertFalse(gameBoard.isFree(6, 2));
    assertFalse(gameBoard.isFree(5, 1));
    assertFalse(gameBoard.isFree(5, 3));
  }

  @Test
  public void addPlayerTwoTest() {
    director = new BoardBuilderDirector();
    director.setGameMode(1);
    gameBoard = director.buildGameBoard(6);

    assertFalse(gameBoard.isFree(3, 5));
    assertFalse(gameBoard.isFree(1, 5));
    assertFalse(gameBoard.isFree(2, 6));
    assertFalse(gameBoard.isFree(3, 7));
  }

  @Test
  public void addPlayerThreeTest() {
    director = new BoardBuilderDirector();
    director.setGameMode(1);
    gameBoard = director.buildGameBoard(6);

    assertFalse(gameBoard.isFree(4, 9));
    assertFalse(gameBoard.isFree(5, 10));
    assertFalse(gameBoard.isFree(6, 11));
    assertFalse(gameBoard.isFree(7, 12));
  }

  @Test
  public void addPlayerFourTest() {
    director = new BoardBuilderDirector();
    director.setGameMode(1);
    gameBoard = director.buildGameBoard(6);

    assertFalse(gameBoard.isFree(9, 13));
    assertFalse(gameBoard.isFree(10, 14));
    assertFalse(gameBoard.isFree(11, 15));
    assertFalse(gameBoard.isFree(11, 13));
  }

  @Test
  public void addPlayerFifeTest() {
    director = new BoardBuilderDirector();
    director.setGameMode(1);
    gameBoard = director.buildGameBoard(6);

    assertFalse(gameBoard.isFree(13, 9));
    assertFalse(gameBoard.isFree(14, 10));
    assertFalse(gameBoard.isFree(15, 11));
    assertFalse(gameBoard.isFree(13, 10));
  }

  @Test
  public void addPlayerSixTest() {
    director = new BoardBuilderDirector();
    director.setGameMode(1);
    gameBoard = director.buildGameBoard(6);

    assertFalse(gameBoard.isFree(9, 4));
    assertFalse(gameBoard.isFree(10, 5));
    assertFalse(gameBoard.isFree(11, 6));
    assertFalse(gameBoard.isFree(10, 4));
  }

}
