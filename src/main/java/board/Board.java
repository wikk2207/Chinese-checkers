package board;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Board{

  private ArrayList<ArrayList<Hexagon>> rows;
  private Scene scene;

  Board(){
    rows = new ArrayList<ArrayList<Hexagon>>();
    BorderPane borderPane = new BorderPane();
    // GridPane pane = new GridPane();
    Pane pane = new Pane();
    ArrayList<Hexagon> newRow;
    Hexagon newHexagon;

    scene = new Scene(borderPane,760,500);
    //primaryStage.setScene(scene);


    for (int y =0; y<17; y++) {
      newRow = new ArrayList<Hexagon>();
      for (int x=0; x<17; x++) {
        newHexagon =new Hexagon(x*30 + y*15,y*25);
        newHexagon.setFill(Color.WHITE);

        pane.getChildren().add(newHexagon);
        newRow.add(newHexagon);
      }
      rows.add(newRow);
    }
    paintBoard();
    borderPane.setCenter(pane);
    paintPlayerFields(1);
    //primaryStage.show();

  }

  private void paintBoard() {
    for(int y=0;y<13;y++) {
      for (int x=12;x>11-y;x--) {
        rows.get(y).get(x).setFill(Color.BISQUE);
      }
    }
    for(int y=4;y<17;y++) {
      for (int x=4;x<17-y+4;x++) {
        rows.get(y).get(x).setFill(Color.BISQUE);
      }
    }
  }

  private void paintPlayerFields(int playerId) {
    int x,y; //coordinates

    //fields1
    for(y=13;y<17;y++){
      for(x=4; x<8-y+13;x++) {
        rows.get(y).get(x).setFill(Color.SALMON);
      }
    }

    //fields 2
    for( y=12;y>8;y--) {
      for ( x=3; x>8+3-y; x--) {
        rows.get(y).get(x).setFill(Color.SANDYBROWN);
      }
    }

    //fields3
    for(y=4;y<8;y++){
      for(x=4; x<8-y+4;x++) {
        rows.get(y).get(x).setFill(Color.LIGHTGREEN);
      }
    }

    //fields4
    for( y=3;y>=0;y--) {
      for ( x=12; x>=12-y; x--) {
        rows.get(y).get(x).setFill(Color.LIGHTYELLOW);
      }
    }

    //fields5
    for(y=4;y<8;y++){
      for(x=13; x<17-y+4;x++) {
        rows.get(y).get(x).setFill(Color.PLUM);
      }
    }


    //fields6
    for( y=12;y>8;y--) {
      for ( x=12; x>8+12-y; x--) {
        rows.get(y).get(x).setFill(Color.LIGHTBLUE);
      }
    }
  }



  public Scene getScene() {
    return scene;
  }
}
