package tp.chinesecheckers.Server.GameBoard;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

//TODO napisac testy do mastera
public class GameMasterTest {
  GameMaster testedObject = GameMaster.getInstance();

  @Test
  public void movePawnTest() {
    testedObject.setGameMode(1);
    testedObject.setPlayerNumber(6);
    assertEquals(0, testedObject.movePawn(false, 4, 3, 4, 4));
    assertEquals(0, testedObject.movePawn(false, 6, 11, 6, 10));

    assertEquals(1, testedObject.movePawn(false, 2, 5, 4, 7));
    assertEquals(1, testedObject.movePawn(false, 14, 11, 12, 9));
    assertEquals(1, testedObject.movePawn(true, 10, 14, 10, 12));

    assertEquals(2, testedObject.movePawn(false, 4, 3, 4, 2));
    assertEquals(2, testedObject.movePawn(false, 4, 3, 8, 9));
    assertEquals(2, testedObject.movePawn(false, 4, 3, 4, 10));
    assertEquals(2, testedObject.movePawn(true, 4, 3, 4, 4));
  }
}
