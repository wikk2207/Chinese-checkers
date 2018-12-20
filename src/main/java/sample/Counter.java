package sample;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import player.Boot;
import player.Player;
import player.RealPlayer;

public class Counter {
  Player player;
  VBox pane;

  Counter() {
      player = new RealPlayer(this);

    setPlayerId(1);
    setBoardSize(17);
    createBoard();
  }

  /**
   *
   */
  public void yourTurn() {
    player.setMyTurn(true);
  }

  public void turnEnd() {
    player.setMyTurn(false);
  }

  public void uploadMove(int playerId, int fromX, int fromY, int toX, int toY) {
    player.uploadMove(playerId, fromX, fromY, toX, toY);
  }

  public void setPlayerId(int id) {
    player.setId(id);
  }

  public void setBoardSize(int size) {
    player.setBoardSize(size);
  }

  public void addPlayer(int playerId) {
    player.addPlayer(playerId);
  }

  public void createBoard() {
    player.createBoard();
  }

  public Scene getScene() {
    return player.getScene();
  }

  public boolean isMoveValid (int fromX, int fromY, int toX, int toY) {
    //TODO pytaj serwera
    return true;
  }

  public boolean amIFirst() {
    return true;
  }

  public int getMyId() {
    //asking serwer
    return 1;
  }

  public void startGame() {
    pane.getChildren().setAll(Main.getCounter().getScene().getRoot());
  }

  public void createGame(int players, int robots, VBox pane) {
    //TODO poprawić na komunikację z serwerem
    switch (players) {
      case 2:
        player.addPlayer(4);
        break;
      case 3:
        player.addPlayer(5);
        player.addPlayer(3);
        break;
      case 4:
        player.addPlayer(2);
        player.addPlayer(5);
        player.addPlayer(4);
        break;
      case 6:
        player.addPlayer(2);
        player.addPlayer(5);
        player.addPlayer(3);
        player.addPlayer(6);
        player.addPlayer(4);
        break;
    }

    this.pane = pane;
    //send players and robots to server
  }
}
