package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class JoinGui {

  @FXML
  VBox joinPane;

  public void joinGame(ActionEvent event) {
    try {
      VBox pane=FXMLLoader.load(getClass().getResource("/waitingWindow.fxml"));
      joinPane.getChildren().setAll(pane);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
