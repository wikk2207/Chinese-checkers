package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
  private VBox root;
  public static Counter counter;
  private Stage stage;

  @Override
  public void start(Stage primaryStage) {
      counter = new Counter();
      counter.setPlayerId(getMyId());
      counter.setBoardSize(17);
      stage = primaryStage;

      try {
        if (amIFirst()) {
          //create new game
          root = FXMLLoader.load(getClass().getResource("/startGui.fxml"));
        } else {
          //join created game
          root = FXMLLoader.load(getClass().getResource("/joinGui.fxml"));
        }
        Scene scene = new Scene(root, 1000, 600);
        stage.setTitle("Chinese Checkers");
        stage.setScene(scene);
        stage.show();
      } catch (IOException e) {
        System.out.println(e.getMessage());
      }

  }

  public static void main(String[] args) {
    launch(args);
  }

  boolean amIFirst() {
    return true; //asking server
  }

  int getMyId() {
    //asking serwer
    return 1;
  }

   void createGame(int players, int robots) {
    counter.createBoard();
    counter.addPlayer(2);
    stage.setScene(counter.getScene());
  }

}
