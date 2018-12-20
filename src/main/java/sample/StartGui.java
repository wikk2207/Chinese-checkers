package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

import static java.lang.Thread.sleep;


public class StartGui {
  @FXML
  MenuButton players;
  @FXML
  MenuButton robots;
  @FXML
  MenuItem robot0;
  @FXML
  MenuItem robot1;
  @FXML
  MenuItem robot2;
  @FXML
  MenuItem robot3;
  @FXML
  MenuItem robot4;
  @FXML
  MenuItem robot5;

  @FXML
  VBox startPane;


  int playersInt;
  int robotsInt;


  @FXML
  void choose2Players(ActionEvent event) {
    players.setText("2");
    playersInt = 2;
    robot1.setVisible(true);
    robot2.setVisible(false);
    robot3.setVisible(false);
    robot4.setVisible(false);
    robot5.setVisible(false);
    robot0.setVisible(true);
    robots.setText("-----");
    robotsInt = -1;
  }

  @FXML
  void choose3Players(ActionEvent event) {
    players.setText("3");
    playersInt = 3;
    robot1.setVisible(true);
    robot2.setVisible(true);
    robot3.setVisible(false);
    robot4.setVisible(false);
    robot5.setVisible(false);
    robot0.setVisible(true);
    robots.setText("-----");
    robotsInt = -1;
  }

  @FXML
  void choose4Players(ActionEvent event) {
    players.setText("4");
    playersInt = 4;
    robot1.setVisible(true);
    robot2.setVisible(true);
    robot3.setVisible(true);
    robot4.setVisible(false);
    robot5.setVisible(false);
    robot0.setVisible(true);
    robots.setText("-----");
    robotsInt = -1;
  }

  @FXML
  void choose6Players(ActionEvent event) {
    players.setText("6");
    playersInt = 5;
    robot1.setVisible(true);
    robot2.setVisible(true);
    robot3.setVisible(true);
    robot4.setVisible(true);
    robot5.setVisible(true);
    robot0.setVisible(true);
    robots.setText("-----");
    robotsInt = -1;
  }

  @FXML
  void choose0Robot(ActionEvent event) {
    robots.setText("0");
    robotsInt = 0;
  }

  @FXML
  void choose1Robot(ActionEvent event) {
    robots.setText("1");
    robotsInt = 1;
  }

  @FXML
  void choose2Robot(ActionEvent event) {
    robots.setText("2");
    robotsInt = 2;
  }

  @FXML
  void choose3Robot(ActionEvent event) {
    robots.setText("3");
    robotsInt = 3;

  }

  @FXML
  void choose4Robot(ActionEvent event) {
    robots.setText("4");
    robotsInt = 4;

  }

  @FXML
  void choose5Robot(ActionEvent event) {
    robots.setText("5");
    robotsInt = 5;

  }

  @FXML
  /*void createGame(ActionEvent event) {
      try {
        if(playersInt!=0 && robotsInt != -1) {
          Main.getCounter().createGame(playersInt, robotsInt, startPane);
          VBox pane=FXMLLoader.load(getClass().getResource("/waitingWindow.fxml"));
          startPane.getChildren().setAll(pane);
          startPane.getChildren().setAll(Main.getCounter().getScene().getRoot());
        }
      } catch (IOException e) {
        System.out.println(e.getMessage());
      }

      //counter.createBoard();
      //counter.addPlayer(2);
  }*/

  void createGame(ActionEvent event) {
    try {
      if (playersInt != 0 && robotsInt != -1) {
        Main.getCounter().createGame(playersInt, robotsInt, startPane);
        VBox pane = FXMLLoader.load(getClass().getResource("/waitingWindow.fxml"));
        startPane.getChildren().setAll(pane);
        Main.getCounter().startGame();
        //Main.getCounter().yourTurn();
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
