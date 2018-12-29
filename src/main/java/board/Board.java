package board;


import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import player.Player;
import player.RealPlayer;

import java.awt.*;
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
  private boolean fieldClicked;
  private Pawn chosenPawn;
  private Hexagon oldHex, newHex;
  private RealPlayer player;
  private boolean myTurn;




  public Board(int size, int playerID, RealPlayer player) throws BoardException {
    if (size % 4 != 1) {
      throw new BoardException("Invalid size of board. It should be number x which: x%4=1");
    }
    this.size = size;
    pawns = (size - 1) / 4;
    this.player = player;

    this.playerId = playerID;
    this.colors = Colors.getColors(playerID);
    pawnsList = new ArrayList<Pawn>();
    fieldClicked = false;
    chosenPawn = null;
    myTurn = false;


    rows = new ArrayList<ArrayList<Hexagon>>();
    BorderPane borderPane = new BorderPane();
    // GridPane pane = new GridPane();
    pane = new Pane();

    //scene = new Scene(borderPane,1.5*size*30,size*28);
    scene = new Scene(borderPane,1.5*size*40,size*35);

    createFields();
    paintBoard();
    createPawns();
    addPawns(pawnsList);
    borderPane.setCenter(pane);
    paintPlayerFields(1);
    paneEvent(pane);

  }

  private void paneEvent(Pane pane) {
    Platform.runLater(() -> {
      pane.setOnMouseClicked(e -> {
        if (myTurn) {
          if (!fieldClicked) {
            oldHex = findField(e.getX(),e.getY());
            if (oldHex.getPawn() != null && oldHex.getPawn().getPlayerId() == playerId) {
              chosenPawn = oldHex.getPawn();
              if (playerId == 4) {
                chosenPawn.setFill(chosenPawn.getColor().brighter());
              } else {
                chosenPawn.setFill(chosenPawn.getColor().darker());
              }

              fieldClicked = true;
            }
          } else {
            if (chosenPawn != null && oldHex != null) {
              newHex = findField(e.getX(),e.getY());
              if (newHex.getPawn() == null) {
                //TODO
                System.out.println(oldHex.getX() +" "+ oldHex.getY()+" "+
                  newHex.getX()+" "+ newHex.getY());
                boolean move = isMovePermitted(oldHex.getX(), oldHex.getY(),
                  newHex.getX(), newHex.getY());
                if (move) {
                  chosenPawn.setCenterX(newHex.getCenterX());
                  chosenPawn.setCenterY(newHex.getCenterY());
                  newHex.setPawn(chosenPawn);
                  oldHex.setAsEmpty();
                  chosenPawn.setFill(chosenPawn.getColor());
                }
              }
              chosenPawn.setFill(chosenPawn.getColor());
              chosenPawn = null;
              newHex = null;
            }
            oldHex = null;
            fieldClicked = false;
          }
        }


      });

    });

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

    for (y = size - pawns; y < size;y++) {
      for (x = pawns; x < pawns + size - y; x++) {
        xCoord = rows.get(y).get(x).getCenterX();
        yCoord = rows.get(y).get(x).getCenterY();
        newPawn = new Pawn(xCoord,yCoord,playerId);
        pawnsList.add(newPawn);
        rows.get(y).get(x).setPawn(newPawn);
        //addMoveToPawn(newPawn);
      }
    }
  }

  private void createFields() {
    ArrayList<Hexagon> newRow;
    Hexagon newHexagon;

    for (int y =0; y<size; y++) {
      newRow = new ArrayList<Hexagon>();
      for (int x=0; x<size; x++) {
        //TODO zmienione tworzenie new hexagon
        newHexagon =new Hexagon(x*40 + y*20,y*32, x, y);
        //newHexagon =new Hexagon(x*40 + y*20,y*32);
        // newHexagon =new Hexagon(x*30 + y*15,y*25);
        newHexagon.setFill(Color.WHITESMOKE);
        pane.getChildren().add(newHexagon);
        newRow.add(newHexagon);
      }
      rows.add(newRow);
    }
  }

  private void addActionToField(Hexagon field) {
    field.setOnMousePressed(e-> {
      if(!fieldClicked) {
        if(field.getPawn()!=null) {
          fieldClicked = true;
          chosenPawn = field.getPawn();
          field.setAsEmpty();
          chosenPawn.setFill(chosenPawn.getColor().brighter());
        }
      } else {
        field.setPawn(chosenPawn);
        chosenPawn.setCenterX(field.getCenterX());
        chosenPawn.setCenterY(field.getCenterY());
        chosenPawn.setFill(chosenPawn.getColor().darker());
      }

    });
  }

  private void addMoveToPawn(Pawn pawn) {

    pawn.setOnDragDetected(e -> {
      final Hexagon oldHex = findField(e.getX(), e.getY());
      pawn.setOnMouseDragged(ee -> {
        pawn.setCenterX(ee.getX());
        pawn.setCenterY(ee.getY());

        pawn.setOnMouseReleased(eee -> {
          final Hexagon newHex = findField(eee.getX(), eee.getY());
          if (newHex != null && newHex.getPawn() == null && isMovePermitted(oldHex.getX(), oldHex.getY(), newHex.getX(), newHex.getY())) {
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


  public int amountOfPawns() {
    int n=((size-1)/4);
    int amount = (n*(n+1))/2;
    return amount;
  }

  public void addPawns(ArrayList<Pawn> pawns) {
    for(int i=0; i<pawns.size();i++){
      pane.getChildren().add(pawns.get(i));
    }
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

  public Hexagon getField(int x, int y) {
    return rows.get(x).get(y);
  }

  public void addPlayer(ArrayList<Pawn> pawns) {
    pane.getChildren().addAll(pawns);
  }
  public ArrayList<ArrayList<Hexagon>> getRows() {
  return rows;
  }

  public void uploadMove(int playerId, int fromX, int fromY, int toX, int toY) {
    Hexagon fromField = rows.get(fromY).get(fromX);
    Hexagon toField = rows.get(toY).get(toX);
    fromField.getPawn().setCenterY(toField.getCenterY());
    fromField.getPawn().setCenterX(toField.getCenterX());
    fromField.getPawn().move(toX, toY);
    toField.setPawn(fromField.getPawn());
    fromField.setAsEmpty();
  }

  /**
   * Checking if move is valid (following game rules).
   *
   * @param fromX X coordinate of field when pawn starts move
   * @param fromY Y coordinate of field when pawn starts move
   * @param toX   X coordinate of field when pawn ends move
   * @param toY   Y coordinate of field when pawn ends move
   * @return
   */
  private boolean isMovePermitted (int fromX, int fromY, int toX, int toY) {
    //tODO
    boolean result = player.isMovePermitted(fromX,fromY,toX,toY);
    if (result) System.out.println("Otrzymana wartość correct move: true");
    else System.out.println("Otrzymana wartość correct move: false");
    return result;
  }

  public void setMyTurn(boolean myTurn) {
    this.myTurn=myTurn;
  }

  /*private void addButton() {
    Button skipButton = new Button();
    skipButton.
    pane.getChildren().add(skipButton);
  }*/
}
