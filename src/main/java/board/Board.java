package board;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import player.RealPlayer;

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
  private Hexagon oldHex;
  private Hexagon newHex;
  private RealPlayer player;
  private boolean myTurn;
  private Button endMoveButton;
  private Label isMyTurn;
  private HBox hBox;




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

    hBox = new HBox();
    hBox.setAlignment(Pos.CENTER);
    hBox.setSpacing(30);
    endMoveButton= new Button("Skip");
    addActionToButton();
    isMyTurn = new Label();
    hBox.getChildren().addAll(isMyTurn, endMoveButton);

    createFields();
    paintBoard();
    createPawns();
    addPawns(pawnsList);
    borderPane.setCenter(pane);
    borderPane.setTop(hBox);

    paintPlayerFields(1);
    paneEvent(pane);

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


  //METHODS NEEDED BY PLAYER CLASS

  public Scene getScene() {
    return scene;
  }

  public ArrayList<ArrayList<Hexagon>> getRows() {
    return rows;
  }

  public void addPawns(ArrayList<Pawn> pawns) {
    for(int i=0; i<pawns.size();i++){
      pane.getChildren().add(pawns.get(i));
    }
  }


  //METHODS TO CREATE AND CONFIGURATE BOARD

  private void paneEvent(Pane pane) {
    Platform.runLater(() -> {
      pane.setOnMouseClicked(e -> {
        if (myTurn) {
          //todo
          System.out.println("It's my turn");
          if (!fieldClicked) {
            //todo
            System.out.println("first click");
            oldHex = findField(e.getX(),e.getY());
            if (oldHex != null && oldHex.getPawn() != null && oldHex.getPawn().getPlayerId() == playerId) {
              //todo
              System.out.println("field is founded, field has pawn, player id is correct");
              chosenPawn = oldHex.getPawn();
              if (playerId == 4) {
                chosenPawn.setFill(Color.DARKGREY);
              } else {
                chosenPawn.setFill(chosenPawn.getColor().darker());
              }

              fieldClicked = true;
            }
          } else {
            //todo
            System.out.println("Now put pawn");
            if (chosenPawn != null && oldHex != null) {
              //todo
              System.out.println("i have pawn chosen and oldhex isnt null");
              newHex = findField(e.getX(),e.getY());
              if (newHex != null && newHex.getPawn() == null) {
                //todo
                System.out.println("i found new field and it doesnt have any pawn");
                boolean move = isMovePermitted(oldHex.getX(), oldHex.getY(),
                  newHex.getX(), newHex.getY());
                if (move) {
                  //todo
                  System.out.println("move is permitted");
                  chosenPawn.setCenterX(newHex.getCenterX());
                  chosenPawn.setCenterY(newHex.getCenterY());
                  newHex.setPawn(chosenPawn);
                  oldHex.setAsEmpty();
                  chosenPawn.setFill(chosenPawn.getColor());
                } else {
                  //todo
                  System.out.println("move isnt permitted");
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

  private void createPawns() {
    int x,y,xx, yy,i,j,counter;
    counter=0;
    double xCoord, yCoord;
    Pawn newPawn;

    for (y = size - pawns; y < size;y++) {
      for (x = pawns; x < pawns + size - y; x++) {
        xCoord = rows.get(y).get(x).getCenterX();
        yCoord = rows.get(y).get(x).getCenterY();
        xx = rows.get(y).get(x).getX();
        yy = rows.get(y).get(x).getY();
        newPawn = new Pawn(xCoord,yCoord,xx, yy, playerId);
        pawnsList.add(newPawn);
        rows.get(y).get(x).setPawn(newPawn);
      }
    }
  }

  private void createFields() {
    ArrayList<Hexagon> newRow;
    Hexagon newHexagon;

    for (int y =0; y<size; y++) {
      newRow = new ArrayList<Hexagon>();
      for (int x=0; x<size; x++) {
        newHexagon =new Hexagon(x*40 + y*20,y*32, x, size-1-y);
        //newHexagon =new Hexagon(x*40 + y*20,y*32);
        // newHexagon =new Hexagon(x*30 + y*15,y*25);
        newHexagon.setFill(Color.WHITESMOKE);
        pane.getChildren().add(newHexagon);
        newRow.add(newHexagon);
      }
      rows.add(newRow);
    }
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

  private void addActionToButton(){
    endMoveButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        if(myTurn) {
          endMove();
          if(chosenPawn!=null) {
            chosenPawn.setFill(chosenPawn.getColor());
            chosenPawn=null;
            oldHex=null;
          }
        }
      }
    });
  }


  //METHODS TO COMMUNICATE WITH SERVER

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
    //todo
    System.out.println("sprawdzam czy dozwolony...");
    boolean result = player.isMovePermitted(fromX,fromY,toX,toY);
    if(result) System.out.println("true");
    else System.out.println("false");
    return result;
  }

  public void setMyTurn(boolean myTurn) {
    Platform.runLater(() -> {
      if (myTurn) {
        isMyTurn.setText("Your turn!");
        endMoveButton.setDisable(false);
      } else {
        isMyTurn.setText("Wait...");
        endMoveButton.setDisable(true);
      }
    });

    this.myTurn=myTurn;
  }

  public void uploadMove(int playerId, int fromX, int fromY, int toX, int toY) {
    Hexagon fromField = rows.get(size-1-fromY).get(fromX);
    Hexagon toField = rows.get(size-1-toY).get(toX);
    fromField.getPawn().setCenterY(toField.getCenterY());
    fromField.getPawn().setCenterX(toField.getCenterX());
    fromField.getPawn().move(toX, toY);
    toField.setPawn(fromField.getPawn());
    fromField.setAsEmpty();
  }

  public void endMove() {
    player.endMove();
  }
}
