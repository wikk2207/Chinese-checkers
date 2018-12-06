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

    board = new Board();
    primaryStage.setScene(board.getScene());
    primaryStage.show();


  }

  public static void main (String[] args) {
    launch(args);
  }

}
