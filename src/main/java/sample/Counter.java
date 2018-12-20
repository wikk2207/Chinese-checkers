package sample;

import javafx.scene.Scene;
import player.Boot;
import player.Player;
import player.RealPlayer;

public class Counter {
  Player player;

  Counter() {
      player = new RealPlayer(this);

    setPlayerId(1);
    setBoardSize(17);
    createBoard();
    player.addPlayer(2);
    player.addPlayer(5);
    player.addPlayer(3);
    player.addPlayer(6);
    player.addPlayer(4);

  }

  /**
   *
   */
  public void yourTurn() {
    player.setMyTurn(true);
    try {
      Thread.sleep(15000);  //15s na ture
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }
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
    return true;
  }

}
