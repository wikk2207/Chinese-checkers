package boot;

public class Move {
  private int fromX;
  private int fromY;
  private int toX;
  private int toY;
  private boolean isJump;

  public Move(int fromX, int fromY, int toX, int toY, boolean isJump) {
    this.fromX = fromX;
    this.fromY = fromY;
    this.toX = toX;
    this.toY = toY;
    this.isJump = isJump;
  }

  public int getToX() {
    return toX;
  }

  public int getToY() {
    return toY;
  }

  public boolean isJump() {
    return isJump;
  }

  public int getFromY() {
    return fromY;
  }

  public int getFromX() {
    return fromX;
  }
}
