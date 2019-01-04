package boot;

import java.util.ArrayList;

public class Path<E> extends ArrayList<E> {
  private int pawnId;

  Path(int pawnId) {
    this.pawnId = pawnId;
  }

  public int getPawnId() {
    return pawnId;
  }
}
