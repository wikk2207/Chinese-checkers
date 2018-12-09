package board;


import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class Pawn extends Circle{
  private double x;
  private double y;
  private static Color[] color = {Color.RED, Color.ORANGE,Color.GREEN, Color.BLACK, Color.PURPLE, Color.BLUE};
  private int playerId;

  Pawn(double x, double y, int playerID) {
    super(x,y,7,color[playerID-1]);
    this.x=x;
    this.y=y;
    this.playerId=playerID;
  }

  public void move(int x, int y) {
    this.x=x;
    this.y=y;
  }

  public int getPlayerId() {
    return playerId;
  }

}
