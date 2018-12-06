package board;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Board {

  private ArrayList<ArrayList<Hexagon>> rows;
  private Scene scene;
  private int size;
  private int pawns; //amount of pawns for one player

  Board(int size) throws BoardException{

    if(size%4!=1) {
      throw new BoardException("Invalid size of board. It should be number x which: x%4=1");
    }
    this.size=size;
    pawns=size/4;

    rows = new ArrayList<ArrayList<Hexagon>>();
    BorderPane borderPane = new BorderPane();
    // GridPane pane = new GridPane();
    Pane pane = new Pane();
    ArrayList<Hexagon> newRow;
    Hexagon newHexagon;

    scene = new Scene(borderPane,1.5*size*30,size*28);
    //primaryStage.setScene(scene);


    for (int y =0; y<size; y++) {
      newRow = new ArrayList<Hexagon>();
      for (int x=0; x<size; x++) {
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
    for(int y=0;y<size-pawns;y++) {
      for (int x=size-pawns-1;x>size-pawns-2-y;x--) {
        rows.get(y).get(x).setFill(Color.BISQUE);
      }
    }
    for(int y=pawns;y<size;y++) {
      for (int x=pawns;x<size-y+pawns;x++) {
        rows.get(y).get(x).setFill(Color.BISQUE);
      }
    }
  }

  private void paintPlayerFields(int playerId) {
    int x,y; //coordinates
    int i,j;

    //fields1
    for(i=0,y=size-pawns;i<pawns;i++) {
      for (j=0,x=pawns;j<pawns-i;j++) {
        rows.get(y+i).get(x+j).setFill(Color.SALMON);
      }
    }
    //fields2
    for(i=0, y=size-pawns-1;i<pawns;i++) {
      for ( x=pawns-1, j=0; j<pawns-i; j++) {
        rows.get(y-i).get(x-j).setFill(Color.SANDYBROWN);
      }
    }

    //fields3
    for(y=pawns, i=0;i<pawns;i++){
      for(x=pawns,j=0; j<pawns-i;j++) {
        rows.get(y+i).get(x+j).setFill(Color.LIGHTGREEN);
      }
    }

    //fields4
    for(y=pawns-1, i=0;i<pawns;i++) {
      for ( x=size-pawns-1, j=0; j<pawns-i; j++) {
        rows.get(y-i).get(x-j).setFill(Color.LIGHTYELLOW);
      }
    }

    //fields5
    for(y=pawns, i=0;i<pawns;i++){
      for(x=size-pawns, j=0; j<pawns-i;j++) {
        rows.get(y+i).get(x+j).setFill(Color.PLUM);
      }
    }

    //fields6
    for( y=size-pawns-1,i=0;i<pawns;i++) {
      for ( x=size-pawns-1, j=0; j<pawns-i; j++) {
        rows.get(y-i).get(x-j).setFill(Color.LIGHTBLUE);
      }
    }
  }



  public Scene getScene() {
    return scene;
  }
}
