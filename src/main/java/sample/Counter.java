package sample;

import javafx.application.Platform;
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
  private int players;
  private boolean myTurn;
  private boolean boardCreated;


  Counter() {
    player = new RealPlayer(this);
    client = new Client(this);
    myTurn = false;
    boardCreated = false;
  }

  public void addPlayers() {

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

  /**
   *
   * @param pane Pane from window which is element of startGui.
   */
  public void setPane (VBox pane) {

    this.pane = pane;
  }

  //METHODS USING CLIENT

  /**
   * Method used by STARTGUI class.
   *
   * @param players Number of players (real + robots).
   * @param robots Number of robots.
   */
  public void createGame(int players, int robots) {

    client.setNumOfPlayers(players, robots);
    this.players=players;
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
  //Dodana przez matika ;-)
  public void runServerListener() {

    client.start();
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

    this.players=players;
  }

  public void uploadMove(int playerId, int fromX, int fromY, int toX, int toY) {
    player.uploadMove(playerId, fromX, fromY, toX, toY);
  }

  /**
   * Set my turn as true.
   */
  public void yourTurn() {
    myTurn=true;
    if (boardCreated) {
      player.setMyTurn(true);
    }
  }

  public void turnEnd() {
    myTurn=false;
    if (boardCreated) {
      player.setMyTurn(false);
    }
  }

  public void startGame() {

    Platform.runLater(new Runnable() {
      public void run() {
        createBoard();
        addPlayers();
        player.setMyTurn(myTurn);
        Scene scene = player.getScene();
        pane.getChildren().setAll(scene.getRoot());
      }
    });

  }

  public void wrongMove() {
    correctMove = false;
  }

  public void correctMove() {
    //todo
    correctMove = true;
    System.out.println("move changed to correct");
  }

}





