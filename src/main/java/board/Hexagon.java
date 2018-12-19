package board;

import javafx.collections.ObservableList;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;

public class Hexagon extends Polygon {
  private double x;
  private double y;
  private boolean isField;
  private Pawn pawn;
  private double[] points;

  Hexagon(double x, double y) {

    super (x+10, y+32, x+25, y+41, x+40, y+32, x+40, y+14, x+25, y+5, x+10, y+14);

   double[] points = {x+10, y+32, x+25, y+41, x+40, y+32, x+40, y+14, x+25, y+5, x+10, y+14};

    //super( 10.0+x, 26.0+y, 20.0+x, 32.0+y, 30.0+x, 26.0+y,
     //             30.0+x, 14.0+y, 20.0+x,  8.0+y, 10.0+x, 14.0+y);
    /*double[] points = { 10+x, 26+y,
                        20+x, 32+y,
                        30+x, 26+y,
                        30+x, 14+y,
                        20+x, 8+y,
                        10+x,14+y};*/

    this.points=points;
    this.x =x;
    this.y =y;
    isField=false;
    this.pawn=null;
  }

  public void setField(boolean isField) {
    this.isField=isField;
  }

  public void setAsEmpty() {
    pawn=null;
  }

  public Pawn getPawn() {
    return pawn;
  }

  public void setPawn (Pawn pawn) {
    this.pawn=pawn;
  }

  public int getUserID () {
    if(isField && pawn!=null) {
      return pawn.getPlayerId();
    } else return 0;
  }

  public boolean contains( double x, double y) {
   boolean result =false;
     if(x>points[0]&&x<points[2]) {
       if(y>points[11]-((x-points[10])/(points[8]-points[10])*(points[9]-points[11]))&&
          y<points[1]+(((x-points[0])/(points[2]-points[0])*(points[3]-points[1])))) {
         result = true;
       }
     } else if(x>=points[2]&&x<points[4]) {
       if(y>points[7]-((points[6]-x)/(points[6]-points[8])*(points[9]-points[7])) &&
          y<points[5]+((points[4]-x)/(points[4]-points[2])*(points[3]-points[1]))) {
         result=true;
       }
     }

    /*if(x>points[0]-5&&x<points[2+5]) {  //rozszerzam szesciokąty, a by nie było widać pustych przerw między nimi
      if(y>points[11]-((x-points[10])/(points[8]-points[10])*(points[9]-points[11]))&&
        y<points[1]+(((x-points[0])/(points[2]-points[0])*(points[3]-points[1])))) {
        result = true;
      }
    } else if(x>=points[2]&&x<points[4]) {
      if(y>points[7]-((points[6]-x)/(points[6]-points[8])*(points[9]-points[7])) &&
        y<points[5]+((points[4]-x)/(points[4]-points[2])*(points[3]-points[1]))) {
        result=true;
      }
    }*/
    return result;
  }

  public boolean isField() {
    return isField;
  }

  public double getX()
  {
    return x;
  }

  public double getY() {
    return y;
  }

  public double getCenterX(){
    double center = points[2];
    return center;
  }

  public double getCenterY(){
    double height = points[3]-points[9];
    double center = points[9]+(height/2);
    return  center;
  }

}