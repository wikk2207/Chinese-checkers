package board;

import board.Board;
import board.BoardException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Player extends Application {
  private Board board;
  private int size = 21;
  private int playerID = 2;

  @Override
  public void start(Stage primaryStage) {

    try{
      board = new Board(size, playerID);
      primaryStage.setScene(board.getScene());

      //addPlayers
     // addOtherPlayer(1, coords(1));

      primaryStage.show();
    } catch (BoardException x) {
      System.out.println(x.getMessage());
      System.exit(-1);
    }


  }

  public void addOtherPlayer(int playerID, int[] coordinates) {
    ArrayList<Pawn> pawns = new ArrayList<>();
    double x, y;
    Pawn newPawn;
    for(int i=0; i<coordinates.length; i=+2) {
      x=board.getField(i, i+1).getCenterX();
      y=board.getField(i, i+1).getCenterY();
      board.getField(i, i+1);
      newPawn = new Pawn(coordinates[i], coordinates[i+1],playerID);
      newPawn.setFill(Color.RED);//(Colors.getColors(this.playerID)[playerID]);
      board.getField(i, i+1).setPawn(newPawn);
      pawns.add(newPawn);
    }
    board.addPawns(pawns);
  }

  public static void main (String[] args) {
    launch(args);
  }

  public static boolean isMoveValid(double fromX, double fromY, double toX, double toY) {
    return true;
  }

  private int[] coords(int playerId) {
    int maxPawns = board.amountOfPawns();
    int coords = 2*maxPawns;
    int i, j, x, y;
    int counter=0;
    int pawns=(size-1)/4;
    int[] coordTable=new int[coords];
    switch (playerId) {
      case 1:
        for(i=0,y=size-pawns;i<pawns;i++) {
          for (j=0,x=pawns;j<pawns-i;j++,counter++) {
            coordTable[counter]=x+j;
            coordTable[counter+1]=y+i;
          }
        }
        break;
      case 2:
        break;
      case 3:
        break;
      case 4:
        break;
      case 5:
        break;
    }
    return coordTable;
  }




}
