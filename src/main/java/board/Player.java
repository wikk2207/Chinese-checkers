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
  private int size = 17;
  private int playerID = 4;

  @Override
  public void start(Stage primaryStage) {

    try{
      board = new Board(size, playerID);
      primaryStage.setScene(board.getScene());



      primaryStage.show();
    } catch (BoardException x) {
      System.out.println(x.getMessage());
      System.exit(-1);
    }


  }

  public static void main (String[] args) {
    launch(args);
  }



}
