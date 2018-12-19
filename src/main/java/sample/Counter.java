package sample;

import javafx.scene.Scene;
import player.Boot;
import player.Player;
import player.RealPlayer;

public class Counter {
  Player player;

  Counter(boolean isReal) {
    if (isReal) {
      player = new RealPlayer();
    } else {
      player = new Boot();
    }

    setPlayerId(1);
    setBoardSize(17);
    createBoard();
    player.addPlayer(3);
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


}
