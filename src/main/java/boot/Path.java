package boot;

import java.util.ArrayList;

public class Path {
  private int pawnId;
  private int length;
  private int distanceToGoal;
  private int fromX;
  private int fromY;
  private int toX;
  private int toY;
  private ArrayList<Move> path;

  Path(int pawnId, int fromX, int fromY) {
    this.pawnId = pawnId;
    this.fromX = fromX;
    this.fromY = fromY;
    this.path = new ArrayList<>();
  }

  public void add (Move move) {
    path.add(move);
  }

  public int getPawnId() {
    return pawnId;
  }

  public int getLength() {
    return length;
  }

  public void setLength(int length) {
    this.length=length;
  }

  public int getDistanceToGoal() {
    return distanceToGoal;
  }

  public void setDistanceToGoal(int distanceToGoal) {
    this.distanceToGoal=distanceToGoal;
  }

  public int getFromX() {
    return fromX;
  }

  public int getFromY() {
    return fromY;
  }

  public int getToX() {
    return toX;
  }

  public void setToX(int toX) {
    this.toX=toX;
  }

  public int getToY() {
    return toY;
  }

  public void setToY(int toY) {
    this.toY=toY;
  }
  public Move getMove(int index) {
    return path.get(index);
  }

  public int size() {
    return path.size();
  }

  public Move get(int index) {
    return path.get(index);
  }
}
