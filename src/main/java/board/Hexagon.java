package board;

import javafx.scene.shape.Polygon;

public class Hexagon extends Polygon {
  double x;
  double y;

  Hexagon(double x, double y) {

    super(10.0+x, 26.0+y, 20.0+x, 32.0+y, 30.0+x, 26.0+y, 30.0+x,14.0+y, 20.0+x, 8.0+y, 10.0+x,14.0+y);
    this.x =x;
    this.y =y;

  }

}