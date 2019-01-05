package boot;

import boot.Boot;
import boot.MovingPawnBootException;
import org.junit.Assert;
import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import tp.chinesecheckers.Server.GameBoard.Game;

public class BootTest {
  Boot boot;

  @Test
  public void otherPlayerMovedTest() {

    boot.otherPlayerMoved(1,5,3,8,8);

    int actualBeg = boot.getGameBoard()[5][3];
    int actualEnd = boot.getGameBoard()[8][8];

    Assert.assertEquals(0,actualBeg);
    Assert.assertEquals(1,actualEnd);
  }

  @Test
  public void movePawnTest() {
    try {
      boot.movePawn(3,4,4,4);
      int actualBeg = boot.getGameBoard()[3][4];
      int actualEnd = boot.getGameBoard()[4][4];
      Assert.assertEquals(0,actualBeg);
      Assert.assertEquals(2,actualEnd);
    } catch (MovingPawnBootException e) {
    }
  }

  //Beginning out of the board
  @Test (expected=MovingPawnBootException.class)
  public void movePawnTestExpectedException() throws MovingPawnBootException {
    boot.movePawn(0,0,4,4);
  }

  //End out of the board
  @Test (expected=MovingPawnBootException.class)
  public void movePawnTestExpectedException2() throws MovingPawnBootException {
    boot.movePawn(5,2,7,15);
  }

  //out of the array
  @Test (expected=MovingPawnBootException.class)
  public void movePawnTestExpectedException3() throws MovingPawnBootException {
    boot.movePawn(-1,3,6,6);
  }

  //Beginning is empty
  @Test (expected=MovingPawnBootException.class)
  public void movePawnTestExpectedException4() throws MovingPawnBootException {
    boot.movePawn(7,7,8,8);
  }

  //End is not empty
  @Test (expected=MovingPawnBootException.class)
  public void movePawnTestExpectedException5() throws MovingPawnBootException {
    boot.movePawn(5,3,2,5);
  }

  //TODO poprawić isMovePosiible
  /*
  @Test
  public void isMovePossibleTestWithoutJump() {
    boolean actual = boot.isMovePossible(6,3,6,4, false);
    Assert.assertTrue(actual);
  }
  @Test
  public void isMovePossibleTestWithJump() {
    boolean actual = boot.isMovePossible(6,2,6,4,true);
    Assert.assertTrue(actual);
  }
  @Test
  public void isMovePossibleTestWithoutJump2() {
    boolean actual = boot.isMovePossible(6,3,5,4,false);
    Assert.assertTrue(actual);
  }
  @Test
  public void isMovePossibleTestWithJump2() {
    boolean actual = boot.isMovePossible(6,3,6,5,true);
    Assert.assertFalse(actual);
  }
  */




  @Before
  public void createBoot() {
    boot = new Boot(17, new Game(),1);
    boot.setId(1);
    boot.setOpponetsNum(5);
    boot.start();
  }
}
