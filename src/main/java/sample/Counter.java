package sample;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import player.Player;
import player.RealPlayer;

public class Counter {
  private Player player;
  private VBox pane;
  private Client client;
  private boolean correctMove;
  private boolean amIFirst;


  Counter() {
    player = new RealPlayer(this);
    //TODO
    System.out.println("player created");

    client = new Client(this);
    System.out.println("client created");

  }


  public void addPlayer(int playerId) {
    player.addPlayer(playerId);
  }

  public int getMyId() {
    return player.getId();
  }


  //METHODS USED BY MAIN

  public void setBoardSize(int size) {
    player.setBoardSize(size);
  }

  public boolean amIFirst() {
    return amIFirst;
  }

  public void createBoard() {
    player.createBoard();
  }

  //METHODS USING CLIENT

  /**
   * Method used by STARTGUI class.
   *
   * @param players Number of players (real + robots).
   * @param robots Number of robots.
   * @param pane Pane from window which is element of startGui.
   */
  public void createGame(int players, int robots, VBox pane) {
    client.setNumOfPlayers(players, robots);
    this.pane = pane;
  }

  /**
   * Method used by player to check if his move is permitted.
   * @param fromX Coordinate X of field where player starts move.
   * @param fromY Coordinate Y of field where player starts move.
   * @param toX Coordinate X of field where player ends move.
   * @param toY Coordinate Y of field where player ends move.
   * @return True if move is correct and if not - false.
   */
  public boolean isMovePermitted(int fromX, int fromY, int toX, int toY) {
    //TODO ta metoda powinna zmieniać parametr correctMove
    client.move(fromX, fromY, toX, toY);
    return correctMove;
  }



  //METHODS USED BY CLIENT

  public void setId(int id) {
    player.setId(id);
  }

  public void setFirst(boolean isFirst) {
    this.amIFirst = isFirst;
  }

  /**
   * Set number of players in this game.
   * @param players Number of players.
   */
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
      default:
        break;
    }
  }

  public void uploadMove(int playerId, int fromX, int fromY, int toX, int toY) {
    player.uploadMove(playerId, fromX, fromY, toX, toY);
  }

  /**
   * Set my turn as true.
   */
  public void yourTurn() {
    player.setMyTurn(true);
  }

  public void turnEnd() {
    player.setMyTurn(false);
  }

  public void startGame() {
    createBoard();
    Scene scene = player.getScene();
    pane.getChildren().setAll(scene.getRoot());
  }

  public void wrongMove() {
    correctMove = false;
  }

  public void correctMove() {
    correctMove = true;
  }

  //Dodana przez matika ;-)
  public void runServerListener() {
    client.start();
  }
}





