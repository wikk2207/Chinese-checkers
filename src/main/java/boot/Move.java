package boot;

public class Move {
  private int fromX;
  private int fromY;
  private int toX;
  private int toY;
  private boolean isJump;
  private boolean isFirst;
  private boolean previousWasJump;

  public Move(int fromX, int fromY, int toX, int toY, boolean isFirst, boolean isJump, boolean previousWasJump) {
    this.fromX = fromX;
    this.fromY = fromY;
    this.toX = toX;
    this.toY = toY;
    this.isJump = isJump;
    this.isFirst = isFirst;
    this.previousWasJump = previousWasJump;

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

  public boolean isFirst() {
    return isFirst;
  }

  public boolean isPreviousWasJump() {
    return previousWasJump;
  }

}
