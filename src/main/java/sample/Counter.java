package sample;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import player.Boot;
import player.Player;
import player.RealPlayer;

public class Counter {
  Player player;
  VBox pane;
  Client client;
  boolean correctMove;
  int id;
  boolean amIFirst;

  Counter() {
    player=new RealPlayer(this);
    client=new Client(this);
    correctMove=false;

    setPlayerId(1);
    setBoardSize(17);
    createBoard();
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

  public boolean isMoveValid(int fromX, int fromY, int toX, int toY) {
    //TODO ta metoda powinna zmieniać parametr correctMove
    client.move(fromX, fromY, toX, toY);
    return correctMove;
  }

  public boolean amIFirst() {
    return amIFirst;
  }

  public int getMyId() {
    return id;
  }


  //METHODS USING CLIENT

  /**
   * Method used by
   *
   * @param players
   * @param robots
   * @param pane
   */
  public void createGame(int players, int robots, VBox pane) {
    client.setNumOfPlayers(players, robots);
    this.pane=pane;
  }


  //METHODS USED BY CLIENT

  public void setId(int id) {
    this.id=id;
  }

  public void set_First(boolean isFirst) {
    this.amIFirst=isFirst;
  }

  public void setNumberOfPlayers(int players) {
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
  }

  public void uploadMove(int playerId, int fromX, int fromY, int toX, int toY) {
    player.uploadMove(playerId, fromX, fromY, toX, toY);
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

  public void startGame() {
    pane.getChildren().setAll(Main.getCounter().getScene().getRoot());
  }

  public void wrongMove() {
    correctMove=false;
  }

  public void correctMove() {
    correctMove=true;
  }
}





