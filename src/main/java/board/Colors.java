package board;
import javafx.scene.paint.Color;

public class Colors {
  private static Color[] player1 = {Color.SALMON, Color.PALEGOLDENROD, Color.LIGHTGREEN, Color.LIGHTGREY, Color.PLUM, Color.LIGHTBLUE};
  private static Color[] player2 = {Color.PALEGOLDENROD, Color.LIGHTGREEN, Color.LIGHTGREY, Color.PLUM, Color.LIGHTBLUE,Color.SALMON};
  private static Color[] player3 = {Color.LIGHTGREEN, Color.LIGHTGREY, Color.PLUM, Color.LIGHTBLUE,Color.SALMON, Color.PALEGOLDENROD};
  private static Color[] player4 = {Color.LIGHTGREY, Color.PLUM, Color.LIGHTBLUE,Color.SALMON, Color.PALEGOLDENROD, Color.LIGHTGREEN};
  private static Color[] player5 = {Color.PLUM, Color.LIGHTBLUE,Color.SALMON, Color.PALEGOLDENROD, Color.LIGHTGREEN, Color.LIGHTGREY};
  private static Color[] player6 = {Color.LIGHTBLUE,Color.SALMON, Color.PALEGOLDENROD, Color.LIGHTGREEN, Color.LIGHTGREY, Color.PLUM};

  public static Color[] getColors(int playerID) {
    switch(playerID) {
      case 1:
        return player1;
      case 2:
        return player2;
      case 3:
        return player3;
      case 4:
        return player4;
      case 5:
        return player5;
      case 6:
        return player6;
    }
    return null;
  }
}
