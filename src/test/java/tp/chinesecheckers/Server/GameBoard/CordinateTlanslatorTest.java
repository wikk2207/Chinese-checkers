package tp.chinesecheckers.Server.GameBoard;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;

/**
 * Test klasy przeksztalcajacej wzgledne wspolrzedne gracza na bezwzgledne servera i odwrotnie
 */
public class CordinateTlanslatorTest {


  @Test
  public void playerToServerTest1() {
    int[] testingVect = { 7, 6, 4, 8};
    int[] expectedVect1 = {7, 6, 4, 8};
    int[] expectedVect2 = {6, 7, 8, 12};
    int[] expectedVect3 = {7, 9, 12, 12};
    int[] expectedVect4 = {9, 10, 12, 8};
    int[] expectedVect5 = {10, 9, 8, 4};
    int[] expectedVect6 = {9, 7, 4, 4};

    int[] result1 = CordinateTranslator.playerToServer(
        1, testingVect[0], testingVect[1], testingVect[2], testingVect[3]
    );
    int[] result2 = CordinateTranslator.playerToServer(
        2, testingVect[0], testingVect[1], testingVect[2], testingVect[3]
    );
    int[] result3 = CordinateTranslator.playerToServer(
        3, testingVect[0], testingVect[1], testingVect[2], testingVect[3]
    );
    int[] result4 = CordinateTranslator.playerToServer(
        4, testingVect[0], testingVect[1], testingVect[2], testingVect[3]
    );
    int[] result5 = CordinateTranslator.playerToServer(
        5, testingVect[0], testingVect[1], testingVect[2], testingVect[3]
    );
    int[] result6 = CordinateTranslator.playerToServer(
        6, testingVect[0], testingVect[1], testingVect[2], testingVect[3]
    );
    assertArrayEquals(expectedVect1, result1);
    assertArrayEquals(expectedVect2, result2);
    assertArrayEquals(expectedVect3, result3);
    assertArrayEquals(expectedVect4, result4);
    assertArrayEquals(expectedVect5, result5);
    assertArrayEquals(expectedVect6, result6);

  }

  @Test
  public void playerToServerTest2() {
    int[] testingVect = { 4, 0, 6, 3};
    int[] expectedVect1 = {4, 0, 6, 3};
    int[] expectedVect2 = {0, 4, 3, 5};
    int[] expectedVect3 = {4, 12, 5, 10};
    int[] expectedVect4 = {12, 16, 10, 13};
    int[] expectedVect5 = {16, 12, 13, 11};
    int[] expectedVect6 = {12, 4, 11, 6};

    int[] result1 = CordinateTranslator.playerToServer(
        1, testingVect[0], testingVect[1], testingVect[2], testingVect[3]
    );
    int[] result2 = CordinateTranslator.playerToServer(
        2, testingVect[0], testingVect[1], testingVect[2], testingVect[3]
    );
    int[] result3 = CordinateTranslator.playerToServer(
        3, testingVect[0], testingVect[1], testingVect[2], testingVect[3]
    );
    int[] result4 = CordinateTranslator.playerToServer(
        4, testingVect[0], testingVect[1], testingVect[2], testingVect[3]
    );
    int[] result5 = CordinateTranslator.playerToServer(
        5, testingVect[0], testingVect[1], testingVect[2], testingVect[3]
    );
    int[] result6 = CordinateTranslator.playerToServer(
        6, testingVect[0], testingVect[1], testingVect[2], testingVect[3]
    );
    assertArrayEquals(expectedVect1, result1);
    assertArrayEquals(expectedVect2, result2);
    assertArrayEquals(expectedVect3, result3);
    assertArrayEquals(expectedVect4, result4);
    assertArrayEquals(expectedVect5, result5);
    assertArrayEquals(expectedVect6, result6);

  }

  @Test
  public void serverToPlayerTest1() {
    int[] expectedVect = { 4, 0, 6, 3};
    int[] testingVect1 = {4, 0, 6, 3};
    int[] testingVect2 = {0, 4, 3, 5};
    int[] testingVect3 = {4, 12, 5, 10};
    int[] testingVect4 = {12, 16, 10, 13};
    int[] testingVect5 = {16, 12, 13, 11};
    int[] testingVect6 = {12, 4, 11, 6};

    int[] result1 = CordinateTranslator.serverToPlayer(
        1, testingVect1[0], testingVect1[1], testingVect1[2], testingVect1[3]
    );
    int[] result2 = CordinateTranslator.serverToPlayer(
        2, testingVect2[0], testingVect2[1], testingVect2[2], testingVect2[3]
    );
    int[] result3 = CordinateTranslator.serverToPlayer(
        3, testingVect3[0], testingVect3[1], testingVect3[2], testingVect3[3]
    );
    int[] result4 = CordinateTranslator.serverToPlayer(
        4, testingVect4[0], testingVect4[1], testingVect4[2], testingVect4[3]
    );
    int[] result5 = CordinateTranslator.serverToPlayer(
        5, testingVect5[0], testingVect5[1], testingVect5[2], testingVect5[3]
    );
    int[] result6 = CordinateTranslator.serverToPlayer(
        6, testingVect6[0], testingVect6[1], testingVect6[2], testingVect6[3]
    );
    assertArrayEquals(expectedVect, result1);
//    assertArrayEquals(expectedVect, result2);
    assertArrayEquals(expectedVect, result3);
    assertArrayEquals(expectedVect, result4);
    assertArrayEquals(expectedVect, result5);
    assertArrayEquals(expectedVect, result6);
  }
/*
  @Test
  public void serverToPlayerTest2() {

  }
*/


}
