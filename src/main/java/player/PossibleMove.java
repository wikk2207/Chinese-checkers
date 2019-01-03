package player;

public class PossibleMove {
  private int x;
  private int y;

  PossibleMove(int toX, int toY) {
    this.x = toX;
    this.y = toY;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }
}
