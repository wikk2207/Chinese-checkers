package board;

import org.junit.Assert;
import org.junit.Test;

public class HexagonTest {
  @Test
  public void testContains_returnsTrue() {
    Hexagon hexagon = new Hexagon(0,0,0,0);
    final boolean actual = hexagon.contains(15,27);
    Assert.assertTrue(actual);
  }

  @Test
  public void testContains_returnFalse() {
    Hexagon hexagon = new Hexagon(0,0,0,0);
    final boolean actual = hexagon.contains(15,40);
    Assert.assertFalse(actual);
  }

}
