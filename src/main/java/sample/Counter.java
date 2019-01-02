package sample;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import player.RealPlayer;

public class Counter {
  private RealPlayer player;
  private VBox pane;
  private Client client;
  private boolean correctMove;
  private boolean amIFirst;
  private int players;
  private boolean myTurn;
  private boolean boardCreated;
  private int playerId;
  private boolean czyPadlaOdpowiedz;


  Counter() {
    player = new RealPlayer(this);
    client = new Client(this);
    myTurn = false;
    boardCreated = false;
    czyPadlaOdpowiedz = false;
  }

  public void addPlayers() {

    switch (players) {
      case 2:
        if (playerId == 1) player.addPlayer(4);
        else player.addPlayer(1);
        break;
      case 3:
        if (playerId == 1) {
          player.addPlayer(5);
          player.addPlayer(3);
        } else if (playerId == 3) {
          player.addPlayer(5);
          player.addPlayer(1);
        } else {
          player.addPlayer(3);
          player.addPlayer(1);
        }

        break;
      case 4:
        switch (playerId){
          case 1:
            player.addPlayer(2);
            player.addPlayer(5);
            player.addPlayer(4);
            break;
          case 2:
            player.addPlayer(1);
            player.addPlayer(5);
            player.addPlayer(4);
            break;
          case 4:
            player.addPlayer(2);
            player.addPlayer(5);
            player.addPlayer(1);
            break;
          case 5:
            player.addPlayer(2);
            player.addPlayer(1);
            player.addPlayer(4);
            break;
        }
        break;
      case 6:
        switch (playerId) {
          case 1:
            player.addPlayer(2);
            player.addPlayer(5);
            player.addPlayer(3);
            player.addPlayer(6);
            player.addPlayer(4);
            break;
          case 2:
            player.addPlayer(1);
            player.addPlayer(5);
            player.addPlayer(3);
            player.addPlayer(6);
            player.addPlayer(4);
            break;
          case 3:
            player.addPlayer(2);
            player.addPlayer(5);
            player.addPlayer(1);
            player.addPlayer(6);
            player.addPlayer(4);
            break;
          case 4:
            player.addPlayer(2);
            player.addPlayer(5);
            player.addPlayer(3);
            player.addPlayer(6);
            player.addPlayer(1);
            break;
          case 5:
            player.addPlayer(2);
            player.addPlayer(1);
            player.addPlayer(3);
            player.addPlayer(6);
            player.addPlayer(4);
            break;
          case 6:
            player.addPlayer(2);
            player.addPlayer(5);
            player.addPlayer(3);
            player.addPlayer(1);
            player.addPlayer(4);
            break;
        }
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
    //todo
    System.out.println("checking in counter...");
    client.move(fromX, fromY, toX, toY);
    while (!czyPadlaOdpowiedz){
      //todo
      System.out.println("...");
    }
    //todo
    System.out.println("response...");
    czyPadlaOdpowiedz = false;
    return correctMove;
  }

  //Dodana przez matika ;-)
  public void runServerListener() {

    client.start();
  }

  public void endMove() {
    client.endMove();
  }



  //METHODS USED BY CLIENT

  public void setId(int id) {
    playerId=id;
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
        boardCreated=true;
        addPlayers();
        player.setMyTurn(myTurn);
        Scene scene = player.getScene();
        pane.getChildren().setAll(scene.getRoot());
      }
    });

  }

  public void wrongMove() {
    correctMove = false;
    czyPadlaOdpowiedz = true;
  }

  public void correctMove() {
    correctMove = true;
    czyPadlaOdpowiedz = true;
  }

}





