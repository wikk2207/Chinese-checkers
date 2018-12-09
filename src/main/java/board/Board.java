package board;

import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Board {

  private ArrayList<ArrayList<Hexagon>> rows;
  private Scene scene;
  private int size;
  private int pawns; //max amount of pawns in one row for one player
  private Color[] colors;
  private int playerId;
  private ArrayList<Pawn> pawnsList;
  private Pane pane;




  Board(int size, int playerID) throws BoardException{

    if(size%4!=1) {
      throw new BoardException("Invalid size of board. It should be number x which: x%4=1");
    }
    this.size=size;
    pawns = (size-1)/4;

    this.playerId=playerID;
    this.colors=Colors.getColors(playerID);
    pawnsList = new ArrayList<Pawn>();


    rows = new ArrayList<ArrayList<Hexagon>>();
    BorderPane borderPane = new BorderPane();
    // GridPane pane = new GridPane();
    pane = new Pane();


    scene = new Scene(borderPane,1.5*size*30,size*28);
    //primaryStage.setScene(scene);


    createFields();
    paintBoard();
    createPawns();
    addPawns(pawnsList);

    borderPane.setCenter(pane);
    paintPlayerFields(1);
    //primaryStage.show();

  }

  private void paintBoard() {
    for(int y=0;y<size-pawns;y++) {
      for (int x=size-pawns-1;x>size-pawns-2-y;x--) {
        rows.get(y).get(x).setFill(Color.BISQUE);
        rows.get(y).get(x).setField(true);
      }
    }
    for(int y=pawns;y<size;y++) {
      for (int x=pawns;x<size-y+pawns;x++) {
        rows.get(y).get(x).setFill(Color.BISQUE);
        rows.get(y).get(x).setField(true);
      }
    }
  }

  private void paintPlayerFields(int playerId) {
    int x,y; //coordinates
    int i,j;

    //fields1
    for(i=0,y=size-pawns;i<pawns;i++) {
      for (j=0,x=pawns;j<pawns-i;j++) {
        rows.get(y+i).get(x+j).setFill(colors[0]);
      }
    }
    //fields2
    for(i=0, y=size-pawns-1;i<pawns;i++) {
      for ( x=pawns-1, j=0; j<pawns-i; j++) {
        rows.get(y-i).get(x-j).setFill(colors[1]);
      }
    }

    //fields3
    for(y=pawns, i=0;i<pawns;i++){
      for(x=pawns,j=0; j<pawns-i;j++) {
        rows.get(y+i).get(x+j).setFill(colors[2]);
      }
    }

    //fields4
    for(y=pawns-1, i=0;i<pawns;i++) {
      for ( x=size-pawns-1, j=0; j<pawns-i; j++) {
        rows.get(y-i).get(x-j).setFill(colors[3]);
      }
    }

    //fields5
    for(y=pawns, i=0;i<pawns;i++){
      for(x=size-pawns, j=0; j<pawns-i;j++) {
        rows.get(y+i).get(x+j).setFill(colors[4]);
      }
    }

    //fields6
    for( y=size-pawns-1,i=0;i<pawns;i++) {
      for ( x=size-pawns-1, j=0; j<pawns-i; j++) {
        rows.get(y-i).get(x-j).setFill(colors[5]);
      }
    }
  }

  private void createPawns() {
    int x,y,i,j,counter;
    counter=0;
    double xCoord, yCoord;
    Pawn newPawn;

    for(y=size-pawns;y<size;y++) {
      for (x=pawns;x<pawns+size-y;x++) {
        xCoord=rows.get(y).get(x).getCenterX();
        yCoord=rows.get(y).get(x).getCenterY();
        newPawn = new Pawn(xCoord,yCoord,playerId);
        pawnsList.add(newPawn);
        addActionToPawn(newPawn);
      }
    }
  }

  private int amountOfPawns() {
    int n=((size-1)/4);
    int amount = (n*(n+1))/2;
    return amount;
  }

  public void addPawns(ArrayList<Pawn> pawns) {
    for(int i=0; i<pawns.size();i++){
      pane.getChildren().add(pawns.get(i));
    }
  }

  private void createFields() {
    ArrayList<Hexagon> newRow;
    Hexagon newHexagon;

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
}

  private void addActionToPawn(Pawn pawn) {

    pawn.setOnMousePressed(e->{
      final Hexagon oldHex = findField(e.getX(), e.getY());
      pawn.setOnMouseDragged(ee->{
        pawn.setCenterX(ee.getX());
        pawn.setCenterY(ee.getY());

        pawn.setOnMouseReleased(eee->{
          final Hexagon newHex = findField(eee.getX(), eee.getY());
          if(newHex!=null&&newHex.getPawn()==null){
            pawn.setCenterX(newHex.getCenterX());
            pawn.setCenterY(newHex.getCenterY());
            oldHex.setAsEmpty();
            newHex.setPawn(pawn);
          } else {
            pawn.setCenterX(oldHex.getCenterX());
            pawn.setCenterY(oldHex.getCenterY());
          }
        });

      });
    });

  }

  public Hexagon findField(double x, double y) {
    for (int i=0; i<rows.size(); i++) {
      for (int j=0; j<rows.get(i).size(); j++) {
        final Hexagon hex=rows.get(i).get(j);
        if (hex.isField()) {
          if (hex.contains(x, y)) {
            return hex;
          }
        }
      }
    }
    return null;
  }


  public Scene getScene() {
    return scene;
  }
}