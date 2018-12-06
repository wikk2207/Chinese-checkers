package board;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class BoardAdapter extends Application {
  private Board board;

  @Override
  public void start(Stage primaryStage) {

    try{
      board = new Board(17);
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
