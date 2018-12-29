package sample;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {
  private VBox root;
  private static Counter counter;
  private Stage stage;

  @Override
  public void start(Stage primaryStage) {
    //Przerobione przez matika
    stage = primaryStage;


      Scene scene = new Scene(root, 1000, 600);
      stage.setTitle("Chinese Checkers");
      stage.setScene(scene);
      stage.show();



  }

  @Override
  public void init() {
    counter = new Counter();
    counter.setBoardSize(17);


    try {
      if (counter.amIFirst()) {
        //create new game
        root = FXMLLoader.load(getClass().getResource("/startGui.fxml"));
      } else {
        //join created game
        root = FXMLLoader.load(getClass().getResource("/waitingWindow.fxml"));
      }
      counter.setPane(root);

    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    counter.runServerListener();
    //counter.createBoard();



  }



  public static void main(String[] args) {
    launch(args);
  }

  public static Counter getCounter() {
    return counter;
  }

}
