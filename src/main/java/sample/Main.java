import board.Board;
import board.BoardException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

  public void start(Stage primaryStage) {
    if(amIFirst()) {
      //create new game
      try {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("startGui.fxml"));
        Scene scene = new Scene(root,400, 500);
        primaryStage.setTitle("Chinese Checkers");
        primaryStage.setScene(scene);
        primaryStage.show();
      } catch (IOException e) {
        System.out.println(e.getMessage());
      }

    } else {
      //join created game
    }
  }

  public static void main(String[] args) {
    launch(args);
  }

  boolean amIFirst() {
    return true; //asking server
  }

}
